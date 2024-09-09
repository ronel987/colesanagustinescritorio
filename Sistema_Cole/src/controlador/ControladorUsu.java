package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.UsuarioDao;
import vista.FormUsuario;

public class ControladorUsu implements ActionListener {

    UsuarioDao dao = new UsuarioDao();
    Usuario u = new Usuario();
    FormUsuario vista = new FormUsuario();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorUsu(FormUsuario v) {
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
                String clave = (String) vista.TablaDatos.getValueAt(fila, 2);
                String tipo = (String) vista.TablaDatos.getValueAt(fila, 3);
                String descripcion = (String) vista.TablaDatos.getValueAt(fila, 4);
                String estado = (String) vista.TablaDatos.getValueAt(fila, 5);
                String codigo = (String) vista.TablaDatos.getValueAt(fila, 6);
                
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nom);
                vista.txtClave.setText(clave);
                vista.txtTipo.setText(tipo);
                vista.txtDescripcion.setText(descripcion);
                vista.cbxEstado.setSelectedItem(estado);
                vista.cbxPersona.setSelectedItem(codigo);

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
        String clave = vista.txtClave.getText();
        String tipo = vista.txtTipo.getText();
        String descripcion = vista.txtDescripcion.getText();
        String estado = vista.cbxEstado.getSelectedItem().toString();
        String codigo = vista.cbxPersona.getSelectedItem().toString();
        //int codigo = Integer.parseInt(vista.txtCodigo.getText());

        u.setNombre(nom);
        u.setClave(clave);
        u.setTipo(tipo);
        u.setDescripcion(descripcion);
        u.setEstado(estado);
        u.setPersona(codigo);
        int r = dao.agregar(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Guardado con Exito.");
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
            String nom = vista.txtNombre.getText();
            String clave = vista.txtClave.getText();
            String tipo = vista.txtTipo.getText();
            String descripcion = vista.txtDescripcion.getText();
            String estado = vista.cbxEstado.getSelectedItem().toString();
            String codigo = vista.cbxPersona.getSelectedItem().toString();

            u.setId(id);
            u.setNombre(nom);
            u.setClave(clave);
            u.setTipo(tipo);
            u.setDescripcion(descripcion);
            u.setEstado(estado);
            u.setPersona(codigo);
            int r = dao.actualizar(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Usuario Eliminada con Exito...!!!");
        }
        limpiarTabla();

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtClave.setText("");
        vista.txtTipo.setText("");
        vista.txtDescripcion.setText("");
        vista.cbxEstado.setSelectedIndex(0);
        vista.cbxPersona.setSelectedIndex(0);
        vista.txtNombre.requestFocus();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Usuario> lista = dao.listar();
        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getClave();
            objeto[3] = lista.get(i).getTipo();
            objeto[4] = lista.get(i).getDescripcion();
            objeto[5] = lista.get(i).getEstado();
            objeto[6] = lista.get(i).getPersona();
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
