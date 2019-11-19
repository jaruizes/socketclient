package com.jaruiz.examples.socket.socketclient.utils;

/**
 * Clase Utilidad que permite procesar una cadena de parametros.
 * 
 * @author capgemini
 */
public class StringBufferBuilder extends Object {

    private String buf = null;

    /**
     * Constructor con la cadena de parametros
     * 
     * @param str
     */
    public StringBufferBuilder(String str) {
        this.buf = str;
    }

    /**
     * Recupera un fragmento de longitud len empezando por el principio. Lo elimina de la cadena y lo devuelve.
     * 
     * @param len
     *            longitud del fragmento a recuperar.
     * @return el fragmento de la cadena
     */
    public String get(int len) {
        String result = "";

        result = this.buf.substring(0, len);
        this.buf = this.buf.substring(len);

        return result;
    }

    /**
     * Recupera un fragmento de longitud len empezando en la posicion pos. Lo elimina de la cadena y lo devuelve.
     * 
     * @param pos posicion de la cadena donde debe recuperar el fragmento.
     * @param len longitud del fragmento a recuperar.
     * @return el fragmento de la cadena
     */
    public String get(int pos, int len) {
        String result = "";

        result = this.buf.substring(pos, pos + len);
        this.buf = this.buf.substring(0, pos) + this.buf.substring(pos + len);

        return result;
    }

    /**
     * Recupera un fragmento de la cadena como un nuevo StringBufferBuilder.
     * El fragmento se obtiene empezando por el principio con la longitud indicada por parametro.
     * @param len longitud del fragmento
     * @return el nuevo StringBufferBuilder del fragmento.
     */
    public StringBufferBuilder getSB(int len) {
        StringBufferBuilder resultSB = null;
        String result = "";

        result = this.buf.substring(0, len);
        this.buf = this.buf.substring(len);

        resultSB = new StringBufferBuilder(result);

        return resultSB;
    }

    /**
     * Recupera un fragmento de la cadena como un nuevo StringBufferBuilder.
     * El fragmento se obtiene empezando por la posicion pos con la longitud len.
     * 
     * @param pos posicion de inicio del fragmento
     * @param len longitud del fragmento
     * @return el nuevo StringBufferBuilder del fragmento.
     */
    public StringBufferBuilder getSB(int pos, int len) {
        StringBufferBuilder resultSB = null;
        String result = "";

        result = this.buf.substring(pos, pos + len);
        this.buf = this.buf.substring(0, pos) + this.buf.substring(pos + len);

        resultSB = new StringBufferBuilder(result);

        return resultSB;
    }

    /**
     * Retorna la longitud de la cadena
     * 
     * @return la longitud de la cadena
     */
    public int length() {
        return this.buf.length();
    }

    @Override
    public String toString() {
        return this.buf;
    }
}
