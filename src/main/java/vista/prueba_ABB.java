package vista;

import algoritmos.ListaDoblePilaCola;
import controlador.HotelControl;
import modelo.Habitacion;

public class prueba_ABB {

    public static void main(String[] args) throws Exception {

        // creo un hotel y sus respectivas habitaciones, que seran los nodos del arbol

        HotelControl hotel = new HotelControl("Hotel", "Lima", "Av. Los Alamos", "123456789");
        hotel.agregarHabitacion(1, 1, 1, 100);
        hotel.agregarHabitacion(1, 2, 3, 150);
        hotel.agregarHabitacion(3, 4, 2, 120);
        hotel.agregarHabitacion(3, 3, 1, 100);
        hotel.agregarHabitacion(2, 4, 2, 120);
        hotel.agregarHabitacion(2, 3, 1, 100);
        hotel.agregarHabitacion(1, 4, 2, 120);
        hotel.agregarHabitacion(1, 3, 1, 100);
        hotel.agregarHabitacion(2, 1, 1, 100);
        hotel.agregarHabitacion(2, 2, 3, 150);
        hotel.agregarHabitacion(3, 1, 1, 100);
        hotel.agregarHabitacion(3, 2, 3, 150);


        // Cambio estado, no usar de esta manera, es solo para la prueba
        hotel.cambiarEstado(1, "Ocupada");
        hotel.cambiarEstado(2, "Ocupada");
        hotel.cambiarEstado(7, "Ocupada");

        System.out.println("Las habitaciones se ingresan ordenadamente por piso (por defecto):");
        ListaDoblePilaCola<Habitacion> habitaciones = hotel.primerNododeListaEnlazada();
        for (int i = 0; i < habitaciones.getTamanio(); i++) {
            System.out.println(habitaciones.obtenerPorIndice(i).getPiso()+"-"+habitaciones.obtenerPorIndice(i).getNumero() + " " + habitaciones.obtenerPorIndice(i).getEstado());
        }

        // Actualización de información de las habitaciones.
        hotel.actualizarHabitacion(1 , 1, 1, 2, 110);

        System.out.println("\nRevisando que se haya actualizado la habitacion:");
        for (int i = 0; i < habitaciones.getTamanio(); i++) {
            System.out.println(habitaciones.obtenerPorIndice(i).getPiso()+"-"+habitaciones.obtenerPorIndice(i).getNumero() + " " + habitaciones.obtenerPorIndice(i).getEstado());
        }

        // Por el metodo de atender por Cola
        hotel.atenderHabitacionPorCola();
        hotel.atenderHabitacionPorCola();
        // Por el metodo de atender por Pila
        hotel.atenderHabitacionPorPila();
        hotel.atenderHabitacionPorPila();


        // Reordeno por Estado
        System.out.println("\nOrdenado por estado:");
        ListaDoblePilaCola<Habitacion> habitacionesPorEstado = hotel.ordanadoPorEstado();
        for (int i = 0; i < habitacionesPorEstado.getTamanio(); i++) {
            System.out.println(habitacionesPorEstado.obtenerPorIndice(i).getPiso()+"-"+habitacionesPorEstado.obtenerPorIndice(i).getNumero() + " " + habitacionesPorEstado.obtenerPorIndice(i).getEstado());
        }


        System.out.println("\nReporte monto del dia (Habitaciones Ocupadas):");
        int monto = hotel.reporteHabitacionesOcupadas();
        System.out.println(monto);

        System.out.println("\nReporte monto del dia (Habitaciones Disponibles):");
        int monto2 = hotel.reporteHabitacionesDisponibles();
        System.out.println(monto2);


    }
}
