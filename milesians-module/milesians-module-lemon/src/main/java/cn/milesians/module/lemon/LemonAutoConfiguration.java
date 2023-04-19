package cn.milesians.module.lemon;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@EnableConfigurationProperties({ApiCloudProperties.class, LemonProperties.class})
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "cn.milesians.module.lemon")
@EntityScan(basePackages = "cn.milesians.module.lemon")
@ComponentScan({"cn.milesians.module.lemon"})
@AutoConfiguration
public class LemonAutoConfiguration {

}
