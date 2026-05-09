package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FORMATO = 
            DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");

    private int numero;
    private LocalDateTime fechaHoraGeneracion;
    private LocalDateTime fechaHoraActualizacion;
    private Cliente suCliente;
    private ArrayList<PaqueteTuristico> susPaquetesTuristicos;
    private char estado; // 'A' = Activa, 'P' = Pagada, 'C' = Cancelada

    // Constructor
    public Venta(int numero, Cliente suCliente,
            ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.numero = numero;
        this.fechaHoraGeneracion = LocalDateTime.now();
        this.fechaHoraActualizacion = this.fechaHoraGeneracion;
        this.suCliente = suCliente;
        this.susPaquetesTuristicos = (susPaquetesTuristicos != null)
                ? susPaquetesTuristicos : new ArrayList<>();
        this.estado = 'A';
    }

    // Getters
    public int getNumero() { return numero; }
    public LocalDateTime getFechaHoraGeneracion() { return fechaHoraGeneracion; }
    public LocalDateTime getFechaHoraActualizacion() { return fechaHoraActualizacion; }
    public Cliente getSuCliente() { return suCliente; }
    public ArrayList<PaqueteTuristico> getSusPaquetesTuristicos() { return susPaquetesTuristicos; }
    public char getEstado() { return estado; }

    // Setters
    public void setNumero(int numero) { this.numero = numero; }
    public void setSuCliente(Cliente suCliente) { this.suCliente = suCliente; }
    public void setSusPaquetesTuristicos(ArrayList<PaqueteTuristico> paquetes) {
        this.susPaquetesTuristicos = paquetes;
    }
    public void setEstado(char estado) {
        char e = Character.toUpperCase(estado);
        if (e == 'A' || e == 'P' || e == 'C') {
            this.estado = e;
            this.fechaHoraActualizacion = LocalDateTime.now();
        }
    }

    // Cantidad total de unidades de todos los paquetes
    public int calcularCantidadTotalUnidadesPaquetes() {
        int total = 0;
        for (PaqueteTuristico p : susPaquetesTuristicos) {
            total += p.getCantidadUnidades();
        }
        return total;
    }

    // Suma del valor total de cada paquete
    public int calcularValorTotalPaquetes() {
        int total = 0;
        for (PaqueteTuristico p : susPaquetesTuristicos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    // Descuento = valor total paquetes × (porcentaje descuento cliente / 100)
    public int calcularValorDescuento() {
        return (int) (calcularValorTotalPaquetes()
                * (suCliente.getPorcentajeDescuento() / 100.0));
    }

    // Total a pagar = valor total paquetes - descuento
    public int calcularValorTotalPagar() {
        return calcularValorTotalPaquetes() - calcularValorDescuento();
    }

    // Descripción del estado
    private String descripcionEstado() {
        switch (estado) {
            case 'A': return "Activa";
            case 'P': return "Pagada";
            case 'C': return "Cancelada/Anulada";
            default:  return "Desconocido";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("  Número venta   : ").append(numero).append("\n");
        sb.append("  Generación     : ").append(fechaHoraGeneracion.format(FORMATO)).append("\n");
        sb.append("  Actualización  : ").append(fechaHoraActualizacion.format(FORMATO)).append("\n");
        sb.append("  Estado         : ").append(descripcionEstado()).append("\n");
        sb.append("  --- Cliente ---\n");
        sb.append(suCliente.toString());
        sb.append("  --- Paquetes ---\n");
        sb.append("  Cant. paquetes : ").append(susPaquetesTuristicos.size()).append("\n");
        sb.append("  Total unidades : ").append(calcularCantidadTotalUnidadesPaquetes()).append("\n");
        sb.append("  Valor paquetes : $").append(calcularValorTotalPaquetes()).append("\n");
        sb.append("  Descuento      : $").append(calcularValorDescuento())
                .append(" (").append(suCliente.getPorcentajeDescuento()).append("%)\n");
        sb.append("  Total a pagar  : $").append(calcularValorTotalPagar()).append("\n");

        if (estado == 'A' || estado == 'P') {
            int i = 1;
            for (PaqueteTuristico p : susPaquetesTuristicos) {
                sb.append("  -- Paquete #").append(i++).append(" --\n");
                sb.append(p.toString());
            }
        }
        sb.append("========================================\n");
        return sb.toString();
    }
}
