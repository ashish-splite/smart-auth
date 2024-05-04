package com.smart.smartauth.smartauth.swagger;

import java.lang.annotation.Annotation;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;

import com.smart.smartauth.smartauth.annotations.AdminKeyRequired;
import com.smart.smartauth.smartauth.annotations.ApiKeyRequired;
import com.smart.smartauth.smartauth.annotations.BearerTokenRequired;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration
public class SwaggerConfig {

    public Parameter getRequestHeader(String name, String description) {
        return new Parameter()
                .in("header")
                .name(name)
                .schema(new StringSchema())
                .description(description)
                .required(true);
    }

    public Boolean isAuthRequired(Class<? extends Annotation> annotatedClass, HandlerMethod handlerMethod) {
        return handlerMethod.hasMethodAnnotation(annotatedClass) ||
                handlerMethod.getBeanType().isAnnotationPresent(annotatedClass);
    }

    @Bean
    public OperationCustomizer customOperationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            if (isAuthRequired(BearerTokenRequired.class, handlerMethod)) {
                operation.addParametersItem(
                        getRequestHeader(HttpHeaders.AUTHORIZATION, "Bearer token for authentication"));
            }

            if (isAuthRequired(ApiKeyRequired.class, handlerMethod)) {
                operation.addParametersItem(getRequestHeader("Api-Key", "API Key for authentication"));
            }

            if (isAuthRequired(AdminKeyRequired.class, handlerMethod)) {
                operation.addParametersItem(getRequestHeader("Admin-Key", "Admin Key for authentication"));
            }
            return operation;
        };
    }
}
