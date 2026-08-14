package com.brenocosta.helpdeskapi.services;


import com.brenocosta.helpdeskapi.config.TokenProvider;
import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.auth.TokenResponseDTO;
import com.brenocosta.helpdeskapi.dtos.auth.UserLoginDTO;
import com.brenocosta.helpdeskapi.dtos.auth.UserRegisterDTO;
import com.brenocosta.helpdeskapi.repositories.RoleRepository;
import com.brenocosta.helpdeskapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private Long expirationTime;

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

    public TokenResponseDTO login(UserLoginDTO dto) throws Exception {
            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
                String token = tokenProvider.generateToken(authentication);

                return new TokenResponseDTO(token, expirationTime);

            } catch (BadCredentialsException e) {
                throw new BadRequestException("Credenciais inválidas");
            } catch (Exception e ) {
                throw e;
            }
        }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

}
