package ch.mlz.axon.todo.app.domain.task;

import ch.mlz.axon.todo.app.tenancy.ThreadTenantContext;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
public class CommandTenantMetadataInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        Map<String, String> currentTenant = getTenantFromContext();
        return message.andMetaData(currentTenant);
    }

    private Map<String, String> getTenantFromContext() {
        Map<String, String> currentTenant = new HashMap<>();
        final String tenantId = ThreadTenantContext.getCurrentTenant();
        log.info("got tenant id from ThreadLocalContext {}", tenantId);
        currentTenant.put("tenant", tenantId);
        return currentTenant;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        final Map<String, String> currentTenant = getTenantFromContext();
        return (index, command) -> command.andMetaData(currentTenant);
    }
}
