package modelo;

import java.io.Serializable;
import java.util.LinkedList;

public class Destino implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreLugar;
    private int diasPermanencia; // mínimo 1
    private LinkedList<String> atractivos;
    private boolean atractivosIncluidos;

    // Constructor
    public Destino(String nombreLugar, int diasPermanencia,
            LinkedList<String> atractivos, boolean atractivosIncluidos) {
        this.nombreLugar = nombreLugar;
        this.diasPermanencia = (diasPermanencia >= 1) ? diasPermanencia : 1;
        this.atractivos = (atractivos != null) ? atractivos : new LinkedList<>();
        this.atractivosIncluidos = atractivosIncluidos;
    }

    // Getters
    public String getNombreLugar() {
        return nombreLugar;
    }

    public int getDiasPermanencia() {
        return diasPermanencia;
    }

    public LinkedList<String> getAtractivos() {
        return atractivos;
    }

    public boolean isAtractivosIncluidos() {
        return atractivosIncluidos;
    }

    // Setters
    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public void setDiasPermanencia(int diasPermanencia) {
        if (diasPermanencia >= 1) {
            this.diasPermanencia = diasPermanencia;
        }
    }

    public void setAtractivos(LinkedList<String> atractivos) {
        this.atractivos = atractivos;
    }

    public void setAtractivosIncluidos(boolean atractivosIncluidos) {
        this.atractivosIncluidos = atractivosIncluidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Lugar       : ").append(nombreLugar).append("\n");
        sb.append("  Días        : ").append(diasPermanencia).append("\n");
        sb.append("  Atractivos  : ").append(atractivosIncluidos ? "Incluidos" : "Opcionales").append("\n");
        if (!atractivos.isEmpty()) {
            sb.append("  Lista       : ").append(String.join(", ", atractivos)).append("\n");
        }
        return sb.toString();
    }
}
