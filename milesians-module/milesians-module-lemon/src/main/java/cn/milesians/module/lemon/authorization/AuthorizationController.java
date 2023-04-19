package cn.milesians.module.lemon.authorization;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.milesians.module.lemon.ApiCloudProperties;
import cn.milesians.module.lemon.LemonProperties;
import cn.milesians.module.lemon.authorization.dto.AuthorizationSaveRequest;
import cn.milesians.provider.commons.exception.ProviderException;
import cn.milesians.provider.lemon.auth.AuthFeign;
import cn.milesians.provider.lemon.auth.TokenResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证服务授权
 *
 * @author nhsoft.shidq
 * @date 2021-08-26
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthorizationController {


    private final ApiCloudProperties apiCloudProperties;

    private final LemonProperties lemonProperties;

    private final AuthFeign authFeign;

    private final AuthorizationManager authorizationManager;

    @RequestMapping("/api/lemon/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codeUrlTemplate = apiCloudProperties.getAuthUrl()
            + "/oauth/authorize?response_type=code&state=state&client_id=%s&redirect_uri=%s";

        String codeUrl = String.format(codeUrlTemplate, lemonProperties.getAppId(),
            apiCloudProperties.getRedirectUrl());

        response.sendRedirect(codeUrl);
    }

    @GetMapping("/api/lemon/code")
    public String getCode(@RequestParam(value = "code", required = false) String code,
        @RequestParam(value = "state", required = false) String state) {
        if (StrUtil.isBlank(code)) {
            return "/fail";
        }
        try {
            getToken(code);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ProviderException("授权发生异常");
        }

        return "授权成功";
    }

    private void getToken(String code) {

        String appId = lemonProperties.getAppId();
        String appSecret = lemonProperties.getAppSecret();
        String credential = "Basic ".concat(Base64.encode(appId.concat(":").concat(appSecret)));
        TokenResponseDTO tokenResponseDTO = authFeign.getTokenByCode(credential, "authorization_code", code,
            apiCloudProperties.getRedirectUrl());
        String accessToken = tokenResponseDTO.getAccessToken();
        String refreshToken = tokenResponseDTO.getRefreshToken();

        // 解析token
        String systemBookCode;
        try {
            JWT token = JWT.of(accessToken);
            systemBookCode = (String) token.getPayload().getClaim("Nhsoft-Merchant-Id");

        } catch (Exception e) {
            log.error("token解析异常：" + e);
            throw new ProviderException("解析异常");
        }

        if (systemBookCode == null) {
            throw new ProviderException("解析异常");
        }
        AuthorizationSaveRequest saveRequest = new AuthorizationSaveRequest();
        saveRequest.setBookCode(systemBookCode);
        saveRequest.setAppType(lemonProperties.getAppType());
        saveRequest.setAccessToken(accessToken);
        saveRequest.setRefreshToken(refreshToken);

        authorizationManager.setOne(saveRequest);

    }
}
