package com.jaruiz.examples.socket.socketclient;

import com.jaruiz.examples.socket.socketclient.service.RoutineCallerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SocketclientApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketclientApplicationTests.class);
    private static final int PORT = 6666;
    private static final String RESPONSE_PSTL009 = "                    00                              2837";

    @Autowired
    RoutineCallerService routineCallerService;

    @Test
    public void send1() {
        PSTL009 in = new PSTL009();
        in.setPoliza("12345678");
        in.setFecha("20191116");
        PSTL009Out out = (PSTL009Out) this.routineCallerService.callRoutine("PSTL09", in);
        Assert.assertTrue(out.getEstado().equalsIgnoreCase("2837"));

    }

}
