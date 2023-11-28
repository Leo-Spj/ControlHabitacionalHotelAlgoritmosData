package controlador;

import algoritmos.ListaEnlazadaDoble;
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

    // preorden
    public ListaEnlazadaDoble<Habitacion> imprimirArbolPreorden() {
        return this.hotel.getHabitaciones().obtenerHabitacionesInOrden();
    }


}
