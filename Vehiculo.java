package proyecto1prograii;

/**
 * Esta es una super clase de las subclases tipos de vehiculos y su funcionalidad es fundamental para el funcionamiento del programa
 * @author Pablo Garcia
 * @version 1.0
 */
public class Vehiculo extends ArrayVehiculo{
    
    private int noEstacionamiento;
    private String placa;
    private String horaYFecha;
    private double precioTarifa;
    private TipoVehiculo tipoVehiculo;
    
    /**
    * Este es un metodo constructor que no recibe parametros
    **/
    public Vehiculo() {
    }    

    /**
     * Este es un metodo constructor que recibe varios parametros
     * @param noEstacionamiento entero que se utiliza para mandar un paramtero a la superclase
     * @param placa String que se utiliza para mandar un paramtero a la superclase
     * @param horaYFecha tipo Date que se utiliza para mandar un paramtero a la superclase
     * @param precioTarifa double que se utiliza para mandar un paramtero a la superclase
     * @param tipoVehiculo tipo EnumVehiculo que se utiliza para mandar un paramtero a la superclase
     */
    public Vehiculo(int noEstacionamiento, String placa, String horaYFecha, double precioTarifa, TipoVehiculo tipoVehiculo) {
        this.noEstacionamiento = noEstacionamiento;
        this.placa = placa;
        this.horaYFecha = horaYFecha;
        this.precioTarifa = precioTarifa;
        this.tipoVehiculo = tipoVehiculo;
    }
    
    /**
     * Metodo accesor al atributo
     * @return int devuelve cuanto vale el atributo
     */
    public int getNoEstacionamiento() {
        return noEstacionamiento;
    }

    /**
     * Metodo accesor al atributo
     * @return int devuelve cuanto vale el atributo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Metodo accesor al atributo
     * @return int devuelve cuanto vale el atributo
     */
    public String gethoraYFecha() {
        return horaYFecha;
    }

    /**
     * Metodo accesor al atributo
     * @return int devuelve cuanto vale el atributo
     */
    public double getPrecioTarifa() {
        return precioTarifa;
    }

    /**
     * Metodo accesor al atributo
     * @return int devuelve cuanto vale el atributo
     */
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
    
}