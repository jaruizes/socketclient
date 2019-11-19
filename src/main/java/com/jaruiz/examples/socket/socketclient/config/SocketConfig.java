package com.jaruiz.examples.socket.socketclient.config;

import com.jaruiz.examples.socket.socketclient.adapter.RoutineAdapter;
import com.jaruiz.examples.socket.socketclient.connector.SocketConnector;
import com.jaruiz.examples.socket.socketclient.definitions.RoutineDefinitions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfig {
    @Value(value = "${routinecaller.socket.server:localhost}")
    private String server;
    @Value(value = "${routinecaller.socket.port:6666}")
    private int port;
    @Value(value = "${routinecaller.socket.timeout:3600}")
    private int timeout;
    @Value(value = "${routinecaller.definitionsFile}")
    private String definitionsFile;

    @Bean
    public SocketConnector socketConnector(){
        return new SocketConnector(this.server, this.port, this.timeout);
    }

    @Bean
    public RoutineDefinitions routineDefinitions(){
        return new RoutineDefinitions(definitionsFile);
    }

    @Bean
    public RoutineAdapter routineAdapter(){
        return new RoutineAdapter();
    }



}
