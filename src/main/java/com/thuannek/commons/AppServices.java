package com.thuannek.commons;

import org.springframework.context.ApplicationContext;

import com.thuannek.services.project.ProjectRepository;
import com.thuannek.services.project.ProjectService;
import com.thuannek.services.user.UserService;

public class AppServices {

    public AppServices(ApplicationContext context){
        _context = context;
    }

    static private ApplicationContext _context;
    static private UserService _userService;
    static private ProjectService _projectService;

    public ApplicationContext getAppContext(){
        return _context;
    }

    public UserService getUserService(){
        return _userService;
    }

    public void setUserService(UserService userService){
        _userService = userService;
    }   

    public ProjectService getProjectService(){
        return _projectService;
    }

    public void setProjectService(ProjectService projectService){
        _projectService =  projectService;
    }

}
