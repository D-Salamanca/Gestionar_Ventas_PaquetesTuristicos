package modelo;

import java.io.Serializable;
import java.util.LinkedList;

public class Destino implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreLugar;
    private int diasPermanencia;
    private LinkedList<String> atractivos;
    private boolean atractivosIncluidos;

    public Destino(String nombreLugar, int diasPermanencia,
            LinkedList<String> atractivos, boolean atractivosIncluidos) {

        setNombreLugar(nombreLugar);
        setDiasPermanencia(diasPermanencia);
        setAtractivos(atractivos);
        this.atractivosIncluidos = atractivosIncluidos;
    }

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

    public void setNombreLugar(String nombreLugar) {
        if (nombreLugar == null || nombreLugar.trim().isEmpty()) {
            this.nombreLugar = "Destino sin nombre";
        } else {
            this.nombreLugar = nombreLugar.trim();
        }
    }

    public void setDiasPermanencia(int diasPermanencia) {
        this.diasPermanencia = Math.max(1, diasPermanencia);
    }

    public void setAtractivos(LinkedList<String> atractivos) {
        if (atractivos == null) {
            this.atractivos = new LinkedList<>();
        } else {
            this.atractivos = atractivos;
        }
    }

    public void setAtractivosIncluidos(boolean atractivosIncluidos) {
        this.atractivosIncluidos = atractivosIncluidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lugar: ").append(nombreLugar).append("\n");
        sb.append("Dias permanencia: ").append(diasPermanencia).append("\n");
        sb.append("Atractivos: ").append(atractivosIncluidos ? "Incluidos" : "Opcionales").append("\n");
        sb.append("Lista atractivos: ");

        if (atractivos.isEmpty()) {
            sb.append("Sin atractivos registrados\n");
        } else {
            sb.append(String.join(", ", atractivos)).append("\n");
        }

        return sb.toString();
    }
}