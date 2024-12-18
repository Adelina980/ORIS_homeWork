package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

public class UserService {
    private UserRepository repository;

    public UserService(){
        repository = new UserRepository();
    }
    public User save(User user) {
        return repository.save(user);
    }
}
