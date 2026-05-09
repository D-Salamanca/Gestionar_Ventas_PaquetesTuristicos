package modelo;

import java.util.ArrayList;

public final class PaqueteTuristicoMultiple extends PaqueteTuristico {

    private String obsequio;

    // Constructor
    public PaqueteTuristicoMultiple(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades,
            String obsequio) {
        super(codigo, nombre, tipologiaTurismo, descripcion, origen, susDestinos,
                hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                tarifaDia, cantidadUnidades);
        this.obsequio = obsequio;
    }

    // Getter
    public String getObsequio() { return obsequio; }

    // Setter
    public void setObsequio(String obsequio) { this.obsequio = obsequio; }

    // Valor unidad = (tarifaDia × duración total) + (1% de tarifaDia × cantidad de destinos)
    @Override
    public int calcularValorUnidad() {
        int base = tarifaDia * calcularDuracionTotalDias();
        int incremento = (int) (tarifaDia * 0.01 * susDestinos.size());
        return base + incremento;
    }

    // Retorna el primer destino de la lista
    public Destino obtenerDestinoInicial() {
        if (!susDestinos.isEmpty()) {
            return susDestinos.get(0);
        }
        return null;
    }

    // Retorna el último destino de la lista
    public Destino obtenerDestinoFinal() {
        if (!susDestinos.isEmpty()) {
            return susDestinos.get(susDestinos.size() - 1);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  [Categoría: Múltiple]\n");
        sb.append(super.toString());
        sb.append("  Obsequio       : ").append(obsequio).append("\n");

        Destino inicial = obtenerDestinoInicial();
        Destino finalDest = obtenerDestinoFinal();
        if (inicial != null) {
            sb.append("  Destino inicial: ").append(inicial.getNombreLugar()).append("\n");
        }
        if (finalDest != null) {
            sb.append("  Destino final  : ").append(finalDest.getNombreLugar()).append("\n");
        }
        sb.append("  Valor unidad   : $").append(calcularValorUnidad()).append("\n");
        sb.append("  Valor total    : $").append(calcularValorTotal()).append("\n");
        return sb.toString();
    }
}
