package com.jaruiz.examples.socket.socketclient.service;

import com.jaruiz.examples.socket.socketclient.adapter.AdapterException;
import com.jaruiz.examples.socket.socketclient.adapter.RoutineAdapter;
import com.jaruiz.examples.socket.socketclient.connector.SocketConnector;
import com.jaruiz.examples.socket.socketclient.definitions.RoutineDefinitionNotFoundException;
import com.jaruiz.examples.socket.socketclient.model.RoutineInput;
import com.jaruiz.examples.socket.socketclient.model.RoutineOutput;
import com.jaruiz.examples.socket.socketclient.definitions.RoutineDefinitions;
import com.jaruiz.examples.socket.socketclient.definitions.impl.RoutineDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutineCallerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutineCallerService.class);

    private SocketConnector connector;
    private RoutineDefinitions routineDefinitions;
    private RoutineAdapter routineAdapter;

    @Autowired
    public RoutineCallerService(SocketConnector connector, RoutineDefinitions routineDefinitions, RoutineAdapter routineAdapter) {
        this.connector = connector;
        this.routineDefinitions = routineDefinitions;
        this.routineAdapter = routineAdapter;
    }

    public RoutineOutput callRoutine(String routineName, RoutineInput input) throws RoutineDefinitionNotFoundException {
        // Check if the routine is loaded from the definition file
        final RoutineDef routineDef = this.routineDefinitions.get(routineName);

        RoutineOutput output = null;
        try {
            // Marshalling process in order to transform the input object values to a String
            final String stringIn = this.routineAdapter.marshall(input, routineDef);

            // Open a socket connection
            this.connector.openConnection();

            // Send the message
            String response = this.connector.send(stringIn);

            output = (RoutineOutput) this.routineAdapter.unmarshall(response, routineDef);
            this.connector.closeConnection();
        } catch (AdapterException e) {
            e.printStackTrace();
        }


        return output;
    }


}
