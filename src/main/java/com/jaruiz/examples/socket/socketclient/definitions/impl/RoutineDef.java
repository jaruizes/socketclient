package com.jaruiz.examples.socket.socketclient.definitions.impl;

/**
 * Clase que representa la definici�n de una rutina.
 * 
 * @author capgemini
 */
public class RoutineDef {

    private String name;
    private String generateHeader = "true";
    private InputMap inputMap;
    private ResultMap resultMap;

    /**
     * Establece el nombre de la rutina.
     * 
     * @param name nombre de la rutina.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Recupera el nombre de la rutina.
     * 
     * @return el nombre de la rutina.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Establece el objeto que agrupa la definici�n de los parametros de entrada a la rutina.
     * 
     * @param inputMap definici�n de los parametros de entrada a la rutina.
     */
    public void setInputMap(InputMap inputMap) {
        this.inputMap = inputMap;
    }

    /**
     * Recupera la definici�n de los parametros de entrada de la rutina.
     * 
     * @return la definici�n de los parametros de entrada de la rutina.
     */
    public InputMap getInputMap() {
        return this.inputMap;
    }

    /**
     * Establece la definici�n de los parametros de salida de la rutina.
     * 
     * @param resultMap la definici�n de los parametros de salida de la rutina.
     */
    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * Recupera la definici�n de los parametros de salida de la rutina.
     * 
     * @return la definici�n de los parametros de salida de la rutina.
     */
    public ResultMap getResultMap() {
        return this.resultMap;
    }

    /**
     * Establece el atributo de activaci�n de la creaci�n de cabeceras.
     * 
     * @param String el atributo de activaci�n de la creaci�n de cabeceras.
     */
	public void setGenerateHeader(String generateHeader) {
		this.generateHeader = generateHeader;
	}

    /**
     * Recupera la definici�n del atributo de activaci�n de la creaci�n de cabeceras.
     * 
     * @return atributo de activaci�n de la creaci�n de cabeceras.
     */
	public String getGenerateHeader() {
		return generateHeader;
	}

}
