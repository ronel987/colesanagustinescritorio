package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradoDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    Anio_Escolar anio=null;
    
    public List listar() {
        List<Grado> datos = new ArrayList<>();
        String sql = "select * from grado";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grado g = new Grado();
                g.setId(rs.getInt(1));
                g.setNombre(rs.getString(2));
                g.setEstado(rs.getString(3));
                g.setAnio(rs.getString(4));
                datos.add(g);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Grado gra) {
        int r=0;
        String sql="insert into grado(Nombre, Estado, Codigo_AE)values(?,?,(select id from anio_escolar where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,gra.getNombre());
            ps.setString(2,gra.getEstado());
            ps.setString(3,gra.getAnio());
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

    public int actualizar(Grado gra) {
        int r=0;
        String sql="update grado set Nombre=?, Estado=?, Codigo_AE=(select id from anio_escolar where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,gra.getNombre());
            ps.setString(2,gra.getEstado());
            ps.setString(3,gra.getAnio());
            ps.setInt(4, gra.getId());
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
        String sql="delete from grado where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
}
