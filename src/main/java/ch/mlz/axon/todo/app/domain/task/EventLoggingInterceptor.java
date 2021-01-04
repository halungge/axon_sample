package ch.mlz.axon.todo.app.domain.task;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
public class EventLoggingInterceptor implements MessageDispatchInterceptor<EventMessage<?>> {
    @Override
    public EventMessage<?> handle(EventMessage<?> message) {
        log.info("received event {}", message);
        return message;
    }

    @Override
    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(List<? extends EventMessage<?>> messages) {
        return (index, message) -> handle(message);
    }
}
