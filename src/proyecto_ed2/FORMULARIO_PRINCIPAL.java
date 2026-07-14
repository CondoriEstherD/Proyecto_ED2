package proyecto_ed2;

import ARBOLES.ArbolB;
import ARBOLES.ArbolBinarioBase;
import ARBOLES.ArbolBinarioDeBusqueda;
import ARBOLES.AVL;
import ARBOLES.IArbolBase;
import GRAFOS.Dijkstra;
import GRAFOS.Grafo;
import GRAFOS.GrafoPesado;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Tienda de comida usando las estructuras implementadas en clases.
 */
public class FORMULARIO_PRINCIPAL extends javax.swing.JFrame {

    private static final String LOCAL = "Local";
    private IArbolBase<String> arbolProductos;
    private IArbolBase<String> arbolCategorias;
    private Grafo<String> grafoSimpleZonas;
    private GrafoPesado<String> grafoZonas;
    private final Map<String, Integer> precios = new LinkedHashMap<>();
    private final Map<String, String> categoriasPorProducto = new LinkedHashMap<>();
    private final Map<String, IArbolBase<String>> arbolesPorCategoria = new LinkedHashMap<>();
    private final Map<String, ControlPedido> controlesPedido = new LinkedHashMap<>();
    private final Map<String, Double> rutasEntrega = new LinkedHashMap<>();
    private final List<PedidoRealizado> historialPedidos = new ArrayList<>();
    private PedidoRealizado pedidoActual;
    private int contadorPedidos = 1;
    private String tipoArbolActual;

    private static class ControlPedido {
        javax.swing.JCheckBox nombre;
        javax.swing.JSpinner cantidad;

        ControlPedido(javax.swing.JCheckBox nombre, javax.swing.JSpinner cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }
    }

    private static class ItemPedido {
        String producto;
        String categoria;
        int cantidad;
        int precio;

        ItemPedido(String producto, String categoria, int cantidad, int precio) {
            this.producto = producto;
            this.categoria = categoria;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        int subtotal() {
            return cantidad * precio;
        }
    }

    private static class PedidoRealizado {
        int numero;
        String destino;
        String ruta;
        String estado;
        int totalProductos;
        double costoEnvio;
        double totalFinal;
        List<ItemPedido> items;

        PedidoRealizado(int numero, String destino, String ruta, int totalProductos, double costoEnvio, double totalFinal, List<ItemPedido> items) {
            this.numero = numero;
            this.destino = destino;
            this.ruta = ruta;
            this.estado = "Pendiente";
            this.totalProductos = totalProductos;
            this.costoEnvio = costoEnvio;
            this.totalFinal = totalFinal;
            this.items = items;
        }
    }

