package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Grupo> datos = new ArrayList<>();
        String sql = "select * from grupo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grupo g = new Grupo();
                g.setId(rs.getInt(1));
                g.setCant_Alumnos(rs.getInt(2));
                g.setDocente(rs.getString(3));
                g.setCurso(rs.getString(4));
                datos.add(g);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Grupo gru) {
        int r=0;
        String sql="insert into grupo(Cant_Alumnos, Codigo_Docente, Codigo_Curso)values(?,(select id from docente where nombres=?),(select id from curso where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,gru.getCant_Alumnos());
            ps.setString(2, gru.getDocente());
            ps.setString(3,gru.getCurso());
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

    public int actualizar(Grupo gru) {
        int r=0;
        String sql="update grupo set Cant_Alumnos=?, Codigo_Docente=(select id from docente where nombres=?), Codigo_Curso=(select id from curso where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,gru.getCant_Alumnos());
            ps.setString(2, gru.getDocente());
            ps.setString(3,gru.getCurso());
            ps.setInt(4, gru.getId());
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
        String sql="delete from grupo where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
}
