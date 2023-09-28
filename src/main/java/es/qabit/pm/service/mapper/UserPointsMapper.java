package es.qabit.pm.service.mapper;

import es.qabit.pm.domain.User;
import es.qabit.pm.domain.UserPoints;
import es.qabit.pm.service.dto.UserDTO;
import es.qabit.pm.service.dto.UserPointsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPoints} and its DTO {@link UserPointsDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserPointsMapper extends EntityMapper<UserPointsDTO, UserPoints> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    UserPointsDTO toDto(UserPoints s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
