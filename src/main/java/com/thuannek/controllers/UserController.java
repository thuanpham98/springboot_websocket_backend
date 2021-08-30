package com.thuannek.controllers;

import java.util.Optional;

import com.thuannek.models.UserModel;
import com.thuannek.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.*;

public class UserController{

    @Autowired
    private UserRepository _userRepository ;

    public void createUser(UserModel model){
        _userRepository.save(model);
    }
}