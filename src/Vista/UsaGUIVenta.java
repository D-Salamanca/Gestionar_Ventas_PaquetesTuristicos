package vista;

import modelo.Cliente;
import modelo.Destino;
import modelo.PaqueteTuristico;
import modelo.PaqueteTuristicoMultiple;
import modelo.PaqueteTuristicoUnico;
import modelo.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class UsaGUIVenta extends JFrame {

    private ArrayList<Venta> datosVentas;
    private ArrayList<Cliente> datosClientes;

    private static final String ARCHIVO_VENTAS = "ventas.dat";
    private static final String ARCHIVO_CLIENTES = "clientes.dat";

    private JTextArea areaResultados;
    private JLabel lblResumen;

    private final Color colorFondo = new Color(241, 245, 249);
    private final Color colorPanel = new Color(15, 23, 42);
    private final Color colorBoton = new Color(30, 64, 175);
    private final Color colorBotonHover = new Color(37, 99, 235);
    private final Color colorTextoClaro = Color.WHITE;

    public UsaGUIVenta() {
        datosVentas = new ArrayList<>();
        datosClientes = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestion de Ventas - Paquetes Turisticos");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        JPanel panelSuperior = crearPanelSuperior();
        JPanel panelMenu = crearPanelMenu();
        JPanel panelCentro = crearPanelCentro();

        add(panelSuperior, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.WEST);
        add(panelCentro, BorderLayout.CENTER);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorPanel);
        panel.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel titulo = new JLabel("Gestion de Ventas de Paquetes Turisticos");
        titulo.setForeground(colorTextoClaro);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblResumen = new JLabel("Ventas: 0 | Clientes archivo: 0");
        lblResumen.setForeground(new Color(203, 213, 225));
        lblResumen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblResumen.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(titulo, BorderLayout.WEST);
        panel.add(lblResumen, BorderLayout.EAST);

        return panel;
    }

    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridLayout(12, 1, 8, 8));
        panel.setBackground(new Color(226, 232, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] textos = {
            "1. Nueva venta",
            "2. Consultar todas",
            "3. Venta por numero",
            "4. Primera / ultima",
            "5. Ventas por estado",
            "6. Ventas por categoria",
            "7. Guardar ventas",
            "8. Cargar ventas",
            "9. Actualizar venta",
            "10. Guardar clientes",
            "11. Cargar clientes",
            "12. Salir"
        };

        for (int i = 0; i < textos.length; i++) {
            JButton boton = crearBoton(textos[i]);
            final int opcion = i + 1;
            boton.addActionListener(e -> procesarOpcion(opcion));
            panel.add(boton);
        }

        return panel;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBotonHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBoton);
            }
        });

        return boton;
    }

    private JPanel crearPanelCentro() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResultados.setBackground(Color.WHITE);
        areaResultados.setForeground(new Color(15, 23, 42));
        areaResultados.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        areaResultados.setText("Bienvenido.\n\nSeleccione una opcion del menu para iniciar.");

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225), 1));

        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearNuevaVenta(datosVentas);
                break;
            case 2:
                mostrar(consultarTodasVentas(datosVentas));
                break;
            case 3:
                opcionConsultarVentaPorNumero();
                break;
            case 4:
                opcionConsultarVentaPorPosicion();
                break;
            case 5:
                opcionConsultarVentasPorEstado();
                break;
            case 6:
                opcionConsultarVentasPorCategoria();
                break;
            case 7:
                generarArchivoObjetosVentas(datosVentas);
                break;
            case 8:
                recuperarVentasDesdeArchivoObjetos();
                break;
            case 9:
                opcionActualizarVenta();
                break;
            case 10:
                generarArchivoObjetosClientes(datosClientes);
                break;
            case 11:
                recuperarClientesDesdeArchivoObjetos();
                break;
            case 12:
                salir();
                break;
            default:
                mostrar("Opcion no valida.");
        }

        actualizarResumen();
    }

    private void opcionConsultarVentaPorNumero() {
        Integer numero = pedirEntero("Ingrese el numero de venta:", 1);
        if (numero != null) {
            mostrar(consultarVentaDadoNumero(datosVentas, numero));
        }
    }

    private void opcionConsultarVentaPorPosicion() {
        String[] opciones = {"Primera", "Ultima"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione la posicion:",
                "Consultar por posicion", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) {
            mostrar(consultarVentaDadaPosicion(datosVentas, 'P'));
        } else if (seleccion == 1) {
            mostrar(consultarVentaDadaPosicion(datosVentas, 'U'));
        }
    }

    private void opcionConsultarVentasPorEstado() {
        String[] opciones = {"Activa", "Cancelada", "Pagada"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione el estado:",
                "Consultar por estado", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        char[] estados = {'A', 'C', 'P'};

        if (seleccion >= 0) {
            mostrar(consultarVentasDadoEstado(datosVentas, estados[seleccion]));
        }
    }

    private void opcionConsultarVentasPorCategoria() {
        String[] opciones = {"Unico", "Multiple"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione la categoria:",
                "Consultar por categoria", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion >= 0) {
            mostrar(consultarVentasDadaCategoriaPaquete(datosVentas, opciones[seleccion]));
        }
    }

    private void opcionActualizarVenta() {
        Integer numero = pedirEntero("Ingrese el numero de venta:", 1);

        if (numero == null) {
            return;
        }

        String[] opciones = {"Cancelar", "Pagar"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione la operacion:",
                "Actualizar venta", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) {
            actualizarVenta(datosVentas, numero, 'C');
        } else if (seleccion == 1) {
            actualizarVenta(datosVentas, numero, 'P');
        }
    }

    private void mostrar(String texto) {
        areaResultados.setText(texto);
        areaResultados.setCaretPosition(0);
    }

    private void actualizarResumen() {
        lblResumen.setText("Ventas: " + datosVentas.size() + " | Clientes archivo: " + datosClientes.size());
    }

    public static int generarNumeroVenta(ArrayList<Venta> datosVentas) {
        int mayor = 0;

        for (Venta venta : datosVentas) {
            if (venta.getNumero() > mayor) {
                mayor = venta.getNumero();
            }
        }

        return mayor + 1;
    }

    public void crearNuevaVenta(ArrayList<Venta> datosVentas) {
        try {
            Cliente cliente = crearCliente();

            if (cliente == null) {
                mostrar("Creacion cancelada.");
                return;
            }

            ArrayList<PaqueteTuristico> paquetes = crearPaquetes();

            if (paquetes.isEmpty()) {
                mostrar("No se puede crear una venta sin paquetes.");
                return;
            }

            int numero = generarNumeroVenta(datosVentas);
            Venta venta = new Venta(numero, cliente, paquetes);
            datosVentas.add(venta);

            mostrar("Venta creada correctamente.\n\n" + venta.toString());
        } catch (Exception ex) {
            mostrar("Error al crear la venta: " + ex.getMessage());
        }
    }

    private Cliente crearCliente() {
        Character tipo = pedirCaracter("Tipo identificacion: C = Cedula, N = NIT", new char[]{'C', 'N'});

        if (tipo == null) {
            return null;
        }

        String numeroId = pedirTexto(tipo == 'C'
                ? "Numero de cedula. Minimo 6 digitos:"
                : "Numero de NIT. Exactamente 9 digitos:");

        boolean empresa = tipo == 'N';
        String nombre = pedirTexto("Nombre completo o razon social:");
        String email = pedirTexto("Email:");
        String telefono = pedirTexto("Telefono:");
        String contacto = pedirTexto("Nombre del contacto:");
        Double descuento = pedirDouble("Porcentaje de descuento. Minimo 0 y maximo 70:", 0.0, 70.0);

        if (descuento == null) {
            descuento = 0.0;
        }

        return new Cliente(tipo, numeroId, empresa, nombre, email, telefono, contacto, descuento);
    }

    private ArrayList<PaqueteTuristico> crearPaquetes() {
        ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            PaqueteTuristico paquete = crearPaquete();

            if (paquete != null) {
                paquetes.add(paquete);
            }

            int opcion = JOptionPane.showConfirmDialog(this,
                    "Desea agregar otro paquete?",
                    "Agregar paquete",
                    JOptionPane.YES_NO_OPTION);

            continuar = opcion == JOptionPane.YES_OPTION;
        }

        return paquetes;
    }

    private PaqueteTuristico crearPaquete() {
        String[] categorias = {"Unico", "Multiple"};

        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione la categoria del paquete:",
                "Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, categorias, categorias[0]);

        if (seleccion < 0) {
            return null;
        }

        boolean esUnico = seleccion == 0;

        String codigo = pedirTexto("Codigo del paquete:");
        String nombre = pedirTexto("Nombre del paquete. Minimo 10 caracteres:");
        String tipologia = pedirTexto("Tipologia de turismo:");
        String descripcion = pedirTexto("Descripcion. Maximo 500 caracteres:");
        String origen = pedirTexto("Lugar de origen:");

        boolean hotel = confirmar("Incluye hotel?");
        boolean alimentacion = confirmar("Incluye alimentacion?");
        boolean alimentacionTodo = false;

        if (alimentacion) {
            alimentacionTodo = confirmar("La alimentacion es todo incluido?");
        }

        boolean vuelo = confirmar("Incluye vuelo?");
        boolean asistencia = confirmar("Incluye asistencia?");

        Integer tarifaDia = pedirEntero("Tarifa por dia:", 1);
        Integer cantidadUnidades = pedirEntero("Cantidad de unidades:", 1);

        if (tarifaDia == null) {
            tarifaDia = 1;
        }

        if (cantidadUnidades == null) {
            cantidadUnidades = 1;
        }

        int minimoDestinos = esUnico ? 1 : 2;
        int cantidadDestinos;

        if (esUnico) {
            cantidadDestinos = 1;
        } else {
            Integer numeroDestinos = pedirEntero("Cantidad de destinos. Minimo 2:", minimoDestinos);
            cantidadDestinos = numeroDestinos == null ? minimoDestinos : numeroDestinos;
        }

        ArrayList<Destino> destinos = crearDestinos(cantidadDestinos);

        if (esUnico) {
            String nombreHotel = hotel ? pedirTexto("Nombre del hotel:") : "No aplica";
            String tipoDesayuno = null;

            if (alimentacion && !alimentacionTodo) {
                tipoDesayuno = pedirTexto("Tipo de desayuno. Ejemplo: Buffet, Americano:");
            }

            return new PaqueteTuristicoUnico(codigo, nombre, tipologia, descripcion, origen,
                    destinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                    tarifaDia, cantidadUnidades, nombreHotel, tipoDesayuno);
        } else {
            String obsequio = pedirTexto("Obsequio del paquete multiple:");

            return new PaqueteTuristicoMultiple(codigo, nombre, tipologia, descripcion, origen,
                    destinos, hotel, alimentacion, alimentacionTodo, vuelo, asistencia,
                    tarifaDia, cantidadUnidades, obsequio);
        }
    }

    private ArrayList<Destino> crearDestinos(int cantidadDestinos) {
        ArrayList<Destino> destinos = new ArrayList<>();

        for (int i = 0; i < cantidadDestinos; i++) {
            String lugar = pedirTexto("Nombre del destino #" + (i + 1) + ":");
            Integer dias = pedirEntero("Dias de permanencia en " + lugar + ":", 1);
            boolean atractivosIncluidos = confirmar("Los atractivos estan incluidos en " + lugar + "?");
            String textoAtractivos = pedirTexto("Atractivos separados por coma:");

            LinkedList<String> atractivos = new LinkedList<>();

            if (textoAtractivos != null && !textoAtractivos.trim().isEmpty()) {
                String[] partes = textoAtractivos.split(",");

                for (String parte : partes) {
                    if (!parte.trim().isEmpty()) {
                        atractivos.add(parte.trim());
                    }
                }
            }

            destinos.add(new Destino(lugar, dias == null ? 1 : dias, atractivos, atractivosIncluidos));
        }

        return destinos;
    }

    public String consultarTodasVentas(ArrayList<Venta> datosVentas) {
        if (datosVentas.isEmpty()) {
            return "No hay ventas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("CONSULTA DE TODAS LAS VENTAS\n\n");

        for (Venta venta : datosVentas) {
            sb.append(venta.toString()).append("\n");
        }

        return sb.toString();
    }

    public String consultarVentaDadoNumero(ArrayList<Venta> datosVentas, int numeroVenta) {
        for (Venta venta : datosVentas) {
            if (venta.getNumero() == numeroVenta) {
                return venta.toString();
            }
        }

        return "No existe una venta con numero " + numeroVenta + ".";
    }

    public String consultarVentaDadaPosicion(ArrayList<Venta> datosVentas, char posicionVenta) {
        if (datosVentas.isEmpty()) {
            return "No hay ventas registradas.";
        }

        char posicion = Character.toUpperCase(posicionVenta);

        if (posicion == 'P') {
            return datosVentas.get(0).toString();
        }

        if (posicion == 'U') {
            return datosVentas.get(datosVentas.size() - 1).toString();
        }

        return "Posicion no valida. Use P o U.";
    }

    public String consultarVentasDadoEstado(ArrayList<Venta> datosVentas, char estadoVenta) {
        if (datosVentas.isEmpty()) {
            return "No hay ventas registradas.";
        }

        char estado = Character.toUpperCase(estadoVenta);
        StringBuilder sb = new StringBuilder();
        sb.append("VENTAS CON ESTADO ").append(estado).append("\n\n");

        for (Venta venta : datosVentas) {
            if (venta.getEstado() == estado) {
                sb.append(venta.toString()).append("\n");
            }
        }

        if (sb.toString().equals("VENTAS CON ESTADO " + estado + "\n\n")) {
            return "No hay ventas con estado " + estado + ".";
        }

        return sb.toString();
    }

    public String consultarVentasDadaCategoriaPaquete(ArrayList<Venta> datosVentas, String categoriaPaquete) {
        if (datosVentas.isEmpty()) {
            return "No hay ventas registradas.";
        }

        boolean buscarUnico = categoriaPaquete.equalsIgnoreCase("Unico");
        boolean buscarMultiple = categoriaPaquete.equalsIgnoreCase("Multiple");

        StringBuilder sb = new StringBuilder();
        sb.append("VENTAS CON PAQUETES DE CATEGORIA ").append(categoriaPaquete).append("\n\n");

        for (Venta venta : datosVentas) {
            boolean encontrada = false;

            for (PaqueteTuristico paquete : venta.getSusPaquetesTuristicos()) {
                if (buscarUnico && paquete instanceof PaqueteTuristicoUnico) {
                    encontrada = true;
                }

                if (buscarMultiple && paquete instanceof PaqueteTuristicoMultiple) {
                    encontrada = true;
                }
            }

            if (encontrada) {
                sb.append(venta.toString()).append("\n");
            }
        }

        if (sb.toString().equals("VENTAS CON PAQUETES DE CATEGORIA " + categoriaPaquete + "\n\n")) {
            return "No hay ventas con paquetes de categoria " + categoriaPaquete + ".";
        }

        return sb.toString();
    }

    public void actualizarVenta(ArrayList<Venta> datosVentas, int numeroVenta, char operacion) {
        char op = Character.toUpperCase(operacion);

        for (Venta venta : datosVentas) {
            if (venta.getNumero() == numeroVenta) {
                if (op == 'C') {
                    venta.setEstado('C');
                    mostrar("Venta cancelada correctamente.\n\n" + venta.toString());
                    return;
                }

                if (op == 'P') {
                    venta.setEstado('P');
                    mostrar("Venta pagada correctamente.\n\n" + venta.toString());
                    return;
                }

                mostrar("Operacion no valida. Use C o P.");
                return;
            }
        }

        mostrar("No se encontro la venta numero " + numeroVenta + ".");
    }

    public void generarArchivoObjetosVentas(ArrayList<Venta> datosVentas) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_VENTAS))) {
            salida.writeObject(datosVentas);
            mostrar("Archivo de ventas generado correctamente: " + ARCHIVO_VENTAS);
        } catch (IOException ex) {
            mostrar("Error al guardar ventas: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperarVentasDesdeArchivoObjetos() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_VENTAS))) {
            datosVentas.clear();
            datosVentas.addAll((ArrayList<Venta>) entrada.readObject());
            mostrar("Ventas recuperadas correctamente.\nTotal ventas: " + datosVentas.size());
        } catch (IOException | ClassNotFoundException ex) {
            mostrar("Error al recuperar ventas: " + ex.getMessage());
        }
    }

    public void generarArchivoObjetosClientes(ArrayList<Cliente> datosClientes) {
        ArrayList<Cliente> clientesSinRepetir = new ArrayList<>();

        for (Venta venta : datosVentas) {
            Cliente cliente = venta.getSuCliente();

            if (cliente != null && !existeCliente(clientesSinRepetir, cliente)) {
                clientesSinRepetir.add(cliente);
            }
        }

        datosClientes.clear();
        datosClientes.addAll(clientesSinRepetir);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CLIENTES))) {
            salida.writeObject(datosClientes);
            mostrar("Archivo de clientes generado correctamente: " + ARCHIVO_CLIENTES
                    + "\nClientes guardados sin repetir: " + datosClientes.size());
        } catch (IOException ex) {
            mostrar("Error al guardar clientes: " + ex.getMessage());
        }
    }

    private boolean existeCliente(ArrayList<Cliente> clientes, Cliente cliente) {
        for (Cliente actual : clientes) {
            if (actual.getTipoIdentificacion() == cliente.getTipoIdentificacion()
                    && actual.getNumeroIdentificacion().equals(cliente.getNumeroIdentificacion())) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public void recuperarClientesDesdeArchivoObjetos() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_CLIENTES))) {
            datosClientes.clear();
            datosClientes.addAll((ArrayList<Cliente>) entrada.readObject());

            StringBuilder sb = new StringBuilder();
            sb.append("Clientes recuperados correctamente.\n");
            sb.append("Total clientes: ").append(datosClientes.size()).append("\n\n");

            for (Cliente cliente : datosClientes) {
                sb.append("-----------------------------\n");
                sb.append(cliente.toString());
            }

            mostrar(sb.toString());
        } catch (IOException | ClassNotFoundException ex) {
            mostrar("Error al recuperar clientes: " + ex.getMessage());
        }
    }

    private boolean confirmar(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    private String pedirTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(this, mensaje);

        if (texto == null) {
            return "";
        }

        return texto.trim();
    }

    private Integer pedirEntero(String mensaje, int minimo) {
        while (true) {
            String texto = JOptionPane.showInputDialog(this, mensaje);

            if (texto == null) {
                return null;
            }

            try {
                int numero = Integer.parseInt(texto.trim());

                if (numero >= minimo) {
                    return numero;
                }

                JOptionPane.showMessageDialog(this, "El valor debe ser minimo " + minimo + ".");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero entero valido.");
            }
        }
    }

    private Double pedirDouble(String mensaje, double minimo, double maximo) {
        while (true) {
            String texto = JOptionPane.showInputDialog(this, mensaje);

            if (texto == null) {
                return null;
            }

            try {
                double numero = Double.parseDouble(texto.trim());

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                JOptionPane.showMessageDialog(this, "El valor debe estar entre " + minimo + " y " + maximo + ".");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero valido.");
            }
        }
    }

    private Character pedirCaracter(String mensaje, char[] permitidos) {
        while (true) {
            String texto = JOptionPane.showInputDialog(this, mensaje);

            if (texto == null) {
                return null;
            }

            if (!texto.trim().isEmpty()) {
                char caracter = Character.toUpperCase(texto.trim().charAt(0));

                for (char permitido : permitidos) {
                    if (caracter == permitido) {
                        return caracter;
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Ingrese una opcion valida.");
        }
    }

    private void salir() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "Desea salir del sistema?",
                "Salir",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsaGUIVenta ventana = new UsaGUIVenta();
            ventana.setVisible(true);
        });
    }
}