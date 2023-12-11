package controlador;

import modelo.Habitacion;
import algoritmos.ListaDoblePilaCola;
import modelo.MetodosComunes;

public class HabitacionControl extends MetodosComunes<Habitacion> {

    private int id = 1;

    private ListaDoblePilaCola<Habitacion> listaHabitaciones = new ListaDoblePilaCola<>();


    public int getCantidadHabitaciones() {
        return listaHabitaciones.getTamanio();
    }

    public void agregarHabitacion(int piso, int numero, int cantidadCamas, double precioDia) {
        Habitacion habitacion = new Habitacion(piso, numero, cantidadCamas, precioDia);
        habitacion.setId(id);
        id++;
        listaHabitaciones.insertarOrdenado(habitacion, new Habitacion.ComparadorPorPiso()); // se ingresan ordenados por piso <---
    }

    public void actualizarHabitacion(int id, int piso, int numero, int cantidadCamas, double precioDia) throws Exception {
        Habitacion habitacion = new Habitacion(piso, numero, cantidadCamas, precioDia);
        habitacion.setId(id);

        int indice = getBusqueda().secuencial(listaHabitaciones, habitacion, new Habitacion.ComparadorPorId());
        Habitacion habitacionEncontrada = listaHabitaciones.obtenerPorIndice(indice);
        habitacion.setEstado(habitacionEncontrada.getEstado());

        //si hay hay una habitacion con el mismo piso y puerta lazan una excepcion, a no ser que sea la misma habitacion
        if (indice != -1) {
            for (int i = 0; i < listaHabitaciones.getTamanio(); i++) {
                if (listaHabitaciones.obtenerPorIndice(i).getPiso() == piso
                        && listaHabitaciones.obtenerPorIndice(i).getNumero() == numero
                        && listaHabitaciones.obtenerPorIndice(i).getId() != id) {
                    throw new Exception("Ya existe una habitacion con el mismo piso y numero");
                }
            }
            // si no hay una habitacion con el mismo piso y puerta, actualiza la habitacion
            listaHabitaciones.actualizar(habitacion, new Habitacion.ComparadorPorId());

        }
    }

    public Habitacion buscarHabitacionporId(int id) {
        Habitacion habitacion = new Habitacion(0,0,0,0);
        habitacion.setId(id);
        getOrdenamiento().deBurbuja(listaHabitaciones, new Habitacion.ComparadorPorId());
        int indice = getBusqueda().binaria(listaHabitaciones, habitacion, new Habitacion.ComparadorPorId());
        return listaHabitaciones.obtenerPorIndice(indice);
    }

    public ListaDoblePilaCola<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }
    public void setListaHabitaciones(ListaDoblePilaCola<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorPiso() {
        return getOrdenamiento().porInsercion(listaHabitaciones, new Habitacion.ComparadorPorPiso());
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorEstado() {
        return getOrdenamiento().deBurbuja(listaHabitaciones, new Habitacion.ComparadorPorEstado());
    }



    /*
    Generar una cola de habitaciones disponibles. Con dos tipos de atención: uno tipo
    cola y otro tipo pila.
    */

    // cuando se antiende una habitacion por cola la primera habitacion que esté disponible es la que se atiende y cambia su estado a ocupada
    public void atenderHabitacionPorCola(){

        // creo esta habitacion ficticia para usar el metodo de busqueda
        Habitacion habitacion = new Habitacion(0,0,0,0);
        habitacion.setEstado("Disponible");

        // usando el metodo de busqueda
        int indice = getBusqueda().secuencial(listaHabitaciones, habitacion, new Habitacion.ComparadorPorEstado());
        if (indice != -1) {
            listaHabitaciones.obtenerPorIndice(indice).setEstado("Ocupada");
        }
    }

    public void atenderHabitacionPorPila(){
        Habitacion habitacion = new Habitacion(0,0,0,0);
        habitacion.setEstado("Disponible");

        // uso la busqueda en reversa
        int indice = getBusqueda().secuencialReversa(listaHabitaciones, habitacion, new Habitacion.ComparadorPorEstado());
        if (indice != -1) {
            listaHabitaciones.obtenerPorIndice(indice).setEstado("Ocupada");
        }
    }

    public int reporteHabitacionesOcupadas(){
        int monto = 0;

        for (int i = 0; i < listaHabitaciones.getTamanio(); i++ ){
            if(listaHabitaciones.obtenerPorIndice(i).getEstado().equals("Ocupada") || listaHabitaciones.obtenerPorIndice(i).getEstado().equals("Ocupada-limpieza"))
                monto += listaHabitaciones.obtenerPorIndice(i).getPrecioDia();
        }
        return monto;
    }

    public int reporteHabitacionesDisponibles(){
        int monto = 0;

        for (int i = 0; i < listaHabitaciones.getTamanio(); i++ ){
            if(listaHabitaciones.obtenerPorIndice(i).getEstado().equals("Disponible") || listaHabitaciones.obtenerPorIndice(i).getEstado().equals("Disponible-limpieza"))
                monto += listaHabitaciones.obtenerPorIndice(i).getPrecioDia();
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
