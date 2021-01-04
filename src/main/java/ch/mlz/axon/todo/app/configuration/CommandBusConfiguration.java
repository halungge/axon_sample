package ch.mlz.axon.todo.app.configuration;

import ch.mlz.axon.todo.app.domain.task.CommandLoggingInterceptor;
import ch.mlz.axon.todo.app.domain.task.CommandTenantMetadataInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfiguration {
    @Bean
    public CommandBus configureCommandBus() {
        CommandBus commandBus = SimpleCommandBus.builder().build();
        commandBus.registerDispatchInterceptor(new CommandTenantMetadataInterceptor());
        commandBus.registerDispatchInterceptor(new CommandLoggingInterceptor());
        return commandBus;
    }
}

