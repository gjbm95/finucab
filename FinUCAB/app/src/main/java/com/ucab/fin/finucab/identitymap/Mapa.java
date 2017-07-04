package com.ucab.fin.finucab.identitymap;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Junior on 14/06/2017.
 */

public class Mapa {

   private HashMap<Integer,Object> objetos;
   private static Mapa mapa;


    private Mapa (){

    }

    public static Mapa obtenerInstancia(){
        if (mapa==null)
            mapa = new Mapa ();

        return mapa;
    }

    public void agregarObjeto(int clave,Object object){
        if (objetos==null)
            objetos = new HashMap<Integer,Object>();
        objetos.put(clave,object);
    }

    public void modificarObjeto(int clave,Object object){
        if (objetos.containsKey(clave)){
            objetos.put(clave,object);
        }
    }

    public void eliminarObjeto(int clave, Object object){
        if (objetos.containsKey(clave)) {
            objetos.remove(clave);
        }
    }

    public Object obtenerObjeto(int clave){
        Iterator iterador = objetos.entrySet().iterator();
        Object obtenido = null;
        Map.Entry objeto;
        while (iterador.hasNext()) {
            objeto = (Map.Entry) iterador.next();
            obtenido  = objeto.getValue();
        }
        return obtenido;
    }




}
