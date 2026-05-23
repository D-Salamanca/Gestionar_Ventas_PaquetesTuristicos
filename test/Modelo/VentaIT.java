/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.LinkedList;
import modelo.Destino;
import modelo.PaqueteTuristicoUnico;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.PaqueteTuristico;
import modelo.Venta;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class VentaIT {
    
    private Venta laVenta;
    
    public VentaIT() {
    }

    /**
     * Test of calcularCantidadTotalUnidadesPaquetes method, of class Venta.
     */
    @Test
    public void testCalcularCantidadTotalUnidadesPaquetes() {
        System.out.println("Test -> calcularCantidadTotalUnidadesPaquetes");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino = new Destino(
                "Cartagena",
                4,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        PaqueteTuristicoUnico paquete = new PaqueteTuristicoUnico(
                "PT001",
                "Paquete Turistico",
                "Descanso",
                "Viaje",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                100000,
                3,
                "Hilton"
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(paquete);
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        laVenta = new Venta(1, cliente, paquetes);
        int resultadoEsperado = 3;
        int resultadoObtenido = laVenta.calcularCantidadTotalUnidadesPaquetes();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorTotalPaquetes method, of class Venta.
     */
    @Test
    public void testCalcularValorTotalPaquetes() {
        System.out.println("Test -> calcularValorTotalPaquetes");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino = new Destino(
                "Cartagena",
                4,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        PaqueteTuristicoUnico paquete = new PaqueteTuristicoUnico(
                "PT001",
                "Paquete Turistico",
                "Descanso",
                "Viaje",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                100000,
                2,
                "Hilton"
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(paquete);
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        laVenta = new Venta(1, cliente, paquetes);
        int resultadoEsperado = 800000;
        int resultadoObtenido = laVenta.calcularValorTotalPaquetes();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorDescuento method, of class Venta.
     */
    @Test
    public void testCalcularValorDescuento() {
        System.out.println("Test -> calcularValorDescuento");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino = new Destino(
                "Cartagena",
                4,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        PaqueteTuristicoUnico paquete = new PaqueteTuristicoUnico(
                "PT001",
                "Paquete Turistico",
                "Descanso",
                "Viaje",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                100000,
                2,
                "Hilton"
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(paquete);
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        laVenta = new Venta(1, cliente, paquetes);
        int resultadoEsperado = 80000;
        int resultadoObtenido = laVenta.calcularValorDescuento();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorTotalPagar method, of class Venta.
     */
    @Test
    public void testCalcularValorTotalPagar() {
        System.out.println("Test -> calcularValorTotalPagar");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino = new Destino(
                "Cartagena",
                4,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        PaqueteTuristicoUnico paquete = new PaqueteTuristicoUnico(
                "PT001",
                "Paquete Turistico",
                "Descanso",
                "Viaje",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                100000,
                2,
                "Hilton"
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        paquetes.add(paquete);
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        laVenta = new Venta(1, cliente, paquetes);
        int resultadoEsperado = 720000;
        int resultadoObtenido= laVenta.calcularValorTotalPagar();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of obtenerEstadoTexto method, of class Venta.
     */
    @Test
    public void testObtenerEstadoTexto() {
        System.out.println("Test -> obtenerEstadoTexto");
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        laVenta = new Venta(1, cliente, paquetes);
        laVenta.setEstado('P');
        String resultadoEsperado = "Pagada";
        String resultadoObtenido = laVenta.obtenerEstadoTexto();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of obtenerResumen method, of class Venta.
     */
    @Test
    public void testObtenerResumen() {
        System.out.println("Test -> obtenerResumen");
        Cliente cliente = new Cliente(
                'C',
                "123456",
                false,
                "Lina",
                "lina@gmail.com",
                "3001234567",
                "Lina",
                10
        );
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        laVenta = new Venta(1, cliente, paquetes);
        String resultadoObtenido = laVenta.obtenerResumen();
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertTrue(resultadoObtenido.contains("Venta #1"));
    }

}
