package com.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@Import({
        JpaStoreConfiguration.class,
        SecurityStoreConfiguration.class,
        WebStoreMvcConfiguration.class
})
public class StoreConfiguration {


}
