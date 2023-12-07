package com.idrive.driveronboardingservice.framework;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class HttpRequestContext {

    private String userId;

    private String requestId;
}
