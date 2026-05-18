package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PaqueteTuristico implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String codigo;
    protected String nombre;
    protected String tipologiaTurismo;
    protected String descripcion;
    protected String origen;
    protected ArrayList<Destino> susDestinos;
    protected boolean hotel;
    protected boolean alimentacion;
    protected boolean alimentacionTodo;
    protected boolean vuelo;
    protected boolean asistencia;
    protected int tarifaDia;
    protected int cantidadUnidades;

    public PaqueteTuristico(String codigo, String nombre, String tipologiaTurismo,
            String descripcion, String origen, ArrayList<Destino> susDestinos,
            boolean hotel, boolean alimentacion, boolean alimentacionTodo,
            boolean vuelo, boolean asistencia, int tarifaDia, int cantidadUnidades) {

        setCodigo(codigo);
        setNombre(nombre);
        setTipologiaTurismo(tipologiaTurismo);
        setDescripcion(descripcion);
        setOrigen(origen);
        setSusDestinos(susDestinos);
        this.hotel = hotel;
        this.alimentacion = alimentacion;
        this.alimentacionTodo = alimentacionTodo;
        this.vuelo = vuelo;
        this.asistencia = asistencia;
        setTarifaDia(tarifaDia);
        setCantidadUnidades(cantidadUnidades);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipologiaTurismo() {
        return tipologiaTurismo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public ArrayList<Destino> getSusDestinos() {
        return susDestinos;
    }

    public boolean isHotel() {
        return hotel;
    }

    public boolean isAlimentacion() {
        return alimentacion;
    }

    public boolean isAlimentacionTodo() {
        return alimentacionTodo;
    }

    public boolean isVuelo() {
        return vuelo;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public int getTarifaDia() {
        return tarifaDia;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            this.codigo = "SIN-CODIGO";
        } else {
            this.codigo = codigo.trim();
        }
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 10) {
            this.nombre = "Paquete sin nombre";
        } else {
            this.nombre = nombre.trim();
        }
    }

    public void setTipologiaTurismo(String tipologiaTurismo) {
        if (tipologiaTurismo == null || tipologiaTurismo.trim().isEmpty()) {
            this.tipologiaTurismo = "General";
        } else {
            this.tipologiaTurismo = tipologiaTurismo.trim();
        }
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null) {
            this.descripcion = "";
        } else if (descripcion.length() > 500) {
            this.descripcion = descripcion.substring(0, 500);
        } else {
            this.descripcion = descripcion;
        }
    }

    public void setOrigen(String origen) {
        if (origen == null || origen.trim().isEmpty()) {
            this.origen = "Sin origen";
        } else {
            this.origen = origen.trim();
        }
    }

    public void setSusDestinos(ArrayList<Destino> susDestinos) {
        if (susDestinos == null) {
            this.susDestinos = new ArrayList<>();
        } else {
            this.susDestinos = susDestinos;
        }
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public void setAlimentacion(boolean alimentacion) {
        this.alimentacion = alimentacion;
    }

    public void setAlimentacionTodo(boolean alimentacionTodo) {
        this.alimentacionTodo = alimentacionTodo;
    }

    public void setVuelo(boolean vuelo) {
        this.vuelo = vuelo;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void setTarifaDia(int tarifaDia) {
        this.tarifaDia = Math.max(1, tarifaDia);
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = Math.max(1, cantidadUnidades);
    }

    public int calcularDuracionTotalDias() {
        int total = 0;

        for (Destino destino : susDestinos) {
            total += destino.getDiasPermanencia();
        }

        return total;
    }

    public abstract int calcularValorUnidad();

    public int calcularValorTotal() {
        return calcularValorUnidad() * cantidadUnidades;
    }

    public String obtenerCategoria() {
        if (this instanceof PaqueteTuristicoUnico) {
            return "Unico";
        }
        if (this instanceof PaqueteTuristicoMultiple) {
            return "Multiple";
        }
        return "Sin categoria";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Categoria: ").append(obtenerCategoria()).append("\n");
        sb.append("Codigo: ").append(codigo).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Tipologia: ").append(tipologiaTurismo).append("\n");
        sb.append("Descripcion: ").append(descripcion).append("\n");
        sb.append("Origen: ").append(origen).append("\n");
        sb.append("Hotel: ").append(hotel ? "Si" : "No").append("\n");
        sb.append("Alimentacion: ");

        if (!alimentacion) {
            sb.append("No incluida\n");
        } else if (alimentacionTodo) {
            sb.append("Todo incluido\n");
        } else {
            sb.append("Solo desayuno\n");
        }

        sb.append("Vuelo: ").append(vuelo ? "Si" : "No").append("\n");
        sb.append("Asistencia: ").append(asistencia ? "Si" : "No").append("\n");
        sb.append("Tarifa dia: $").append(tarifaDia).append("\n");
        sb.append("Cantidad unidades: ").append(cantidadUnidades).append("\n");
        sb.append("Duracion total dias: ").append(calcularDuracionTotalDias()).append("\n");

        sb.append("Destinos:\n");
        for (Destino destino : susDestinos) {
            sb.append("-----------------------------\n");
            sb.append(destino.toString());
        }

        return sb.toString();
    }
}