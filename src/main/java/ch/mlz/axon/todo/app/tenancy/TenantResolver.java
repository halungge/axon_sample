package ch.mlz.axon.todo.app.tenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver {
    private final String DEFAULT_SCHEMA = "public";
    @Override
    public String resolveCurrentTenantIdentifier() {
        final String currentTenant = ThreadTenantContext.getCurrentTenant();
        return currentTenant != null? currentTenant : DEFAULT_SCHEMA;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
