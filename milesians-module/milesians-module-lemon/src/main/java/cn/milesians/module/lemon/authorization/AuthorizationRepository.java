package cn.milesians.module.lemon.authorization;

import cn.milesians.module.lemon.authorization.Authorization.AuthorizationId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, AuthorizationId> {

    List<Authorization> findById_AppType(String appType);

}