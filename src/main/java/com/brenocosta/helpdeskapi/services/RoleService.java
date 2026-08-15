package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public List<Roles> findAll() {
        return repository.findAll();
    }

    public Roles findRoleById (Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
    }
}
