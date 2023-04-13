package cn.milesians.module.lemon.authorization;

import cn.hutool.core.codec.Base64;
import cn.milesians.module.lemon.LemonProperties;
import cn.milesians.module.lemon.authorization.Authorization.AuthorizationId;
import cn.milesians.provider.lemon.TokenResponseDTO;
import cn.milesians.provider.lemon.auth.AuthFeign;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class AuthorizationScheduler {

    private final AuthorizationManager authorizationManager;


    private final AuthFeign authFeign;

    private final LemonProperties lemonProperties;


    @Scheduled(fixedDelay = 1000 * 60 * 30)
    public void refreshToken() {
        log.info(Thread.currentThread().getName() + ": 定时任务开始刷新token");
        List<AuthorizationDTO> authorizations = authorizationManager.findAll(lemonProperties.getAppType());

        for (AuthorizationDTO authorization : authorizations) {
            this.refreshToken(
                authorization.getBookCode(),
                authorization.getAppType(),
                lemonProperties.getAppId(),
                lemonProperties.getAppSecret(),
                authorization.getRefreshToken());
        }
        log.info(Thread.currentThread().getName() + ": 定时任务结束刷新token");
    }

    public void refreshToken(String systemBookCode, String appType, String appId, String appSecret,
        String refreshToken) {
        String credential = "Basic ".concat(Base64.encode(appId.concat(":").concat(appSecret)));
        TokenResponseDTO tokenResponseDTO = authFeign.getTokenByRefreshToken(credential, refreshToken);
        AuthorizationId tokenId = new AuthorizationId();
        tokenId.setBookCode(systemBookCode);
        tokenId.setAppType(appType);
        Authorization token = new Authorization();
        token.setId(tokenId);
        token.setAccessToken(tokenResponseDTO.getAccessToken());
        token.setRefreshToken(tokenResponseDTO.getRefreshToken());
        log.info("账套号{}刷新token成功", systemBookCode);
    }


}
