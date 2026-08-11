package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }
}
