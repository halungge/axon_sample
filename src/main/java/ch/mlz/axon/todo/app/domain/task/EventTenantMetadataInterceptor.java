package ch.mlz.axon.todo.app.domain.task;

import ch.mlz.axon.todo.app.tenancy.ThreadTenantContext;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
public class EventTenantMetadataInterceptor implements MessageDispatchInterceptor<EventMessage<?>> {
    @Override
    public EventMessage<?> handle(EventMessage<?> message) {
        Map<String, String> currentTenant = getTenantFromContext();
        final EventMessage<?> modifiedMessage = message.andMetaData(currentTenant);
        return modifiedMessage;
    }

    private Map<String, String> getTenantFromContext() {
        Map<String, String> currentTenant = new HashMap<>();
        final String tenantId = ThreadTenantContext.getCurrentTenant();
        currentTenant.put("tenant", tenantId);
        log.info("got tenant id from ThreadLocalContext {}", tenantId);
        return currentTenant;
    }

    @Override
    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(List<? extends EventMessage<?>> messages) {
        final Map<String, String> currentTenant = getTenantFromContext();

        return (index, command) -> command.andMetaData(currentTenant);
    }
}
