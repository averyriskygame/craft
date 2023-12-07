package com.idrive.driveronboardingservice.filters;

import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
@Order(3)
public class HttpResponseFilter implements Filter {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterchain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterchain.doFilter(request, response);
        log.info( "requestId={} responseTime={}ms", httpRequestContext.getRequestId(), System.currentTimeMillis() - start );
    }

    @Override
    public void destroy() {}


}