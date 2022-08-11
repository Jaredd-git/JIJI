/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorConexion;
import Controlador.ControllerLibros;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Estudiante_PC19
 */
public class CRUD extends javax.swing.JFrame {

    /**
     * Creates new form CRUD
     */
    public CRUD() {
        initComponents();
        ComprobarConexión();
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        setLocationRelativeTo(null);
        CargarComboCategoria();
        CargarComboEditorial();
        CargarComboEstado();
        String[] Libros = {"ID", "Titulo", "Resumen", "ISBN", "F.edicion", "Año", "Estado", "# Paginas", "F.publicacion", "Categoria", "Editorial"};
        modelo = new DefaultTableModel(null, Libros);
        TablaLibros.setModel(modelo);
        CargarTabla();
    }

    ControllerLibros obj = new ControllerLibros();
    private List myArrayList1;
    private List myArrayList2;
    private List myArrayList3;
    DefaultComboBoxModel<String> modelocombo1;
    DefaultComboBoxModel<String> modelocombo2;
    DefaultComboBoxModel<String> modelocombo3;
    private Calendar cld1;
    private Calendar cld2;
    DefaultTableModel modelo;
    public int ID;

    void LimpiarCampos() {
        txtId.setText("");
        txtISBN.setText("");
        txtNumpaginas.setText("");
        txtResumen.setText("");
        txtTitulo.setText("");
        dtAnio.setYear(2022);
        dtEdicion.setDate(null);
        dtPublicacion.setDate(null);
        cmbCategoria.setSelectedIndex(0);
        cmbEditorial.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
    }
    
    public void ComprobarConexión() {
        ControladorConexion conexion = new ControladorConexion();
        conexion.getConnectionController();
    }
//    "F.publicacion", "F.edicion","ISBN", "Año", "Editorial", "Estado", "Categoria"

    final void CargarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        try {
            ResultSet rs = obj.CargarLibrosResultSet();
            while (rs.next()) {
                Object[] oValores = {rs.getInt("idLibro"), rs.getString("titulo"), rs.getString("resumen"), rs.getString("isbn"), rs.getDate("fechaEdicion"), rs.getInt("anio"), rs.getInt("idestado"), rs.getInt("NumPaginas"), rs.getDate("fechaPublicacion"), rs.getInt("idCategoria"), rs.getInt("idEditorial")};
                modelo.addRow(oValores);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el contenido de la tabla" + e.toString());
        }
    }

    void GuardarLibro() {
        obj.titulo = txtTitulo.getText();
        obj.resumen = txtResumen.getText();
        obj.ISBN = txtISBN.getText();
        java.util.Date date = dtPublicacion.getDate();
        cld1 = new GregorianCalendar();
        cld1.setTime(date);
        String F = String.valueOf(cld1.get(Calendar.YEAR) + "/" + cld1.get(Calendar.MONTH) + "/" + cld1.get(Calendar.DAY_OF_MONTH));
        obj.Fecha_Publicacion = F;
        obj.anio = dtAnio.getYear();
        obj.IdEstado = cmbEstado.getSelectedIndex() + 1;
        obj.NumPag = Integer.parseInt(String.valueOf(txtNumpaginas.getText()));
        java.util.Date date2 = dtEdicion.getDate();
        cld2 = new GregorianCalendar();
        cld2.setTime(date2);
        String F2 = String.valueOf(cld2.get(Calendar.YEAR) + "/" + cld2.get(Calendar.MONTH) + "/" + cld2.get(Calendar.DAY_OF_MONTH));
        obj.Fecha_Edicion = F2;
        obj.IdCategoria = cmbCategoria.getSelectedIndex() + 1;
        obj.IdEditorial = cmbEditorial.getSelectedIndex() + 1;
         Calendar hoy = Calendar.getInstance();
         int difAnio1= hoy.get(Calendar.YEAR)-cld1.get(Calendar.YEAR);
        int difMes1= hoy.get(Calendar.MONTH)-cld1.get(Calendar.MONTH);
        int difDia1= hoy.get(Calendar.DAY_OF_MONTH)-cld1.get(Calendar.DAY_OF_MONTH);
        int difAnio2= hoy.get(Calendar.YEAR)-cld2.get(Calendar.YEAR);
        int difMes2= hoy.get(Calendar.MONTH)-cld2.get(Calendar.MONTH);
        int difDia2= hoy.get(Calendar.DAY_OF_MONTH)-cld2.get(Calendar.DAY_OF_MONTH);
        if (obj.LibroNuevoController()== true||difAnio1!=0||difMes1!=0||difDia1!=0||difAnio2!=0||difMes2!=0||difDia2!=0 ) {
            JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            LimpiarCampos();
        }
    }
    
