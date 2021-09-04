package proyecto1prograii;

/**
 * Esta es una subclase de la superclase vehiculo y su funcionalidad es fundamental para el funcionamiento del programa
 * @author Albert Castañeda
 * @version 1.0
 */
public class TicketMoto extends Vehiculo{  
    
    /**
    * Este es un metodo constructor que no recibe parametros
    **/
    public TicketMoto(){
        
    }

    /**
     * Este es un metodo constructor que recibe varios parametros
     * @param noEstacionamiento entero que se utiliza para mandar un paramtero a la superclase
     * @param placa String que se utiliza para mandar un paramtero a la superclase
     * @param horaYFecha tipo Date que se utiliza para mandar un paramtero a la superclase
     * @param precioTarifa double que se utiliza para mandar un paramtero a la superclase
     * @param tipoVehiculo tipo EnumVehiculo que se utiliza para mandar un paramtero a la superclase
     */
    public TicketMoto(int noEstacionamiento, String placa, String horaYFecha, double precioTarifa, TipoVehiculo tipoVehiculo) {
        super(noEstacionamiento, placa, horaYFecha, precioTarifa, tipoVehiculo);
    }

    /**
     * Este es un metodo que se utiliza para hacer el calculo del monto total a pagar para las motos
     * @param precioTarifa double que se utiliza para realizar el calculo del monto total
     * @return double que se utiliza para devolver el monto total a pagar
     */
    public double monto(double precioTarifa){
        
        double monto = precioTarifa-(precioTarifa*0.10);
                
        return monto;
    }    
}