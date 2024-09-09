package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Curso> datos = new ArrayList<>();
        String sql = "select * from curso";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setNota(rs.getDouble(3));
                c.setEstado(rs.getString(4));
                c.setGrado(rs.getString(5));
                datos.add(c);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Curso cur) {
        int r=0;
        String sql="insert into curso(Nombre, Nota, Estado, Codigo_Grado)values(?,?,?,(select id from grado where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cur.getNombre());
            ps.setDouble(2, cur.getNota());
            ps.setString(3,cur.getEstado());
            ps.setString(4,cur.getGrado());
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

    public int actualizar(Curso cur) {
        int r=0;
        String sql="update curso set Nombre=?, Nota=?, Estado=?, Codigo_Grado=(select id from grado where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cur.getNombre());
            ps.setDouble(2, cur.getNota());
            ps.setString(3,cur.getEstado());
            ps.setString(4,cur.getGrado());
            ps.setInt(5, cur.getId());
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
        String sql="delete from curso where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
