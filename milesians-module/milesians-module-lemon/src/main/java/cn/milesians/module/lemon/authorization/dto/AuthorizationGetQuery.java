package cn.milesians.module.lemon.authorization.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Getter
@Setter
public class AuthorizationGetQuery {

    private String bookCode;

    private String appType;
}
