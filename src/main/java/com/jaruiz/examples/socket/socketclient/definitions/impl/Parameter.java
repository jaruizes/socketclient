package com.jaruiz.examples.socket.socketclient.definitions.impl;

import java.util.Vector;

/**
 * Clase que representa cada uno de los parametros que componen tanto la cadena den entrada como la de salida de una
 * rutina.
 * 
 * @author capgemini
 */
public class Parameter {

    private String property;
    private String javaType = "";
    private String signed = "";
    private String size;
    private String defaultValue;
    private String nameClass;
    private String nameMethodSet;
    private String nameMethodGet;
    private Vector<Parameter> parameters;
    private Boolean truncated = false;
    /**
     * Constructor
     */
    public Parameter() {
        this.parameters = new Vector<Parameter>();
    }

    /**
     * Recupera el nombre de la propiedad a ser leida en el caso de inputMap o la propiedad a setearse en caso de
     * resultMap.
     * 
     * @return el nombre de la propiedad.
     */
    public String getProperty() {
        return this.property;
    }

    /**
     * Establece el nombre de la propiedad a ser leida en el caso de inputMap o la propiedad a setearse en caso de
     * resultMap.
     * 
     * @param property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Recupera el tipo de objeto java que vamos a obtener o setear con la propiedad
     * 
     * @return el tipo de objeto java que vamos a obtener o setear con la propiedad
     */
    public String getJavaType() {
        return this.javaType;
    }

    /**
     * Establece el tipo de objeto java que vamos a obtener o setear con la propiedad.
     * 
     * @param javaType
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    /**
     * Recupera el signo del objeto java que vamos a obtener o setear con la propiedad
     * 
     * @return el signo del objeto java que vamos a obtener o setear con la propiedad
     */
    public String getSigned() {
        return this.signed;
    }

    /**
     * Establece el tipo de objeto java que vamos a obtener o setear con la propiedad.
     * 
     * @param javaType
     */
    public void setSigned(String signed) {
        this.signed = signed;
    }
    
    /**
     * Recupera el tama�o del valor de la propiedad
     * 
     * @return el tama�o del valor de la propiedad
     */
    public String getSize() {
        return this.size;
    }

    /**
     * Establece el tama�o del valor de la propiedad
     * 
     * @param size
     *            el tama�o del valor de la propiedad
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Establece el valor por defecto en caso de no recibirlo
     * 
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Recupera el valor por defecto en caso de no recibirlo
     * 
     * @return el valor por defecto en caso de no recibirlo
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Establece el nombre de la clase que debe ser construida cuando el tipo es Object
     * 
     * @param nameClass
     *            el nombre de la clase que debe ser construida
     */
    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    /**
     * Recupera el nombre de la clase que debe ser construida
     * 
     * @return el nombre de la clase que debe ser construida
     */
    public String getNameClass() {
        return this.nameClass;
    }

    /**
     * Establece la coleccion de parametros hijos.
     * 
     * @param parameters
     *            coleccion de parametros hijos.
     */
    public void setParameters(Vector<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Devuelve la colecci�n de parametros hijos.
     * 
     * @return la colecci�n de parametros hijos.
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
     * Establece el nombre del metodo a usar cuando se necesite setear el parametro. Solo debe ser indicado cuando no se
     * desea usar el metodo set<nombrepropiedad>.
     * 
     * @param nameMethodSet
     */
    public void setNameMethodSet(String nameMethodSet) {
        this.nameMethodSet = nameMethodSet;
    }

    /**
     * Recupera el nombre del metodo a usar para setear la propiedad.
     * 
     * @return el nombre del metodo a usar para setear la propiedad.
     */
    public String getNameMethodSet() {
        return this.nameMethodSet;
    }

    /**
     * Establece el nombre del metodo que se debe usar para recuperar el valor de la propiedad. Solo debe ser indicado
     * cuando no se desea usar el metodo get<nombrepropiedad>.
     * 
     * @param nameMethodGet
     */
    public void setNameMethodGet(String nameMethodGet) {
        this.nameMethodGet = nameMethodGet;
    }

    /**
     * Recupera el nombre del metodo a usar en caso para recuperar el valor de propiedad.
     * 
     * @return el nombre del metodo a usar en caso para recuperar el valor de propiedad.
     */
    public String getNameMethodGet() {
        return this.nameMethodGet;
    }
    
    /**
     * Establece si el campo debe ser cortado en caso de exceder 
     * el tama�o definido en el mapeo
     * 
     * @param truncated: por defecto false
     */
    public void setTruncated(Boolean truncated) {
		this.truncated = truncated;
	}
    
    /**
     * Indica si el campo se cortar� en caso de exceder el tama�o definido
     * 
     * @return true indica que el campo se cortar� en caso de exceder el tama�o definido.
     */
    public Boolean getTruncated() {
		return truncated;
	}

	
    
    
}
