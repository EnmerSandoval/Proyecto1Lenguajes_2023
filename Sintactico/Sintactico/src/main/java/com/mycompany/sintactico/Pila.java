package com.mycompany.sintactico;

/**
 *
 * @author enmer
 */
public class Pila {
    private Nodo ultimoValorIngresado;

    public Pila() {
        ultimoValorIngresado = null;
    }
    
    public void insertar(String informacionNodo){
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.informacion = informacionNodo;
        if (ultimoValorIngresado == null) {
            
            nuevoNodo.siguiente = null;
            ultimoValorIngresado = nuevoNodo;
            
        } else {
            
            nuevoNodo.siguiente = ultimoValorIngresado;
            ultimoValorIngresado = nuevoNodo;
            
        }
    }
    
    public String extraer(){
        String pilaVacia = "";
        
        if (ultimoValorIngresado != null) {
            
            String informacion = ultimoValorIngresado.informacion;
            ultimoValorIngresado = ultimoValorIngresado.siguiente;
            return informacion;
            
        } else {
            return pilaVacia;
        }
    }
    
    public boolean esVacia(){
        return ultimoValorIngresado == null;
    }
}
