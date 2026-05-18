package modelo;

import java.util.ArrayList;

public final class PaqueteTuristicoMultiple extends PaqueteTuristico {

    private static final long serialVersionUID = 1L;

    private String obsequio;

    public PaqueteTuristicoMultiple(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String obsequio) {

        super(codigo, nombre, tipologiaTurismo, descripcion, origen,
                ajustarDestinos(susDestinos), hotel, alimentacion, alimentacionTodo,
                vuelo, asistencia, tarifaDia, cantidadUnidades);

        setObsequio(obsequio);
    }

    private static ArrayList<Destino> ajustarDestinos(ArrayList<Destino> destinos) {
        if (destinos == null) {
            return new ArrayList<>();
        }
        return destinos;
    }

    public String getObsequio() {
        return obsequio;
    }

    public void setObsequio(String obsequio) {
        if (obsequio == null || obsequio.trim().isEmpty()) {
            this.obsequio = "Sin obsequio";
        } else {
            this.obsequio = obsequio.trim();
        }
    }

    @Override
    public int calcularValorUnidad() {
        int base = tarifaDia * calcularDuracionTotalDias();
        int incremento = (int) (tarifaDia * 0.01 * susDestinos.size());
        return base + incremento;
    }

    public Destino obtenerDestinoInicial() {
        if (susDestinos.isEmpty()) {
            return null;
        }
        return susDestinos.get(0);
    }

    public Destino obtenerDestinoFinal() {
        if (susDestinos.isEmpty()) {
            return null;
        }
        return susDestinos.get(susDestinos.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Obsequio: ").append(obsequio).append("\n");

        Destino inicial = obtenerDestinoInicial();
        Destino destinoFinal = obtenerDestinoFinal();

        if (inicial != null) {
            sb.append("Destino inicial: ").append(inicial.getNombreLugar()).append("\n");
        }

        if (destinoFinal != null) {
            sb.append("Destino final: ").append(destinoFinal.getNombreLugar()).append("\n");
        }

        sb.append("Valor unidad: $").append(calcularValorUnidad()).append("\n");
        sb.append("Valor total: $").append(calcularValorTotal()).append("\n");

        return sb.toString();
    }
}