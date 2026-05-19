/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import modelo.Destino;
import modelo.PaqueteTuristicoUnico;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PaqueteTuristicoUnicoIT {
    
    private PaqueteTuristicoUnico elPaquete;
    
    public PaqueteTuristicoUnicoIT() {
    }

    /**
     * Test of setNombreHotel method, of class PaqueteTuristicoUnico.
     */
    @Test
    public void testSetNombreHotel() {
        System.out.println("Test -> setNombreHotel");
        LinkedList<String> atractivos = new LinkedList<>();
        String resultadoEsperado = "No aplica";
        Destino destino = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        elPaquete = new PaqueteTuristicoUnico(
                "PTU001",
                "Paquete Turistico Unico",
                "Descanso",
                "Viaje de prueba",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                100000,
                2,
                ""
        );
        elPaquete.setNombreHotel("");
        String resultadoObtenido = elPaquete.getNombreHotel();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);
    }

    /**
     * Test of setTipoDesayuno method, of class PaqueteTuristicoUnico.
     */
    @Test
    public void testSetTipoDesayuno() {
        System.out.println("Test -> setTipoDesayuno");
        LinkedList<String> atractivos = new LinkedList<>();
        String resultadoEsperado = "Americano";
        Destino destino = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        elPaquete = new PaqueteTuristicoUnico(
                "PTU001",
                "Paquete Turistico Unico",
                "Descanso",
                "Viaje de prueba",
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
        elPaquete.setTipoDesayuno("Americano");
        String resultadoObtenido = elPaquete.getTipoDesayuno();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorUnidad method, of class PaqueteTuristicoUnico.
     */
    @Test
    public void testCalcularValorUnidad() {
        System.out.println("Test -> calcularValorUnidad");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino = new Destino(
                "Cartagena",
                4,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino);
        elPaquete = new PaqueteTuristicoUnico(
                "PTU001",
                "Paquete Turistico Unico",
                "Descanso",
                "Viaje de prueba",
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
        int resultadoEsperado = 400000;
        int resultadoObtenido = elPaquete.calcularValorUnidad();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
}
