package cn.milesians.module.lemon.authorization;

import cn.milesians.module.lemon.authorization.Authorization.AuthorizationId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Slf4j
@ConditionalOnMissingBean(AuthorizationManager.class)
@RequiredArgsConstructor
@Service
public class DBAuthorizationManager implements AuthorizationManager {


    private final AuthorizationRepository authorizationRepository;

    @Override
    public AuthorizationDTO getOne(AuthorizationGetQuery getQuery) {
        AuthorizationId id = AuthorizationMapper.INSTANCE.query2EntityId(getQuery);
        return authorizationRepository.findById(id)
            .map(AuthorizationMapper.INSTANCE::entity2Dto)
            .orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setOne(AuthorizationSaveRequest saveRequest) {
        Authorization authorization = AuthorizationMapper.INSTANCE.saveRequest2Entity(saveRequest);
        authorizationRepository.save(authorization);
    }

    @Override
    public List<AuthorizationDTO> findAll() {
        return authorizationRepository.findAll()
            .stream()
            .map(AuthorizationMapper.INSTANCE::entity2Dto)
            .toList();
    }

    @Override
    public List<AuthorizationDTO> findAll(String appType) {
        return authorizationRepository.findById_AppType(appType)
            .stream()
            .map(AuthorizationMapper.INSTANCE::entity2Dto)
            .toList();
    }
}
