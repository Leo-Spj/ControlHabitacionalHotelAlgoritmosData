package controlador;

import algoritmos.ListaDoblePilaCola;
import modelo.Habitacion;
import modelo.Hotel;

public class HotelControl {

    private int id = 1;
    private ListaDoblePilaCola<Hotel> listaHoteles = new ListaDoblePilaCola<>();

    Hotel hotelBuscar = new Hotel(); // hotel con datos falsos para poder buscarlo en la lista de hoteles mediante Comparator

    public int getCantidadHoteles() {
        return this.listaHoteles.getTamanio();
    }

    public void agregarHotel(String nombre, String distrito, String direccion, String telefono) {
        Hotel nuevoHotel = new Hotel(nombre, distrito, direccion, telefono);
        nuevoHotel.setId(id);
        id++;
        this.listaHoteles.insertarOrdenado(nuevoHotel, new Hotel.ComparadorPorNombre());
    }

    public Hotel hotelEncontrado(int idHotel){
        hotelBuscar.setId(idHotel);

        int indice = listaHoteles.getBusqueda().secuencial(listaHoteles, hotelBuscar, new Hotel.ComparadorPorId());
        return listaHoteles.obtenerPorIndice(indice);
    }

    public void agregarHabitacion(int idHotel, int piso, int numero, int cantidadCamas, double precioDia) {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().agregarHabitacion(piso, numero, cantidadCamas, precioDia);
    }

    public void actualizarHabitacion(int idHotel, int id, int piso, int numero, int cantidadCamas, double precioDia) throws Exception {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().actualizarHabitacion(id, piso, numero, cantidadCamas, precioDia);
    }

    //cambiarEstado
    public void cambiarEstado(int idHotel, int id, String estado) {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().buscarHabitacionporId(id).setEstado(estado);
    }

    public ListaDoblePilaCola<Habitacion> primerNododeListaEnlazada(int idHotel) { // Esta imprime tal cual como se ingreso, sin ordenar
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        return hotelEncontrado.getHabitaciones().getListaHabitaciones();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorPiso(int idHotel) {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        return hotelEncontrado.getHabitaciones().getHabitacionesOrdenadasPorPiso();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorEstado(int idHotel) {
        Hotel hotelEncontrado = hotelEncontrado(idHotel);
        return hotelEncontrado.getHabitaciones().getHabitacionesOrdenadasPorEstado();
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
