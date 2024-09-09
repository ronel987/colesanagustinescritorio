package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Dia_Asistencia;
import modelo.Dia_AsistenciaDao;
import vista.FormDia_Asistencia;


public class Controlador_DiaAsisten implements ActionListener{

    Dia_AsistenciaDao dao = new Dia_AsistenciaDao();
    Dia_Asistencia d = new Dia_Asistencia();
    FormDia_Asistencia vista = new FormDia_Asistencia();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador_DiaAsisten(FormDia_Asistencia v) {
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
                String fecha = (String) vista.TablaDatos.getValueAt(fila, 1);
                String estado = (String) vista.TablaDatos.getValueAt(fila, 2);
                String codigo = (String) vista.TablaDatos.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtFecha.setText(fecha);
                vista.cbxEstado.setSelectedItem(estado);
                vista.cbxHorario.setSelectedItem(codigo);
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
        String fecha = vista.txtFecha.getText();
        String estado = vista.cbxEstado.getSelectedItem().toString();
        String codigo = vista.cbxHorario.getSelectedItem().toString();
        d.setFecha(fecha);
        d.setEstado(estado);
        d.setHorario(codigo);
        int r = dao.agregar(d);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Dia de Asistencia Agregado con Exito.");
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
            String fecha = vista.txtFecha.getText();
            String estado = vista.cbxEstado.getSelectedItem().toString();
            String codigo = vista.cbxHorario.getSelectedItem().toString();
            d.setId(id);
            d.setFecha(fecha);
            d.setEstado(estado);
            d.setHorario(codigo);
            int r = dao.actualizar(d);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Dia de Asistencia Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Dia de Asistencia Eliminado con Exito...!!!");
        }
        limpiarTabla();
        
    }
    
    
    
    void nuevo() {
        vista.txtId.setText("");
        vista.txtFecha.setText("");
        vista.cbxEstado.setSelectedIndex(0);
        vista.cbxHorario.setSelectedIndex(0);
        vista.txtFecha.requestFocus();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Dia_Asistencia> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getFecha();
            objeto[2] = lista.get(i).getEstado();
            objeto[3] = lista.get(i).getHorario();
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
