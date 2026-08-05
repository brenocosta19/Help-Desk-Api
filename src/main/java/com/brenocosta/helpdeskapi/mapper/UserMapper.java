package com.brenocosta.helpdeskapi.mapper;

import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.user.UserDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.user.UserSummaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserSummaryDTO toSummary(User user);

    UserDetailsDTO toDetails(User user);

}
