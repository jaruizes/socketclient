package com.jaruiz.examples.socket.socketclient.adapter;

import com.jaruiz.examples.socket.socketclient.utils.StringBufferBuilder;
import com.jaruiz.examples.socket.socketclient.utils.StringBufferMessage;
import com.jaruiz.examples.socket.socketclient.definitions.impl.Parameter;
import com.jaruiz.examples.socket.socketclient.definitions.impl.RoutineDef;

import java.util.Date;
import java.util.ListIterator;

public class RoutineAdapter {

    public static final String TYPE_STRING = "String";
    public static final String TYPE_BOOLEAN = "Boolean";
    public static final String TYPE_INTEGER = "Integer";
    public static final String TYPE_DOUBLE = "Double";
    public static final String TYPE_FLOAT = "Float";
    public static final String TYPE_LONG = "Long";
    public static final String TYPE_SHORT = "Short";
    public static final String TYPE_DATE = "Date";
    public static final String TYPE_OBJECT = "Object";

    public String marshall(Object pInputObject, RoutineDef routine) throws AdapterException {
        StringBufferMessage buf = new StringBufferMessage(routine.getInputMap().getBuffer_size());

        ListIterator<Parameter> lparameters = routine.getInputMap().getParameters().listIterator();

        while (lparameters.hasNext()) {
            Parameter param = lparameters.next();

            if (param.getJavaType().equalsIgnoreCase(TYPE_STRING) || param.getJavaType().equalsIgnoreCase(TYPE_BOOLEAN)
                    || param.getJavaType().equalsIgnoreCase(TYPE_INTEGER) || param.getJavaType().equalsIgnoreCase(TYPE_DOUBLE)
                    || param.getJavaType().equalsIgnoreCase(TYPE_FLOAT) || param.getJavaType().equalsIgnoreCase(TYPE_LONG)
                    || param.getJavaType().equalsIgnoreCase(TYPE_SHORT) || param.getJavaType().equalsIgnoreCase(TYPE_DATE)) {
                buf = marshallParameter(buf, param, pInputObject);
            } else {
                throw new AdapterException("Error al tratar el parametro de entrada:: " + param.getProperty() + " JavaType erroneo");
            }
        }
        return buf.toString();
    }

