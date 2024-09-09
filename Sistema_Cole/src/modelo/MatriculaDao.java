package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Matricula> datos = new ArrayList<>();
        String sql = "select * from matricula";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Matricula m = new Matricula();
                m.setId(rs.getInt(1));
                m.setFecha_Matricula(rs.getString(2));
                m.setEstado(rs.getString(3));
                m.setAlumno(rs.getString(4));
                m.setGrado(rs.getString(5));
                datos.add(m);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Matricula matri) {
        int r=0;
        String sql="insert into matricula(Fecha_Matricula, Estado, Codigo_Alumno, Codigo_Grado)values(?,?,(select id from alumno where nombres=?),(select id from grado where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,matri.getFecha_Matricula());
            ps.setString(2,matri.getEstado());
            ps.setString(3,matri.getAlumno());
            ps.setString(4,matri.getGrado());
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

    public int actualizar(Matricula matri) {
        int r=0;
        String sql="update matricula set Fecha_Matricula=?, Estado=?, Codigo_Alumno=(select id from alumno where nombres=?), Codigo_Grado=(select id from grado where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,matri.getFecha_Matricula());
            ps.setString(2,matri.getEstado());
            ps.setString(3,matri.getAlumno());
            ps.setString(4,matri.getGrado());
            ps.setInt(5, matri.getId());
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
        String sql="delete from matricula where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
