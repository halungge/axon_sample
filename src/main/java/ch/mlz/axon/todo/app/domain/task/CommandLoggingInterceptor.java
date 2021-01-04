package ch.mlz.axon.todo.app.domain.task;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
public class CommandLoggingInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        log.info("received command {}", message);
        return message;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, message) -> handle(message);
    }
}
