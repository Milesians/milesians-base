package cn.milesians.module.lemon;

import cn.milesians.provider.lemon.LemonJacksonDecoder;
import cn.milesians.provider.lemon.LemonJacksonEncoder;
import cn.milesians.provider.lemon.auth.AuthFeign;
import cn.milesians.provider.lemon.basic.branch.BranchFeign;
import cn.milesians.provider.lemon.basic.item.PosItemFeign;
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
public class LemonConfiguration {

    private final ApiCloudProperties apiCloudProperties;

    private final ApiCloudFeignInterceptor apiCloudFeignInterceptor;

    @Bean
    public AuthFeign lemonAuthFeign() {
        return Feign.builder()
            .decoder(LemonJacksonDecoder.getInstance())
            .options(new Request.Options(5, TimeUnit.SECONDS, 3, TimeUnit.MINUTES, true))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .logger(new Slf4jLogger(AuthFeign.class))
//            .requestInterceptor(apiCloudFeignInterceptor)
            .logLevel(Level.FULL)
            .target(AuthFeign.class, apiCloudProperties.getAuthUrl());
    }


    @Bean
    public BranchFeign lemonBranchFeign() {
        return Feign.builder()
            .decoder(LemonJacksonDecoder.getInstance())
            .encoder(LemonJacksonEncoder.getInstance())
            .options(new Request.Options(5, TimeUnit.SECONDS, 3, TimeUnit.MINUTES, true))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .logger(new Slf4jLogger(BranchFeign.class))
            .requestInterceptor(apiCloudFeignInterceptor)
            .logLevel(Level.FULL)
            .target(BranchFeign.class, apiCloudProperties.getUrl());
    }


    @Bean
    public PosItemFeign lemonPosItemFeign() {
        return Feign.builder()
            .decoder(LemonJacksonDecoder.getInstance())
            .encoder(LemonJacksonEncoder.getInstance())
            .options(new Request.Options(5, TimeUnit.SECONDS, 3, TimeUnit.MINUTES, true))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .logger(new Slf4jLogger(PosItemFeign.class))
            .requestInterceptor(apiCloudFeignInterceptor)
            .logLevel(Level.FULL)
            .target(PosItemFeign.class, apiCloudProperties.getUrl());
    }
}

