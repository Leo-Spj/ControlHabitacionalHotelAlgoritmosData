package controlador;

import algoritmos.ListaDoblePilaCola;
import modelo.Hotel;

public class HotelControl extends MetodosComunes<Hotel> {

    private int id = 1;
    private ListaDoblePilaCola<Hotel> listaHoteles = new ListaDoblePilaCola<>();

    // solo sirve para comparar, no tocar
    private Hotel hotelBuscar = new Hotel(); // hotel con datos falsos para poder buscarlo en la lista de hoteles mediante Comparator


    public ListaDoblePilaCola<Hotel> getListaHoteles() {
        return listaHoteles;
    }
    public int getCantidadHoteles() {
        return this.listaHoteles.getTamanio();
    }

    public int agregarHotel(String nombre, String distrito, String direccion, String telefono) {
        Hotel nuevoHotel = new Hotel(nombre, distrito, direccion, telefono);
        nuevoHotel.setId(id);
        id++;
        this.listaHoteles.insertarOrdenado(nuevoHotel, new Hotel.ComparadorPorNombre());

        return nuevoHotel.getId();
    }

    public Hotel hotelEncontrado(int idHotel){
        hotelBuscar.setId(idHotel);

        int indice = getBusqueda().secuencial(this.listaHoteles, hotelBuscar, new Hotel.ComparadorPorId());
        return this.listaHoteles.obtenerPorIndice(indice);
    }

    public void agregarHabitacion(int idHotel, int piso, int numero, int cantidadCamas, double precioDia) throws Exception {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().agregarHabitacion(piso, numero, cantidadCamas, precioDia);
    }

    public void actualizarHabitacion(int idHotel, int id, int piso, int numero, int cantidadCamas, double precioDia) throws Exception {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().actualizarHabitacion(id, piso, numero, cantidadCamas, precioDia);
    }

    public void atenderHabitacionPorCola(int idHotel){
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().atenderHabitacionPorCola();
    }

    public void atenderHabitacionPorPila(int idHotel) {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().atenderHabitacionPorPila();
    }

    public int reporteHabitacionesOcupadas(int idHotel){
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        return hotelEncontrado.getHabitaciones().reporteHabitacionesOcupadas();
    }

    public int reporteHabitacionesDisponibles(int idHotel){
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        return hotelEncontrado.getHabitaciones().reporteHabitacionesDisponibles();
    }


}
