package com.brenocosta.helpdeskapi.repositories;

import com.brenocosta.helpdeskapi.domain.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
