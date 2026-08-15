package com.brenocosta.helpdeskapi.mapper;


import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.dtos.role.RolesResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RolesResponseDTO> toResponse(List<Roles> roles);
}
