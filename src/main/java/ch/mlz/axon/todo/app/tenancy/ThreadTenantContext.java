package ch.mlz.axon.todo.app.tenancy;

import java.util.Optional;

public class ThreadTenantContext {
    private static final ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    public static Optional<String> getCurrentTenant() {
        return  Optional.ofNullable(currentTenant.get());
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
