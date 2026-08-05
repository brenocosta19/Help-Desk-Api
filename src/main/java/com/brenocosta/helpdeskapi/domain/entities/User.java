package com.brenocosta.helpdeskapi.domain.entities;

import com.brenocosta.helpdeskapi.domain.enums.Role;
import com.brenocosta.helpdeskapi.dtos.user.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserDTO data) {
        this.name = data.name();
        this.role = data.role();
        this.password = data.password();
        this.email = data.email();
    }
}
