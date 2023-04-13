package cn.milesians.module.lemon.authorization;

import cn.milesians.module.lemon.authorization.Authorization.AuthorizationId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Mapper
public interface AuthorizationMapper {

    AuthorizationMapper INSTANCE = Mappers.getMapper(AuthorizationMapper.class);

    @Mapping(target = "bookCode", source = "id.bookCode")
    @Mapping(target = "appType", source = "id.appType")
    AuthorizationDTO entity2Dto(Authorization authorization);


    @Mapping(target = "id.bookCode", source = "bookCode")
    @Mapping(target = "id.appType", source = "appType")
    Authorization saveRequest2Entity(AuthorizationSaveRequest saveRequest);

    AuthorizationId query2EntityId(AuthorizationGetQuery getQuery);
}
