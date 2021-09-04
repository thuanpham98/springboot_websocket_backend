package com.thuannek.controllers;

import com.thuannek.models.UserModel;
import com.thuannek.repositorys.UserRepository;

public class UserController{

    private UserRepository _userRepository ;

    public UserController(UserRepository userRepository){
        _userRepository = userRepository;
    }

    public UserModel checkUserEmail(String email){
        return _userRepository.findbyEmail(email);

    }

    public void createOrUpdateUser(UserModel model){
        UserModel ret = _userRepository.findbyEmail(model.getUserEmail());
        if(ret == null){ // create
            _userRepository.save(model);
        }else{          // update
            ret.setUserEmail(model.getUserEmail());
            ret.setUserId(model.getUserId());
            ret.setUserName(model.getUserName());
            ret.setUserToken(model.getUserToken());
            _userRepository.save(ret);
        }
        
    }
}