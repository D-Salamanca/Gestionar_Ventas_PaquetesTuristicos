/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import modelo.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ClienteITest {
    
    private Cliente elCliente;
    
    public ClienteITest() {
    }

    /**
     * Test of setTipoIdentificacion method, of class Cliente.
     */
    @Test
    public void testSetTipoIdentificacion1() {
        System.out.println("Test -> setTipoIdentificacion1");
        char tipoIdentificacion = 'X';
        elCliente = new Cliente('X',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10);
         
    char resultadoEsperado = 'C';
    elCliente.setTipoIdentificacion(tipoIdentificacion);
    char resultadoObtenido = elCliente.getTipoIdentificacion();
    System.out.println("Resultado esperado: " + resultadoEsperado);
    System.out.println("Resultado obtenido: " + resultadoObtenido);

    assertEquals(resultadoEsperado, resultadoObtenido);
        
    }

    /**
     * Test of setNumeroIdentificacion method, of class Cliente.
     */
    @Test
    public void testSetNumeroIdentificacion() {
        System.out.println("Test -> setNumeroIdentificacion");
        String numeroIdentificacion = "";
        elCliente = new Cliente ('C',
        "123",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10);
        String resultadoEsperado = "000000";

    String resultadoObtenido = elCliente.getNumeroIdentificacion();
    System.out.println("Resultado esperado: " + resultadoEsperado);
    System.out.println("Resultado obtenido: " + resultadoObtenido);

    assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setEmpresa method, of class Cliente.
     */
    @Test
    public void testSetEmpresa() {
        System.out.println("Test -> setEmpresa");
        elCliente = new Cliente('C',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10);
    
    boolean resultadoEsperado = true;
    elCliente.setEmpresa(true);
    boolean resultadoObtenido = elCliente.isEmpresa();
    System.out.println("Resultado esperado: " + resultadoEsperado);
    System.out.println("Resultado obtenido: " + resultadoObtenido);

    assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setNombre method, of class Cliente.
     */
    @Test
    public void testSetNombre() {
        System.out.println("Test -> setNombre");
        String resultadoEsperado = "Sin nombre";
        elCliente = new Cliente(
        'C',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10
        );
     
        elCliente.setNombre("");
        String resultadoObtenido = elCliente.getNombre();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

    assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setTelefono method, of class Cliente.
     */
    @Test
    public void testSetTelefono() {
        System.out.println("Test -> setTelefono");
        String resultadoEsperado = "Sin telefono";
        elCliente = new Cliente(
        'C',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10
        );
    
        elCliente.setTelefono("");
        String resultadoObtenido = elCliente.getTelefono();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setNombreContacto method, of class Cliente.
     */
    @Test
    public void testSetNombreContacto() {
        System.out.println("Test -> setNombreContacto");
        String resultadoEsperado = "Lina";
        elCliente = new Cliente(
        'C',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        10
        );
        elCliente.setNombreContacto("");
        String resultadoObtenido = elCliente.getNombreContacto();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test of setPorcentajeDescuento method, of class Cliente.
     */
    @Test
    public void testSetPorcentajeDescuento() {
        System.out.println("Test -> setPorcentajeDescuento");
        double resultadoEsperado = 0.0;
        elCliente = new Cliente(
        'C',
        "123456",
        false,
        "Lina",
        "lina@gmail.com",
        "3001234567",
        "Lina",
        -10
        );
        double resultadoObtenido = elCliente.getPorcentajeDescuento();
        System.out.println("Resultado esperado: " + resultadoEsperado);
        System.out.println("Resultado obtenido: " + resultadoObtenido);

        assertEquals(resultadoEsperado, resultadoObtenido, 0.01);
    }

    
}
