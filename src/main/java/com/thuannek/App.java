package com.thuannek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class App extends SpringBootServletInitializer  {
    public static void main(String[] args) throws IOException {

        FileInputStream serviceAccount = new FileInputStream("src/main/java/com/thuannek/resources/iotcore-208310-firebase-adminsdk-7rvva-8c6e6ccfb9.json");

        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://iotcore-208310.firebaseio.com")
            .setProjectId("iotcore-208310")
            .build();
        

        FirebaseApp.initializeApp(options);
        System.out.println("init firebase app successfully");


        System.out.println("start websocket server");
        SpringApplication.run(App.class, args);
    }
}