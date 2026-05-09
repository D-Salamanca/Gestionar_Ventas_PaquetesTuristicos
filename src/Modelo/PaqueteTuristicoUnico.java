package modelo;

import java.util.ArrayList;

public final class PaqueteTuristicoUnico extends PaqueteTuristico {

    private String nombreHotel;
    private String tipoDesayuno; // opcional, solo si alimentación está incluida

    // Constructor completo (con desayuno)
    public PaqueteTuristicoUnico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String nombreHotel, String tipoDesayuno) {
        super(codigo, nombre, tipologiaTurismo, descripcion, origen, susDestinos,
                hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                tarifaDia, cantidadUnidades);
        this.nombreHotel = nombreHotel;
        this.tipoDesayuno = (alimentacion) ? tipoDesayuno : null;
    }

    // Constructor sin tipo de desayuno (cuando la alimentación no está incluida)
    public PaqueteTuristicoUnico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String nombreHotel) {
        super(codigo, nombre, tipologiaTurismo, descripcion, origen, susDestinos,
                hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                tarifaDia, cantidadUnidades);
        this.nombreHotel = nombreHotel;
        this.tipoDesayuno = null;
    }

    // Getters
    public String getNombreHotel() { return nombreHotel; }
    public String getTipoDesayuno() { return tipoDesayuno; }

    // Setters
    public void setNombreHotel(String nombreHotel) { this.nombreHotel = nombreHotel; }
    public void setTipoDesayuno(String tipoDesayuno) {
        if (alimentacion) this.tipoDesayuno = tipoDesayuno;
    }

    // Valor unidad = tarifa día × duración total en días
    @Override
    public int calcularValorUnidad() {
        return tarifaDia * calcularDuracionTotalDias();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  [Categoría: Único]\n");
        sb.append(super.toString());
        sb.append("  Hotel nombre   : ").append(nombreHotel).append("\n");
        if (alimentacion && tipoDesayuno != null) {
            sb.append("  Tipo desayuno  : ").append(tipoDesayuno).append("\n");
        }
        sb.append("  Valor unidad   : $").append(calcularValorUnidad()).append("\n");
        sb.append("  Valor total    : $").append(calcularValorTotal()).append("\n");
        return sb.toString();
    }
}
