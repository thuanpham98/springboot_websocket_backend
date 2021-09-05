package com.thuannek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.thuannek.commons.AppCommons;

import com.thuannek.services.user.UserRepository;
import com.thuannek.services.user.UserService;
import com.thuannek.util.AppDI;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.ApplicationContext;
import com.thuannek.commons.AppCommons;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.thuannek.services.*"})  
@EntityScan(basePackages ={"com.thuannek.services.*"})
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
        // ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        AppDI.appInstance = new AppCommons(SpringApplication.run(App.class, args));
        // AppCommons.context = SpringApplication.run(App.class, args);
        // UserRepository userRepository = AppDI.appInstance.getAppContext().getBean(UserRepository.class);
        AppDI.appInstance.setUserService(new UserService(AppDI.appInstance.getAppContext().getBean(UserRepository.class)));
    }
}