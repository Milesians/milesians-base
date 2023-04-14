package cn.milesians.module.lemon.authorization.dto;

import cn.milesians.module.lemon.authorization.Authorization;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link Authorization} entity
 */
@Data
public class AuthorizationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String bookCode;
    private String appType;
    private String accessToken;
    private String refreshToken;
}