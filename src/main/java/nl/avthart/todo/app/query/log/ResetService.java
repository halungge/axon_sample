package nl.avthart.todo.app.query.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.GapAwareTrackingToken;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.tokenstore.UnableToClaimTokenException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResetService {

    private final EventProcessingConfiguration eventProcessingConfiguration;

    private TrackingEventProcessor getTrackingEventProcessor(String name) {
        return this.eventProcessingConfiguration
                .eventProcessor(name, TrackingEventProcessor.class)
                .orElseThrow(TrackingEventProcessorNotFoundException::new);
    }

    public boolean replay(String trackingEventProcessorName, Long index, TimedContext context) {
        TrackingEventProcessor trackingEventProcessor = this.getTrackingEventProcessor(trackingEventProcessorName);
        if (!trackingEventProcessor.isRunning()) {
            log.warn("Tracking event processor {} is not running in current instance or not running at all", trackingEventProcessorName);
            return false;
        }

        trackingEventProcessor.shutDown();

        try {
            trackingEventProcessor.resetTokens(GapAwareTrackingToken.newInstance(index - 1, Collections.emptySortedSet()), context);
        } catch (UnableToClaimTokenException e) {
            // Ignore this exception and let the caller know setting the replay failed.
            log.warn("Unable to claim token for trackingEventProcessor {} on id {}", trackingEventProcessorName, index - 1, e);
            return false;
        } finally {
            log.info("Starting replay for trackingEventProcessor {} on id {}", trackingEventProcessorName, index - 1);
            trackingEventProcessor.start();
        }
        return true;
    }



    public <R> void resetWithContext(Configuration config, R resetContext) {
        EventProcessingConfiguration eventProcessingConfig = config.eventProcessingConfiguration();
        eventProcessingConfig.eventProcessor("console-log", TrackingEventProcessor.class)
                .ifPresent(processor -> {
                    processor.shutDown();
                    processor.resetTokens(resetContext);
                    processor.start();
                });
    }
}