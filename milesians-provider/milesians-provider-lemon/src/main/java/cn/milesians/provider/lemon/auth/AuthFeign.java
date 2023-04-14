package cn.milesians.provider.lemon.auth;


import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 乐檬授权请求
 *
 * @author nhsoft.shidq
 * @date 2022-06-14
 */
public interface AuthFeign {

    /**
     * 通过授权码获取token
     *
     * @param authorization
     * @param grantType
     * @param code
     * @param redirectUrl
     * @return
     */
    @RequestLine("POST /oauth/token?grant_type={grantType}&code={code}&redirect_uri={redirectUrl}")
    @Headers({"Content-Type:application/x-www-form-urlencoded", "Authorization:{authorization}"})
    TokenResponseDTO getTokenByCode(@Param("authorization") String authorization,
        @Param("grantType") String grantType,
        @Param("code") String code,
        @Param("redirectUrl") String redirectUrl);

    /**
     * 通过refreshToken 获取新的token
     *
     * @param authorization
     * @param refreshToken
     * @return
     */
    @RequestLine("POST /oauth/token?grant_type=refresh_token&refresh_token={refreshToken}")
    @Headers({"Content-Type:application/x-www-form-urlencoded", "Authorization:{authorization}"})
    @Body("{requestDTO}")
    TokenResponseDTO getTokenByRefreshToken(@Param("authorization") String authorization,
        @Param("refreshToken") String refreshToken);

}
