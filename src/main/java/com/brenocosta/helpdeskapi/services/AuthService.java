package com.brenocosta.helpdeskapi.services;


import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.auth.UserRegisterDTO;
import com.brenocosta.helpdeskapi.repositories.RoleRepository;
import com.brenocosta.helpdeskapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final Set<Roles> roles = new HashSet<>();


    public User register(UserRegisterDTO dto) throws Exception {

        if (repository.existsByEmail(dto.email())) {
            throw new BadRequestException("E-mail já cadastrado.");
        }

        Roles clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() ->
                        new IllegalStateException("Role CLIENT não configurada."));

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        user.getRoles().add(clientRole);

        return repository.save(user);
    }



}
