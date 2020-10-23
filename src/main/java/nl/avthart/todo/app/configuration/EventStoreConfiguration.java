package nl.avthart.todo.app.configuration;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.springframework.context.annotation.Bean;

public class EventStoreConfiguration {
    @Bean
    public EventStorageEngine eventStore() {
        return JpaEventStorageEngine.builder()
                .build();
    }

}
