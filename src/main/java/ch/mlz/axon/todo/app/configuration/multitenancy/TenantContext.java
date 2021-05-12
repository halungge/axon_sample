package ch.mlz.axon.todo.app.configuration.multitenancy;

public class TenantContext {
    private static ThreadLocal<String> tenant = new InheritableThreadLocal<>();
    public static void setTenant(String value){
        tenant.set(value);
    }
    public static String getTenant(){
        return tenant.get();
    }

    public static void clear(){
        tenant.set(null);
    }

}
