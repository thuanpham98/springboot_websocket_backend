package com.thuannek.services.user;

import com.thuannek.services.user.UserEntity;
import com.thuannek.services.user.UserRepository;

public class UserService{

    private UserRepository _userRepository ;

    public UserService(UserRepository userRepository){
        _userRepository = userRepository;
    }

    public UserEntity checkUserEmail(String email){
        return _userRepository.findbyEmail(email);

    }

    public void createOrUpdateUser(UserEntity user){
        UserEntity ret = _userRepository.findbyEmail(user.toUserModel().getUserEmail());
        if(ret == null){ // create
            _userRepository.save(user);
        }else{          // update
            ret.setUserName(user.toUserModel().getUserName());
            ret.setUserToken(user.toUserModel().getUserToken());
            _userRepository.save(ret);
        }
        
    }
}