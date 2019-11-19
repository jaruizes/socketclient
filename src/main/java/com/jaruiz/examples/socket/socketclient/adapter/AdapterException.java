package com.jaruiz.examples.socket.socketclient.adapter;

/**
 * Excepcion generada durante la ejecucion del adapter
 * 
 * @author capgemini
 */
public class AdapterException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto de AdapterException
     */
    public AdapterException() {
        super();
    }

    /**
     * Constructor a partir de mensaje de texto
     * 
     * @param pMessage
     *            mensaje de la excepcion
     */
    public AdapterException(String pMessage) {
        super(pMessage);
    }

    /**
     * Constructor a partir de Excepci�n Throwable.
     * 
     * @param pCause
     *            Throwable con la excepci�n
     */
    public AdapterException(Throwable pCause) {
        super(pCause);
    }

    /**
     * Constructor a partir de Excepci�n Throwable y mensaje asociado.
     * 
     * @param pMessage mensaje de la excepcion
     * @param pCause Throwable con la excepci�n
     */
    public AdapterException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}