    private StringBufferMessage marshallParameter(StringBufferMessage buf, Parameter param, Object pInputObject)
            throws AdapterException {
        Object paramvalue;

        try {
            paramvalue = UtilsAdapter.getPropertyValue(param, pInputObject);
            String itype = param.getJavaType();
            if (paramvalue == null) {
                if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_STRING)) paramvalue = "";
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_BOOLEAN)) paramvalue = new Boolean(false);
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_INTEGER)) paramvalue = new Integer(0);
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_LONG)) paramvalue = new Long(0);
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_SHORT)) paramvalue = new Short("0");
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_DOUBLE)) paramvalue = new Double(0);
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_FLOAT)) paramvalue = new Float(0);
                else if (itype.equalsIgnoreCase(StringBufferMessage.TYPE_DATE)) paramvalue = new Date();
                else
                    throw new Exception("Type erroneo");
            }
            if (paramvalue.toString().length() > Integer.parseInt(param.getSize())
                    && (!param.getTruncated() || !itype.equalsIgnoreCase(StringBufferMessage.TYPE_STRING))) {
                throw new AdapterException("Error al tratar el parametro de entrada:: " + param.getProperty() + " :: El parametro excede el tama�o definido por configuracion");
            } else if (paramvalue.toString().length() > Integer.parseInt(param.getSize())
                    && itype.equalsIgnoreCase(StringBufferMessage.TYPE_STRING)) {
                paramvalue = paramvalue.toString().substring(0, Integer.parseInt(param.getSize()));
            }

            buf.append(param.getJavaType(), paramvalue, Integer.parseInt(param.getSize()), param.getSigned());
        } catch (AdapterException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AdapterException("Error al tratar el parametro de entrada:: " + param.getProperty() , e);
        }

        return buf;
    }

    public Object unmarshall(String presult, RoutineDef routine) throws AdapterException {
        Object result = UtilsAdapter.getClassForName(routine.getResultMap().getNameClass());

        try {
            StringBufferBuilder sbout = new StringBufferBuilder(presult);

            if (routine.getGenerateHeader() == null || !routine.getGenerateHeader().equalsIgnoreCase("false")) {
                StringBufferBuilder header = sbout.getSB(52);

                header.get(20);
                String codigo = header.get(2);
                String descrip = header.get(30);

                UtilsAdapter.setPropertyValue("codRetorno", result, codigo);
                UtilsAdapter.setPropertyValue("descError", result, descrip);

                if (!codigo.equals("00"))
                    return result;
            }

            ListIterator<Parameter> lparameters = routine.getResultMap().getParameters().listIterator();

            while (lparameters.hasNext()) {
                Parameter param = lparameters.next();
                String paramvalue = "";
                if (param.getSigned().equalsIgnoreCase("true")
                        && !param.getJavaType().equalsIgnoreCase(TYPE_STRING)
                        && !param.getJavaType().equalsIgnoreCase(TYPE_OBJECT)
                        && !param.getJavaType().equalsIgnoreCase(TYPE_DATE))
                    paramvalue = sbout.get(Integer.parseInt(param.getSize()) + 1);
                else
                    paramvalue = sbout.get(Integer.parseInt(param.getSize()));

                if (param.getJavaType().equalsIgnoreCase(TYPE_OBJECT)) {
                    Object valueini = UtilsAdapter.getPropertyValue(param, result);
                    UtilsAdapter.setPropertyValue(param, result, this.subUnmarshall(paramvalue, param, valueini));
                } else {
                    if (param.getJavaType() == null)
                        throw new AdapterException("parametro JavaType obligatorio en Parameter:: property:"
                                + param.getProperty());
                    Object valueparam = this.valueOfParameter(param.getJavaType(), paramvalue);
//			    if (valueparam == null)
//				throw new AdapterException("parametro JavaType obligatorio en Parameter:: property:"
//					+ param.getProperty());
                    UtilsAdapter.setPropertyValue(param, result, valueparam);
                }

            }

        } catch (AdapterException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AdapterException("Error al procesar el resultado de la rutina " + routine.getName(), e);
        }
        return result;
    }

    private Object subUnmarshall(String presult, Parameter param, Object pResult) throws AdapterException {
        Object result = pResult;
        try {
            if (result == null)
                result = UtilsAdapter.getClassForName(param.getNameClass());

            StringBufferBuilder sbout = new StringBufferBuilder(presult);

            ListIterator<Parameter> lparameters = param.getParameters().listIterator();

            while (lparameters.hasNext()) {
                Parameter subparam = lparameters.next();
                String subparamvalue = "";
                if (subparam.getSigned().equalsIgnoreCase("true")
                        && !subparam.getJavaType().equalsIgnoreCase(TYPE_STRING)
                        && !subparam.getJavaType().equalsIgnoreCase(TYPE_OBJECT)
                        && !subparam.getJavaType().equalsIgnoreCase(TYPE_DATE))
                    subparamvalue = sbout.get(Integer.parseInt(subparam.getSize()) + 1);
                else
                    subparamvalue = sbout.get(Integer.parseInt(subparam.getSize()));

                if (subparam.getJavaType().equalsIgnoreCase(TYPE_OBJECT)) {
                    Object valueini = UtilsAdapter.getPropertyValue(subparam, result);
                    UtilsAdapter.setPropertyValue(subparam, result, this.subUnmarshall(subparamvalue, subparam, valueini));
                } else {
                    if (subparam.getJavaType() == null)
                        throw new AdapterException("parametro JavaType obligatorio en Parameter:: property:"
                                + subparam.getProperty());
                    Object valueparam = this.valueOfParameter(subparam.getJavaType(), subparamvalue);
//		    if (valueparam == null)
//			throw new AdapterException("parametro JavaType obligatorio en Parameter:: property:"
//				+ subparam.getProperty());
                    UtilsAdapter.setPropertyValue(subparam, result, valueparam);
                }

            }
        } catch (AdapterException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AdapterException("Error al procesar el resultado el parametro " + param.getProperty(), e);
        }
        return result;

    }

    private Object valueOfParameter(String javaType, String value) {
        Object result = null;

        // Fix routine caller - 20/09/2016 everis
        // En alg�n caso, hay campos que no son de tipo String,
        // que son vaci�s y que provocan errores al invocarse desde m�todo como valueOf()
        if(!javaType.equalsIgnoreCase(TYPE_STRING) && !value.isEmpty()) {
            return null;
        }

        if (javaType.equalsIgnoreCase(TYPE_STRING))
            return value;

        if (javaType.equalsIgnoreCase(TYPE_BOOLEAN))
            return Boolean.valueOf(value.trim());

        if (javaType.equalsIgnoreCase(TYPE_INTEGER))
            return Integer.valueOf(value.trim());

        if (javaType.equalsIgnoreCase(TYPE_DOUBLE))
            return Double.valueOf(value.trim().replaceAll(",", "."));

        if (javaType.equalsIgnoreCase(TYPE_FLOAT))
            return Float.valueOf(value.trim().replaceAll(",", "."));

        if (javaType.equalsIgnoreCase(TYPE_LONG))
            return Long.valueOf(value.trim());

        if (javaType.equalsIgnoreCase(TYPE_SHORT))
            return Short.valueOf(value.trim());

        if (javaType.equalsIgnoreCase(TYPE_DATE)) {
            return java.sql.Date.valueOf(new StringBuffer().append(value.trim().substring(0, 4)).append("-").append(
                    value.trim().substring(4, 6)).append("-").append(value.trim().substring(6, 8)).toString());
        }

        return result;

    }
}
