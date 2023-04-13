package cn.milesians.module.lemon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@ConfigurationProperties(prefix = "lemon")
@Getter
@Setter
public class LemonProperties {

    /**
     * 账套号
     */
    private String bookCode;

    /**
     * 应用类型
     */
    private String appType = "default";

    /**
     * 乐檬应用id
     */
    private String appId;

    /**
     * 乐檬应用密钥
     */
    private String appSecret;
}
