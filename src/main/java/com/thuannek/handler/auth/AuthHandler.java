package com.thuannek.handler.auth;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springframework.http.HttpHeaders;
import java.net.InetSocketAddress;

import com.google.cloud.storage.Acl.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// import org.springframework.boot.security.*;

import com.thuannek.handler.auth.AuthController;
import com.thuannek.models.UserModel;
import com.thuannek.util.JwtUtil;

import com.thuannek.repositorys.UserRepository;

import org.springframework.stereotype.Service;

import com.thuannek.controllers.InterfacePostgresController;
import com.thuannek.controllers.UserController;

// @Service
public class AuthHandler extends TextWebSocketHandler{
    private final List<WebSocketSession> sessionsAuth = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
    private JwtUtil jwtUtil = new JwtUtil();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception, IllegalArgumentException {
        try {

            String idToken = session.getHandshakeHeaders().get("id_token").get(0);
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
            logger.info("Accepted connection from: {}", decodedToken.getEmail());
            
            AuthController.addSession(session);
            sessionsAuth.add(session);
            
            String jwtreturn = jwtUtil.generateToken(decodedToken.getEmail());

            UserModel user = new UserModel(
                decodedToken.getName(),
                decodedToken.getUid(),
                decodedToken.getEmail(),
                jwtreturn
            );
            InterfacePostgresController.userController.createOrUpdateUser(user);

            session.sendMessage(new TextMessage(jwtreturn));

        }catch (Exception e ){
            logger.error(e.getMessage(), e.getCause());
            session.close();
        }
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String idToken = session.getHandshakeHeaders().get("id_token").get(0);
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();

        UserModel user = new UserModel(
            decodedToken.getName(),
            decodedToken.getUid(),
            decodedToken.getEmail(),
            ""
        );
        InterfacePostgresController.userController.createOrUpdateUser(user);

        System.out.println("disconnect from " + session.getId());
        AuthController.removeSession(session);
        sessionsAuth.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception,IllegalArgumentException,IOException {
        super.handleTextMessage(session, message);
        try {
            int retError = jwtUtil.validateJwtToken(message.getPayload());
            if( retError == 0 ){
                logger.info("JWT is ok");
                session.sendMessage(new TextMessage("ok"));
            }else if (retError ==1 ){
                logger.info("JWT is expire time");
                try {
                    String idToken = session.getHandshakeHeaders().get("id_token").get(0);
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
                    logger.info("Accepted connection from: {}", decodedToken.getEmail());
                    
                    String jwtreturn = jwtUtil.generateToken(decodedToken.getEmail());

                    UserModel user = new UserModel(
                        decodedToken.getName(),
                        decodedToken.getUid(),
                        decodedToken.getEmail(),
                        jwtreturn
                    );
                    InterfacePostgresController.userController.createOrUpdateUser(user);
        
                    session.sendMessage(new TextMessage(jwtreturn));
        
                }catch (Exception e ){
                    logger.error(e.getMessage(), e.getCause());
                    session.close();
                }
            }else {
                session.close();
            }
            
        } catch (IOException e) {
            System.out.println(e);
            session.close();
        }
    }
}
