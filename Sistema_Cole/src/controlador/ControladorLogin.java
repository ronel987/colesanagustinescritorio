package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.UsuarioDao;
import vista.FormLogin;
import vista.Principal;


public class ControladorLogin implements ActionListener {

    UsuarioDao dao = new UsuarioDao();
    Usuario u = new Usuario();
    FormLogin vista = new FormLogin();

    public ControladorLogin(FormLogin v) {
        this.vista = v;
        this.vista.btnIngresar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vista.btnIngresar ) {
            validar();
            nuevo();

        }
    }
    
    public void validar(){
        String clave = vista.txtClave.getText();
        String nombre = vista.txtUser.getText();
        if (vista.txtUser.getText().equals("") || vista.txtClave.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar datos en las cajas de texto");
            vista.txtUser.requestFocus();
        }else{
            u = dao.ValidarUsuario(nombre, clave);
            if (u.getNombre() != null && u.getClave() != null) {
                Principal p = new Principal();
                p.setVisible(true);
                vista.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Debe ingresar datos validos");
                vista.txtUser.requestFocus();
            }
        }
    }
    
    void nuevo() {
        vista.txtUser.setText("");
        vista.txtClave.setText("");
        vista.txtUser.requestFocus();
    }
    
    
}
