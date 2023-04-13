package cn.milesians.module.lemon;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.milesians.module.lemon.authorization.AuthorizationDTO;
import cn.milesians.module.lemon.authorization.AuthorizationGetQuery;
import cn.milesians.module.lemon.authorization.AuthorizationManager;
import cn.milesians.provider.commons.exception.ProviderException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ApiCloudFeignInterceptor implements RequestInterceptor {

    private final AuthorizationManager authorizationManager;
    private final LemonProperties lemonProperties;
    private final LoadingCache<String, AuthorizationDTO> authorizationCache = CacheBuilder.newBuilder()
        .concurrencyLevel(8)
        .initialCapacity(1)

        .maximumSize(100)
        .build(new CacheLoader<>() {
                   @Override
                   public AuthorizationDTO load(String key) {
                       AuthorizationGetQuery query = new AuthorizationGetQuery();
                       query.setBookCode(key);
                       query.setAppType(lemonProperties.getAppType());
                       return authorizationManager.getOne(query);
                   }
               }
        );

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String systemBookCode = CollUtil.get(requestTemplate.headers().get("Book-Code"), 0);
        if (StrUtil.isEmpty(systemBookCode)) {
            systemBookCode = lemonProperties.getBookCode();
        }
        if (StrUtil.isEmpty(systemBookCode)) {
            throw new ProviderException("Book-Code is empty");
        }

        String accessToken = null;
        try {
            accessToken = authorizationCache.get(systemBookCode).getRefreshToken();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (StrUtil.isEmpty(accessToken)) {
            throw new ProviderException("开放平台账套号未授权或已过期");
        }
        accessToken = "Bearer ".concat(accessToken);
        requestTemplate.header("Authorization", accessToken);
    }
}
