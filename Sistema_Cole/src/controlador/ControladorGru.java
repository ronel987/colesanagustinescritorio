package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import modelo.GrupoDao;
import vista.FormGrupo;


public class ControladorGru implements ActionListener {

    GrupoDao dao = new GrupoDao();
    Grupo g = new Grupo();
    FormGrupo vista = new FormGrupo();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorGru(FormGrupo v) {
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
                int cant = Integer.parseInt((String) vista.TablaDatos.getValueAt(fila, 1).toString());
                String docente = (String) vista.TablaDatos.getValueAt(fila, 2);
                String curso = (String) vista.TablaDatos.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtCant_Alumnos.setText("" + cant);
                vista.cbxDocente.setSelectedItem(docente);
                vista.cbxCurso.setSelectedItem(curso);
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
        int cant = Integer.parseInt(vista.txtCant_Alumnos.getText());
        String docente = vista.cbxDocente.getSelectedItem().toString();
        String curso = vista.cbxCurso.getSelectedItem().toString();
        g.setCant_Alumnos(cant);
        g.setDocente(docente);
        g.setCurso(curso);
        int r = dao.agregar(g);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Grupo Guardado con Exito.");
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
            int cant = Integer.parseInt(vista.txtCant_Alumnos.getText());
            String docente = vista.cbxDocente.getSelectedItem().toString();
            String curso = vista.cbxCurso.getSelectedItem().toString();
            g.setId(id);
            g.setCant_Alumnos(cant);
            g.setDocente(docente);
            g.setCurso(curso);
            int r = dao.actualizar(g);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Grupo Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Grupo Eliminado con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtCant_Alumnos.setText("");
        vista.cbxDocente.setSelectedIndex(0);
        vista.cbxCurso.setSelectedIndex(0);
        vista.txtCant_Alumnos.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Grupo> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getCant_Alumnos();
            objeto[2] = lista.get(i).getDocente();
            objeto[3] = lista.get(i).getCurso();
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
