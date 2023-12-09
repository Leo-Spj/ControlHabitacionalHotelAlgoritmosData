/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.HotelControl;
import modelo.Habitacion;
import modelo.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 *
 * @author Leo
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */

    HotelControl hotelControl = new HotelControl();

    DefaultTableModel model = new DefaultTableModel();


    public VentanaPrincipal() {
        initComponents();

        // Centrar ventana
        this.setLocationRelativeTo(null);

        datosDePrueba();
    }

    public class HabitacionCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String estado = (String) table.getValueAt(row, 5); // Asume que "Estado" es la sexta columna (índice 5)

            if ("Ocupada".equals(estado)) {
                c.setBackground(Color.RED);
            } else if ("Ocupada-limpieza".equals(estado)) {
                c.setBackground(Color.ORANGE);
            } else if ("Disponible".equals(estado)) {
                c.setBackground(Color.GREEN);
            } else if ("Disponible-limpieza".equals(estado)) {
                c.setBackground(Color.YELLOW);
            } else {
                c.setBackground(table.getBackground());
            }

            return c;
        }
    }

    public void datosDePrueba(){
        hotelControl.agregarHotel("Las Fores", "Pueblo Libre", "Calle las flores 420", "999666999");

        hotelControl.agregarHabitacion(1, 1, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 1, 2, 3, 150);
        hotelControl.agregarHabitacion(1, 3, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 3, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 2, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 1, 4, 2, 120);
        hotelControl.agregarHabitacion(1, 1, 3, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 2, 2, 3, 150);
        hotelControl.agregarHabitacion(1, 3, 1, 1, 100);
        hotelControl.agregarHabitacion(1, 3, 2, 3, 150);

        actualizandoComboBox();

        cargarTabla(1);
    }



    // por cada hotel se inserta en el combobox
    public void actualizandoComboBox(){

        cbx_selectSucursal.removeAllItems();
        cbx_gestion_selectSucursal.removeAllItems();

        for (int i = 0; i < hotelControl.getCantidadHoteles(); i++) {
            Hotel hotel = hotelControl.getListaHoteles().obtenerPorIndice(i);
            String nombre = hotel.getId()+" - "+hotel.getNombre();
            cbx_selectSucursal.addItem(nombre);
            cbx_gestion_selectSucursal.addItem(nombre);
        }
    }

    public void cargarTabla(int idHotel){
        Hotel hotelEncontrado = hotelControl.hotelEncontrado(idHotel);
        model = (DefaultTableModel) tbl_habitaciones.getModel();

        //seteo de columnas y nombres
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Piso");
        model.addColumn("Puerta");
        model.addColumn("Camas");
        model.addColumn("Precio");
        model.addColumn("Estado");

        //limpia
        model.setRowCount(0);

        //llenando
        for (int i = 0; i < hotelEncontrado.getHabitaciones().getCantidadHabitaciones(); i++) {
            model.addRow(new Object[]{
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getId(),
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getPiso(),
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getNumero(),
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getCantidadCamas(),
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getPrecioDia(),
                    hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getEstado()
            });
        }

        TableColumnModel columnModel = tbl_habitaciones.getColumnModel();
        TableColumn column = columnModel.getColumn(5);
        column.setPreferredWidth(170);

        HabitacionCellRenderer cellRenderer = new HabitacionCellRenderer();
        tbl_habitaciones.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        reporteDelDia(hotelEncontrado.getId());
    }

    public void cargarTablaPorPiso(int idHotel){
        Hotel hotelEncontrado = hotelControl.hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().getHabitacionesOrdenadasPorPiso();

        cargarTabla(idHotel);
    }

    public void cargarTablaPorEstado(int idHotel){
        Hotel hotelEncontrado = hotelControl.hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().getHabitacionesOrdenadasPorEstado();

        cargarTabla(idHotel);
    }

    public void cargarTablaPorEstado(int idHotel, String estado){
        Hotel hotelEncontrado = hotelControl.hotelEncontrado(idHotel);
        hotelEncontrado.getHabitaciones().getHabitacionesOrdenadasPorEstado();

        //seteo de columnas y nombres
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Piso");
        model.addColumn("Puerta");
        model.addColumn("Camas");
        model.addColumn("Precio");
        model.addColumn("Estado");

        //limpia
        model.setRowCount(0);

        //llenando
        for (int i = 0; i < hotelEncontrado.getHabitaciones().getCantidadHabitaciones(); i++) {
            if (hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getEstado().equals(estado)){
                model.addRow(new Object[]{
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getId(),
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getPiso(),
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getNumero(),
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getCantidadCamas(),
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getPrecioDia(),
                        hotelEncontrado.getHabitaciones().getListaHabitaciones().obtenerPorIndice(i).getEstado()
                });
            }
        }

        TableColumnModel columnModel = tbl_habitaciones.getColumnModel();
        TableColumn column = columnModel.getColumn(5);
        column.setPreferredWidth(170);

        HabitacionCellRenderer cellRenderer = new HabitacionCellRenderer();
        tbl_habitaciones.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
    }

    //obtener hotel por el id_nombre del combobox
    public Hotel obtenerHotelPorIdNombre(String id_nombre) {
        for (int i = hotelControl.getCantidadHoteles()-1; i >= 0; i--) {
            Hotel hotel = hotelControl.getListaHoteles().obtenerPorIndice(i);
            if ((hotel.getId() + " - " + hotel.getNombre()).equals(id_nombre)) {
                return hotel;
            }
        }
        return null;
    }

    public void reporteDelDia(int idHotel){
        double montoOcupadas = hotelControl.reporteHabitacionesOcupadas(idHotel);
        double montoDisponibles = hotelControl.reporteHabitacionesDisponibles(idHotel);
        txf_monto_ocupadas.setText(String.valueOf(montoOcupadas));
        txf_monto_disponibles.setText(String.valueOf(montoDisponibles));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbx_selectSucursal = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txf_idHabitacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        spnr_actualizarHab_piso = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        spnr_actualizarHab_puerta = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        spnr_actualizarHab_camas = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        txf_actualizarHab_precio = new javax.swing.JTextField();
        btn_actualizarHabitacion = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_habitaciones = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        CheckBx_limpieza = new javax.swing.JCheckBox();
        CheckBx_ocupada = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_ordenarPorPiso = new javax.swing.JButton();
        btn_ordenarPorEstado = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbx_filtrarPorEstado = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btn_atenderPorCola = new javax.swing.JButton();
        btn_atenderPorPila = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txf_monto_ocupadas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txf_monto_disponibles = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txf_crearSucursal_nombre = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txf_crearSucursal_distrito = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txf_crearSucursal_direc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txf_crearSucursal_telefono = new javax.swing.JTextField();
        btn_crearSucursal_ = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cbx_gestion_selectSucursal = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txf_actualizarSucursal_nombre = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txf_actualizarSucursal_distrito = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txf_actualizarSucursal_direc = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txf_actualizarSucursal_telefono = new javax.swing.JTextField();
        btn_actualizarSucursal = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        spnr_crearHab_piso = new javax.swing.JSpinner();
        spnr_crearHab_puerta = new javax.swing.JSpinner();
        jLabel29 = new javax.swing.JLabel();
        spnr_crearHab_camas = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        txf_crearHab_precio = new javax.swing.JTextField();
        btn_crearHab = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Seleccionar Sucursal");

        cbx_selectSucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_selectSucursalItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbx_selectSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbx_selectSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Actualizar Habitación ID:");

        txf_idHabitacion.setEditable(false);

        jLabel3.setText("Piso");

        spnr_actualizarHab_piso.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        jLabel4.setText("Puerta");

        spnr_actualizarHab_puerta.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel5.setText("Camas");

        spnr_actualizarHab_camas.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel6.setText("Precio");

        btn_actualizarHabitacion.setText("Actualizar");
        btn_actualizarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btn_actualizarHabitacionActionPerformed(evt);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spnr_actualizarHab_camas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(spnr_actualizarHab_puerta)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnr_actualizarHab_piso))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txf_idHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txf_actualizarHab_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_actualizarHabitacion)))
                        .addGap(25, 25, 25))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txf_idHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnr_actualizarHab_piso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnr_actualizarHab_puerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnr_actualizarHab_camas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txf_actualizarHab_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_actualizarHabitacion))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbl_habitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Piso", "Puerta", "Camas", "Precio", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_habitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_habitacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_habitaciones);
        if (tbl_habitaciones.getColumnModel().getColumnCount() > 0) {
            tbl_habitaciones.getColumnModel().getColumn(5).setResizable(false);
            tbl_habitaciones.getColumnModel().getColumn(5).setPreferredWidth(300);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Estados");

        CheckBx_limpieza.setText("En Limpieza");
        CheckBx_limpieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBx_limpiezaActionPerformed(evt);
            }
        });

        CheckBx_ocupada.setText("Ocupada");
        CheckBx_ocupada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBx_ocupadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckBx_ocupada, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(CheckBx_limpieza, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBx_ocupada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckBx_limpieza)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Ordenar por:");

        btn_ordenarPorPiso.setText("Piso");
        btn_ordenarPorPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ordenarPorPisoActionPerformed(evt);
            }
        });

        btn_ordenarPorEstado.setText("Estado");
        btn_ordenarPorEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ordenarPorEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ordenarPorPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ordenarPorEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btn_ordenarPorPiso)
                    .addComponent(btn_ordenarPorEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Filtrar");

        jLabel10.setText("Estado:");

        cbx_filtrarPorEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Disponible", "Disponible-limpieza", "Ocupada", "Ocupada-limpieza" }));
        cbx_filtrarPorEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_filtrarPorEstadoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_filtrarPorEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbx_filtrarPorEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Atender");

        btn_atenderPorCola.setText("Cola");
        btn_atenderPorCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atenderPorColaActionPerformed(evt);
            }
        });

        btn_atenderPorPila.setText("Pila");
        btn_atenderPorPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atenderPorPilaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_atenderPorPila, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(btn_atenderPorCola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(btn_atenderPorCola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_atenderPorPila)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("Reporte del día");

        jLabel13.setText("Ocupadas: S/.");

        txf_monto_ocupadas.setEditable(false);
        txf_monto_ocupadas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel14.setText("Disponibles: S/.");

        txf_monto_disponibles.setEditable(false);
        txf_monto_disponibles.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txf_monto_disponibles)
                            .addComponent(txf_monto_ocupadas)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txf_monto_ocupadas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txf_monto_disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(336, 336, 336))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sucursal", jPanel1);

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setText("Crear Sucursal");

        jLabel16.setText("Nombre");

        jLabel17.setText("Distrito");

        jLabel18.setText("Dirección");

        jLabel19.setText("Telefono");

        btn_crearSucursal_.setText("Crear");
        btn_crearSucursal_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearSucursal_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txf_crearSucursal_distrito))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txf_crearSucursal_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txf_crearSucursal_direc, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txf_crearSucursal_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_crearSucursal_)))))
                .addGap(80, 80, 80))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txf_crearSucursal_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txf_crearSucursal_direc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txf_crearSucursal_distrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txf_crearSucursal_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_crearSucursal_))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setText("Seleccionar Sucursal");

        cbx_gestion_selectSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_gestion_selectSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel21))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(cbx_gestion_selectSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbx_gestion_selectSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Actualizar Sucursal");

        jLabel22.setText("Nombre");

        jLabel23.setText("Distrito");

        jLabel24.setText("Direccion");

        jLabel25.setText("Telefono");

        btn_actualizarSucursal.setText("Actualizar");
        btn_actualizarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txf_actualizarSucursal_nombre)
                                    .addComponent(txf_actualizarSucursal_distrito, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txf_actualizarSucursal_direc))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txf_actualizarSucursal_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_actualizarSucursal)))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txf_actualizarSucursal_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txf_actualizarSucursal_distrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txf_actualizarSucursal_direc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txf_actualizarSucursal_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_actualizarSucursal)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setText("Crear Habitación");

        jLabel27.setText("Piso");

        jLabel28.setText("Puerta");

        spnr_crearHab_piso.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        spnr_crearHab_puerta.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel29.setText("Camas");

        spnr_crearHab_camas.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel30.setText("Precio");

        btn_crearHab.setText("Crear");
        btn_crearHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearHabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnr_crearHab_puerta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnr_crearHab_piso, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_crearHab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnr_crearHab_camas)
                            .addComponent(txf_crearHab_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(spnr_crearHab_piso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(spnr_crearHab_camas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(spnr_crearHab_puerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txf_crearHab_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_crearHab)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Gestión", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_crearSucursal_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearSucursal_ActionPerformed

        String nombre = txf_crearSucursal_nombre.getText();
        String distrito = txf_crearSucursal_distrito.getText();
        String direccion = txf_crearSucursal_direc.getText();
        String telefono = txf_crearSucursal_telefono.getText();

        if (nombre.equals("") || distrito.equals("") || direccion.equals("") || telefono.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            int idHotel = hotelControl.agregarHotel(nombre, distrito, direccion, telefono);
            actualizandoComboBox();
            txf_crearSucursal_nombre.setText("");
            txf_crearSucursal_distrito.setText("");
            txf_crearSucursal_direc.setText("");
            txf_crearSucursal_telefono.setText("");

            JOptionPane.showMessageDialog(null, "Se ha creado la sucursal");
        }
    }//GEN-LAST:event_btn_crearSucursal_ActionPerformed

    private void cbx_gestion_selectSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_gestion_selectSucursalActionPerformed
        String item = (String) cbx_gestion_selectSucursal.getSelectedItem();
        if (item != null) {
            //al seleccionar una sucursal se cargan los datos de la sucursal en los campos de texto para porder actualizar
            String nombreSucursal = cbx_gestion_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            //si no es nulo
            if (hotel != null) {
                txf_actualizarSucursal_nombre.setText(hotel.getNombre());
                txf_actualizarSucursal_distrito.setText(hotel.getDistrito());
                txf_actualizarSucursal_direc.setText(hotel.getDireccion());
                txf_actualizarSucursal_telefono.setText(hotel.getTelefono());
            }
        }

    }//GEN-LAST:event_cbx_gestion_selectSucursalActionPerformed

    private void btn_actualizarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarSucursalActionPerformed

        String item = (String) cbx_gestion_selectSucursal.getSelectedItem();
        if (item != null) {
            String id_nombre = cbx_gestion_selectSucursal.getSelectedItem().toString();

            String nombre = txf_actualizarSucursal_nombre.getText();
            String distrito = txf_actualizarSucursal_distrito.getText();
            String direccion = txf_actualizarSucursal_direc.getText();
            String telefono = txf_actualizarSucursal_telefono.getText();

            if (nombre.equals("") || distrito.equals("") || direccion.equals("") || telefono.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            } else {

                Hotel hotel = obtenerHotelPorIdNombre(id_nombre);
                String nombreAnterior = hotel.getNombre();

                hotel.setNombre(nombre);
                hotel.setDistrito(distrito);
                hotel.setDireccion(direccion);
                hotel.setTelefono(telefono);

                if(!nombreAnterior.equals(nombre)){
                    actualizandoComboBox();
                }

                JOptionPane.showMessageDialog(null, "Se ha actualizado la sucursal");
            }
        }

    }//GEN-LAST:event_btn_actualizarSucursalActionPerformed
    private void btn_crearHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearHabActionPerformed

        String nombreSucursal = cbx_gestion_selectSucursal.getSelectedItem().toString();
        Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

        int piso = (int) spnr_crearHab_piso.getValue();
        int puerta = (int) spnr_crearHab_puerta.getValue();
        int camas = (int) spnr_crearHab_camas.getValue();
        double precio = Double.parseDouble(txf_crearHab_precio.getText());

        if (piso == 0 || puerta == 0 || camas == 0 || precio < 0) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            hotelControl.agregarHabitacion(hotel.getId(), piso, puerta, camas, precio);
            cargarTabla(hotel.getId());

            spnr_crearHab_puerta.setValue(puerta + 1);
            cbx_selectSucursal.setSelectedItem(hotel.getId()+" - "+hotel.getNombre());
            reporteDelDia(hotel.getId());
            JOptionPane.showMessageDialog(null, "Se ha creado la habitación");
        }
    }//GEN-LAST:event_btn_crearHabActionPerformed

    private void cbx_selectSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_selectSucursalItemStateChanged

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            if (hotel != null) {
                cargarTabla(hotel.getId());
                reporteDelDia(hotel.getId());
            }
        }
    }//GEN-LAST:event_cbx_selectSucursalItemStateChanged

    private void btn_actualizarHabitacionActionPerformed(java.awt.event.ActionEvent evt) throws Exception {//GEN-FIRST:event_btn_actualizarHabitacionActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            int idHabitacion = Integer.parseInt(txf_idHabitacion.getText());
            int piso = (int) spnr_actualizarHab_piso.getValue();
            int puerta = (int) spnr_actualizarHab_puerta.getValue();
            int camas = (int) spnr_actualizarHab_camas.getValue();
            double precio = Double.parseDouble(txf_actualizarHab_precio.getText());

            if (piso == 0 || puerta == 0 || camas == 0 || precio < 0) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            } else{

                try {
                    hotelControl.actualizarHabitacion(hotel.getId(), idHabitacion, piso, puerta, camas, precio);
                    cargarTabla(hotel.getId());
                    reporteDelDia(hotel.getId());

                    JOptionPane.showMessageDialog(null, "Se ha actualizado la habitación");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ya existe una habitación con ese número de puerta.");
                }

            }
        }
    }//GEN-LAST:event_btn_actualizarHabitacionActionPerformed

    private void btn_ordenarPorPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ordenarPorPisoActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            cargarTablaPorPiso(hotel.getId());
        }
    }//GEN-LAST:event_btn_ordenarPorPisoActionPerformed

    private void btn_ordenarPorEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ordenarPorEstadoActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            cargarTablaPorEstado(hotel.getId());
        }
    }//GEN-LAST:event_btn_ordenarPorEstadoActionPerformed

    private void cbx_filtrarPorEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_filtrarPorEstadoItemStateChanged

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {

            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            if (cbx_filtrarPorEstado.getSelectedItem().toString().equals("Todas")) {
                cargarTabla(hotel.getId());
            } else {
                String estado = cbx_filtrarPorEstado.getSelectedItem().toString();
                cargarTablaPorEstado(hotel.getId(), estado);
            }

            //reporte
            reporteDelDia(hotel.getId());
        }


    }//GEN-LAST:event_cbx_filtrarPorEstadoItemStateChanged

    private void btn_atenderPorColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atenderPorColaActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            try {
                hotelControl.atenderHabitacionPorCola(hotel.getId());
                cargarTabla(hotel.getId());
                reporteDelDia(hotel.getId());

                JOptionPane.showMessageDialog(null, "Se ha atendido a la persona");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay personas en cola");
            }

        }
    }//GEN-LAST:event_btn_atenderPorColaActionPerformed

    private void btn_atenderPorPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atenderPorPilaActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            try {
                hotelControl.atenderHabitacionPorPila(hotel.getId());
                cargarTabla(hotel.getId());
                reporteDelDia(hotel.getId());

                JOptionPane.showMessageDialog(null, "Se ha atendido a la persona");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay personas en cola");
            }

        }
    }//GEN-LAST:event_btn_atenderPorPilaActionPerformed

    private void tbl_habitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_habitacionesMouseClicked

        int fila = tbl_habitaciones.getSelectedRow();
        if (fila != -1) {
            String idHabitacion = tbl_habitaciones.getValueAt(fila, 0).toString();
            String piso = tbl_habitaciones.getValueAt(fila, 1).toString();
            String puerta = tbl_habitaciones.getValueAt(fila, 2).toString();
            String camas = tbl_habitaciones.getValueAt(fila, 3).toString();
            String precio = tbl_habitaciones.getValueAt(fila, 4).toString();

            txf_idHabitacion.setText(idHabitacion);
            spnr_actualizarHab_piso.setValue(Integer.parseInt(piso));
            spnr_actualizarHab_puerta.setValue(Integer.parseInt(puerta));
            spnr_actualizarHab_camas.setValue(Integer.parseInt(camas));
            txf_actualizarHab_precio.setText(precio);

            //en el checkbox se selecciona el estado de la habitacion si esta Ocupada / Ocupada-limpieza etc
            String estado = tbl_habitaciones.getValueAt(fila, 5).toString();
            if(estado.equals("Ocupada")){
                CheckBx_ocupada.setSelected(true);
                CheckBx_limpieza.setSelected(false);
            }
            if (estado.equals("Ocupada-limpieza")) {
                CheckBx_ocupada.setSelected(true);
                CheckBx_limpieza.setSelected(true);
            }
            if (estado.equals("Disponible")) {
                CheckBx_ocupada.setSelected(false);
                CheckBx_limpieza.setSelected(false);
            }
            if(estado.equals("Disponible-limpieza")){
                CheckBx_ocupada.setSelected(false);
                CheckBx_limpieza.setSelected(true);
            }
        }
    }//GEN-LAST:event_tbl_habitacionesMouseClicked

    private void CheckBx_ocupadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBx_ocupadaActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            if (hotel != null) {
                int idHabitacion = Integer.parseInt(txf_idHabitacion.getText());
                Habitacion habitacion = hotel.getHabitaciones().buscarHabitacionporId(idHabitacion);

                if (CheckBx_ocupada.isSelected()) {
                    if (CheckBx_limpieza.isSelected()) {
                        habitacion.setEstado("Ocupada-limpieza");
                    } else {
                        habitacion.setEstado("Ocupada");
                    }
                } else {
                    if (CheckBx_limpieza.isSelected()) {
                        habitacion.setEstado("Disponible-limpieza");
                    } else {
                        habitacion.setEstado("Disponible");
                    }
                }

                //hotel.getHabitaciones().getHabitacionesOrdenadasPorPiso();
                cargarTabla(hotel.getId());
                reporteDelDia(hotel.getId());

            }
        }
    }//GEN-LAST:event_CheckBx_ocupadaActionPerformed

    private void CheckBx_limpiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBx_limpiezaActionPerformed

        String item = (String) cbx_selectSucursal.getSelectedItem();
        if (item != null) {
            String nombreSucursal = cbx_selectSucursal.getSelectedItem().toString();
            Hotel hotel = obtenerHotelPorIdNombre(nombreSucursal);

            if (hotel != null) {
                int idHabitacion = Integer.parseInt(txf_idHabitacion.getText());
                Habitacion habitacion = hotel.getHabitaciones().buscarHabitacionporId(idHabitacion);

                if (CheckBx_ocupada.isSelected()) {
                    if (CheckBx_limpieza.isSelected()) {
                        habitacion.setEstado("Ocupada-limpieza");
                    } else {
                        habitacion.setEstado("Ocupada");
                    }
                } else {
                    if (CheckBx_limpieza.isSelected()) {
                        habitacion.setEstado("Disponible-limpieza");
                    } else {
                        habitacion.setEstado("Disponible");
                    }
                }

                //hotel.getHabitaciones().getHabitacionesOrdenadasPorPiso();
                cargarTabla(hotel.getId());
                reporteDelDia(hotel.getId());

            }
        }
    }//GEN-LAST:event_CheckBx_limpiezaActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckBx_limpieza;
    private javax.swing.JCheckBox CheckBx_ocupada;
    private javax.swing.JButton btn_actualizarHabitacion;
    private javax.swing.JButton btn_actualizarSucursal;
    private javax.swing.JButton btn_atenderPorCola;
    private javax.swing.JButton btn_atenderPorPila;
    private javax.swing.JButton btn_crearHab;
    private javax.swing.JButton btn_crearSucursal_;
    private javax.swing.JButton btn_ordenarPorEstado;
    private javax.swing.JButton btn_ordenarPorPiso;
    private javax.swing.JComboBox<String> cbx_filtrarPorEstado;
    private javax.swing.JComboBox<String> cbx_gestion_selectSucursal;
    private javax.swing.JComboBox<String> cbx_selectSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner spnr_actualizarHab_camas;
    private javax.swing.JSpinner spnr_actualizarHab_piso;
    private javax.swing.JSpinner spnr_actualizarHab_puerta;
    private javax.swing.JSpinner spnr_crearHab_camas;
    private javax.swing.JSpinner spnr_crearHab_piso;
    private javax.swing.JSpinner spnr_crearHab_puerta;
    private javax.swing.JTable tbl_habitaciones;
    private javax.swing.JTextField txf_actualizarHab_precio;
    private javax.swing.JTextField txf_actualizarSucursal_direc;
    private javax.swing.JTextField txf_actualizarSucursal_distrito;
    private javax.swing.JTextField txf_actualizarSucursal_nombre;
    private javax.swing.JTextField txf_actualizarSucursal_telefono;
    private javax.swing.JTextField txf_crearHab_precio;
    private javax.swing.JTextField txf_crearSucursal_direc;
    private javax.swing.JTextField txf_crearSucursal_distrito;
    private javax.swing.JTextField txf_crearSucursal_nombre;
    private javax.swing.JTextField txf_crearSucursal_telefono;
    private javax.swing.JTextField txf_idHabitacion;
    private javax.swing.JTextField txf_monto_disponibles;
    private javax.swing.JTextField txf_monto_ocupadas;
    // End of variables declaration//GEN-END:variables
}
