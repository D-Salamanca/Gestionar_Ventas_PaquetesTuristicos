package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private char tipoIdentificacion;   // 'C' = cédula, 'N' = NIT
    private String numeroIdentificacion; // cédula: mín 6 dígitos, NIT: 9 dígitos
    private boolean empresa;
    private String nombre;             // nombre completo o razón social
    private String email;
    private String telefono;
    private String nombreContacto;
    private double porcentajeDescuento; // 0.0 a 70.0

    // Constructor
    public Cliente(char tipoIdentificacion, String numeroIdentificacion,
            boolean empresa, String nombre, String email,
            String telefono, String nombreContacto, double porcentajeDescuento) {
        this.tipoIdentificacion = Character.toUpperCase(tipoIdentificacion);
        this.numeroIdentificacion = numeroIdentificacion;
        this.empresa = empresa;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.porcentajeDescuento = Math.max(0.0, Math.min(70.0, porcentajeDescuento));
    }

    // Getters
    public char getTipoIdentificacion() { return tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public boolean isEmpresa() { return empresa; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getNombreContacto() { return nombreContacto; }
    public double getPorcentajeDescuento() { return porcentajeDescuento; }

    // Setters
    public void setTipoIdentificacion(char tipoIdentificacion) {
        this.tipoIdentificacion = Character.toUpperCase(tipoIdentificacion);
    }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public void setEmpresa(boolean empresa) { this.empresa = empresa; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = Math.max(0.0, Math.min(70.0, porcentajeDescuento));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Tipo ID        : ").append(tipoIdentificacion == 'C' ? "Cédula" : "NIT").append("\n");
        sb.append("  Número ID      : ").append(numeroIdentificacion).append("\n");
        sb.append("  Tipo cliente   : ").append(empresa ? "Empresa" : "Persona natural").append("\n");
        sb.append("  Nombre         : ").append(nombre).append("\n");
        sb.append("  Email          : ").append(email).append("\n");
        sb.append("  Teléfono       : ").append(telefono).append("\n");
        sb.append("  Contacto       : ").append(nombreContacto).append("\n");
        sb.append("  Descuento      : ").append(porcentajeDescuento).append("%\n");
        return sb.toString();
    }
}
