package com.jaruiz.examples.socket.socketclient.model;

import java.io.Serializable;

public class RoutineOutput implements Serializable {
    private String codRetorno;
    private String descError;

    public String getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(String codRetorno) {
        this.codRetorno = codRetorno;
    }

    public String getDescError() {
        return descError;
    }

    public void setDescError(String descError) {
        this.descError = descError;
    }
}
