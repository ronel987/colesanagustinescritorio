package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Asistencia;
import modelo.AsistenciaDao;
import vista.FormAsistencia;

public class ControladorAsis implements ActionListener {

    AsistenciaDao dao = new AsistenciaDao();
    Asistencia a = new Asistencia();
    FormAsistencia vista = new FormAsistencia();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorAsis(FormAsistencia v) {
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
                String marcacion = (String) vista.TablaDatos.getValueAt(fila, 1);
                String asistencia = (String) vista.TablaDatos.getValueAt(fila, 2);
                int cod_alu = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 3).toString());
                int cod_dia = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 4).toString());
                vista.txtId.setText("" + id);
                vista.txtMarcacion.setText(marcacion);
                vista.txtAsistencia.setText(asistencia);
                vista.txtCodAlu.setText("" + cod_alu);
                vista.txtCodDia.setText("" + cod_dia);
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
        String marcacion = vista.txtMarcacion.getText();
        String asistencia = vista.txtAsistencia.getText();
        int cod_alu = Integer.parseInt(vista.txtCodAlu.getText());
        int cod_dia = Integer.parseInt(vista.txtCodDia.getText());
        a.setMarcacion(marcacion);
        a.setAsistencia(asistencia);
        a.setCodigo_Alumno(cod_alu);
        a.setCodigo_Dia(cod_dia);
        int r = dao.agregar(a);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Asistencia Agregada con Exito.");
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
            String marcacion = vista.txtMarcacion.getText();
            String asistencia = vista.txtAsistencia.getText();
            int cod_alu = Integer.parseInt(vista.txtCodAlu.getText());
            int cod_dia = Integer.parseInt(vista.txtCodDia.getText());
            a.setId(id);
            a.setMarcacion(marcacion);
            a.setAsistencia(asistencia);
            a.setCodigo_Alumno(cod_alu);
            a.setCodigo_Dia(cod_dia);
            int r = dao.actualizar(a);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Asistencia Actualizada con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Asistencia Eliminada con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtMarcacion.setText("");
        vista.txtAsistencia.setText("");
        vista.txtCodAlu.setText("");
        vista.txtCodDia.setText("");
        vista.txtMarcacion.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Asistencia> lista = dao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getMarcacion();
            objeto[2] = lista.get(i).getAsistencia();
            objeto[3] = lista.get(i).getCodigo_Alumno();
            objeto[4] = lista.get(i).getCodigo_Dia();
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
