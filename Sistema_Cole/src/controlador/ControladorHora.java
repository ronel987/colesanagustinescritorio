package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Horario;
import modelo.HorarioDao;
import vista.FormHorario;


public class ControladorHora implements ActionListener{

    HorarioDao dao = new HorarioDao();
    Horario h = new Horario();
    FormHorario vista = new FormHorario();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorHora(FormHorario v) {
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
                String dia = (String) vista.TablaDatos.getValueAt(fila, 1);
                String h_inicio = (String) vista.TablaDatos.getValueAt(fila, 2);
                String h_fin = (String) vista.TablaDatos.getValueAt(fila, 3);
                String codigo = (String) vista.TablaDatos.getValueAt(fila, 4);
                vista.txtId.setText("" + id);
                vista.txtDia.setText(dia);
                vista.txtInicio.setText(h_inicio);
                vista.txtFin.setText(h_fin);
                vista.cbxGrupo.setSelectedItem(codigo);
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
        String dia = vista.txtDia.getText();
        String h_inicio = vista.txtInicio.getText();
        String h_fin = vista.txtFin.getText();
        String codigo = vista.cbxGrupo.getSelectedItem().toString();
        h.setDia(dia);
        h.setHora_Inicio(h_inicio);
        h.setHora_Fin(h_fin);
        h.setGrupo(codigo);
        int r = dao.agregar(h);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Horario Agregado con Exito.");
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
            String dia = vista.txtDia.getText();
            String h_inicio = vista.txtInicio.getText();
            String h_fin = vista.txtFin.getText();
            String codigo = vista.cbxGrupo.getSelectedItem().toString();
            h.setId(id);
            h.setDia(dia);
            h.setHora_Inicio(h_inicio);
            h.setHora_Fin(h_fin);
            h.setGrupo(codigo);
            int r = dao.actualizar(h);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Horario Actualizado con Exito.");
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
            JOptionPane.showMessageDialog(vista, "Horario Eliminado con Exito...!!!");
        }
        limpiarTabla();
        
    }
    
    
    
    void nuevo() {
        vista.txtId.setText("");
        vista.txtDia.setText("");
        vista.txtInicio.setText("");
        vista.txtFin.setText("");
        vista.cbxGrupo.setSelectedIndex(0);
        vista.txtDia.requestFocus();
    }
    
    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Horario> lista = dao.listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getDia();
            objeto[2] = lista.get(i).getHora_Inicio();
            objeto[3] = lista.get(i).getHora_Fin();
            objeto[4] = lista.get(i).getGrupo();
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
