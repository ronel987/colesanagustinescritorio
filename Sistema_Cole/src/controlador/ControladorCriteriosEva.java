
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Criterio_Evaluacion;
import modelo.Criterio_EvaDao;
import vista.FormCriterio_Eva;


public class ControladorCriteriosEva implements ActionListener {

    Criterio_EvaDao dao = new Criterio_EvaDao();
    Criterio_Evaluacion c = new Criterio_Evaluacion();
    FormCriterio_Eva vista = new FormCriterio_Eva();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCriteriosEva(FormCriterio_Eva v) {
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
                String des = (String) vista.TablaDatos.getValueAt(fila, 1);
                String codigo = (String) vista.TablaDatos.getValueAt(fila, 2);
                vista.txtId.setText("" + id);
                vista.txtDescripcion.setText(des);
                vista.cbxCurso.setSelectedItem(codigo);
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
        String des = vista.txtDescripcion.getText();
        String codigo = vista.cbxCurso.getSelectedItem().toString();
        //int codigo = Integer.parseInt(vista.txtCodigo.getText());
        c.setDescripcion(des);
        c.setCurso(codigo);
        int r = dao.agregar(c);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Criterio de Evaluación Guardado con Exito.");
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
            String des = vista.txtDescripcion.getText();
            String codigo = vista.cbxCurso.getSelectedItem().toString();
            c.setId(id);
            c.setDescripcion(des);
            c.setCurso(codigo);
            int r = dao.actualizar(c);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Criterio de Evaluación Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Criterio de Evaluación Eliminado con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtDescripcion.setText("");
        vista.cbxCurso.setSelectedIndex(0);
        vista.txtDescripcion.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Criterio_Evaluacion> lista = dao.listar();
        Object[] objeto = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getDescripcion();
            objeto[2] = lista.get(i).getCurso();
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
