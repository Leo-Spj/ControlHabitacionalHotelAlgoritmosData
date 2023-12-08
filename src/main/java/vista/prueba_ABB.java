package vista;

import algoritmos.ListaDoblePilaCola;
import controlador.HotelControl;
import modelo.Habitacion;

public class prueba_ABB {

    public static void main(String[] args) throws Exception {


        HotelControl hotelControl = new HotelControl();

        hotelControl.agregarHotel("Las Fores", "Pueblo Libre", "Calle las flores 420", "999666999");

        hotelControl.agregarHabitacion(1, 1, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 1, 2, 3, 150);
        hotelControl.agregarHabitacion(1, 3, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 3, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 2, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 1, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 1, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 2, 3, 150);
        hotelControl.agregarHabitacion(1, 3, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 3, 2, 3, 150);


        // Cambio estado, no usar de esta manera, es solo para la prueba
        hotelControl.cambiarEstado(1, 1, "Ocupada");
        hotelControl.cambiarEstado(1, 2, "Ocupada");
        hotelControl.cambiarEstado(1, 7, "Ocupada");

        System.out.println("Las habitaciones se ingresan ordenadamente por piso (por defecto):");
        ListaDoblePilaCola<Habitacion> habitaciones = hotelControl.primerNododeListaEnlazada(1);
        for (int i = 0; i < habitaciones.getTamanio(); i++) {
            System.out.println(habitaciones.obtenerPorIndice(i).getPiso()+"-"+habitaciones.obtenerPorIndice(i).getNumero() + " " + habitaciones.obtenerPorIndice(i).getEstado());
        }

        // Actualización de información de las habitaciones.
        hotelControl.actualizarHabitacion(1, 2 , 1, 7, 2, 110);

        System.out.println("\nRevisando que se haya actualizado la habitacion:");
        for (int i = 0; i < habitaciones.getTamanio(); i++) {
            System.out.println(habitaciones.obtenerPorIndice(i).getPiso()+"-"+habitaciones.obtenerPorIndice(i).getNumero() + " " + habitaciones.obtenerPorIndice(i).getEstado());
        }

        // Por el metodo de atender por Cola
        hotelControl.atenderHabitacionPorCola(1);
        hotelControl.atenderHabitacionPorCola(1);
        // Por el metodo de atender por Pila
        hotelControl.atenderHabitacionPorPila(1);
        hotelControl.atenderHabitacionPorPila(1);


        // Reordeno por Estado
        System.out.println("\nOrdenado por estado:");
        ListaDoblePilaCola<Habitacion> habitacionesPorEstado = hotelControl.ordanadoPorEstado(1);
        for (int i = 0; i < habitacionesPorEstado.getTamanio(); i++) {
            System.out.println(habitacionesPorEstado.obtenerPorIndice(i).getPiso()+"-"+habitacionesPorEstado.obtenerPorIndice(i).getNumero() + " " + habitacionesPorEstado.obtenerPorIndice(i).getEstado());
        }


        System.out.println("\nReporte monto del dia (Habitaciones Ocupadas):");
        int monto = hotelControl.reporteHabitacionesOcupadas(1);
        System.out.println(monto);

        System.out.println("\nReporte monto del dia (Habitaciones Disponibles):");
        int monto2 = hotelControl.reporteHabitacionesDisponibles(1);
        System.out.println(monto2);


    }
}
