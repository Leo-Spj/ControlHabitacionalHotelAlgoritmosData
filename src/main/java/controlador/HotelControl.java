package controlador;

import algoritmos.ListaDoblePilaCola;
import modelo.Habitacion;
import modelo.Hotel;

public class HotelControl {

    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public HotelControl(String nombre, String distrito, String direccion, String telefono) {
        this.hotel = new Hotel(nombre, distrito, direccion, telefono);
    }

    // ==================  metodos para el arbol ===============================

    public void agregarHabitacionABB(int numero, int piso, int cantidadCamas, double precioDia) {
        Habitacion habitacion = new Habitacion(numero, piso, cantidadCamas, precioDia);
        this.hotel.getHabitaciones().agregarHabitacionABB(habitacion);
    }

    // inorden
    public ListaDoblePilaCola<Habitacion> imprimirArbolInorden_Estado() {
        return this.hotel.getHabitaciones().obtenerPorEstadoInOrden();
    }
    public ListaDoblePilaCola<Habitacion> imprimirArbolInorden_Piso() {
        return this.hotel.getHabitaciones().obtenerPorPisoInOrden();
    }


}
