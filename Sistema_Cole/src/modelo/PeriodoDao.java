package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeriodoDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    
    public List listar() {
        List<Periodo> datos = new ArrayList<>();
        String sql = "select * from periodo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Periodo p = new Periodo();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFecha_Inicio(rs.getString(3));
                p.setFecha_Fin(rs.getString(4));
                p.setEstado(rs.getString(5));
                p.setAnio(rs.getString(6));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Periodo peri) {
        int r=0;
        String sql="insert into periodo(Nombre,Fecha_Inicio,Fecha_Fin,Estado,Codigo_AE)values(?,?,?,?,(select id from anio_escolar where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,peri.getNombre());
            ps.setString(2,peri.getFecha_Inicio());
            ps.setString(3,peri.getFecha_Fin());
            ps.setString(4,peri.getEstado());
            ps.setString(5,peri.getAnio());
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

    public int actualizar(Periodo peri) {
        int r=0;
        String sql="update periodo set Nombre=?, Fecha_Inicio=?, Fecha_Fin=?, Estado=?, Codigo_AE=(select id from anio_escolar where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,peri.getNombre());
            ps.setString(2,peri.getFecha_Inicio());
            ps.setString(3,peri.getFecha_Fin());
            ps.setString(4,peri.getEstado());
            ps.setString(5,peri.getAnio());
            ps.setInt(6, peri.getId());
            
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
        String sql="delete from periodo where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
}
