package com.thuannek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thuannek.handler.auth.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
 
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new AuthHandler(), "/authentication");
        // registry.addHandler(new StorageHandler(), "/storage");
        // registry.addHandler(new DatabaseHandler(), "/database");
        // registry.addHandler(new DeviceHandler(), "/device");
    }
}

// #Author gate way : manage sesion token
// #Storage gate way : for store file , binary chanel
// #database gate way : for handle data in data base
// #device gate way : for device connected 