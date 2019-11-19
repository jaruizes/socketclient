package com.jaruiz.examples.socket.socketclient.definitions;

public class RoutineDefinitionNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RoutineDefinitionNotFoundException(String routineName, String defFile) {
        super(new StringBuilder().append("Routine [")
                .append(routineName).append("] not found in definitions file: ")
                .append(defFile).toString());

    }
}
