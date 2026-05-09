package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class UsaVenta extends JFrame {

    private ArrayList<Venta> datosVentas = new ArrayList<>();
    private ArrayList<Cliente> datosClientes = new ArrayList<>();

    private static final String ARCHIVO_VENTAS   = "ventas.dat";
    private static final String ARCHIVO_CLIENTES = "clientes.dat";

    // ─── Componentes GUI ────────────────────────────────────────────────────
    private JTextArea areaResultados;
    private JScrollPane scrollResultados;

    public UsaVenta() {
        initComponents();
        setTitle("Gestión de Ventas - Paquetes Turísticos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        // ── Panel de botones (izquierda) ──────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(11, 1, 4, 4));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));


        String[] etiquetas = {
            "1. Nueva venta",
            "2. Todas las ventas",
            "3. Venta por número",
            "4. Primera / última venta",
            "5. Ventas por estado",
            "6. Generar archivo ventas",
            "7. Cargar ventas desde archivo",
            "8. Actualizar venta",
            "9. Generar archivo clientes",
            "10. Cargar clientes desde archivo",
            "11. Salir"
        };

        for (int i = 0; i < etiquetas.length; i++) {
            JButton btn = new JButton(etiquetas[i]);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            final int opcion = i + 1;
            btn.addActionListener(e -> procesarOpcion(opcion));
            panelBotones.add(btn);
        }

        // ── Área de resultados (derecha) ──────────────────────────────────
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        areaResultados.setMargin(new Insets(8, 8, 8, 8));
        scrollResultados = new JScrollPane(areaResultados);

        // ── Título superior ───────────────────────────────────────────────
        JLabel titulo = new JLabel("  Gestión de Ventas – Paquetes Turísticos", JLabel.LEFT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.WEST);
        add(scrollResultados, BorderLayout.CENTER);
    }

    // ─── Enrutador de opciones ───────────────────────────────────────────────
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1  -> crearNuevaVenta(datosVentas);
            case 2  -> mostrar(consultarTodasVentas(datosVentas));
            case 3  -> {
                String num = JOptionPane.showInputDialog(this, "Número de venta:");
                if (num != null && !num.isBlank())
                    mostrar(consultarVentaDadoNumero(datosVentas, Integer.parseInt(num.trim())));
            }
            case 4  -> {
                String[] opciones = {"P - Primera", "U - Última"};
                int sel = JOptionPane.showOptionDialog(this, "¿Cuál posición?",
                        "Posición", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (sel >= 0)
                    mostrar(consultarVentaDadaPosicion(datosVentas, sel == 0 ? 'P' : 'U'));
            }
            case 5  -> {
                String[] opciones = {"A - Activa", "C - Cancelada", "P - Pagada"};
                int sel = JOptionPane.showOptionDialog(this, "Estado a consultar:",
                        "Estado", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (sel >= 0) {
                    char[] estados = {'A', 'C', 'P'};
                    mostrar(consultarVentasDadoEstado(datosVentas, estados[sel]));
                }
            }
            case 6  -> generarArchivoObjetosVentas(datosVentas);
            case 7  -> recuperarVentasDesdeArchivoObjetos();
            case 8  -> {
                String num = JOptionPane.showInputDialog(this, "Número de venta a actualizar:");
                if (num == null || num.isBlank()) break;
                String[] ops = {"C - Cancelar", "P - Pagar"};
                int sel = JOptionPane.showOptionDialog(this, "Operación:",
                        "Actualizar", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, ops, ops[0]);
                if (sel >= 0)
                    actualizarVenta(datosVentas, Integer.parseInt(num.trim()), sel == 0 ? 'C' : 'P');
            }
            case 9  -> generarArchivoObjetosClientes(datosClientes);
            case 10 -> recuperarClientesDesdeArchivoObjetos();
            case 11 -> {
                int conf = JOptionPane.showConfirmDialog(this,
                        "¿Desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if (conf == JOptionPane.YES_OPTION) System.exit(0);
            }
        }
    }

    private void mostrar(String texto) {
        areaResultados.setText(texto);
        areaResultados.setCaretPosition(0);
    }

    // ─── Generación de número de venta ───────────────────────────────────────
    public static int generarNumeroVenta(ArrayList<Venta> datosVentas) {
        int max = 0;
        for (Venta v : datosVentas) {
            if (v.getNumero() > max) max = v.getNumero();
        }
        return max + 1;
    }

    // ─── Crear nueva venta ───────────────────────────────────────────────────
    public void crearNuevaVenta(ArrayList<Venta> datosVentas) {
        try {
            // Datos del cliente
            char tipoId = JOptionPane.showInputDialog(this,
                    "Tipo identificación (C=Cédula / N=NIT):").toUpperCase().charAt(0);
            String numId  = JOptionPane.showInputDialog(this, "Número de identificación:");
            boolean empresa = tipoId == 'N';
            String nombre  = JOptionPane.showInputDialog(this, "Nombre / Razón social:");
            String email   = JOptionPane.showInputDialog(this, "Email:");
            String telefono = JOptionPane.showInputDialog(this, "Teléfono:");
            String contacto = JOptionPane.showInputDialog(this, "Nombre contacto:");
            double descuento = Double.parseDouble(
                    JOptionPane.showInputDialog(this, "Porcentaje descuento (0.0 - 70.0):"));

            Cliente cliente = new Cliente(tipoId, numId, empresa, nombre,
                    email, telefono, contacto, descuento);

            // Paquetes
            ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
            boolean agregarMas = true;
            while (agregarMas) {
                paquetes.add(crearPaquete());
                int resp = JOptionPane.showConfirmDialog(this,
                        "¿Agregar otro paquete?", "Paquetes", JOptionPane.YES_NO_OPTION);
                agregarMas = (resp == JOptionPane.YES_OPTION);
            }

            int numero = generarNumeroVenta(datosVentas);
            Venta venta = new Venta(numero, cliente, paquetes);
            datosVentas.add(venta);

            mostrar("Venta #" + numero + " creada exitosamente.\n\n" + venta.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al crear venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Crea un paquete turístico (único o múltiple) mediante diálogos
    private PaqueteTuristico crearPaquete() {
        String[] tipos = {"Único", "Múltiple"};
        int sel = JOptionPane.showOptionDialog(this, "Categoría del paquete:",
                "Tipo paquete", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        String codigo   = JOptionPane.showInputDialog(this, "Código del paquete:");
        String nombre   = JOptionPane.showInputDialog(this, "Nombre del paquete (mín. 10 caracteres):");
        String tipologia = JOptionPane.showInputDialog(this, "Tipología (negocios, recreación, educativo, ecológico...):");
        String descripcion = JOptionPane.showInputDialog(this, "Descripción (máx. 500 caracteres):");
        String origen   = JOptionPane.showInputDialog(this, "Ciudad de origen:");
        boolean hotel    = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Incluye hotel?");
        boolean alim     = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Incluye alimentación?");
        boolean alimTodo = alim && JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Alimentación todo incluido?");
        boolean vuelo    = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Incluye vuelo?");
        boolean asistencia = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Incluye asistencia?");
        int tarifa  = Integer.parseInt(JOptionPane.showInputDialog(this, "Tarifa por día ($):"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad de unidades (reservas):"));

        // Destinos
        ArrayList<Destino> destinos = new ArrayList<>();
        int numDestinos = Integer.parseInt(JOptionPane.showInputDialog(this,
                sel == 0 ? "Número de destinos (mín. 1):" : "Número de destinos (mín. 2):"));
        for (int i = 0; i < numDestinos; i++) {
            String lugar = JOptionPane.showInputDialog(this, "Nombre del destino " + (i + 1) + ":");
            int dias = Integer.parseInt(JOptionPane.showInputDialog(this, "Días de permanencia en " + lugar + ":"));
            boolean atracInc = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "¿Atractivos incluidos en " + lugar + "?");
            String atracStr = JOptionPane.showInputDialog(this, "Atractivos de " + lugar + " (separados por coma):");
            LinkedList<String> atractivos = new LinkedList<>();
            if (atracStr != null && !atracStr.isBlank()) {
                for (String a : atracStr.split(",")) atractivos.add(a.trim());
            }
            destinos.add(new Destino(lugar, dias, atractivos, atracInc));
        }

        if (sel == 0) {
            // Único
            String nombreHotel = hotel ? JOptionPane.showInputDialog(this, "Nombre del hotel:") : "N/A";
            String desayuno = (alim && !alimTodo)
                    ? JOptionPane.showInputDialog(this, "Tipo de desayuno (ej. Buffet, Americano):") : null;
            if (desayuno != null) {
                return new PaqueteTuristicoUnico(codigo, nombre, tipologia, descripcion, origen,
                        destinos, hotel, alim, alimTodo, vuelo, asistencia, tarifa, cantidad, nombreHotel, desayuno);
            }
            return new PaqueteTuristicoUnico(codigo, nombre, tipologia, descripcion, origen,
                    destinos, hotel, alim, alimTodo, vuelo, asistencia, tarifa, cantidad, nombreHotel);
        } else {
            // Múltiple
            String obsequio = JOptionPane.showInputDialog(this, "Obsequio del paquete:");
            return new PaqueteTuristicoMultiple(codigo, nombre, tipologia, descripcion, origen,
                    destinos, hotel, alim, alimTodo, vuelo, asistencia, tarifa, cantidad, obsequio);
        }
    }

    // ─── Consultas ───────────────────────────────────────────────────────────
    public String consultarTodasVentas(ArrayList<Venta> datosVentas) {
        if (datosVentas.isEmpty()) return "No hay ventas registradas.";
        StringBuilder sb = new StringBuilder();
        for (Venta v : datosVentas) sb.append(v.toString()).append("\n");
        return sb.toString();
    }

    public String consultarVentaDadoNumero(ArrayList<Venta> datosVentas, int numeroVenta) {
        for (Venta v : datosVentas) {
            if (v.getNumero() == numeroVenta) return v.toString();
        }
        return "No se encontró la venta #" + numeroVenta;
    }

    public String consultarVentaDadaPosicion(ArrayList<Venta> datosVentas, char posicionVenta) {
        if (datosVentas.isEmpty()) return "No hay ventas registradas.";
        if (posicionVenta == 'P') return datosVentas.get(0).toString();
        if (posicionVenta == 'U') return datosVentas.get(datosVentas.size() - 1).toString();
        return "Posición inválida. Use P (primera) o U (última).";
    }

    public String consultarVentasDadoEstado(ArrayList<Venta> datosVentas, char estadoVenta) {
        StringBuilder sb = new StringBuilder();
        char e = Character.toUpperCase(estadoVenta);
        for (Venta v : datosVentas) {
            if (v.getEstado() == e) sb.append(v.toString()).append("\n");
        }
        return sb.isEmpty() ? "No hay ventas con estado '" + e + "'." : sb.toString();
    }

    public String consultarVentasDadaCategoriaPaquete(ArrayList<Venta> datosVentas, String categoriaPaquete) {
        StringBuilder sb = new StringBuilder();
        boolean esUnico = categoriaPaquete.equalsIgnoreCase("Único")
                || categoriaPaquete.equalsIgnoreCase("Unico");
        for (Venta v : datosVentas) {
            boolean incluye = false;
            for (PaqueteTuristico p : v.getSusPaquetesTuristicos()) {
                if (esUnico && p instanceof PaqueteTuristicoUnico) { incluye = true; break; }
                if (!esUnico && p instanceof PaqueteTuristicoMultiple) { incluye = true; break; }
            }
            if (incluye) sb.append(v.toString()).append("\n");
        }
        return sb.isEmpty() ? "No hay ventas con paquetes de categoría '" + categoriaPaquete + "'." : sb.toString();
    }

    // ─── Actualizar venta ────────────────────────────────────────────────────
    public void actualizarVenta(ArrayList<Venta> datosVentas, int numeroVenta, char operacion) {
        for (Venta v : datosVentas) {
            if (v.getNumero() == numeroVenta) {
                char nuevoEstado = Character.toUpperCase(operacion) == 'C' ? 'C' : 'P';
                v.setEstado(nuevoEstado);
                mostrar("Venta #" + numeroVenta + " actualizada.\n\n" + v.toString());
                return;
            }
        }
        mostrar("No se encontró la venta #" + numeroVenta);
    }

    // ─── Archivos de objetos — Ventas ────────────────────────────────────────
    public void generarArchivoObjetosVentas(ArrayList<Venta> datosVentas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_VENTAS))) {
            oos.writeObject(datosVentas);
            JOptionPane.showMessageDialog(this,
                    "Archivo de ventas generado: " + ARCHIVO_VENTAS);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar ventas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperarVentasDesdeArchivoObjetos() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_VENTAS))) {
            datosVentas.clear();
            datosVentas.addAll((ArrayList<Venta>) ois.readObject());
            mostrar("Ventas cargadas desde archivo. Total: " + datosVentas.size());
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar ventas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Archivos de objetos — Clientes ──────────────────────────────────────
    public void generarArchivoObjetosClientes(ArrayList<Cliente> datosClientes) {
        // Construir lista sin repetidos a partir de las ventas
        ArrayList<Cliente> sinRepetidos = new ArrayList<>();
        for (Venta v : datosVentas) {
            Cliente c = v.getSuCliente();
            boolean existe = false;
            for (Cliente existing : sinRepetidos) {
                if (existing.getNumeroIdentificacion().equals(c.getNumeroIdentificacion())) {
                    existe = true; break;
                }
            }
            if (!existe) sinRepetidos.add(c);
        }
        datosClientes.clear();
        datosClientes.addAll(sinRepetidos);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_CLIENTES))) {
            oos.writeObject(datosClientes);
            JOptionPane.showMessageDialog(this,
                    "Archivo de clientes generado: " + ARCHIVO_CLIENTES
                    + "\nClientes únicos: " + datosClientes.size());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar clientes: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperarClientesDesdeArchivoObjetos() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_CLIENTES))) {
            datosClientes.clear();
            datosClientes.addAll((ArrayList<Cliente>) ois.readObject());
            mostrar("Clientes cargados desde archivo. Total: " + datosClientes.size());
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Main ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsaVenta().setVisible(true));
    }
}