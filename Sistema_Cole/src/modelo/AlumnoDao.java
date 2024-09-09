package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao{
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    //Alumno a = new Alumno();
    
    
    /*public Alumno listarID(String dni){
        
        String sql = "select * from alumno where Dni=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt(1));
                a.setNombres(rs.getString(2));
                a.setTelefono(rs.getString(3));
                a.setDni(rs.getString(4));
                a.setGenero(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return a;
    }*/


    public List listar() {
        List<Alumno> datos = new ArrayList<>();
        String sql = "select * from alumno";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt(1));
                a.setNombres(rs.getString(2));
                a.setTelefono(rs.getString(3));
                a.setDni(rs.getString(4));
                a.setGenero(rs.getString(5));
                a.setEstado(rs.getString(6));
                datos.add(a);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Alumno alu) {
        int r=0;
        String sql="insert into alumno(Nombres,Telefono,Dni,Genero, Estado)values(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,alu.getNombres());
            ps.setString(2,alu.getTelefono());
            ps.setString(3,alu.getDni());
            ps.setString(4,alu.getGenero());
            ps.setString(5,alu.getEstado());
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

    public int actualizar(Alumno alu) {
        int r=0;
        String sql="update alumno set Nombres=?,Telefono=?,Dni=?,Genero=?,Estado=? where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,alu.getNombres());
            ps.setString(2,alu.getTelefono());
            ps.setString(3,alu.getDni());
            ps.setString(4,alu.getGenero());
            ps.setString(5,alu.getEstado());
            ps.setInt(6, alu.getId());
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
        String sql="delete from alumno where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
}
