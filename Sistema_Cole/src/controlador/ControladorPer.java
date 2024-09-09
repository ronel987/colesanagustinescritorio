package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDao;
import vista.FormPersona;


public class ControladorPer implements ActionListener{
    
    PersonaDao dao = new PersonaDao();
    Persona p = new Persona();
    FormPersona vista = new FormPersona();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorPer(FormPersona v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.TablaDatos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.TablaDatos);
            nuevo();
        }
        
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.TablaDatos);
            nuevo();
            
        }
        
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.TablaDatos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 0).toString());
                String nom = (String) vista.TablaDatos.getValueAt(fila, 1);
                String paterno = (String) vista.TablaDatos.getValueAt(fila, 2);
                String materno = (String) vista.TablaDatos.getValueAt(fila, 3);
                String dni = (String) vista.TablaDatos.getValueAt(fila, 4);
                String genero = (String) vista.TablaDatos.getValueAt(fila, 5);
                String correo = (String) vista.TablaDatos.getValueAt(fila, 6);
                String telefono = (String) vista.TablaDatos.getValueAt(fila, 7);
                String direccion = (String) vista.TablaDatos.getValueAt(fila, 8);
                String fecha = (String) vista.TablaDatos.getValueAt(fila, 9);
                vista.txtId.setText("" + id);
                vista.txtNombres.setText(nom);
                vista.txtApePaterno.setText(paterno);
                vista.txtApeMaterno.setText(materno);
                vista.txtDni.setText(dni);
                vista.cbxGenero.setSelectedItem(genero);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(telefono);
                vista.txtDireccion.setText(direccion);
                vista.txtFecha.setText(fecha);
                
            }
        }
        
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            listar(vista.TablaDatos);
            nuevo();

        }
        
        if (e.getSource() == vista.btnEliminar) {
            delete();
            listar(vista.TablaDatos);
            nuevo();
            
        }
    }
    
    public void add() {
        String nom = vista.txtNombres.getText();
        String paterno = vista.txtApePaterno.getText();
        String materno = vista.txtApeMaterno.getText();
        String dni = vista.txtDni.getText();
        String genero = vista.cbxGenero.getSelectedItem().toString();
        String correo = vista.txtCorreo.getText();
        String telef = vista.txtTelefono.getText();
        String direccion = vista.txtDireccion.getText();
        String fecha = vista.txtFecha.getText();
        
        p.setNombres(nom);
        p.setApe_Paterno(paterno);
        p.setApe_Materno(materno);
        p.setDni(dni);
        p.setGenero(genero);
        p.setCorreo(correo);
        p.setTelefono(telef);
        p.setDireccion(direccion);
        p.setFecha_Creacion(fecha);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Persona Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id, debe selecionar la opcion a Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNombres.getText();
            String paterno = vista.txtApePaterno.getText();
            String materno = vista.txtApeMaterno.getText();
            String dni = vista.txtDni.getText();
            String genero = vista.cbxGenero.getSelectedItem().toString();
            String correo = vista.txtCorreo.getText();
            String telef = vista.txtTelefono.getText();
            String direccion = vista.txtDireccion.getText();
            String fecha = vista.txtFecha.getText();
        
            
            p.setId(id);
            p.setNombres(nom);
            p.setApe_Paterno(paterno);
            p.setApe_Materno(materno);
            p.setDni(dni);
            p.setGenero(genero);
            p.setCorreo(correo);
            p.setTelefono(telef);
            p.setDireccion(direccion);
            p.setFecha_Creacion(fecha);
            int r = dao.actualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Persona Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        limpiarTabla();
    }
    
    
    public void delete() {
        int fila = vista.TablaDatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 0).toString());
            dao.eliminar(id);
            System.out.println("El Resultado es" + id);
            JOptionPane.showMessageDialog(vista, "Persona Eliminada con Exito...!!!");
        }
        limpiarTabla();
        
    }
    
    
    
    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombres.setText("");
        vista.txtApePaterno.setText("");
        vista.txtApeMaterno.setText("");
        vista.txtDni.setText("");
        vista.cbxGenero.setSelectedIndex(0);
        vista.txtDireccion.setText("");
        vista.txtTelefono.setText("");
        vista.txtCorreo.setText("");
        vista.txtFecha.setText("");
        vista.txtNombres.requestFocus();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Persona> lista = dao.listar();
        Object[] objeto = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombres();
            objeto[2] = lista.get(i).getApe_Paterno();
            objeto[3] = lista.get(i).getApe_Materno();
            objeto[4] = lista.get(i).getDni();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getCorreo();
            objeto[7] = lista.get(i).getTelefono();
            objeto[8] = lista.get(i).getDireccion();
            objeto[9] = lista.get(i).getFecha_Creacion();
            modelo.addRow(objeto);
        }
        vista.TablaDatos.setModel(modelo);
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
    
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.TablaDatos.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    
    
    
    
    void limpiarTabla() {
        for (int i = 0; i < vista.TablaDatos.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    
    
}
