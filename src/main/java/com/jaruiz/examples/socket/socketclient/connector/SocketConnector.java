package com.jaruiz.examples.socket.socketclient.connector;

import com.jaruiz.examples.socket.socketclient.config.SocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketConnector.class);

    private String server;
    private int port;
    private int timeout;
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SocketConnector(String server, int port, int timeout) {
        this.server = server;
        this.port = port;
        this.timeout = timeout;
        LOGGER.info("Created socker connector instance to socket {}:{}", server, port);
    }

    public void openConnection() {
        try {
            this.clientSocket = new Socket(this.server, this.port);
            this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            LOGGER.info("Error connecting to socket {}:{}", this.server, this.port);
            LOGGER.error(e.getMessage());
        }
    }

    public String send(String msg) {
        LOGGER.info("Sending {}", msg);
        this.writer.println(msg);

        String resp = null;
        try {
            resp = this.reader.readLine();
        } catch (IOException e) {
            LOGGER.info("Error reading message from socket");
            LOGGER.error(e.getMessage());
        }
        return resp;
    }

    public void closeConnection() {
        try {
            this.reader.close();
            this.writer.close();
            this.clientSocket.close();
        } catch (IOException e) {
            LOGGER.info("Error closing connection to socket {}:{}", this.server, this.port);
            LOGGER.error(e.getMessage());
        }
    }
}
