package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.user.UpdateRoleDTO;
import com.brenocosta.helpdeskapi.dtos.user.UpdateUserBlocked;
import com.brenocosta.helpdeskapi.dtos.user.UpdateUserStatus;
import com.brenocosta.helpdeskapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;

    private final AuthService authService;

    private final RoleService roleService;

    public User findUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean verifyRole(Long id, String roleName) throws Exception {
        User user = findUserById(id);

        return user.getRoles().stream().anyMatch(roles ->
                roles.getName().equals(roleName));
    }

    public User updateRole(Long id, UpdateRoleDTO dto) throws Exception {
        User user = findUserById(id);

        Roles newRole = roleService.findRoleById(dto.roleId());

        boolean isClient = verifyRole(id, "ROLE_CLIENT");

        if (isClient) {
            user.getRoles().clear();
            user.getRoles().add(newRole);
        } else {
            if ("ROLE_CLIENT".equals(newRole.getName())) {
                throw new Exception(
                        "Usuários TECNICO ou ADMIN não podem receber a role CLIENTE. " +
                                "Remova as roles atuais antes de atribuir CLIENTE.");
            }
            user.getRoles().add(newRole);
        }

        return repository.save(user);
    }

    public User updateUserStatus(Long id, UpdateUserStatus dto) throws Exception {
        User user = findUserById(id);

        boolean isAdmin = verifyRole(id, "ROLE_ADMIN");

        if (isAdmin) {
            throw  new AccessDeniedException("Um admin não pode alterar status da conta de outro admin");
        } else {
            user.setEnabled(dto.enabled());

            return repository.save(user);
        }
    }

    public User updateUserBlocked(Long id, UpdateUserBlocked dto) throws Exception {
        User user = findUserById(id);

        boolean isAdmin = verifyRole(id, "ROLE_ADMIN");

        if (isAdmin) {
            throw  new AccessDeniedException("Um admin não pode alterar bloquear a conta de outro admin");
        } else {
            user.setAccountNonLocked(dto.blocked());

            return repository.save(user);
        }
    }



}
