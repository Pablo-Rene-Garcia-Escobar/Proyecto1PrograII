package proyecto1prograii;

import java.util.ArrayList;

/**
 * Esta clase es abstracta y maneja un arreglo de vehiculos
 * @author Pablo Garcia
 * @version 1.0
 */
public abstract class ArrayVehiculo {
    
    private ArrayList<Vehiculo>  vehiculo;
    
    /**
     * Este es un metodo constructo para incializar un ArrayList
     */
    public ArrayVehiculo() {
        this.vehiculo = new ArrayList<>();
    }
   
    /**
     * Metodo que añade un objeto a la lista
     * @param v recibe un tipo objeto vehiculo para añadir al ArrayList
     */
    public void addVehiculo(Vehiculo v){
        this.vehiculo.add(v);
    }
    
}
