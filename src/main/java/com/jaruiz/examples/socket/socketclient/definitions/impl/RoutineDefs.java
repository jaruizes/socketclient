package com.jaruiz.examples.socket.socketclient.definitions.impl;

import java.util.HashMap;
import java.util.Map;

public class RoutineDefs {

    private Map<String, RoutineDef> routines;

    /**
     * Constructor
     */
    public RoutineDefs() {
        this.routines = new HashMap<String, RoutineDef>();
    }

    /**
     * A�ade una definici�n de rutina a la colecci�n. Si existe la reemplaza.
     * 
     * @param routine Definici�n de la rutina.
     */
    public void addRoutine(RoutineDef routine) {
        this.routines.put(routine.getName(), routine);
    }

    /**
     * Recupera el Map de definici�n de rutinas.
     * 
     * @return el Map de definici�n de rutinas.
     */
    public Map<String, RoutineDef> getRoutines() {
        return this.routines;
    }

    /**
     * Devuelve la definici�n de la rutina cuyo nombre es name. Si no existe devuelve null.
     * 
     * @param name nombre de la rutina.
     * @return La definici�n de la rutina. Null en caso de no existir.
     */
    public RoutineDef getRoutineDef(String name) {
        return this.routines.get(name);
    }
}
