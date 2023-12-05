package controlador;

import algoritmos.Busqueda;
import algoritmos.Ordenamiento;
import modelo.Habitacion;
import algoritmos.ListaDoblePilaCola;

public class HabitacionControl {

    private int id = 1;

    private ListaDoblePilaCola<Habitacion> listaHabitaciones = new ListaDoblePilaCola<>();
    private Ordenamiento<Habitacion> ordenamiento = new Ordenamiento<>();
    Busqueda<Habitacion> busqueda = new Busqueda<>();


    public int getCantidadHabitaciones() {
        return listaHabitaciones.getTamanio();
    }

    public void agregarHabitacion(int numero, int piso, int cantidadCamas, double precioDia) {
        Habitacion habitacion = new Habitacion(numero, piso, cantidadCamas, precioDia);
        habitacion.setId(id);
        id++;
        listaHabitaciones.insertarOrdenado(habitacion, new Habitacion.ComparadorPorPiso()); // se ingresan ordenados por piso <---
    }

    public ListaDoblePilaCola<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorPiso() {
        return ordenamiento.porInsercion(listaHabitaciones, new Habitacion.ComparadorPorPiso());
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorEstado() {
        return ordenamiento.deBurbuja(listaHabitaciones, new Habitacion.ComparadorPorEstado());
    }



    /*
    Generar una cola de habitaciones disponibles. Con dos tipos de atención: uno tipo
    cola y otro tipo pila.
    */

    // cuando se antiende una habitacion por cola la primera habitacion que esté disponible es la que se atiende y cambia su estado a ocupada
    public void atenderHabitacionPorCola(){
        // ordenar las habitaciones por piso
        ListaDoblePilaCola<Habitacion> habitacionesOrdenadasPorPiso = ordenamiento.porInsercion(listaHabitaciones, new Habitacion.ComparadorPorPiso());

        // creo esta habitacion ficticia para usar el metodo de busqueda
        Habitacion habitacion = new Habitacion(0,0,0,0);
        habitacion.setEstado("Disponible");

        // usando el metodo de busqueda
        int indice = busqueda.secuencial(habitacionesOrdenadasPorPiso, habitacion, new Habitacion.ComparadorPorEstado());
        if (indice != -1) {
            habitacionesOrdenadasPorPiso.obtenerIndice(indice).setEstado("Ocupada");
        }
    }

    public void atenderHabitacionPorPila(){
        ListaDoblePilaCola<Habitacion> habitacionesOrdenadasPorPiso = ordenamiento.porInsercion(listaHabitaciones, new Habitacion.ComparadorPorPiso());

        Habitacion habitacion = new Habitacion(0,0,0,0);
        habitacion.setEstado("Disponible");

        // uso la busqueda en reversa
        int indice = busqueda.secuencialReversa(habitacionesOrdenadasPorPiso, habitacion, new Habitacion.ComparadorPorEstado());
        if (indice != -1) {
            habitacionesOrdenadasPorPiso.obtenerIndice(indice).setEstado("Ocupada");
        }
    }

    public int reporteHabitacionesOcupadas(){
        int monto = 0;

        for (int i = 0; i < listaHabitaciones.getTamanio(); i++ ){
            if(listaHabitaciones.obtenerIndice(i).getEstado().equals("Ocupada") || listaHabitaciones.obtenerIndice(i).getEstado().equals("Ocupada-limpieza"))
                monto += listaHabitaciones.obtenerIndice(i).getPrecioDia();
        }
        return monto;
    }

    public int reporteHabitacionesDisponibles(){
        int monto = 0;

        for (int i = 0; i < listaHabitaciones.getTamanio(); i++ ){
            if(listaHabitaciones.obtenerIndice(i).getEstado().equals("Disponible") || listaHabitaciones.obtenerIndice(i).getEstado().equals("Disponible-limpieza"))
                monto += listaHabitaciones.obtenerIndice(i).getPrecioDia();
        }
        return monto;
    }


    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + listaHabitaciones.getPrimero().toString() +
                '}';
    }
}
