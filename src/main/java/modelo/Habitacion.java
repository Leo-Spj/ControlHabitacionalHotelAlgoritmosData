package modelo;

import java.util.Comparator;

public class Habitacion {

    private int id;
    private int numero;
    private int piso;
    private int cantidadCamas;
    private double precioDia;
    private String estado; // estado(disponible, ocupada, disponible-limpieza , en ocupada-limpieza)

    public Habitacion( int piso, int numero, int cantidadCamas, double precioDia) {
        this.piso = piso;
        this.numero = numero;
        this.cantidadCamas = cantidadCamas;
        this.precioDia = precioDia;
        this.estado = "Disponible";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", piso=" + piso +
                ", numero=" + numero +
                ", cantidadCamas=" + cantidadCamas +
                ", precioDia=" + precioDia +
                ", estado='" + estado + '\'' +
                '}';
    }

    public boolean comparar(Habitacion habitacion, Comparator<Habitacion> comparador) {
        return comparador.compare(this, habitacion) == 0;
    }

    public static class ComparadorPorId implements Comparator<Habitacion> {
        @Override
        public int compare(Habitacion o1, Habitacion o2) {
            int id1 = o1.getId();
            int id2 = o2.getId();

            return id1 - id2;
        }
    }

    public static class ComparadorPorPiso implements Comparator<Habitacion> {
    @Override
    public int compare(Habitacion o1, Habitacion o2) {
        int piso1 = o1.getPiso();
        int piso2 = o2.getPiso();
        int numero1 = o1.getNumero();
        int numero2 = o2.getNumero();

        if (piso1 == piso2) {
            return numero1 - numero2;
        } else {
            return piso1 - piso2;
        }
    }
}

    public static class ComparadorPorEstado implements Comparator<Habitacion> {
        @Override
        public int compare(Habitacion o1, Habitacion o2) {
            String estado1 = o1.getEstado();
            String estado2 = o2.getEstado();

            return estado1.compareTo(estado2);
        }
    }
}
