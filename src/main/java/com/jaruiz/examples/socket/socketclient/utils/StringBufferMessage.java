package com.jaruiz.examples.socket.socketclient.utils;

import java.io.Serializable;

/**
 * Clase que permite crear una cadena de caracteres con la informaci�n seteada.
 * 
 * @author capgemini
 */
public class StringBufferMessage extends Object implements Serializable {
    
    /** Constante de Tipo de Parametro String */
    public static final String TYPE_STRING = "String";
    /** Constante de Tipo de Parametro Boolean */
    public static final String TYPE_BOOLEAN = "Boolean";
    /** Constante de Tipo de Parametro Integer */
    public static final String TYPE_INTEGER = "Integer";
    /** Constante de Tipo de Parametro Double */
    public static final String TYPE_DOUBLE = "Double";
    /** Constante de Tipo de Parametro Float */
    public static final String TYPE_FLOAT = "Float";
    /** Constante de Tipo de Parametro Long */
    public static final String TYPE_LONG = "Long";
    /** Constante de Tipo de Parametro Short */
    public static final String TYPE_SHORT = "Short";
    /** Constante de Tipo de Parametro Date este formato es en formato sql.Date yyyy-mm-dd */
    public static final String TYPE_DATE = "Date";
    /** Constante de Tipo de Parametro Object */
    public static final String TYPE_OBJECT = "Object";
    
    private static final long serialVersionUID = 7170985969559633411L;
    
    private StringBuffer buf = null;
    private int totalsize = 0;
    
    private static final String ini_format_S = "%1$-";
    private static final String fin_format_S = "s";
    private static final String ini_format_I = "%0";
    private static final String ini_format_I_Signed = "% 0";    
    private static final String fin_format_I = "d";
    private static final String ini_format_F = "%0";
    private static final String ini_format_F_Signed = "% 0";
    private static final String fin_format_F = ".2f";
    private static final String format_Date_Y = "%tY";
    private static final String format_Date_M = "%tm";
    private static final String format_Date_D = "%td";
    
    /**
     * Constructor que recibe la longitud total que debe tener la cadena
     * 
     * @param size
     *            longitud total que debe tener la cadena
     */
    public StringBufferMessage(int size) {
	this.buf = new StringBuffer();
	this.totalsize = size;
    }
    
    /**
     * Constructor que crea la cadena con un string inicial y la longitud total de la cadena
     * 
     * @param str
     *            string inicial
     * @param size
     *            la longitud total de la cadena
     */
    public StringBufferMessage(String str, int size) {
	this.buf = new StringBuffer(str);
	this.totalsize = size;
    }
    
    /**
     * Establece la longitud total de la cadena.
     * 
     * @param size
     */
    public void setSize(int size) {
	this.totalsize = size;
    }
    
    /**
     * A�ade el string al final de la cadena.
     * 
     * @param pmsg
     */
    public void append(String pmsg) {
	this.buf.append(pmsg);
    }
    
    /**
     * Formatea el string a�adiendo espacios al final hasta la longitud psizemsg y lo a�ade a la cadena.
     * 
     * @param pmsg
     *            String a a�adir a la cadena
     * @param psizemsg
     *            longitud que debe tener el texto a�adido.
     */
    public void append(String pmsg, int psizemsg) {
	this.buf.append(String.format(ini_format_S + psizemsg + fin_format_S, pmsg));
    }
    
    /**
     * A�ade el int al final de la cadena
     * 
     * @param pmsg
     *            int a a�adir
     */
    public void append(int pmsg) {
	this.buf.append(pmsg);
    }
    
    /**
     * Formatea el int a�adiendo 0 al principio hasta la longitud psizemsg y lo a�ade a la cadena.
     * 
     * @param pmsg
     *            int a a�adir a la cadena
     * @param psizemsg
     *            longitud que debe tener el int a�adido.
     */
    public void append(int pmsg, int psizemsg) {
	this.buf.append(String.format(ini_format_I + psizemsg + fin_format_I, new Integer(pmsg)));
    }
    
