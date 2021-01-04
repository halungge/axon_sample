package ch.mlz.axon.todo.app.configuration;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.springframework.context.annotation.Bean;

public class EventStoreConfiguration {
    @Bean
    public EventStorageEngine eventStore() {
        return JpaEventStorageEngine.builder()
                .build();
    }
    //TODO [mulittenancy] do we need this)
   /* @Bean
    public EventStorageEngine eventStore(DataSource dataSource, EntityManagerProvider provider) throws SQLException {
        return JpaEventStorageEngine.builder().dataSource(dataSource).entityManagerProvider(provider)
                .build();
    }*/


}
