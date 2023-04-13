package cn.milesians.module.lemon.authorization;

import lombok.Getter;
import lombok.Setter;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Getter
@Setter
public class AuthorizationSaveRequest {

    private String bookCode;
    private String appType;
    private String accessToken;
    private String refreshToken;
}
