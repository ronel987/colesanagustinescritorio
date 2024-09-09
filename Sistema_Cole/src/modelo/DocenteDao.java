package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocenteDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Docente> datos = new ArrayList<>();
        String sql = "select * from docente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Docente d = new Docente();
                d.setId(rs.getInt(1));
                d.setNombres(rs.getString(2));
                d.setTelefono(rs.getString(3));
                d.setDni(rs.getString(4));
                d.setCorreo(rs.getString(5));
                d.setProfesion(rs.getString(6));
                d.setGenero(rs.getString(7));
                d.setEstado(rs.getString(8));
                d.setPersona(rs.getString(9));
                datos.add(d);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Docente doc) {
        int r=0;
        String sql="insert into docente(Nombres, Telefono, Dni, Correo, Profesion, Genero, Estado, Codigo_Persona)values(?,?,?,?,?,?,?,(select id from persona where nombres=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,doc.getNombres());
            ps.setString(2,doc.getTelefono());
            ps.setString(3,doc.getDni());
            ps.setString(4,doc.getCorreo());
            ps.setString(5,doc.getProfesion());
            ps.setString(6,doc.getGenero());
            ps.setString(7,doc.getEstado());
            ps.setString(8, doc.getPersona());
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

    public int actualizar(Docente doc) {
        int r=0;
        String sql="update docente set Nombres=?,Telefono=?,Dni=?,Correo=?,Profesion=?,Genero=?,Estado=?, Codigo_Persona=(select id from persona where nombres=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,doc.getNombres());
            ps.setString(2,doc.getTelefono());
            ps.setString(3,doc.getDni());
            ps.setString(4,doc.getCorreo());
            ps.setString(5,doc.getProfesion());
            ps.setString(6,doc.getGenero());
            ps.setString(7,doc.getEstado());
            ps.setString(8, doc.getPersona());
            ps.setInt(9, doc.getId());
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
        String sql="delete from docente where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
    
}
