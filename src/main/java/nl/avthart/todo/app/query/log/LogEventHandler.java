package nl.avthart.todo.app.query.log;

import lombok.extern.slf4j.Slf4j;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskTitleModifiedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@ProcessingGroup("logHandler")
@AllowReplay
public class LogEventHandler {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss.S" )
                    .withZone( ZoneId.systemDefault() );


    @EventHandler
    public void on(TaskCreatedEvent creation, @Timestamp Instant timestamp){
        final String title = creation.getTitle();
        final String username = creation.getUsername();

        log.info(String.format(" LOG HANDLER: now %s replaying events: event issued at %s:  task  %s, created by %s", LocalDateTime.now(), formatter.format(timestamp), title, username));

    }

    @EventHandler
    @DisallowReplay
    public void on(TaskTitleModifiedEvent event, @Timestamp Instant eventTimestamp){
        log.info(String.format(" LOG HANDLER: now %s replaying events: event issued at %s:  task  %s, new title %s",
                LocalDateTime.now(), formatter.format(eventTimestamp), event.getId(), event.getTitle()));


    }

}
