package com.orkva.xmall.inventory.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;

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

    public static class CustomInstantModule extends SimpleModule {
        private static final JsonSerializer<Instant> INSTANT_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(value.getEpochSecond());
            }
        };

        public CustomInstantModule() {
            addSerializer(Instant.class, INSTANT_SERIALIZER);
        }
    }

}
