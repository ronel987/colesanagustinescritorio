package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    
    public Usuario ValidarUsuario(String nombre, String clave){
        Usuario u = new Usuario();
        String sql = "select * from usuario where nombre=? and clave=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setClave(rs.getString(3));
                u.setTipo(rs.getString(4));
                u.setDescripcion(rs.getString(5));
                u.setEstado(rs.getString(6));
                u.setPersona(rs.getString(7));
            }
        }catch(Exception e){
            
        }
        
        return u;
    }
    
    
    
    public List listar() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "select * from usuario";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setClave(rs.getString(3));
                u.setTipo(rs.getString(4));
                u.setDescripcion(rs.getString(5));
                u.setEstado(rs.getString(6));
                u.setPersona(rs.getString(7));
                datos.add(u);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Usuario usu) {
        int r=0;
        String sql="insert into usuario(Nombre, Clave, Tipo, Descripcion, Estado, Codigo_Persona)values(?,?,?,?,?,(select id from persona where nombres=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,usu.getNombre());
            ps.setString(2,usu.getClave());
            ps.setString(3,usu.getTipo());
            ps.setString(4,usu.getDescripcion());
            ps.setString(5,usu.getEstado());
            ps.setString(6,usu.getPersona());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }

    public int actualizar(Usuario usu) {
        int r=0;
        String sql="update usuario set Nombre=?, Clave=?, Tipo=?, Descripcion=?, Estado=?, Codigo_Persona=(select id from persona where nombres=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,usu.getNombre());
            ps.setString(2,usu.getClave());
            ps.setString(3,usu.getTipo());
            ps.setString(4,usu.getDescripcion());
            ps.setString(5,usu.getEstado());
            ps.setString(6,usu.getPersona());
            ps.setInt(7, usu.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }


    public int eliminar(int id) {
        int r=0;
        String sql="delete from usuario where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    
    

    
}
