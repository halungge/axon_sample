package ch.mlz.axon.todo.app.configuration;

import ch.mlz.axon.todo.app.configuration.multitenancy.TenantHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestConfigurationAdapter implements WebMvcConfigurer {
    @Autowired
    private TenantHeaderInterceptor tenantHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tenantHeaderInterceptor);

    }
}

