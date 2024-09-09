package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Anio_EscolarDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Anio_Escolar> datos = new ArrayList<>();
        String sql = "select * from anio_escolar";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Anio_Escolar a = new Anio_Escolar();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setFecha_Inicio(rs.getString(3));
                a.setFecha_Fin(rs.getString(4));
                a.setEstado(rs.getString(5));
                datos.add(a);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Anio_Escolar anio) {
        int r=0;
        String sql="insert into anio_escolar(Nombre,Fecha_Inicio,Fecha_Fin,Estado)values(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,anio.getNombre());
            ps.setString(2,anio.getFecha_Inicio());
            ps.setString(3,anio.getFecha_Fin());
            ps.setString(4,anio.getEstado());
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

    public int actualizar(Anio_Escolar anio) {
        int r=0;
        String sql="update anio_escolar set Nombre=?,Fecha_Inicio=?,Fecha_Fin=?,Estado=? where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,anio.getNombre());
            ps.setString(2,anio.getFecha_Inicio());
            ps.setString(3,anio.getFecha_Fin());
            ps.setString(4,anio.getEstado());
            ps.setInt(5, anio.getId());
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
        String sql="delete from anio_escolar where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    /*public Anio_Escolar findById(int codigo_AE) {
        Anio_Escolar anioescolar = null;

        try {
            con=cn.getConnection();
            ps = con.prepareStatement("select * from anio_escolar where codigo_AE=?");
            ps.setInt(1, codigo_AE);
            rs = ps.executeQuery();

            if (rs.next()) {
                anioescolar = new Anio_Escolar();
                anioescolar.setId(rs.getInt("id"));
                anioescolar.setFecha_Inicio(rs.getString("fecha_Inicio"));
                anioescolar.setFecha_Fin(rs.getString("fecha_Fin"));
                anioescolar.setEstado(rs.getString("estado"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return anioescolar;
    }*/

    
}
