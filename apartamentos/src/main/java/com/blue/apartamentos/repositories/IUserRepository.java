package com.blue.apartamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blue.apartamentos.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long>{
    
}
