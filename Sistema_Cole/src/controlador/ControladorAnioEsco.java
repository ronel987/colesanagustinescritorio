package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Anio_Escolar;
import modelo.Anio_EscolarDao;
import vista.FormAnioEscolar;


public class ControladorAnioEsco implements ActionListener{

    Anio_EscolarDao dao = new Anio_EscolarDao();
    Anio_Escolar a = new Anio_Escolar();
    FormAnioEscolar vista = new FormAnioEscolar();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorAnioEsco(FormAnioEscolar v) {
        this.vista = v;
        //this.vista.btnListar.addActionListener(this);
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
                String inicio = (String) vista.TablaDatos.getValueAt(fila, 2);
                String fin = (String) vista.TablaDatos.getValueAt(fila, 3);
                String estado = (String) vista.TablaDatos.getValueAt(fila, 4);
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nom);
                vista.txtInicio.setText(inicio);
                vista.txtFin.setText(fin);
                vista.cbxEstado.setSelectedItem(estado);
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
        String nom = vista.txtNombre.getText();
        String inicio = vista.txtInicio.getText();
        String fin = vista.txtFin.getText();
        String estado = vista.cbxEstado.getSelectedItem().toString();
        a.setNombre(nom);
        a.setFecha_Inicio(inicio);
        a.setFecha_Fin(fin);
        a.setEstado(estado);
        int r = dao.agregar(a);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Anio Escolar Guardado con Exito.");
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
            String nom = vista.txtNombre.getText();
        String inicio = vista.txtInicio.getText();
        String fin = vista.txtFin.getText();
        String estado = vista.cbxEstado.getSelectedItem().toString();
            a.setId(id);
            a.setNombre(nom);
            a.setFecha_Inicio(inicio);
            a.setFecha_Fin(fin);
            a.setEstado(estado);
            int r = dao.actualizar(a);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Anio Escolar Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Anio Escolar Eliminado con Exito...!!!");
        }
        limpiarTabla();
        
    }
    
    
    
    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtInicio.setText("");
        vista.txtFin.setText("");
        vista.cbxEstado.setSelectedIndex(0);
        vista.txtNombre.requestFocus();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Anio_Escolar> lista = dao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getFecha_Inicio();
            objeto[3] = lista.get(i).getFecha_Fin();
            objeto[4] = lista.get(i).getEstado();
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
