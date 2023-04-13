package cn.milesians.module.lemon.authorization;

import cn.milesians.module.lemon.ApiCloudProperties;
import cn.milesians.provider.lemon.LemonJacksonDecoder;
import cn.milesians.provider.lemon.auth.AuthFeign;
import feign.Feign;
import feign.Logger.Level;
import feign.Request;
import feign.Retryer;
import feign.slf4j.Slf4jLogger;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class AuthorizationConfiguration {

    private final ApiCloudProperties apiCloudProperties;

    @Bean
    public AuthFeign createAuthFeign() {
        return Feign.builder()
            .decoder(LemonJacksonDecoder.getInstance())
            .options(new Request.Options(5, TimeUnit.SECONDS, 3, TimeUnit.MINUTES, true))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .logger(new Slf4jLogger(AuthFeign.class))
            .logLevel(Level.FULL)
            .target(AuthFeign.class, apiCloudProperties.getAuthUrl());
    }
}

