package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dia_AsistenciaDao {
    
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Dia_Asistencia> datos = new ArrayList<>();
        String sql = "select * from dia_asistencia";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dia_Asistencia d = new Dia_Asistencia();
                d.setId(rs.getInt(1));
                d.setFecha(rs.getString(2));
                d.setEstado(rs.getString(3));
                d.setHorario(rs.getString(4));
                datos.add(d);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Dia_Asistencia dia) {
        int r=0;
        String sql="insert into dia_asistencia(Fecha, Estado, Codigo_Horario)values(?,?,(select id from horario where dia=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,dia.getFecha());
            ps.setString(2,dia.getEstado());
            ps.setString(3,dia.getHorario());
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

    public int actualizar(Dia_Asistencia dia) {
        int r=0;
        String sql="update dia_asistencia set Fecha=?, Estado=?, Codigo_Horario=(select id from horario where dia=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,dia.getFecha());
            ps.setString(2,dia.getEstado());
            ps.setString(3,dia.getHorario());
            ps.setInt(4, dia.getId());
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
        String sql="delete from dia_asistencia where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
