package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Matricula;
import modelo.MatriculaDao;
import vista.FormMatricula;


public class ControladorMatri implements ActionListener {

    MatriculaDao dao = new MatriculaDao();
    Matricula m = new Matricula();
    FormMatricula vista = new FormMatricula();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorMatri(FormMatricula v) {
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
                String alu = (String) vista.TablaDatos.getValueAt(fila, 3);
                String grado = (String) vista.TablaDatos.getValueAt(fila, 4);
                vista.txtId.setText("" + id);
                vista.txtFechaMar.setText(fecha);
                vista.cbxEstado.setSelectedItem(estado);
                vista.cbxAlumno.setSelectedItem(alu);
                vista.cbxGrado.setSelectedItem(grado);
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
        String fecha = vista.txtFechaMar.getText();
        String estado = vista.cbxEstado.getSelectedItem().toString();
        String alu = vista.cbxAlumno.getSelectedItem().toString();
        String grado = vista.cbxGrado.getSelectedItem().toString();
        m.setFecha_Matricula(fecha);
        m.setEstado(estado);
        m.setAlumno(alu);
        m.setGrado(grado);
        int r = dao.agregar(m);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Matricula Guardada con Exito.");
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
            String fecha = vista.txtFechaMar.getText();
            String estado = vista.cbxEstado.getSelectedItem().toString();
            String alu = vista.cbxAlumno.getSelectedItem().toString();
            String grado = vista.cbxGrado.getSelectedItem().toString();
            m.setId(id);
            m.setFecha_Matricula(fecha);
            m.setEstado(estado);
            m.setAlumno(alu);
            m.setGrado(grado);
            int r = dao.actualizar(m);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Matricula Actualizada con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Matricula Eliminada con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtFechaMar.setText("");
        vista.cbxEstado.setSelectedIndex(0);
        vista.cbxAlumno.setSelectedIndex(0);
        vista.cbxGrado.setSelectedIndex(0);
        vista.txtFechaMar.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Matricula> lista = dao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getFecha_Matricula();
            objeto[2] = lista.get(i).getEstado();
            objeto[3] = lista.get(i).getAlumno();
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
