package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.UserModel;
import com.blue.apartamentos.repositories.IUserRepository;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
