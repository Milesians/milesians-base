package cn.milesians.module.lemon.authorization;

import cn.milesians.module.lemon.authorization.dto.AuthorizationDTO;
import cn.milesians.module.lemon.authorization.dto.AuthorizationGetQuery;
import cn.milesians.module.lemon.authorization.dto.AuthorizationSaveRequest;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
public interface AuthorizationManager {

    @Nullable
    AuthorizationDTO getOne(AuthorizationGetQuery getQuery);

    void setOne(AuthorizationSaveRequest saveRequest);

    List<AuthorizationDTO> findAll();

    List<AuthorizationDTO> findAll(String appType);
}
