package com.jaruiz.examples.socket.socketclient.adapter;

import com.jaruiz.examples.socket.socketclient.definitions.impl.Parameter;
import org.springframework.util.MethodInvoker;
import org.springframework.util.StringUtils;

/**
 * Utilidades usadas por el adaptador
 * 
 * @author capgemini
 */
public abstract class UtilsAdapter {

    private static final String START_GET_METHOD = "get";
    private static final String START_SET_METHOD = "set";

    /**
     * Recupera el valor de la propiedad indicada en el objeto parameter del objeto pInputObject
     * 
     * @param parameter
     *            Objeto de configuraci�n que indica la propiedad a recuperar
     * @param pInputObject
     *            Objeto del que recuperar el valor de la propiedad
     * @return El valor de la propiedad
     * @throws AdapterException
     */
    public static Object getPropertyValue(Parameter parameter, Object pInputObject) throws AdapterException {
        Object result = null;

        try {
            MethodInvoker invoker = new MethodInvoker();
            invoker.setTargetObject(pInputObject);

            if (parameter.getNameMethodGet() == null || parameter.getNameMethodGet().trim().equals("")) invoker
                    .setTargetMethod(START_GET_METHOD + StringUtils.capitalize(parameter.getProperty()));
            else invoker.setTargetMethod(parameter.getNameMethodGet());
            
        	invoker.prepare();

            result = invoker.invoke();

            if (result == null) result = parameter.getDefaultValue();            
        } catch (Exception e) {
        	if (parameter.getDefaultValue()==null || parameter.getDefaultValue().trim().equals("")) {
	            e.printStackTrace();
	            throw new AdapterException("Error al recuperar valor de la propiedad:: " + parameter.getProperty() + " de pInputObject::" + pInputObject, e);  
        	} else {
        		result =  parameter.getDefaultValue();
        	}
        }

        return result;
    }


    /**
     * Construye una clase a partir del String con el nombre.
     * 
     * @param name
     *            nombre completo de la clase
     * @return la instancia de la clase
     * @throws AdapterException
     */
    @SuppressWarnings("unchecked")
    public static Object getClassForName(String name) throws AdapterException {
        Class cl;
        java.lang.reflect.Constructor co;
        try {
            cl = Class.forName(name);
            co = cl.getConstructor();
            return co.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AdapterException("Error al construir la clase:: " + name, e);
        }
    }

    /**
     * Setea el valor (value) de la propiedad indicada en el Parameter en el objeto pInputObject. Devuelve el mismo
     * objeto pInputObject con el valor seteado.
     * 
     * @param parameter
     *            Objeto de configuraci�n que indica la propiedad a setear
     * @param pInputObject
     *            Objeto en el que setear el value.
     * @param value
     *            Valor a setear
     * @return Objeto con la propiedad seteada
     * @throws AdapterException
     */
    public static Object setPropertyValue(Parameter parameter, Object pInputObject, Object value)
            throws AdapterException {
        Object result = null;

        try {
            MethodInvoker invoker = new MethodInvoker();
            invoker.setTargetObject(pInputObject);

            if (parameter.getNameMethodSet() == null || parameter.getNameMethodSet().trim().equals("")) invoker
                    .setTargetMethod(START_SET_METHOD + StringUtils.capitalize(parameter.getProperty()));
            else invoker.setTargetMethod(parameter.getNameMethodSet());

            invoker.setArguments(new Object[] { value });
            
            invoker.prepare();

            result = invoker.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AdapterException("Error setear la propiedad:: " + parameter.getProperty() + " pInputObject::" + pInputObject + "con el valor::" + value, e);        	
        }
        return result;
    }

    /**
     * Setea el valor (value) de la propiedad property en el objeto pInputObject. Devuelve el mismo objeto pInputObject
     * con el valor seteado.
     * 
     * @param property
     *            nombre de la propiedad
     * @param pInputObject
     *            Objeto en el que setear el value.
     * @param value
     *            Valor a setear
     * @return Objeto con la propiedad seteada
     * @throws AdapterException
     */
    public static Object setPropertyValue(String property, Object pInputObject, Object value) throws AdapterException {
        Object result = null;

        try {
            MethodInvoker invoker = new MethodInvoker();
            
            invoker.setTargetObject(pInputObject);

            invoker.setTargetMethod(START_SET_METHOD + StringUtils.capitalize(property));

            invoker.setArguments(new Object[] { value });
            
            invoker.prepare();

            result = invoker.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AdapterException("Error setear la propiedad:: " + property + " pInputObject::" + pInputObject + "con el valor::" + value, e);        	
        }
        return result;
    }
}
