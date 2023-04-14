package cn.milesians.provider.lemon.auth;

import java.io.Serializable;
import lombok.Data;

/**
 * 乐檬Auth服务返回TokenDTO
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class TokenResponseDTO implements Serializable {

    private static final long serialVersionUID = 3754629589806126453L;

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private Long expiresIn;

    private String scope;

    private String error;

    private String errorDescription;

}
