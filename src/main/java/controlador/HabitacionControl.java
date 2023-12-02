package controlador;

import algoritmos.ArbolBinario;
import modelo.Habitacion;
import algoritmos.ListaDoblePilaCola;

public class HabitacionControl {

    private int id = 1;

    private ArbolBinario<Habitacion> arbol_porPiso = new ArbolBinario<Habitacion>(new Habitacion.ComparadorPorPiso());
    private ArbolBinario<Habitacion> arbol_porEstado= new ArbolBinario<Habitacion>(new Habitacion.ComparadorPorEstado());


    public int getCantidadHabitaciones() {
        return this.arbol_porPiso.getLista().getTamanio();
    }


    public void agregarHabitacionABB(Habitacion habitacion) {
        arbol_porPiso.insertar(habitacion);
        arbol_porEstado.insertar(habitacion);
        habitacion.setId(id);
        id++;
    }

    //modificar estado
    public void modificarEstado(int id, String estado) {
        Habitacion habitacion = arbol_porPiso.getLista().obtener(id);
        habitacion.setEstado(estado);
    }



    // inorden
    public ListaDoblePilaCola<Habitacion> obtenerPorPisoInOrden() {
        return arbol_porPiso.inorden();
    }
    public ListaDoblePilaCola<Habitacion> obtenerPorEstadoInOrden() {
        return arbol_porEstado.inorden();
    }

    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + arbol_porPiso.getLista().toString() +
                '}';
    }

}