    void ActualizarLibro() {
        ControllerLibros.ID = Integer.parseInt(txtId.getText());
        obj.titulo = txtTitulo.getText();
        obj.resumen = txtResumen.getText();
        obj.ISBN = txtISBN.getText();
        java.util.Date date = dtPublicacion.getDate();
        cld1 = new GregorianCalendar();
        cld1.setTime(date);
        String F = String.valueOf(cld1.get(Calendar.YEAR) + "/" + cld1.get(Calendar.MONTH) + "/" + cld1.get(Calendar.DAY_OF_MONTH));
        obj.Fecha_Publicacion = F;
        obj.anio = dtAnio.getYear();
        obj.IdEstado = cmbEstado.getSelectedIndex() + 1;
        obj.NumPag = Integer.parseInt(String.valueOf(txtNumpaginas.getText()));
        java.util.Date date2 = dtEdicion.getDate();
        cld2 = new GregorianCalendar();
        cld2.setTime(date2);
        String F2 = String.valueOf(cld2.get(Calendar.YEAR) + "/" + cld2.get(Calendar.MONTH) + "/" + cld2.get(Calendar.DAY_OF_MONTH));
        obj.Fecha_Edicion = F2;
        obj.IdCategoria = cmbCategoria.getSelectedIndex() + 1;
        obj.IdEditorial = cmbEditorial.getSelectedIndex() + 1;
        Calendar hoy = Calendar.getInstance();
        int difAnio1= hoy.get(Calendar.YEAR)-cld1.get(Calendar.YEAR);
        int difMes1= hoy.get(Calendar.MONTH)-cld1.get(Calendar.MONTH);
        int difDia1= hoy.get(Calendar.DAY_OF_MONTH)-cld1.get(Calendar.DAY_OF_MONTH);
        int difAnio2= hoy.get(Calendar.YEAR)-cld2.get(Calendar.YEAR);
        int difMes2= hoy.get(Calendar.MONTH)-cld2.get(Calendar.MONTH);
        int difDia2= hoy.get(Calendar.DAY_OF_MONTH)-cld2.get(Calendar.DAY_OF_MONTH);
        if (obj.LibroActualizarController()== true|| difAnio1!=0||difMes1!=0||difDia1!=0||difAnio2!=0||difMes2!=0||difDia2!=0 ) {
            JOptionPane.showMessageDialog(null, "Actulización exitosa");
            LimpiarCampos();
            btnActualizar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    final void CargarComboEstado() {
        ControllerLibros objCargarLibros = new ControllerLibros();
        myArrayList1 = new ArrayList();
        try {
            ResultSet rs = objCargarLibros.CargarEstadoResultSet();
            if (rs.next()) {
                modelocombo1 = new DefaultComboBoxModel<>();
                modelocombo1.addElement("Elija una opción");
                do {
                    myArrayList1.add(rs.getInt("idEstado"));
                    modelocombo1.addElement(rs.getString("EstadoLibro"));
                    cmbEstado.setModel(modelocombo1);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No existen estados de libros", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {

        }
    }

    final void CargarComboEditorial() {
        ControllerLibros objCargarLibros = new ControllerLibros();
        myArrayList2 = new ArrayList();
        try {
            ResultSet rs = objCargarLibros.CargarEditorialResultSet();
            if (rs.next()) {
                modelocombo2 = new DefaultComboBoxModel<>();
                modelocombo2.addElement("Elija una opción");
                do {
                    myArrayList2.add(rs.getInt("idEditorial"));
                    modelocombo2.addElement(rs.getString("editorial"));
                    cmbEditorial.setModel(modelocombo2);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No existen estados de libros", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {

        }
    }

    final void CargarComboCategoria() {
        ControllerLibros objCargarLibros = new ControllerLibros();
        myArrayList3 = new ArrayList();
        try {
            ResultSet rs = objCargarLibros.CargarCategoriaResultSet();
            if (rs.next()) {
                modelocombo3 = new DefaultComboBoxModel<>();
                modelocombo3.addElement("Elija una opción");
                do {
                    myArrayList3.add(rs.getInt("idCategoria"));
                    modelocombo3.addElement(rs.getString("categoria"));
                    cmbCategoria.setModel(modelocombo3);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No existen estados de libros", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {

        }
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaLibros = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        cmbEditorial = new javax.swing.JComboBox<>();
        txtNumpaginas = new javax.swing.JTextField();
        dtPublicacion = new com.toedter.calendar.JDateChooser();
        dtEdicion = new com.toedter.calendar.JDateChooser();
        cmbEstado = new javax.swing.JComboBox<>();
        txtISBN = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        dtAnio = new com.toedter.calendar.JYearChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(640, 694));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        TablaLibros.setBackground(new java.awt.Color(204, 255, 255));
        TablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Titulo", "F. Publicacion", "F. Edicion", "ISBN", "Año", "Editorial", "Estado", "Categoria"
            }
        ));
        TablaLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaLibrosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaLibros);
        if (TablaLibros.getColumnModel().getColumnCount() > 0) {
            TablaLibros.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(638, 30));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setText("Buscar:");
        jPanel7.add(jLabel12);
        jPanel7.add(jTextField1);

        jButton1.setText("Buscar");
        jPanel7.add(jButton1);

        jPanel3.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(640, 694));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimientos"));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(125, 32));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        btnActualizar.setText("Actualizar");
        btnActualizar.setPreferredSize(new java.awt.Dimension(125, 32));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);

        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(125, 32));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.setPreferredSize(new java.awt.Dimension(125, 32));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar);

