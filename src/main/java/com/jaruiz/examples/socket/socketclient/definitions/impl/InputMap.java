package com.jaruiz.examples.socket.socketclient.definitions.impl;

import java.util.Vector;

/**
 * Clase que representa la estructura de los parametros de entrada de la rutina.
 *
 * @author capgemini
 */
public class InputMap {
    private int buffer_size = 3500;
    private Vector<Parameter> parameters;

    /**
     * Constructor
     */
    public InputMap() {
        this.parameters = new Vector<Parameter>();
    }

    /**
     * Establece la coleccion de parametros.
     *
     * @param parameters coleccion de parametros.
     */
    public void setParameters(Vector<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Devuelve la colecci�n de parametros.
     *
     * @return la colecci�n de parametros.
     */
    public Vector<Parameter> getParameters() {
        return this.parameters;
    }

    /**
     * A�ade un parametro a la colecci�n de parametros.
     *
     * @param parameter
     */
    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    /**
     * Establece el tama�o del buffer del mensaje de la rutina.
     *
     * @param buffer_size
     */
    public void setBuffer_size(int buffer_size) {
        this.buffer_size = buffer_size;
    }

    /**
     * Recupera el tama�o de buffer de la cadena de parametros de entrada.
     *
     * @return el tama�o de buffer de la cadena de parametros de entrada.
     */
    public int getBuffer_size() {
        return this.buffer_size;
    }
}