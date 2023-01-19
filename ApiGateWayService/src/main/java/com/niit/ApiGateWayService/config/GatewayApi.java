package com.niit.ApiGateWayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayApi {

    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(p -> p.path("/app/v1/**")
                .uri("lb://User-Service")).route(p -> p.path("/app/v2/**")
                .uri("lb://Pizza-Service")).build();
    }
}
