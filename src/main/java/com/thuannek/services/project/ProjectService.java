package com.thuannek.services.project;

public class ProjectService {
    private ProjectRepository _projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        _projectRepository = projectRepository;
    }
}
