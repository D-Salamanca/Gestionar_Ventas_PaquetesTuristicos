package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private char tipoIdentificacion;
    private String numeroIdentificacion;
    private boolean empresa;
    private String nombre;
    private String email;
    private String telefono;
    private String nombreContacto;
    private double porcentajeDescuento;

    public Cliente(char tipoIdentificacion, String numeroIdentificacion, boolean empresa,
            String nombre, String email, String telefono, String nombreContacto,
            double porcentajeDescuento) {

        setTipoIdentificacion(tipoIdentificacion);
        setNumeroIdentificacion(numeroIdentificacion);
        this.empresa = empresa;
        this.nombre = validarTexto(nombre, "Sin nombre");
        this.email = validarTexto(email, "Sin email");
        this.telefono = validarTexto(telefono, "Sin telefono");
        this.nombreContacto = validarTexto(nombreContacto, this.nombre);
        setPorcentajeDescuento(porcentajeDescuento);
    }

    private String validarTexto(String texto, String valorDefecto) {
        if (texto == null || texto.trim().isEmpty()) {
            return valorDefecto;
        }
        return texto.trim();
    }

    public char getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public boolean isEmpresa() {
        return empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setTipoIdentificacion(char tipoIdentificacion) {
        char tipo = Character.toUpperCase(tipoIdentificacion);
        if (tipo != 'C' && tipo != 'N') {
            tipo = 'C';
        }
        this.tipoIdentificacion = tipo;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        if (numeroIdentificacion == null) {
            this.numeroIdentificacion = "000000";
            return;
        }

        String numero = numeroIdentificacion.trim();

        if (tipoIdentificacion == 'C') {
            if (!numero.matches("\\d{6,}")) {
                numero = "000000";
            }
        } else if (tipoIdentificacion == 'N') {
            if (!numero.matches("\\d{9}")) {
                numero = "000000000";
            }
        }

        this.numeroIdentificacion = numero;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "Sin nombre");
    }

    public void setEmail(String email) {
        this.email = validarTexto(email, "Sin email");
    }

    public void setTelefono(String telefono) {
        this.telefono = validarTexto(telefono, "Sin telefono");
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = validarTexto(nombreContacto, this.nombre);
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0.0) {
            this.porcentajeDescuento = 0.0;
        } else if (porcentajeDescuento > 70.0) {
            this.porcentajeDescuento = 70.0;
        } else {
            this.porcentajeDescuento = porcentajeDescuento;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo ID: ").append(tipoIdentificacion == 'C' ? "Cedula" : "NIT").append("\n");
        sb.append("Numero ID: ").append(numeroIdentificacion).append("\n");
        sb.append("Tipo cliente: ").append(empresa ? "Empresa" : "Persona natural").append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Telefono: ").append(telefono).append("\n");
        sb.append("Contacto: ").append(nombreContacto).append("\n");
        sb.append("Descuento: ").append(porcentajeDescuento).append("%\n");
        return sb.toString();
    }
}