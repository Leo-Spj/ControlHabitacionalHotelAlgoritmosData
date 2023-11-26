package modelo;

public class Habitacion {
    private int numero;
    private int piso;
    private int cantidadCamas;
    private double precioDia;
    private String estado; // estado(disponible, en uso, disponible-limpieza , en uso-limpieza)

    public Habitacion(int numero, int piso, int cantidadCamas, double precioDia) {
        this.numero = numero;
        this.piso = piso;
        this.cantidadCamas = cantidadCamas;
        this.precioDia = precioDia;
        this.estado = "disponible";
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
                "numero=" + numero +
                ", piso=" + piso +
                ", cantidadCamas=" + cantidadCamas +
                ", precioDia=" + precioDia +
                ", estado='" + estado + '\'' +
                '}';
    }
}
