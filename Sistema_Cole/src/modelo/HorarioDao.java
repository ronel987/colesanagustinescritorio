package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HorarioDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Horario> datos = new ArrayList<>();
        String sql = "select * from horario";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Horario h = new Horario();
                h.setId(rs.getInt(1));
                h.setDia(rs.getString(2));
                h.setHora_Inicio(rs.getString(3));
                h.setHora_Fin(rs.getString(4));
                h.setGrupo(rs.getString(5));
                datos.add(h);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Horario hor) {
        int r=0;
        String sql="insert into horario(Dia, Hora_Inicio, Hora_Fin, Codigo_Grupo)values(?,?,?,(select id from grupo where cant_alumnos=?))";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,hor.getDia());
            ps.setString(2,hor.getHora_Inicio());
            ps.setString(3,hor.getHora_Fin());
            ps.setString(4,hor.getGrupo());
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

    public int actualizar(Horario hor) {
        int r=0;
        String sql="update horario set Dia=?, Hora_Inicio=?, Hora_Fin=?, Codigo_Grupo=(select id from grupo where cant_alumnos=?) where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,hor.getDia());
            ps.setString(2,hor.getHora_Inicio());
            ps.setString(3,hor.getHora_Fin());
            ps.setString(4,hor.getGrupo());
            ps.setInt(5, hor.getId());
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
        String sql="delete from horario where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
    
    
}
