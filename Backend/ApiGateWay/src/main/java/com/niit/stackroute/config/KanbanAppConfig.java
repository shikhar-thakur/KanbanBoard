package com.niit.stackroute.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KanbanAppConfig
{
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(p->p
                        .path("/**")//base path of the UserAuthenticationService like "/api/v1/**"
                        .uri("http://localhost:8082/"))//uri of that particular service like  "http://localhost:8081/"
//                .route(p->p
//                        .path("")//base path of the KanbanBoardService like "/api/v2/**"
//                        .uri(""))//uri of that particular service like  "http://localhost:8082/"
//                .route(p->p
//                        .path("")//base path of the NotificationService like "/api/v3/**"
//                        .uri(""))//uri of that particular service like  "http://localhost:8083/"
                .build();
    }
}
