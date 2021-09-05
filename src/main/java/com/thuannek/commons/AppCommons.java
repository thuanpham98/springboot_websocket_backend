package com.thuannek.commons;

import org.springframework.context.ApplicationContext;
import com.thuannek.services.user.UserService;

public class AppCommons {

    public AppCommons(ApplicationContext context){
        _context = context;
    }

    static private ApplicationContext _context;
    static private UserService _userService;

    public ApplicationContext getAppContext(){
        return _context;
    }

    public UserService getUserService(){
        return _userService;
    }

    public void setUserService(UserService userService){
        _userService = userService;
    }   

}
