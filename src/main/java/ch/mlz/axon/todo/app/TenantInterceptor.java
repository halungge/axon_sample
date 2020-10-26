package ch.mlz.axon.todo.app;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantInterceptor implements HandlerInterceptor {

    public static final String TENANT_HEADER = "X-tenant";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String tenantId= request.getHeader(TENANT_HEADER);
        if (tenantId == null) {
            response.getWriter().write("X-tenant not present in the Request Header");
            response.setStatus(400);
            return false;
        }
        ThreadTenantContext.setCurrentTenant(tenantId);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) {
        ThreadTenantContext.clear();
    }
}
