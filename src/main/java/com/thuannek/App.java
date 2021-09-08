package com.thuannek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.thuannek.commons.AppServices;
import com.thuannek.services.project.ProjectRepository;
import com.thuannek.services.project.ProjectService;
import com.thuannek.services.user.UserRepository;
import com.thuannek.services.user.UserService;
import com.thuannek.util.GetIt;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.ApplicationContext;
import com.thuannek.commons.AppServices;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.thuannek.services.user","com.thuannek.services.project"})  
@EntityScan(basePackages ={"com.thuannek.services.user","com.thuannek.services.project"})
public class App extends SpringBootServletInitializer  {
    public static void main(String[] args) throws IOException {

        FileInputStream serviceAccount = new FileInputStream("src/main/java/com/thuannek/resources/iotcore-208310-firebase-adminsdk-7rvva-8c6e6ccfb9.json");

        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://iotcore-208310.firebaseio.com")
            .setProjectId("iotcore-208310")
            .build();
        
        // init firebase
        FirebaseApp.initializeApp(options);
        System.out.println("init firebase app successfully");

        // start server
        System.out.println("start websocket server");
        GetIt.appServices = new AppServices(SpringApplication.run(App.class, args));

        GetIt.appServices.setUserService(new UserService(GetIt.appServices.getAppContext().getBean(UserRepository.class)));
        GetIt.appServices.setProjectService(new ProjectService(GetIt.appServices.getAppContext().getBean(ProjectRepository.class)));
    }
}