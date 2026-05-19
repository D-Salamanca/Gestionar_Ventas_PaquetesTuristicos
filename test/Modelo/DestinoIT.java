/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.LinkedList;
import modelo.Destino;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class DestinoIT {
    
    private Destino elDestino;
    
    public DestinoIT() {
    }

    /**
     * Test of setNombreLugar method, of class Destino.
     */
    @Test
    public void testSetNombreLugar() {
        System.out.println("Test -> setNombreLugar");
        LinkedList<String> atractivos = new LinkedList<>();
        String resultadoEsperado = "Destino sin nombre";
        elDestino = new Destino(
                "",
                5,
                atractivos,
                true
        );
        String resultadoObtenido = elDestino.getNombreLugar();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setDiasPermanencia method, of class Destino.
     */
    @Test
    public void testSetDiasPermanencia() {
        System.out.println("Test -> setDiasPermanencia");
        LinkedList<String> atractivos = new LinkedList<>();
        int resultadoEsperado = 1;
        elDestino = new Destino(
                "Cartagena",
                0,
                atractivos,
                true
        );
        int resultadoObtenido = elDestino.getDiasPermanencia();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setAtractivos method, of class Destino.
     */
    @Test
    public void testSetAtractivos() {
        System.out.println("Test -> setAtractivos");
        LinkedList<String> atractivos = new LinkedList<>();
        int resultadoEsperado = 0;
        elDestino = new Destino(
                "Cartagena",
                5,
                null,
                true
        );
        int resultadoObtenido = elDestino.getAtractivos().size();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setAtractivosIncluidos method, of class Destino.
     */
    @Test
    public void testSetAtractivosIncluidos() {
        System.out.println("Test -> setAtractivosIncluidos");
        LinkedList<String> atractivos = new LinkedList<>();
        boolean resultadoEsperado = true;
        elDestino = new Destino(
                "Cartagena",
                5,
                atractivos,
                false
        );
        elDestino.setAtractivosIncluidos(true);
        boolean resultadoObtenido = elDestino.isAtractivosIncluidos();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    
}
