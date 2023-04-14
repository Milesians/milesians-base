package cn.milesians.module.lemon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@ConfigurationProperties(prefix = "lemon.api-cloud")
@Getter
@Setter
public class ApiCloudProperties {

    /**
     * 网关地址
     */
    private String url;

    /**
     * 授权地址
     */
    private String authUrl;

    /**
     * 跳转地址
     */
    private String redirectUrl;
}
