/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import modelo.Destino;
import modelo.PaqueteTuristicoMultiple;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PaqueteTuristicoMultipleIT {
    
    private PaqueteTuristicoMultiple elPaquete;
    
    public PaqueteTuristicoMultipleIT() {
    }

    /**
     * Test of setObsequio method, of class PaqueteTuristicoMultiple.
     */
    @Test
    public void testSetObsequio() {
        System.out.println("Test -> setObsequio");
        ArrayList<Destino> destinos = new ArrayList<>();
        String resultadoEsperado = "Sin obsequio";
        elPaquete = new PaqueteTuristicoMultiple(
                "PT001",
                "Paquete Turistico Multiple",
                "Aventura",
                "Viaje de prueba",
                "Cali",
                destinos,
                true,
                true,
                false,
                true,
                true,
                200000,
                2,
                ""
        );
        elPaquete.setObsequio("");
        String resultadoObtenido = elPaquete.getObsequio();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorUnidad method, of class PaqueteTuristicoMultiple.
     */
    @Test
    public void testCalcularValorUnidad() {
        System.out.println("Test -> calcularValorUnidad");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino1 = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        Destino destino2 = new Destino(
                "San Andres",
                2,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino1);
        destinos.add(destino2);

        elPaquete = new PaqueteTuristicoMultiple(
                "PT001",
                "Paquete Turistico Multiple",
                "Aventura",
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
                "Maleta"
        );
        int resultadoEsperado = 502000;
        int resultadoObtenido = elPaquete.calcularValorUnidad();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of obtenerDestinoInicial method, of class PaqueteTuristicoMultiple.
     */
    @Test
    public void testObtenerDestinoInicial() {
        System.out.println("Test -> obtenerDestinoInicial");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino1 = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        Destino destino2 = new Destino(
                "San Andres",
                2,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();

        destinos.add(destino1);
        destinos.add(destino2);

        elPaquete = new PaqueteTuristicoMultiple(
                "PT001",
                "Paquete Multiple",
                "Aventura",
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
                "Maleta"
        );
        String resultadoEsperado = "Cartagena";
        String resultadoObtenido
                = elPaquete.obtenerDestinoInicial().getNombreLugar();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of obtenerDestinoFinal method, of class PaqueteTuristicoMultiple.
     */
    @Test
    public void testObtenerDestinoFinal() {
        System.out.println("Test -> obtenerDestinoFinal");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino1 = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        Destino destino2 = new Destino(
                "San Andres",
                2,
                atractivos,
                true
        );
        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino1);
        destinos.add(destino2);
        elPaquete = new PaqueteTuristicoMultiple(
                "PT001",
                "Paquete Multiple",
                "Aventura",
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
                "Maleta"
        );
        String resultadoEsperado = "San Andres";
        String resultadoObtenido = elPaquete.obtenerDestinoFinal().getNombreLugar();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}
