package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeccionDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    Anio_Escolar anio=null;
    
    public List listar() {
        List<Seccion> datos = new ArrayList<>();
        String sql = "select * from seccion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Seccion s = new Seccion();
                s.setId(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setCapacidad(rs.getInt(3));
                s.setEstado(rs.getString(4));
                s.setGrado(rs.getString(5));
                datos.add(s);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Seccion sec) {
        int r=0;
        String sql="insert into seccion(Nombre, Capacidad, Estado, Codigo_Grado)values(?,?,?,(select id from grado where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,sec.getNombre());
            ps.setInt(2, sec.getCapacidad());
            ps.setString(3,sec.getEstado());
            ps.setString(4,sec.getGrado());
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

    public int actualizar(Seccion sec) {
        int r=0;
        String sql="update seccion set Nombre=?, Capacidad=?, Estado=?, Codigo_Grado=(select id from grado where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,sec.getNombre());
            ps.setInt(2, sec.getCapacidad());
            ps.setString(3,sec.getEstado());
            ps.setString(4,sec.getGrado());
            ps.setInt(5, sec.getId());
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
        String sql="delete from seccion where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
