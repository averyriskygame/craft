package com.idrive.driveronboardingservice.filters;

import com.idrive.driveronboardingservice.exception.CustomException;
import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Component
@Order(2)
public class AuthenticationFilter implements Filter {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        String authorizationHeader = ((RequestFacade) request).getHeader("Authorization");
        if (StringUtils.isEmpty(authorizationHeader)) {
            throw new CustomException("missing-header", "UnAuthenticated reason=missing header", HttpStatus.UNAUTHORIZED);
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(authorizationHeader.replace("Bearer ", "").trim());
            JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
            String sub = jwtClaimsSet.getStringClaim("sub");
            String iss = jwtClaimsSet.getStringClaim("iss");
            if (!"https://auth.idrive.com".equals(iss) || StringUtils.isEmpty(sub)) {
                throw new CustomException("invalid-token", "UnAuthorization reason=invalid token", HttpStatus.UNAUTHORIZED);


            }

            // Call authService to validate the signature of the token

            httpRequestContext.setUserId(sub);
        } catch (ParseException e) {
            throw new CustomException("tampered-token", "UnAuthorization reason=tampered token", HttpStatus.UNAUTHORIZED);
        }

        filterchain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}