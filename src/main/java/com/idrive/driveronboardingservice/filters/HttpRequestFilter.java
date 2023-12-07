package com.idrive.driveronboardingservice.filters;

import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@Order(1)
public class HttpRequestFilter implements Filter {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Override
    public void init(FilterConfig filterconfig)
            throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterchain)
            throws IOException, ServletException {
        String requestId = ((RequestFacade) request).getHeader("request_id");
        if(StringUtils.isEmpty(requestId)){
            httpRequestContext.setRequestId(UUID.randomUUID().toString());
        } else {
            httpRequestContext.setRequestId(requestId);
        }

        filterchain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}