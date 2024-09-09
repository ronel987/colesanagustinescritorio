package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import modelo.DocenteDao;
import vista.FormDocente;


public class ControladorDoc implements ActionListener{
    
    DocenteDao dao = new DocenteDao();
    Docente d = new Docente();
    FormDocente vista = new FormDocente();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    public ControladorDoc(FormDocente v) {
        this.vista = v;
        //this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.TablaDatos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {if (e.getSource() == vista.btnListar) {
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
                String telefono = (String) vista.TablaDatos.getValueAt(fila, 2);
                String dni = (String) vista.TablaDatos.getValueAt(fila, 3);
                String correo = (String) vista.TablaDatos.getValueAt(fila, 4);
                String profesion = (String) vista.TablaDatos.getValueAt(fila, 5);
                String genero = (String) vista.TablaDatos.getValueAt(fila, 6);
                String estado = (String) vista.TablaDatos.getValueAt(fila, 7);
                String persona = (String) vista.TablaDatos.getValueAt(fila, 8);
                vista.txtId.setText("" + id);
                vista.txtNombres.setText(nom);
                vista.txtTelefono.setText(telefono);
                vista.txtDni.setText(dni);
                vista.txtCorreo.setText(correo);
                vista.txtProfesion.setText(profesion);
                vista.cbxGenero.setSelectedItem(genero);
                vista.cbxEstado.setSelectedItem(estado);
                vista.cbxPersona.setSelectedItem(persona);
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
        String telef = vista.txtTelefono.getText();
        String dni = vista.txtDni.getText();
        String correo = vista.txtCorreo.getText();
        String profesion = vista.txtProfesion.getText();
        String genero = vista.cbxGenero.getSelectedItem().toString();
        String estado = vista.cbxEstado.getSelectedItem().toString();
        String persona = vista.cbxPersona.getSelectedItem().toString();
        d.setNombres(nom);
        d.setTelefono(telef);
        d.setDni(dni);
        d.setCorreo(correo);
        d.setProfesion(profesion);
        d.setGenero(genero);
        d.setEstado(estado);
        d.setPersona(persona);
        int r = dao.agregar(d);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Docente Guardado con Exito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id, debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNombres.getText();
            String telef = vista.txtTelefono.getText();
            String dni = vista.txtDni.getText();
            String correo = vista.txtCorreo.getText();
            String profesion = vista.txtProfesion.getText();
            String genero = vista.cbxGenero.getSelectedItem().toString();
            String estado = vista.cbxEstado.getSelectedItem().toString();
            String persona = vista.cbxPersona.getSelectedItem().toString();
            d.setId(id);
            d.setNombres(nom);
            d.setTelefono(telef);
            d.setDni(dni);
            d.setCorreo(correo);
            d.setProfesion(profesion);
            d.setGenero(genero);
            d.setEstado(estado);
            d.setPersona(persona);
            int r = dao.actualizar(d);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Docente Actualizado con Exito.");
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
            System.out.println("El Resultado es " + id);
            JOptionPane.showMessageDialog(vista, "Docente Eliminado con Exito...!!!");
        }
        limpiarTabla();
        
    }
    
    
    
    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombres.setText("");
        vista.txtTelefono.setText("");
        vista.txtDni.setText("");
        vista.txtCorreo.setText("");
        vista.txtProfesion.setText("");
        vista.cbxGenero.setSelectedIndex(0);
        vista.cbxEstado.setSelectedIndex(0);
        vista.cbxPersona.setSelectedIndex(0);
        vista.txtNombres.requestFocus();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Docente> lista = dao.listar();
        Object[] objeto = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombres();
            objeto[2] = lista.get(i).getTelefono();
            objeto[3] = lista.get(i).getDni();
            objeto[4] = lista.get(i).getCorreo();
            objeto[5] = lista.get(i).getProfesion();
            objeto[6] = lista.get(i).getGenero();
            objeto[7] = lista.get(i).getEstado();
            objeto[8] = lista.get(i).getPersona();
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
