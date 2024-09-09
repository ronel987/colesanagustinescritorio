package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Seccion;
import modelo.SeccionDao;
import vista.FormSeccion;

public class ControladorSeccion implements ActionListener {

    SeccionDao dao = new SeccionDao();
    Seccion s = new Seccion();
    FormSeccion vista = new FormSeccion();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorSeccion(FormSeccion v) {
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
                int capacidad = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 2).toString());
                String estado = (String) vista.TablaDatos.getValueAt(fila, 3);
                String codigo = (String) vista.TablaDatos.getValueAt(fila, 4);
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nom);
                vista.txtCapacidad.setText("" + capacidad);
                vista.cbxEstado.setSelectedItem(estado);
                vista.cbxGrado.setSelectedItem(codigo);
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
        int capacidad = Integer.parseInt(vista.txtCapacidad.getText());
        String estado = vista.cbxEstado.getSelectedItem().toString();
        String codigo = vista.cbxGrado.getSelectedItem().toString();
        s.setNombre(nom);
        s.setCapacidad(capacidad);
        s.setEstado(estado);
        s.setGrado(codigo);
        int r = dao.agregar(s);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Seccion Guardado con Exito.");
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
            int capacidad = Integer.parseInt(vista.txtCapacidad.getText());
            String estado = vista.cbxEstado.getSelectedItem().toString();
            String codigo = vista.cbxGrado.getSelectedItem().toString();
            s.setId(id);
            s.setNombre(nom);
            s.setCapacidad(capacidad);
            s.setEstado(estado);
            s.setGrado(codigo);
            int r = dao.actualizar(s);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Seccion Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Seccion Eliminado con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtCapacidad.setText("");
        vista.cbxEstado.setSelectedIndex(0);
        vista.cbxGrado.setSelectedIndex(0);
        vista.txtNombre.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Seccion> lista = dao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getCapacidad();
            objeto[3] = lista.get(i).getEstado();
            objeto[4] = lista.get(i).getGrado();
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
