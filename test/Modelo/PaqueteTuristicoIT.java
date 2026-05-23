/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.LinkedList;
import java.util.ArrayList;
import modelo.Destino;
import modelo.PaqueteTuristico;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PaqueteTuristicoIT {
    
    private PaqueteTuristico elPaquete;
    
    public PaqueteTuristicoIT() {
    }

    /**
     * Test of calcularDuracionTotalDias method, of class PaqueteTuristico.
     */
    @Test
    public void testCalcularDuracionTotalDias1() {
        System.out.println("Test -> calcularDuracionTotalDias");
        LinkedList<String> atractivos = new LinkedList<>();
        Destino destino1 = new Destino(
                "Cartagena",
                3,
                atractivos,
                true
        );
        Destino destino2 = new Destino(
                "San Andres",
                5,
                atractivos,
                true
        );

        ArrayList<Destino> destinos = new ArrayList<>();
        destinos.add(destino1);
        destinos.add(destino2);
        elPaquete = new PaqueteTuristicoImpl();
        elPaquete.setSusDestinos(destinos);
        int resultadoEsperado = 8;
        int resultadoObtenido = elPaquete.calcularDuracionTotalDias();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of calcularValorTotal method, of class PaqueteTuristico.
     */
    @Test
    public void testCalcularValorTotal() {
        System.out.println("Test -> calcularValorTotal");
        elPaquete = new PaqueteTuristicoImpl();
        elPaquete.setCantidadUnidades(3);
        int resultadoEsperado = 0;
        int resultadoObtenido = elPaquete.calcularValorTotal();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of obtenerCategoria method, of class PaqueteTuristico.
     */
    @Test
    public void testObtenerCategoria() {
        System.out.println("Test -> obtenerCategoria");
        elPaquete = new PaqueteTuristicoImpl();
        String resultadoEsperado = "Sin categoria";
        String resultadoObtenido = elPaquete.obtenerCategoria();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    

    public class PaqueteTuristicoImpl extends PaqueteTuristico {

        public PaqueteTuristicoImpl() {
            super("", "", "", "", "", null, false, false, false, false, false, 0, 0);
        }

        public int calcularValorUnidad() {
            return 0;
        }
    }
    
}
