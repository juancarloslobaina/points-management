package es.qabit.pm.service.mapper;

import es.qabit.pm.domain.Transaction;
import es.qabit.pm.domain.User;
import es.qabit.pm.service.dto.TransactionDTO;
import es.qabit.pm.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "userFrom", source = "userFrom", qualifiedByName = "userLogin")
    @Mapping(target = "userTo", source = "userTo", qualifiedByName = "userLogin")
    TransactionDTO toDto(Transaction s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
