package ch.mlz.axon.todo.app.configuration;

import ch.mlz.axon.todo.app.tenancy.TenantInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info(" CONFIGURE adding tenant interceptor to registry");
        registry.addInterceptor(tenantInterceptor);
    }

}
