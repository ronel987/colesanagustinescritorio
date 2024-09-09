package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Asistencia> datos = new ArrayList<>();
        String sql = "select * from asistencia";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Asistencia a = new Asistencia();
                a.setId(rs.getInt(1));
                a.setMarcacion(rs.getString(2));
                a.setAsistencia(rs.getString(3));
                a.setCodigo_Alumno(rs.getInt(4));
                a.setCodigo_Dia(rs.getInt(5));
                datos.add(a);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Asistencia asis) {
        int r=0;
        String sql="insert into asistencia(Marcacion, Asistencia, Codigo_Alumno, Codigo_Dia)values(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,asis.getMarcacion());
            ps.setString(2,asis.getAsistencia());
            ps.setInt(3,asis.getCodigo_Alumno());
            ps.setInt(4,asis.getCodigo_Dia());
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

    public int actualizar(Asistencia asis) {
        int r=0;
        String sql="update asistencia set Marcacion=?, Asistencia=?, Codigo_Alumno=?, Codigo_Dia=? where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,asis.getMarcacion());
            ps.setString(2,asis.getAsistencia());
            ps.setInt(3,asis.getCodigo_Alumno());
            ps.setInt(4,asis.getCodigo_Dia());
            ps.setInt(5, asis.getId());
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
        String sql="delete from asistencia where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
