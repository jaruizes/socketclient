package com.jaruiz.examples.socket.socketclient.definitions.impl;

import java.util.Vector;

/**
 * Clase que representa la estructura de los parametros de salida de la rutina.
 * 
 * @author capgemini
 *
 */
public class ResultMap {
    private String nameClass;
    private Vector<Parameter> parameters;

    /**
     * Constructor
     */
    public ResultMap() {
	this.parameters = new Vector<Parameter>();
    }
    
    /**
     * Nombre de la clase que debe ser generada como resultado.
     * 
     * @param nameClass
     */
    public void setNameClass(String nameClass) {
	this.nameClass = nameClass;
    }

    /**
     * Recupera el nombre de la clase que debe generarse como resultado.
     * 
     * @return  el nombre de la clase que debe generarse como resultado.
     */
    public String getNameClass() {
	return this.nameClass;
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
}
