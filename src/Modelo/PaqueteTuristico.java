package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PaqueteTuristico implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String codigo;
    protected String nombre;           // mínimo 10 caracteres
    protected String tipologiaTurismo; // ej. negocios, recreación, educativo, ecológico
    protected String descripcion;      // máximo 500 caracteres
    protected String origen;           // nombre lugar de partida
    protected ArrayList<Destino> susDestinos;
    protected boolean hotel;           // por defecto true
    protected boolean alimentacion;    // por defecto true
    protected boolean alimentacionTodo; // todo o solo desayuno
    protected boolean vuelo;           // por defecto true
    protected boolean asistencia;      // por defecto false
    protected int tarifaDia;           // mayor que cero
    protected int cantidadUnidades;    // mínimo 1

    // Constructor
    public PaqueteTuristico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades) {
        this.codigo = codigo;
        this.nombre = (nombre != null && nombre.length() >= 10) ? nombre : "Sin nombre   ";
        this.tipologiaTurismo = tipologiaTurismo;
        this.descripcion = (descripcion != null && descripcion.length() <= 500) ? descripcion : "";
        this.origen = origen;
        this.susDestinos = (susDestinos != null) ? susDestinos : new ArrayList<>();
        this.hotel = hotel;
        this.alimentacion = alimentacion;
        this.alimentacionTodo = alimentacionTodo;
        this.vuelo = vuelo;
        this.asistencia = asistencia;
        this.tarifaDia = (tarifaDia > 0) ? tarifaDia : 1;
        this.cantidadUnidades = (cantidadUnidades >= 1) ? cantidadUnidades : 1;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTipologiaTurismo() { return tipologiaTurismo; }
    public String getDescripcion() { return descripcion; }
    public String getOrigen() { return origen; }
    public ArrayList<Destino> getSusDestinos() { return susDestinos; }
    public boolean isHotel() { return hotel; }
    public boolean isAlimentacion() { return alimentacion; }
    public boolean isAlimentacionTodo() { return alimentacionTodo; }
    public boolean isVuelo() { return vuelo; }
    public boolean isAsistencia() { return asistencia; }
    public int getTarifaDia() { return tarifaDia; }
    public int getCantidadUnidades() { return cantidadUnidades; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() >= 10) this.nombre = nombre;
    }
    public void setTipologiaTurismo(String tipologiaTurismo) { this.tipologiaTurismo = tipologiaTurismo; }
    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() <= 500) this.descripcion = descripcion;
    }
    public void setOrigen(String origen) { this.origen = origen; }
    public void setSusDestinos(ArrayList<Destino> susDestinos) { this.susDestinos = susDestinos; }
    public void setHotel(boolean hotel) { this.hotel = hotel; }
    public void setAlimentacion(boolean alimentacion) { this.alimentacion = alimentacion; }
    public void setAlimentacionTodo(boolean alimentacionTodo) { this.alimentacionTodo = alimentacionTodo; }
    public void setVuelo(boolean vuelo) { this.vuelo = vuelo; }
    public void setAsistencia(boolean asistencia) { this.asistencia = asistencia; }
    public void setTarifaDia(int tarifaDia) {
        if (tarifaDia > 0) this.tarifaDia = tarifaDia;
    }
    public void setCantidadUnidades(int cantidadUnidades) {
        if (cantidadUnidades >= 1) this.cantidadUnidades = cantidadUnidades;
    }

    // Calcular duración total en días (suma de días de permanencia en cada destino)
    public int calcularDuracionTotalDias() {
        int total = 0;
        for (Destino d : susDestinos) {
            total += d.getDiasPermanencia();
        }
        return total;
    }

    // Método abstracto: cada subclase implementa su propia fórmula
    public abstract int calcularValorUnidad();

    // Valor total = valor unidad × cantidad de unidades
    public int calcularValorTotal() {
        return calcularValorUnidad() * cantidadUnidades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Código         : ").append(codigo).append("\n");
        sb.append("  Nombre         : ").append(nombre).append("\n");
        sb.append("  Tipología      : ").append(tipologiaTurismo).append("\n");
        sb.append("  Origen         : ").append(origen).append("\n");
        sb.append("  Descripción    : ").append(descripcion).append("\n");
        sb.append("  Hotel          : ").append(hotel ? "Sí" : "No").append("\n");
        sb.append("  Alimentación   : ").append(alimentacion ? (alimentacionTodo ? "Todo incluido" : "Solo desayuno") : "No incluida").append("\n");
        sb.append("  Vuelo          : ").append(vuelo ? "Sí" : "No").append("\n");
        sb.append("  Asistencia     : ").append(asistencia ? "Sí" : "No").append("\n");
        sb.append("  Tarifa/día     : $").append(tarifaDia).append("\n");
        sb.append("  Cant. unidades : ").append(cantidadUnidades).append("\n");
        sb.append("  Duración total : ").append(calcularDuracionTotalDias()).append(" días\n");
        sb.append("  Destinos:\n");
        for (Destino d : susDestinos) {
            sb.append(d.toString());
        }
        return sb.toString();
    }
}
