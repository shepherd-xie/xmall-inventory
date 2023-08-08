package com.orkva.xmall.inventory.config;


import com.orkva.xmall.inventory.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SnowflakeIdConfig
 *
 * @author Shepherd Xie
 * @version 2023/8/8
 */
@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(@Value("${snowflake.worker-id}") long workerId,
                                               @Value("${snowflake.datacenter-id}") long datacenterId) {
        return new SnowflakeIdWorker(workerId, datacenterId);
    }

}
