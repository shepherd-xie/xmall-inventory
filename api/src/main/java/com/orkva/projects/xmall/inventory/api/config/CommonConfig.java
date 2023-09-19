package com.orkva.projects.xmall.inventory.api.config;

import com.orkva.projects.xmall.auth.client.config.ClientWebSecurityConfig;
import com.orkva.xmall.common.config.SnowflakeIdConfig;
import com.orkva.xmall.common.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * SwaggerConfig
 *
 * @author Shepherd Xie
 * @version 2023/8/9
 */
@Configuration
@Import({
        SwaggerConfig.class,
        SnowflakeIdConfig.class,
        ClientWebSecurityConfig.class
})
public class CommonConfig {

}
