// package com.thuannek.handler;

// import java.io.IOException;
// import java.util.List;
// import java.util.concurrent.CopyOnWriteArrayList;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// import org.springframework.http.HttpHeaders;
// import java.net.InetSocketAddress;

// import com.google.firebase.FirebaseApp;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.auth.FirebaseAuthException;
// import com.google.firebase.auth.FirebaseToken;
// import com.google.firebase.database.FirebaseDatabase;
// // import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.web.filter.OncePerRequestFilter;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// // import com.thuannek.config.*;

// public class MyTextWebSocketHandler extends TextWebSocketHandler {
//     private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//     private static final Logger logger = LoggerFactory.getLogger(MyTextWebSocketHandler.class);
    

//     @Override
//     public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//         InetSocketAddress clientAddress = session.getRemoteAddress();
//         String id_token = session.getHandshakeHeaders().get("id_token").get(0);
        
//         try {
//             FirebaseToken    decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(id_token).get();
//                 System.out.println(decodedToken.getUid());
//                 System.out.println(decodedToken.getEmail());
//             // Token is valid and not revoked.
//             // String uid = decodedToken.getUid();
//             sessions.add(session);
//             // ControllerSession.setSessions(session);
//             super.afterConnectionEstablished(session);
//             for(WebSocketSession webSocketSession : sessions) {
//                 webSocketSession.sendMessage(new TextMessage("Hello " + decodedToken.getEmail().toString()));
//             }
//         } catch (FirebaseAuthException e) {
//             System.out.println(e);
//             session.close();
//         }

//         //the messages will be broadcasted to all users.
//         logger.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());
        
//     }
 
//     @Override
//     public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//         logger.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
//         sessions.remove(session);
//         ControllerSession.removeSessions(session);
//         super.afterConnectionClosed(session, status);
//     }
 
//     @Override
//     protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//         super.handleTextMessage(session, message);
//         System.out.println(ControllerSession.getSessions().size());
//         sessions.forEach(webSocketSession -> {
//             try {
//                 System.out.println(message);
//                 webSocketSession.sendMessage(message);
//             } catch (IOException e) {
//                 System.out.println(e);
//             }
//         });
//     }
// }