        btnTest.setText("Test");
        btnTest.setPreferredSize(new java.awt.Dimension(125, 32));
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });
        jPanel1.add(btnTest);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(640, 400));

        jLabel1.setText("Titulo");

        jLabel2.setText("Num paginas");

        jLabel3.setText("ID");

        jLabel4.setText("ISBN");

        jLabel5.setText("Fecha de edicion");

        jLabel6.setText("Fecha de publicacion");

        jLabel7.setText("Estado");

        jLabel8.setText("Categoria");

        jLabel9.setText("Año");

        jLabel10.setText("Editorial");

        jLabel11.setText("Resumen");

        txtId.setEnabled(false);

        cmbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriaItemStateChanged(evt);
            }
        });

        cmbEditorial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEditorialItemStateChanged(evt);
            }
        });

        cmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoItemStateChanged(evt);
            }
        });

        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        jScrollPane1.setViewportView(txtResumen);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(cmbEditorial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(txtTitulo)
                    .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(cmbEstado, 0, 199, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addComponent(dtAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(txtNumpaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtISBN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtEdicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(dtPublicacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumpaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 21, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        jTabbedPane1.addTab("Libros", jPanel2);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        LimpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtTitulo.getText().isEmpty() || txtResumen.getText().isEmpty() || txtISBN.getText().isEmpty() || txtNumpaginas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se permiten campos campos vacios.");
        } else {
            GuardarLibro();
            CargarTabla();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        // TOD.O add your handling code here:
        if (ControladorConexion.getConnectionController() != null) {
            JOptionPane.showMessageDialog(null, "Conexión establecida con exito");
        }
    }//GEN-LAST:event_btnTestActionPerformed
//    private int idestado;
//    private int ideditorial;
//    private int idcategoria;
    private void cmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoItemStateChanged
//        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            int pos = cmbEstado.getSelectedIndex();
//            if (pos == 0) {
//                idestado = 0;
//            } else {
//                int dim = myArrayList1.size();
//                for (int i = 0; i < dim; i++) {
//                    if (i == pos - 1) {
//                        idestado = (int) myArrayList1.get(i);
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_cmbEstadoItemStateChanged

    private void cmbEditorialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEditorialItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            int pos = cmbEditorial.getSelectedIndex();
//            if (pos == 0) {
//                ideditorial = 0;
//            } else {
//                int dim = myArrayList2.size();
//                for (int i = 0; i < dim; i++) {
//                    if (i == pos - 1) {
//                        ideditorial = (int) myArrayList2.get(i);
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_cmbEditorialItemStateChanged

    private void cmbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriaItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            int pos = cmbCategoria.getSelectedIndex();
//            if (pos == 0) {
//                idcategoria = 0;
//            } else {
//                int dim = myArrayList3.size();
//                for (int i = 0; i < dim; i++) {
//                    if (i == pos - 1) {
//                        idcategoria = (int) myArrayList3.get(i);
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_cmbCategoriaItemStateChanged

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        ControllerLibros.ID = Integer.parseInt(txtId.getText());
        if (obj.LibroEliminarController()== true) {
            JOptionPane.showMessageDialog(null, "El registro se eliminó exitosamente");
            LimpiarCampos();
            btnActualizar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(true);
            CargarTabla();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void TablaLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaLibrosMouseClicked
        // TODO add your handling code here:
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        if (evt.getClickCount() == 1) {
            JTable rcp = (JTable) evt.getSource();
            txtId.setText(rcp.getModel().getValueAt(rcp.getSelectedRow(), 0).toString());
            txtTitulo.setText(rcp.getModel().getValueAt(rcp.getSelectedRow(), 1).toString());
            txtResumen.setText(rcp.getModel().getValueAt(rcp.getSelectedRow(), 2).toString());
            txtISBN.setText(rcp.getModel().getValueAt(rcp.getSelectedRow(), 3).toString());
            dtEdicion.setDate((java.util.Date) rcp.getModel().getValueAt(rcp.getSelectedRow(), 4));
            dtAnio.setYear((int) rcp.getModel().getValueAt(rcp.getSelectedRow(), 5));
            int estado = (int) rcp.getModel().getValueAt(rcp.getSelectedRow(), 6);
            cmbEstado.setSelectedIndex(estado - 1);
            txtNumpaginas.setText(rcp.getModel().getValueAt(rcp.getSelectedRow(), 7).toString());
            dtPublicacion.setDate((java.util.Date) rcp.getModel().getValueAt(rcp.getSelectedRow(), 8));
            int categoria = (int) rcp.getModel().getValueAt(rcp.getSelectedRow(), 9);
            cmbCategoria.setSelectedIndex(categoria - 1);
            int editorial = (int) rcp.getModel().getValueAt(rcp.getSelectedRow(), 10);
            cmbEditorial.setSelectedIndex(editorial - 1);
        }
    }//GEN-LAST:event_TablaLibrosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (txtTitulo.getText().isEmpty() || txtResumen.getText().isEmpty() || txtISBN.getText().isEmpty() || txtNumpaginas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se permiten campos campos vacios.");
        } else {
            ActualizarLibro();
            CargarTabla();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaLibros;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnTest;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbEditorial;
    private javax.swing.JComboBox<String> cmbEstado;
    private com.toedter.calendar.JYearChooser dtAnio;
    private com.toedter.calendar.JDateChooser dtEdicion;
    private com.toedter.calendar.JDateChooser dtPublicacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNumpaginas;
    private javax.swing.JTextArea txtResumen;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
