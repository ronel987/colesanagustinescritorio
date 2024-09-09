package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Criterio_EvaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    Anio_Escolar anio=null;
    
    public List listar() {
        List<Criterio_Evaluacion> datos = new ArrayList<>();
        String sql = "select * from criterio_evaluacion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Criterio_Evaluacion c = new Criterio_Evaluacion();
                c.setId(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                c.setCurso(rs.getString(3));
                datos.add(c);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Criterio_Evaluacion cri) {
        int r=0;
        String sql="insert into criterio_evaluacion(Descripcion, Codigo_Curso)values(?,(select id from curso where nombre=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cri.getDescripcion());
            ps.setString(2,cri.getCurso());
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

    public int actualizar(Criterio_Evaluacion cri) {
        int r=0;
        String sql="update criterio_evaluacion set Descripcion=?, Codigo_Curso=(select id from curso where nombre=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cri.getDescripcion());
            ps.setString(2,cri.getCurso());
            ps.setInt(3, cri.getId());
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
        String sql="delete from criterio_evaluacion where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