    /**
     * Formatea el objeto pmsg seg�n el tipo type y la longitud psizemsg y lo a�ade a la cadena. Si el tipo es vacio o
     * nulo se trata como un String. Tipos permitidos String o Integer. Cualquier otro tipo devuelve una Exception.
     * 
     * @param type
     *            Tipo del Objeto pmsg
     * @param pmsg
     *            Objeto a a�adir a la cadena
     * @param psizemsg
     *            longitud que va a tener el objeto a�adido
     * @throws Exception
     *             Generada cuando el tipo no esta permitido.
     */
    public void append(String type, Object pmsg, int psizemsg) throws Exception {
	String itype = type;
	if (type == null || type.trim().equals(""))
	    itype = TYPE_STRING;
	
	if (itype.equalsIgnoreCase(TYPE_STRING) || itype.equalsIgnoreCase(TYPE_BOOLEAN))
	    this.buf.append(String.format(ini_format_S + psizemsg + fin_format_S, pmsg));
	else if (itype.equalsIgnoreCase(TYPE_INTEGER) || itype.equalsIgnoreCase(TYPE_LONG)
		|| itype.equalsIgnoreCase(TYPE_SHORT))
	    this.buf.append(String.format(ini_format_I + psizemsg + fin_format_I, pmsg));
	else if (itype.equalsIgnoreCase(TYPE_DOUBLE) || itype.equalsIgnoreCase(TYPE_FLOAT))
	    this.buf.append(String.format(ini_format_F + psizemsg + fin_format_F, pmsg));
	else if (itype.equalsIgnoreCase(TYPE_DATE))
	    this.buf.append(String.format(format_Date_Y, pmsg)).append(String.format(format_Date_M, pmsg)).append(String.format(format_Date_D, pmsg));	
	else
	    throw new Exception("Type erroneo");
	
    }

    /**
     * Formatea el objeto pmsg seg�n el tipo type y la longitud psizemsg, y el atributo signed psigned y lo a�ade a la cadena. Si el tipo es vacio o
     * nulo se trata como un String. Tipos permitidos String o Integer. Cualquier otro tipo devuelve una Exception.
     * 
     * @param type
     *            Tipo del Objeto pmsg
     * @param pmsg
     *            Objeto a a�adir a la cadena
     * @param psizemsg
     *            longitud que va a tener el objeto a�adido
     * @param psigned
     *            longitud que va a tener el objeto a�adido     *            
     * @throws Exception
     *             Generada cuando el tipo no esta permitido.
     */
    public void append(String type, Object pmsg, int psizemsg, String psigned) throws Exception {
    	String itype = type;
    	if (type == null || type.trim().equals(""))
    	    itype = TYPE_STRING;

    	String initformati = ini_format_I_Signed;
    	String initformatf = ini_format_F_Signed;
    	if (psigned == null || psigned.trim().equals("") || psigned.equalsIgnoreCase("false")) {
        	initformati = ini_format_I;
        	initformatf = ini_format_F;
    	}
    	
    	if (itype.equalsIgnoreCase(TYPE_STRING) || itype.equalsIgnoreCase(TYPE_BOOLEAN))
    	    this.buf.append(String.format(ini_format_S + psizemsg + fin_format_S, pmsg));
    	else if (itype.equalsIgnoreCase(TYPE_INTEGER) || itype.equalsIgnoreCase(TYPE_LONG)
    		|| itype.equalsIgnoreCase(TYPE_SHORT))
    	    this.buf.append(String.format(initformati + psizemsg + fin_format_I, pmsg));
    	else if (itype.equalsIgnoreCase(TYPE_DOUBLE) || itype.equalsIgnoreCase(TYPE_FLOAT))
    	    this.buf.append(String.format(initformatf + psizemsg + fin_format_F, pmsg));
    	else if (itype.equalsIgnoreCase(TYPE_DATE))
    	    this.buf.append(String.format(format_Date_Y, pmsg)).append(String.format(format_Date_M, pmsg)).append(String.format(format_Date_D, pmsg));	
    	else
    	    throw new Exception("Type erroneo");
    	
        }    
    /**
     * Remplaza el fragmento de la comienza el start y de longitud psizemsg por el string pmsg. Antes de remplazar se le
     * da formato String al pmsg con espacios al final hasta la longitud psizemsg.
     * 
     * @param start
     *            posicion de comienzo del remplazo en la cadena.
     * @param pmsg
     *            String a a�adir a la cadena.
     * @param psizemsg
     *            longitud total que debe tener tanto el pmsg como el fragmento a remplazar.
     */
    public void replace(int start, String pmsg, int psizemsg) {
	this.buf.replace(start, start + psizemsg, String.format(ini_format_S + psizemsg + fin_format_S, pmsg));
    }
    
    @Override
    public String toString() {
	if (this.buf == null)
	    return "null";
	return String.format(ini_format_S + this.totalsize + fin_format_S, this.buf.toString());
    }
}
