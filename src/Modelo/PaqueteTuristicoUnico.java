package modelo;

import java.util.ArrayList;

public final class PaqueteTuristicoUnico extends PaqueteTuristico {

    private static final long serialVersionUID = 1L;

    private String nombreHotel;
    private String tipoDesayuno;

    public PaqueteTuristicoUnico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String nombreHotel, String tipoDesayuno) {

        super(codigo, nombre, tipologiaTurismo, descripcion, origen,
                ajustarDestinos(susDestinos), hotel, alimentacion, alimentacionTodo,
                vuelo, asistencia, tarifaDia, cantidadUnidades);

        setNombreHotel(nombreHotel);
        setTipoDesayuno(tipoDesayuno);
    }

    public PaqueteTuristicoUnico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String nombreHotel) {

        this(codigo, nombre, tipologiaTurismo, descripcion, origen, susDestinos,
                hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                tarifaDia, cantidadUnidades, nombreHotel, null);
    }

    private static ArrayList<Destino> ajustarDestinos(ArrayList<Destino> destinos) {
        ArrayList<Destino> ajustados = new ArrayList<>();

        if (destinos != null && !destinos.isEmpty()) {
            ajustados.add(destinos.get(0));
        }

        return ajustados;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public String getTipoDesayuno() {
        return tipoDesayuno;
    }

    public void setNombreHotel(String nombreHotel) {
        if (nombreHotel == null || nombreHotel.trim().isEmpty()) {
            this.nombreHotel = "No aplica";
        } else {
            this.nombreHotel = nombreHotel.trim();
        }
    }

    public void setTipoDesayuno(String tipoDesayuno) {
        if (alimentacion && !alimentacionTodo && tipoDesayuno != null && !tipoDesayuno.trim().isEmpty()) {
            this.tipoDesayuno = tipoDesayuno.trim();
        } else {
            this.tipoDesayuno = "No aplica";
        }
    }

    @Override
    public int calcularValorUnidad() {
        return tarifaDia * calcularDuracionTotalDias();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Nombre hotel: ").append(nombreHotel).append("\n");

        if (alimentacion && !alimentacionTodo) {
            sb.append("Tipo desayuno: ").append(tipoDesayuno).append("\n");
        }

        sb.append("Valor unidad: $").append(calcularValorUnidad()).append("\n");
        sb.append("Valor total: $").append(calcularValorTotal()).append("\n");

        return sb.toString();
    }
}