    public FORMULARIO_PRINCIPAL() {
        initComponents();
        setLocationRelativeTo(null);
        cargarDatosIniciales();
        mostrarProductos();
        mostrarResumenGrafo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMenu = new javax.swing.JPanel();
        lblTituloMenu = new javax.swing.JLabel();
        lblTipoArbol = new javax.swing.JLabel();
        cboTipoArbol = new javax.swing.JComboBox();
        btnCambiarArbol = new javax.swing.JButton();
        lblProducto = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        btnAgregarCategoria = new javax.swing.JButton();
        lblCategoriaProducto = new javax.swing.JLabel();
        cboCategoriaProducto = new javax.swing.JComboBox();
        lblPrecioProducto = new javax.swing.JLabel();
        spPrecioProducto = new javax.swing.JSpinner();
        lblFiltroCategoria = new javax.swing.JLabel();
        cboFiltroCategoria = new javax.swing.JComboBox();
        btnAgregarProducto = new javax.swing.JButton();
        btnBuscarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnMostrarProductos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaProductos = new javax.swing.JTextArea();
        panelPedido = new javax.swing.JPanel();
        lblTituloPedido = new javax.swing.JLabel();
        chkHamburguesa = new javax.swing.JCheckBox();
        chkPizza = new javax.swing.JCheckBox();
        chkSalchipapa = new javax.swing.JCheckBox();
        chkPollo = new javax.swing.JCheckBox();
        chkGaseosa = new javax.swing.JCheckBox();
        spHamburguesa = new javax.swing.JSpinner();
        spPizza = new javax.swing.JSpinner();
        spSalchipapa = new javax.swing.JSpinner();
        spPollo = new javax.swing.JSpinner();
        spGaseosa = new javax.swing.JSpinner();
        btnCalcularPedido = new javax.swing.JButton();
        btnLimpiarPedido = new javax.swing.JButton();
        jScrollPaneProductosPedido = new javax.swing.JScrollPane();
        panelProductosPedido = new javax.swing.JPanel();
        lblDestinoPedido = new javax.swing.JLabel();
        cboDestinoPedido = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaPedido = new javax.swing.JTextArea();
        panelEntrega = new javax.swing.JPanel();
        lblTituloEntrega = new javax.swing.JLabel();
        lblOrigen = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        cboOrigen = new javax.swing.JComboBox();
        cboDestino = new javax.swing.JComboBox();
        btnCalcularRuta = new javax.swing.JButton();
        btnMostrarGrafo = new javax.swing.JButton();
        lblNuevoDestino = new javax.swing.JLabel();
        txtNuevoDestino = new javax.swing.JTextField();
        lblConectarDestino = new javax.swing.JLabel();
        cboConectarDestino = new javax.swing.JComboBox();
        lblCostoDestino = new javax.swing.JLabel();
        spCostoDestino = new javax.swing.JSpinner();
        btnAgregarDestino = new javax.swing.JButton();
        btnTomarPedido = new javax.swing.JButton();
        btnIniciarEntrega = new javax.swing.JButton();
        btnMarcarEntregado = new javax.swing.JButton();
        btnHistorialPedidos = new javax.swing.JButton();
        btnReporteGeneral = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaEntrega = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FastFood ED2 - Arboles y Grafos");

        lblTituloMenu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTituloMenu.setText("Menu de productos con arboles");

        lblTipoArbol.setText("Tipo de arbol:");

        cboTipoArbol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arbol Binario", "Arbol Binario de Busqueda", "Arbol AVL" }));
        cboTipoArbol.setSelectedIndex(1);
        cboTipoArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoArbolActionPerformed(evt);
            }
        });

        btnCambiarArbol.setText("Cambiar arbol");
        btnCambiarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarArbolActionPerformed(evt);
            }
        });

        lblProducto.setText("Producto:");

        lblCategoria.setText("Nueva categoria:");

        btnAgregarCategoria.setText("Agregar categoria");
        btnAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCategoriaActionPerformed(evt);
            }
        });

        lblCategoriaProducto.setText("Categoria:");

        lblPrecioProducto.setText("Precio:");

        lblFiltroCategoria.setText("Ver categoria:");

        cboFiltroCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFiltroCategoriaActionPerformed(evt);
            }
        });

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnMostrarProductos.setText("Mostrar arbol");
        btnMostrarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarProductosActionPerformed(evt);
            }
        });

        txtAreaProductos.setColumns(20);
        txtAreaProductos.setRows(5);
        jScrollPane1.setViewportView(txtAreaProductos);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloMenu)
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(lblTipoArbol)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCambiarArbol))
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(lblCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFiltroCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboFiltroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(lblProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCategoriaProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrecioProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMostrarProductos)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoArbol)
                    .addComponent(cboTipoArbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarArbol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarCategoria)
                    .addComponent(lblFiltroCategoria)
                    .addComponent(cboFiltroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoriaProducto)
                    .addComponent(cboCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioProducto)
                    .addComponent(spPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnBuscarProducto)
                    .addComponent(btnEliminarProducto)
                    .addComponent(btnMostrarProductos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Menu", panelMenu);

        lblTituloPedido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTituloPedido.setText("Pedido simple");

        chkHamburguesa.setText("Hamburguesa - 18 Bs");

        chkPizza.setText("Pizza - 22 Bs");

        chkSalchipapa.setText("Salchipapa - 15 Bs");

        chkPollo.setText("Pollo Broaster - 25 Bs");

        chkGaseosa.setText("Gaseosa - 8 Bs");

        btnCalcularPedido.setText("Calcular total");
        btnCalcularPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPedidoActionPerformed(evt);
            }
        });

        btnLimpiarPedido.setText("Limpiar");
        btnLimpiarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProductosPedidoLayout = new javax.swing.GroupLayout(panelProductosPedido);
        panelProductosPedido.setLayout(panelProductosPedidoLayout);
        panelProductosPedidoLayout.setHorizontalGroup(
            panelProductosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        panelProductosPedidoLayout.setVerticalGroup(
            panelProductosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        jScrollPaneProductosPedido.setViewportView(panelProductosPedido);

        lblDestinoPedido.setText("Lugar de envio:");

        txtAreaPedido.setColumns(20);
        txtAreaPedido.setRows(5);
        jScrollPane2.setViewportView(txtAreaPedido);

        javax.swing.GroupLayout panelPedidoLayout = new javax.swing.GroupLayout(panelPedido);
        panelPedido.setLayout(panelPedidoLayout);
        panelPedidoLayout.setHorizontalGroup(
            panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloPedido)
                    .addGroup(panelPedidoLayout.createSequentialGroup()
                        .addComponent(lblDestinoPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDestinoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPedidoLayout.createSequentialGroup()
                        .addComponent(jScrollPaneProductosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalcularPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        panelPedidoLayout.setVerticalGroup(
            panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDestinoPedido)
                    .addComponent(cboDestinoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneProductosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPedidoLayout.createSequentialGroup()
                        .addComponent(btnCalcularPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarPedido)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Pedido", panelPedido);

        lblTituloEntrega.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTituloEntrega.setText("Rutas de entrega con Grafo Pesado y Dijkstra");

        lblOrigen.setText("Origen:");

        lblDestino.setText("Destino:");

        btnCalcularRuta.setText("Calcular ruta");
        btnCalcularRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularRutaActionPerformed(evt);
            }
        });

        btnMostrarGrafo.setText("Mostrar grafo");
        btnMostrarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarGrafoActionPerformed(evt);
            }
        });

        lblNuevoDestino.setText("Nuevo destino:");

        lblConectarDestino.setText("Conectar con:");

        lblCostoDestino.setText("Costo:");

        btnAgregarDestino.setText("Agregar destino");
        btnAgregarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDestinoActionPerformed(evt);
            }
        });

        btnTomarPedido.setText("Tomar pedido");
        btnTomarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTomarPedidoActionPerformed(evt);
            }
        });

        btnIniciarEntrega.setText("Iniciar entrega");
        btnIniciarEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarEntregaActionPerformed(evt);
            }
        });

        btnMarcarEntregado.setText("Entregado");
        btnMarcarEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarEntregadoActionPerformed(evt);
            }
        });

        btnHistorialPedidos.setText("Historial");
        btnHistorialPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialPedidosActionPerformed(evt);
            }
        });

        btnReporteGeneral.setText("Reporte");
        btnReporteGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteGeneralActionPerformed(evt);
            }
        });

        txtAreaEntrega.setColumns(20);
        txtAreaEntrega.setRows(5);
        jScrollPane3.setViewportView(txtAreaEntrega);

        javax.swing.GroupLayout panelEntregaLayout = new javax.swing.GroupLayout(panelEntrega);
        panelEntrega.setLayout(panelEntregaLayout);
        panelEntregaLayout.setHorizontalGroup(
            panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntregaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(lblTituloEntrega)
                    .addGroup(panelEntregaLayout.createSequentialGroup()
                        .addComponent(lblOrigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalcularRuta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarGrafo))
                    .addGroup(panelEntregaLayout.createSequentialGroup()
                        .addComponent(lblNuevoDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConectarDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboConectarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCostoDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spCostoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarDestino))
                    .addGroup(panelEntregaLayout.createSequentialGroup()
                        .addComponent(btnTomarPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIniciarEntrega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMarcarEntregado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHistorialPedidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReporteGeneral)))
                .addGap(25, 25, 25))
        );
        panelEntregaLayout.setVerticalGroup(
            panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntregaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloEntrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrigen)
                    .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestino)
                    .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalcularRuta)
                    .addComponent(btnMostrarGrafo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevoDestino)
                    .addComponent(txtNuevoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConectarDestino)
                    .addComponent(cboConectarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostoDestino)
                    .addComponent(spCostoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTomarPedido)
                    .addComponent(btnIniciarEntrega)
                    .addComponent(btnMarcarEntregado)
                    .addComponent(btnHistorialPedidos)
                    .addComponent(btnReporteGeneral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Entrega", panelEntrega);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        sincronizarArbolSeleccionado();
        String producto = normalizarProducto(txtProducto.getText());
        String categoria = (String) cboCategoriaProducto.getSelectedItem();
        if (producto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba un producto.");
            return;
        }
        if (categoria == null) {
            JOptionPane.showMessageDialog(this, "Primero agregue o seleccione una categoria.");
            return;
        }

        try {
            arbolProductos.insertar(producto);
            precios.put(producto, (Integer) spPrecioProducto.getValue());
            categoriasPorProducto.put(producto, categoria);
            reconstruirArbolesPorCategoria();
            txtProducto.setText("");
            cboFiltroCategoria.setSelectedItem(categoria);
            actualizarOpcionesPedido();
            mostrarProductos();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "El producto ya existe en el arbol.");
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        sincronizarArbolSeleccionado();
        String producto = normalizarProducto(txtProducto.getText());
        if (producto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el producto que desea buscar.");
            return;
        }

        String encontrado = arbolProductos.buscar(producto);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "No se encontro: " + producto);
        } else {
            JOptionPane.showMessageDialog(this, "Producto encontrado: " + encontrado);
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        sincronizarArbolSeleccionado();
        String producto = normalizarProducto(txtProducto.getText());
        if (producto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el producto que desea eliminar.");
            return;
        }

        if (arbolProductos.buscar(producto) == null) {
            JOptionPane.showMessageDialog(this, "No existe el producto: " + producto);
            return;
        }

        arbolProductos.eliminar(producto);
        precios.remove(producto);
        categoriasPorProducto.remove(producto);
        reconstruirArbolesPorCategoria();
        txtProducto.setText("");
        actualizarOpcionesPedido();
        mostrarProductos();
        JOptionPane.showMessageDialog(this, "Producto eliminado de " + tipoArbolActual + ": " + producto);
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnMostrarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarProductosActionPerformed
        sincronizarArbolSeleccionado();
        mostrarProductos();
    }//GEN-LAST:event_btnMostrarProductosActionPerformed

    private void btnCambiarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarArbolActionPerformed
        reconstruirArbolConProductosActuales();
        reconstruirArbolesPorCategoria();
        mostrarProductos();
        JOptionPane.showMessageDialog(this, "Ahora esta trabajando con: " + cboTipoArbol.getSelectedItem());
    }//GEN-LAST:event_btnCambiarArbolActionPerformed
    // calculamos el pepeido  java.awt.event.ActionEvent evt
    private void btnCalcularPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPedidoActionPerformed
        String destino = (String) cboDestinoPedido.getSelectedItem();
        if (destino == null) {
            JOptionPane.showMessageDialog(this, "Seleccione el lugar de envio.");
            return;
        }

        StringBuilder factura = new StringBuilder();
        List<ItemPedido> itemsPedido = new ArrayList<>();
        int total = 0;

        factura.append("PEDIDO FASTFOOD ED2\n");
        factura.append("-------------------\n");
        for (Map.Entry<String, ControlPedido> entrada : controlesPedido.entrySet()) {
            ControlPedido control = entrada.getValue();
            total += agregarLineaPedido(factura, control.nombre.isSelected(), entrada.getKey(), control.cantidad, itemsPedido);
        }

        if (total == 0) {
            factura.append("Seleccione al menos un producto.\n");
        } else {
            try {
                Dijkstra<String> dijkstra = new Dijkstra<>(grafoZonas, LOCAL, destino);
                double costoEnvio = dijkstra.getMenorCosto();
                double totalFinal = total + costoEnvio;
                PedidoRealizado pedido = new PedidoRealizado(
                        contadorPedidos++,
                        destino,
                        dijkstra.getCaminoMasCorto().toString(),
                        total,
                        costoEnvio,
                        totalFinal,
                        itemsPedido);
                pedidoActual = pedido;
                historialPedidos.add(pedido);

                factura.append("Pedido Nro: ").append(pedido.numero).append("\n");
                factura.append("-------------------\n");
                factura.append("Envio desde: ").append(LOCAL).append("\n");
                factura.append("Lugar de envio: ").append(destino).append("\n");
                factura.append("Ruta: ").append(dijkstra.getCaminoMasCorto()).append("\n");
                factura.append("Costo de envio: ").append(formatearCosto(costoEnvio)).append(" Bs\n");
                factura.append("TOTAL productos: ").append(total).append(" Bs\n");
                factura.append("TOTAL con envio: ").append(formatearCosto(totalFinal)).append(" Bs\n");
                factura.append("\nORDEN PARA REPARTIDOR\n");
                factura.append("Recoger el pedido en ").append(LOCAL).append(" y seguir la ruta mas corta hasta ").append(destino).append(".\n");
                mostrarRutaDelPedidoEnEntrega(pedido);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }
        }

        txtAreaPedido.setText(factura.toString());
    }//GEN-LAST:event_btnCalcularPedidoActionPerformed

    private void btnLimpiarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarPedidoActionPerformed
        for (ControlPedido control : controlesPedido.values()) {
            control.nombre.setSelected(false);
            control.cantidad.setValue(1);
        }
        txtAreaPedido.setText("");
    }//GEN-LAST:event_btnLimpiarPedidoActionPerformed

    private void btnCalcularRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularRutaActionPerformed
        String origen = (String) cboOrigen.getSelectedItem();
        String destino = (String) cboDestino.getSelectedItem();

        if (origen == null || destino == null) {
            return;
        }
        if (origen.equals(destino)) {
            txtAreaEntrega.setText("El origen y destino son iguales.\nCosto de entrega: 0 Bs");
            return;
        }

        try {
            Dijkstra<String> dijkstra = new Dijkstra<>(grafoZonas, origen, destino);
            StringBuilder resultado = new StringBuilder();
            resultado.append("RUTA MAS CORTA\n");
            resultado.append("-------------------\n");
            resultado.append("Origen: ").append(origen).append("\n");
            resultado.append("Destino: ").append(destino).append("\n");
            resultado.append("Camino: ").append(dijkstra.getCaminoMasCorto()).append("\n");
            resultado.append("Costo: ").append(formatearCosto(dijkstra.getMenorCosto())).append(" Bs\n\n");
            resultado.append("Estructura usada: Grafo pesado + algoritmo Dijkstra.\n");
            txtAreaEntrega.setText(resultado.toString());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnCalcularRutaActionPerformed

    private void btnMostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarGrafoActionPerformed
        mostrarResumenGrafo();
    }//GEN-LAST:event_btnMostrarGrafoActionPerformed

    private void btnTomarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTomarPedidoActionPerformed
        cambiarEstadoPedidoActual("Tomado por repartidor");
    }//GEN-LAST:event_btnTomarPedidoActionPerformed

    private void btnIniciarEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarEntregaActionPerformed
        cambiarEstadoPedidoActual("En camino");
    }//GEN-LAST:event_btnIniciarEntregaActionPerformed

    private void btnMarcarEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarEntregadoActionPerformed
        cambiarEstadoPedidoActual("Entregado");
    }//GEN-LAST:event_btnMarcarEntregadoActionPerformed

    private void btnHistorialPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialPedidosActionPerformed
        mostrarHistorialPedidos();
    }//GEN-LAST:event_btnHistorialPedidosActionPerformed

    private void btnReporteGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteGeneralActionPerformed
        mostrarReporteGeneral();
    }//GEN-LAST:event_btnReporteGeneralActionPerformed

    private void cambiarEstadoPedidoActual(String nuevoEstado) {
        if (pedidoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero calcule un pedido.");
            return;
        }
        pedidoActual.estado = nuevoEstado;
        mostrarRutaDelPedidoEnEntrega(pedidoActual);
    }

    private void mostrarRutaDelPedidoEnEntrega(PedidoRealizado pedido) {
        cboDestino.setSelectedItem(pedido.destino);

        StringBuilder texto = new StringBuilder();
        texto.append("RUTA PARA ENTREGAR EL PEDIDO\n");
        texto.append("-------------------\n");
        texto.append("Pedido Nro: ").append(pedido.numero).append("\n");
        texto.append("Estado: ").append(pedido.estado).append("\n");
        texto.append("Repartidor recoge en: ").append(LOCAL).append("\n");
        texto.append("Destino del cliente: ").append(pedido.destino).append("\n");
        texto.append("Camino mas corto: ").append(pedido.ruta).append("\n");
        texto.append("Costo de envio: ").append(formatearCosto(pedido.costoEnvio)).append(" Bs\n");
        texto.append("Total productos: ").append(pedido.totalProductos).append(" Bs\n");
        texto.append("Total pedido con envio: ").append(formatearCosto(pedido.totalFinal)).append(" Bs\n\n");
        texto.append("PRODUCTOS A ENTREGAR\n");
        texto.append("-------------------\n");
        for (ItemPedido item : pedido.items) {
            texto.append("- [")
                    .append(item.categoria)
                    .append("] ")
                    .append(item.producto)
                    .append(" x")
                    .append(item.cantidad)
                    .append(" = ")
                    .append(item.subtotal())
                    .append(" Bs\n");
        }
        texto.append("\nEl repartidor debe seguir la ruta indicada por Dijkstra.");
        txtAreaEntrega.setText(texto.toString());
        jTabbedPane1.setSelectedComponent(panelEntrega);
    }

    private void mostrarHistorialPedidos() {
        StringBuilder texto = new StringBuilder();
        texto.append("HISTORIAL DE PEDIDOS\n");
        texto.append("-------------------\n");
        if (historialPedidos.isEmpty()) {
            texto.append("Todavia no hay pedidos registrados.\n");
        }
        for (PedidoRealizado pedido : historialPedidos) {
            texto.append("Pedido Nro: ").append(pedido.numero).append("\n");
            texto.append("Estado: ").append(pedido.estado).append("\n");
            texto.append("Destino: ").append(pedido.destino).append("\n");
            texto.append("Ruta: ").append(pedido.ruta).append("\n");
            texto.append("Total: ").append(formatearCosto(pedido.totalFinal)).append(" Bs\n");
            texto.append("Productos:\n");
            for (ItemPedido item : pedido.items) {
                texto.append("  - ")
                        .append(item.producto)
                        .append(" x")
                        .append(item.cantidad)
                        .append("\n");
            }
            texto.append("-------------------\n");
        }
        txtAreaEntrega.setText(texto.toString());
        jTabbedPane1.setSelectedComponent(panelEntrega);
    }

    private void mostrarReporteGeneral() {
        StringBuilder texto = new StringBuilder();
        PedidoRealizado pedidoMasCaro = null;

        for (PedidoRealizado pedido : historialPedidos) {
            if (pedidoMasCaro == null || pedido.totalFinal > pedidoMasCaro.totalFinal) {
                pedidoMasCaro = pedido;
            }
        }

        texto.append("REPORTE GENERAL DEL SISTEMA\n");
        texto.append("-------------------\n");
        texto.append("Categorias: ").append(arbolesPorCategoria.size()).append("\n");
        texto.append("Productos: ").append(precios.size()).append("\n");
        texto.append("Destinos disponibles: ").append(cboDestino.getItemCount()).append("\n");
        texto.append("Rutas registradas: ").append(rutasEntrega.size()).append("\n");
        texto.append("Pedidos registrados: ").append(historialPedidos.size()).append("\n\n");

        texto.append("PRODUCTOS POR CATEGORIA\n");
        texto.append("-------------------\n");
        for (String categoria : arbolesPorCategoria.keySet()) {
            texto.append(categoria).append(": ");
            IArbolBase<String> arbolCategoria = arbolesPorCategoria.get(categoria);
            texto.append(arbolCategoria.recorridoEnInOrden()).append("\n");
        }
        texto.append("\n");

        texto.append("DESTINO MAS BARATO DESDE LOCAL\n");
        texto.append("-------------------\n");
        texto.append(obtenerDestinoMasBarato()).append("\n\n");

        texto.append("PEDIDO MAS CARO\n");
        texto.append("-------------------\n");
        if (pedidoMasCaro == null) {
            texto.append("Todavia no hay pedidos.\n");
        } else {
            texto.append("Pedido Nro: ").append(pedidoMasCaro.numero).append("\n");
            texto.append("Destino: ").append(pedidoMasCaro.destino).append("\n");
            texto.append("Total: ").append(formatearCosto(pedidoMasCaro.totalFinal)).append(" Bs\n");
            texto.append("Estado: ").append(pedidoMasCaro.estado).append("\n");
        }

        txtAreaEntrega.setText(texto.toString());
        jTabbedPane1.setSelectedComponent(panelEntrega);
    }

    private void btnAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaActionPerformed
        String categoria = normalizarProducto(txtCategoria.getText());
        if (categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba una categoria.");
            return;
        }
        if (arbolesPorCategoria.containsKey(categoria)) {
            JOptionPane.showMessageDialog(this, "La categoria ya existe.");
            return;
        }
        agregarCategoria(categoria);
        cboCategoriaProducto.setSelectedItem(categoria);
        cboFiltroCategoria.setSelectedItem(categoria);
        txtCategoria.setText("");
        mostrarProductos();
    }//GEN-LAST:event_btnAgregarCategoriaActionPerformed

    private void cboFiltroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFiltroCategoriaActionPerformed
        if (arbolProductos != null) {
            mostrarProductos();
        }
    }//GEN-LAST:event_cboFiltroCategoriaActionPerformed

    private void btnAgregarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDestinoActionPerformed
        String nuevoDestino = normalizarProducto(txtNuevoDestino.getText());
        String conectarCon = (String) cboConectarDestino.getSelectedItem();
        int costo = (Integer) spCostoDestino.getValue();

        if (nuevoDestino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el nuevo destino.");
            return;
        }
        if (LOCAL.equals(nuevoDestino)) {
            JOptionPane.showMessageDialog(this, "Local es el origen fijo y no puede agregarse como destino.");
            return;
        }
        if (conectarCon == null) {
            JOptionPane.showMessageDialog(this, "Seleccione con que vertice se conectara.");
            return;
        }
        try {
            grafoSimpleZonas.insertarVertice(nuevoDestino);
            grafoZonas.insertarVertice(nuevoDestino);
            cboDestino.addItem(nuevoDestino);
            cboDestinoPedido.addItem(nuevoDestino);
            cboConectarDestino.addItem(nuevoDestino);
            insertarRuta(conectarCon, nuevoDestino, costo);
            txtNuevoDestino.setText("");
            cboDestino.setSelectedItem(nuevoDestino);
            cboDestinoPedido.setSelectedItem(nuevoDestino);
            mostrarResumenGrafo();
            JOptionPane.showMessageDialog(this, "Destino agregado y conectado al mapa.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarDestinoActionPerformed

    private void cboTipoArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoArbolActionPerformed

    private void cargarDatosIniciales() {
        arbolProductos = crearArbolSeleccionado();
        arbolCategorias = new AVL<>();
        tipoArbolActual = obtenerTipoArbolSeleccionado();
        grafoSimpleZonas = new Grafo<>();
        grafoZonas = new GrafoPesado<>();

        agregarCategoria("Comidas");
        agregarCategoria("Bebidas");
        agregarCategoria("Complementos");

        agregarProductoInicial("Hamburguesa", "Comidas", 18);
        agregarProductoInicial("Pizza", "Comidas", 22);
        agregarProductoInicial("Salchipapa", "Comidas", 15);
        agregarProductoInicial("Pollo Broaster", "Comidas", 25);
        agregarProductoInicial("Gaseosa", "Bebidas", 8);
        agregarProductoInicial("Papas Fritas", "Complementos", 12);

        String[] zonas = {LOCAL, "Plaza", "Universidad", "Mercado", "Terminal", "Barrio Norte"};
        for (String zona : zonas) {
            grafoSimpleZonas.insertarVertice(zona);
            grafoZonas.insertarVertice(zona);
            cboOrigen.addItem(zona);
            if (!LOCAL.equals(zona)) {
                cboDestino.addItem(zona);
                cboDestinoPedido.addItem(zona);
            }
            cboConectarDestino.addItem(zona);
        }

        insertarRuta(LOCAL, "Plaza", 5);
        insertarRuta(LOCAL, "Mercado", 4);
        insertarRuta("Plaza", "Universidad", 7);
        insertarRuta("Mercado", "Terminal", 6);
        insertarRuta("Terminal", "Barrio Norte", 5);
        insertarRuta("Universidad", "Barrio Norte", 3);
        insertarRuta("Plaza", "Mercado", 2);

        cboOrigen.setSelectedItem(LOCAL);
        cboOrigen.setEnabled(false);
        cboDestino.setSelectedItem("Universidad");
        cboDestinoPedido.setSelectedItem("Universidad");
        cboFiltroCategoria.setSelectedItem("Comidas");
        cboCategoriaProducto.setSelectedItem("Comidas");
        actualizarOpcionesPedido();
    }

    private IArbolBase<String> crearArbolSeleccionado() {
        String tipoArbol = obtenerTipoArbolSeleccionado();
        if (tipoArbol.equals("Arbol Binario")) {
            return new ArbolBinarioBase<>();
        }
        if (tipoArbol.equals("Arbol Binario de Busqueda")) {
            return new ArbolBinarioDeBusqueda<>();
        }
        if (tipoArbol.equals("Arbol AVL")) {
            return new AVL<>();
        }

        int orden = obtenerOrdenSeleccionado();
        return new ArbolB<>(orden);
    }

    private String obtenerTipoArbolSeleccionado() {
        Object seleccionado = cboTipoArbol.getSelectedItem();
        if (seleccionado == null) {
            return "Arbol B orden 4";
        }
        return seleccionado.toString();
    }

    private int obtenerOrdenSeleccionado() {
        Object seleccionado = cboTipoArbol.getSelectedItem();
        if (seleccionado == null) {
            return 4;
        }
        String texto = seleccionado.toString();
        if (texto.contains("3")) {
            return 3;
        }
        if (texto.contains("5")) {
            return 5;
        }
        return 4;
    }

    private void reconstruirArbolConProductosActuales() {
        IArbolBase<String> nuevoArbol = crearArbolSeleccionado();
        for (String producto : precios.keySet()) {
            nuevoArbol.insertar(producto);
        }
        arbolProductos = nuevoArbol;
        tipoArbolActual = obtenerTipoArbolSeleccionado();
    }

    private void sincronizarArbolSeleccionado() {
        String tipoSeleccionado = obtenerTipoArbolSeleccionado();
        if (tipoArbolActual == null || !tipoArbolActual.equals(tipoSeleccionado)) {
            reconstruirArbolConProductosActuales();
            reconstruirArbolesPorCategoria();
        }
    }

    private void reconstruirArbolesPorCategoria() {
        for (String categoria : new java.util.ArrayList<>(arbolesPorCategoria.keySet())) {
            arbolesPorCategoria.put(categoria, crearArbolSeleccionado());
        }
        for (Map.Entry<String, String> entrada : categoriasPorProducto.entrySet()) {
            IArbolBase<String> arbolCategoria = arbolesPorCategoria.get(entrada.getValue());
            if (arbolCategoria != null) {
                arbolCategoria.insertar(entrada.getKey());
            }
        }
    }

    private void agregarProductoInicial(String producto, String categoria, int precio) {
        arbolProductos.insertar(producto);
        precios.put(producto, precio);
        categoriasPorProducto.put(producto, categoria);
        IArbolBase<String> arbolCategoria = arbolesPorCategoria.get(categoria);
        if (arbolCategoria != null) {
            arbolCategoria.insertar(producto);
        }
    }

    private void agregarCategoria(String categoria) {
        try {
            arbolCategorias.insertar(categoria);
        } catch (IllegalArgumentException ex) {
            return;
        }
        arbolesPorCategoria.put(categoria, crearArbolSeleccionado());
        cboCategoriaProducto.addItem(categoria);
        cboFiltroCategoria.addItem(categoria);
    }

    private void insertarRuta(String origen, String destino, double costo) {
        grafoSimpleZonas.insertarArista(origen, destino);
        grafoZonas.insertarArista(origen, destino, costo);
        rutasEntrega.put(origen + " - " + destino, costo);
    }

    private void mostrarProductos() {
        StringBuilder texto = new StringBuilder();
        String categoriaSeleccionada = (String) cboFiltroCategoria.getSelectedItem();
        texto.append("PRODUCTOS GUARDADOS EN ").append(cboTipoArbol.getSelectedItem()).append("\n");
        texto.append("------------------------------\n");
        texto.append("ARBOL DE CATEGORIAS (AVL)\n");
        texto.append("InOrden categorias: ").append(arbolCategorias.recorridoEnInOrden()).append("\n");
        texto.append(arbolCategorias.toString()).append("\n\n");

        if (categoriaSeleccionada != null) {
            IArbolBase<String> arbolCategoria = arbolesPorCategoria.get(categoriaSeleccionada);
            if (arbolCategoria != null) {
                texto.append("ARBOL DE PRODUCTOS DE LA CATEGORIA: ").append(categoriaSeleccionada).append("\n");
                texto.append("InOrden: ").append(arbolCategoria.recorridoEnInOrden()).append("\n");
                texto.append("PreOrden: ").append(arbolCategoria.recorridoEnPreOrden()).append("\n");
                texto.append("Altura: ").append(alturaSegura(arbolCategoria)).append("\n");
                texto.append(arbolCategoria.toString()).append("\n\n");
            }
        }

        texto.append("ARBOL GLOBAL DE PRODUCTOS\n");
        texto.append("InOrden: ").append(arbolProductos.recorridoEnInOrden()).append("\n");
        texto.append("PreOrden: ").append(arbolProductos.recorridoEnPreOrden()).append("\n");
        texto.append("Por niveles: ").append(arbolProductos.recorridoPorNiveles()).append("\n");
        texto.append("Altura: ").append(alturaSegura(arbolProductos)).append("\n\n");
        texto.append("Detalle de productos:\n");
        for (String producto : precios.keySet()) {
            texto.append("- ")
                    .append(producto)
                    .append(" | Categoria: ")
                    .append(categoriasPorProducto.get(producto))
                    .append(" | Precio: ")
                    .append(precios.get(producto))
                    .append(" Bs\n");
        }
        texto.append("\n");
        texto.append("Representacion del arbol:\n");
        texto.append(arbolProductos.toString());
        txtAreaProductos.setText(texto.toString());
    }

    private void mostrarResumenGrafo() {
        StringBuilder texto = new StringBuilder();
        texto.append("ZONAS DE ENTREGA\n");
        texto.append("-------------------\n");
        texto.append("Vertices: ").append(grafoSimpleZonas.contarVertices()).append("\n");
        texto.append("Aristas: ").append(grafoSimpleZonas.contarArista()).append("\n");
        texto.append("Es conexo: ").append(grafoSimpleZonas.esConexo()).append("\n");
        texto.append("Tiene ciclo: ").append(grafoSimpleZonas.hayCiclo()).append("\n\n");
        texto.append("MAPA DE RUTAS (Grafo pesado)\n");
        texto.append("-------------------\n");
        texto.append(obtenerMapaVisual());
        texto.append("\n");
        texto.append("CAMINOS MINIMOS DEL REPARTIDOR DESDE LOCAL\n");
        texto.append("-------------------\n");
        for (int i = 0; i < cboDestino.getItemCount(); i++) {
            String destino = (String) cboDestino.getItemAt(i);
            try {
                Dijkstra<String> dijkstra = new Dijkstra<>(grafoZonas, LOCAL, destino);
                texto.append(destino)
                        .append(": ")
                        .append(dijkstra.getCaminoMasCorto())
                        .append(" | ")
                        .append(formatearCosto(dijkstra.getMenorCosto()))
                        .append(" Bs\n");
            } catch (IllegalArgumentException ex) {
                texto.append(destino).append(": sin camino disponible\n");
            }
        }
        texto.append("\n");
        texto.append("Para calcular el camino minimo se usa Dijkstra.");
        txtAreaEntrega.setText(texto.toString());
    }

    private String obtenerMapaVisual() {
        Map<String, List<String>> conexiones = new LinkedHashMap<>();
        for (String ruta : rutasEntrega.keySet()) {
            String[] partes = ruta.split(" - ");
            if (partes.length != 2) {
                continue;
            }
            String origen = partes[0];
            String destino = partes[1];
            String costo = formatearCosto(rutasEntrega.get(ruta));

            if (!conexiones.containsKey(origen)) {
                conexiones.put(origen, new ArrayList<String>());
            }
            if (!conexiones.containsKey(destino)) {
                conexiones.put(destino, new ArrayList<String>());
            }
            conexiones.get(origen).add(destino + " (" + costo + " Bs)");
            conexiones.get(destino).add(origen + " (" + costo + " Bs)");
        }

        StringBuilder mapa = new StringBuilder();
        for (Map.Entry<String, List<String>> entrada : conexiones.entrySet()) {
            mapa.append(entrada.getKey()).append("\n");
            for (String conexion : entrada.getValue()) {
                mapa.append("  |-- ").append(conexion).append("\n");
            }
        }
        return mapa.toString();
    }

    private String obtenerDestinoMasBarato() {
        String mejorDestino = null;
        double menorCosto = Double.POSITIVE_INFINITY;

        for (int i = 0; i < cboDestino.getItemCount(); i++) {
            String destino = (String) cboDestino.getItemAt(i);
            try {
                Dijkstra<String> dijkstra = new Dijkstra<>(grafoZonas, LOCAL, destino);
                if (dijkstra.getMenorCosto() < menorCosto) {
                    menorCosto = dijkstra.getMenorCosto();
                    mejorDestino = destino;
                }
            } catch (IllegalArgumentException ex) {
                // Si un destino queda sin camino, no entra al reporte de menor costo.
            }
        }

        if (mejorDestino == null) {
            return "No hay destinos con camino disponible.";
        }
        return mejorDestino + " con costo " + formatearCosto(menorCosto) + " Bs";
    }

    private int agregarLineaPedido(StringBuilder factura, boolean seleccionado, String producto, javax.swing.JSpinner spinner, List<ItemPedido> itemsPedido) {
        if (!seleccionado) {
            return 0;
        }
        Integer precio = precios.get(producto);
        if (precio == null) {
            factura.append(producto).append(" no esta disponible en el menu.\n");
            return 0;
        }
        int cantidad = (Integer) spinner.getValue();
        int subtotal = cantidad * precio;
        String categoria = categoriasPorProducto.get(producto);
        itemsPedido.add(new ItemPedido(producto, categoria, cantidad, precio));
        factura.append("[")
                .append(categoria)
                .append("] ")
                .append(producto)
                .append(" x")
                .append(cantidad)
                .append(" = ")
                .append(subtotal)
                .append(" Bs\n");
        return subtotal;
    }

    private void actualizarOpcionesPedido() {
        controlesPedido.clear();
        panelProductosPedido.removeAll();
        panelProductosPedido.setLayout(new javax.swing.BoxLayout(panelProductosPedido, javax.swing.BoxLayout.Y_AXIS));

        for (Map.Entry<String, Integer> entrada : precios.entrySet()) {
            String producto = entrada.getKey();
            Integer precio = entrada.getValue();
            String categoria = categoriasPorProducto.get(producto);
            javax.swing.JCheckBox checkBox = new javax.swing.JCheckBox("[" + categoria + "] " + producto + " - " + precio + " Bs");
            javax.swing.JSpinner spinner = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
            javax.swing.JPanel fila = new javax.swing.JPanel(new BorderLayout(8, 0));
            fila.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 28));
            fila.add(checkBox, BorderLayout.CENTER);
            fila.add(spinner, BorderLayout.EAST);

            controlesPedido.put(producto, new ControlPedido(checkBox, spinner));
            panelProductosPedido.add(fila);
        }

        panelProductosPedido.revalidate();
        panelProductosPedido.repaint();
    }

    private String formatearCosto(double costo) {
        if (costo == (int) costo) {
            return String.valueOf((int) costo);
        }
        return String.valueOf(costo);
    }

    private int alturaSegura(IArbolBase<String> arbol) {
        try {
            return arbol.altura();
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    private String normalizarProducto(String texto) {
        texto = texto.trim();
        if (texto.isEmpty()) {
            return "";
        }
        String[] palabras = texto.toLowerCase().split("\\s+");
        StringBuilder productoNormalizado = new StringBuilder();
        for (String palabra : palabras) {
            if (productoNormalizado.length() > 0) {
                productoNormalizado.append(" ");
            }
            productoNormalizado.append(palabra.substring(0, 1).toUpperCase())
                    .append(palabra.substring(1));
        }
        return productoNormalizado.toString();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FORMULARIO_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FORMULARIO_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FORMULARIO_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FORMULARIO_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FORMULARIO_PRINCIPAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCategoria;
    private javax.swing.JButton btnAgregarDestino;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCalcularPedido;
    private javax.swing.JButton btnCalcularRuta;
    private javax.swing.JButton btnCambiarArbol;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnHistorialPedidos;
    private javax.swing.JButton btnIniciarEntrega;
    private javax.swing.JButton btnLimpiarPedido;
    private javax.swing.JButton btnMarcarEntregado;
    private javax.swing.JButton btnMostrarGrafo;
    private javax.swing.JButton btnMostrarProductos;
    private javax.swing.JButton btnReporteGeneral;
    private javax.swing.JButton btnTomarPedido;
    private javax.swing.JComboBox cboCategoriaProducto;
    private javax.swing.JComboBox cboConectarDestino;
    private javax.swing.JComboBox cboDestino;
    private javax.swing.JComboBox cboDestinoPedido;
    private javax.swing.JComboBox cboFiltroCategoria;
    private javax.swing.JComboBox cboOrigen;
    private javax.swing.JComboBox cboTipoArbol;
    private javax.swing.JCheckBox chkGaseosa;
    private javax.swing.JCheckBox chkHamburguesa;
    private javax.swing.JCheckBox chkPizza;
    private javax.swing.JCheckBox chkPollo;
    private javax.swing.JCheckBox chkSalchipapa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneProductosPedido;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCategoriaProducto;
    private javax.swing.JLabel lblConectarDestino;
    private javax.swing.JLabel lblCostoDestino;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblDestinoPedido;
    private javax.swing.JLabel lblFiltroCategoria;
    private javax.swing.JLabel lblNuevoDestino;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblPrecioProducto;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTipoArbol;
    private javax.swing.JLabel lblTituloEntrega;
    private javax.swing.JLabel lblTituloMenu;
    private javax.swing.JLabel lblTituloPedido;
    private javax.swing.JPanel panelEntrega;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPedido;
    private javax.swing.JPanel panelProductosPedido;
    private javax.swing.JSpinner spCostoDestino;
    private javax.swing.JSpinner spGaseosa;
    private javax.swing.JSpinner spHamburguesa;
    private javax.swing.JSpinner spPizza;
    private javax.swing.JSpinner spPollo;
    private javax.swing.JSpinner spPrecioProducto;
    private javax.swing.JSpinner spSalchipapa;
    private javax.swing.JTextArea txtAreaEntrega;
    private javax.swing.JTextArea txtAreaPedido;
    private javax.swing.JTextArea txtAreaProductos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtNuevoDestino;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
