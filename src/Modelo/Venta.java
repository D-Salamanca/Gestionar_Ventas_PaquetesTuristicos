package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");

    private int numero;
    private LocalDateTime fechaHoraGeneracion;
    private LocalDateTime fechaHoraActualizacion;
    private Cliente suCliente;
    private ArrayList<PaqueteTuristico> susPaquetesTuristicos;
    private char estado;

    public Venta(int numero, Cliente suCliente, ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.numero = numero;
        this.fechaHoraGeneracion = LocalDateTime.now();
        this.fechaHoraActualizacion = this.fechaHoraGeneracion;
        this.suCliente = suCliente;
        this.susPaquetesTuristicos = susPaquetesTuristicos != null ? susPaquetesTuristicos : new ArrayList<>();
        this.estado = 'A';
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public LocalDateTime getFechaHoraActualizacion() {
        return fechaHoraActualizacion;
    }

    public Cliente getSuCliente() {
        return suCliente;
    }

    public ArrayList<PaqueteTuristico> getSusPaquetesTuristicos() {
        return susPaquetesTuristicos;
    }

    public char getEstado() {
        return estado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSuCliente(Cliente suCliente) {
        this.suCliente = suCliente;
    }

    public void setSusPaquetesTuristicos(ArrayList<PaqueteTuristico> susPaquetesTuristicos) {
        this.susPaquetesTuristicos = susPaquetesTuristicos != null ? susPaquetesTuristicos : new ArrayList<>();
    }

    public void setEstado(char estado) {
        char nuevoEstado = Character.toUpperCase(estado);

        if (nuevoEstado == 'A' || nuevoEstado == 'P' || nuevoEstado == 'C') {
            this.estado = nuevoEstado;
            this.fechaHoraActualizacion = LocalDateTime.now();
        }
    }

    public int calcularCantidadTotalUnidadesPaquetes() {
        int total = 0;

        for (PaqueteTuristico paquete : susPaquetesTuristicos) {
            total += paquete.getCantidadUnidades();
        }

        return total;
    }

    public int calcularValorTotalPaquetes() {
        int total = 0;

        for (PaqueteTuristico paquete : susPaquetesTuristicos) {
            total += paquete.calcularValorTotal();
        }

        return total;
    }

    public int calcularValorDescuento() {
        if (suCliente == null) {
            return 0;
        }

        return (int) (calcularValorTotalPaquetes() * (suCliente.getPorcentajeDescuento() / 100.0));
    }

    public int calcularValorTotalPagar() {
        return calcularValorTotalPaquetes() - calcularValorDescuento();
    }

    public String obtenerEstadoTexto() {
        switch (estado) {
            case 'A':
                return "Activa";
            case 'P':
                return "Pagada";
            case 'C':
                return "Cancelada";
            default:
                return "Desconocido";
        }
    }

    public String obtenerResumen() {
        return "Venta #" + numero
                + " | Estado: " + obtenerEstadoTexto()
                + " | Cliente: " + (suCliente != null ? suCliente.getNombre() : "Sin cliente")
                + " | Total: $" + calcularValorTotalPagar();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("============================================================\n");
        sb.append("VENTA NUMERO: ").append(numero).append("\n");
        sb.append("Fecha generacion: ").append(fechaHoraGeneracion.format(FORMATO)).append("\n");
        sb.append("Fecha actualizacion: ").append(fechaHoraActualizacion.format(FORMATO)).append("\n");
        sb.append("Estado: ").append(obtenerEstadoTexto()).append("\n");

        sb.append("\nCLIENTE\n");
        sb.append("------------------------------------------------------------\n");
        if (suCliente != null) {
            sb.append(suCliente.toString());
        } else {
            sb.append("Sin cliente asignado\n");
        }

        sb.append("\nRESUMEN DE PAQUETES\n");
        sb.append("------------------------------------------------------------\n");
        sb.append("Cantidad paquetes: ").append(susPaquetesTuristicos.size()).append("\n");
        sb.append("Cantidad total unidades: ").append(calcularCantidadTotalUnidadesPaquetes()).append("\n");
        sb.append("Valor total paquetes: $").append(calcularValorTotalPaquetes()).append("\n");
        sb.append("Valor descuento: $").append(calcularValorDescuento()).append("\n");
        sb.append("Valor total a pagar: $").append(calcularValorTotalPagar()).append("\n");

        if (estado == 'A' || estado == 'P') {
            sb.append("\nDETALLE DE PAQUETES\n");
            sb.append("------------------------------------------------------------\n");

            int contador = 1;
            for (PaqueteTuristico paquete : susPaquetesTuristicos) {
                sb.append("\nPAQUETE #").append(contador).append("\n");
                sb.append("------------------------------------------------------------\n");
                sb.append(paquete.toString());
                contador++;
            }
        } else {
            sb.append("\nVenta cancelada. No se muestran paquetes por regla de consulta.\n");
        }

        sb.append("============================================================\n");
        return sb.toString();
    }
}