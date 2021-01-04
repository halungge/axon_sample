package ch.mlz.axon.todo.app.configuration;

import ch.mlz.axon.todo.app.domain.task.EventLoggingInterceptor;
import ch.mlz.axon.todo.app.domain.task.EventTenantMetadataInterceptor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.springframework.context.annotation.Bean;

//@Configuration
public class EventBusConfiguration {

    @Bean
    public EventBus configureEventBus(EventStorageEngine jpaEngine) {
        EventBus eventBus = EmbeddedEventStore.builder().storageEngine(jpaEngine).build();
        eventBus.registerDispatchInterceptor(new EventLoggingInterceptor());
        eventBus.registerDispatchInterceptor(new EventTenantMetadataInterceptor());
        return eventBus;
    }
}

