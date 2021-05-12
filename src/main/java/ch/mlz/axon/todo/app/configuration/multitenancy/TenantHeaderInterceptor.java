package ch.mlz.axon.todo.app.configuration.multitenancy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TenantHeaderInterceptor implements HandlerInterceptor {
    private static final String TENANT_HEADER = "X-tenant";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantContext.setTenant(null);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        final String tenant = request.getHeader(TENANT_HEADER);
        log.info("received request for tenant {}", tenant);

        if(tenant == null){
            log.error("no tenant set in request");
            response.getWriter().write("no tenant set in reqeust");
            response.setStatus(400);
            return false;
        }else{
            TenantContext.setTenant(tenant.toLowerCase());
            return true;
        }

    }


}
