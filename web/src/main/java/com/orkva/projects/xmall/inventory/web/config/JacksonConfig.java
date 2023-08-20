package com.orkva.projects.xmall.inventory.web.config;

import com.fasterxml.jackson.databind.Module;
import com.orkva.xmall.common.config.CustomInstantModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JacksonConfig
 *
 * @author Shepherd Xie
 * @version 2023/8/9
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Module customInstantModule() {
        return new CustomInstantModule();
    }

}
