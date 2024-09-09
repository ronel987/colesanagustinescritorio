package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    
    public List listar() {
        List<Persona> datos = new ArrayList<>();
        String sql = "select * from persona";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setApe_Paterno(rs.getString(3));
                p.setApe_Materno(rs.getString(4));
                p.setDni(rs.getString(5));
                p.setGenero(rs.getString(6));
                p.setCorreo(rs.getString(7));
                p.setTelefono(rs.getString(8));
                p.setDireccion(rs.getString(9));
                p.setFecha_Creacion(rs.getString(10));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }


    public int agregar(Persona per) {
        int r=0;
        String sql="insert into persona(Nombres,Ape_Paterno,Ape_Materno,Dni,Genero,Correo,Telefono,Direccion,Fecha_Creacion)values(?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,per.getNombres());
            ps.setString(2,per.getApe_Paterno());
            ps.setString(3,per.getApe_Materno());
            ps.setString(4,per.getDni());
            ps.setString(5,per.getGenero());
            ps.setString(6,per.getCorreo());
            ps.setString(7,per.getTelefono());
            ps.setString(8,per.getDireccion());
            ps.setString(9,per.getFecha_Creacion());
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

    public int actualizar(Persona per) {
        int r=0;
        String sql="update persona set Nombres=?,Ape_Paterno=?,Ape_Materno=?,Dni=?,Genero=?,Correo=?,Telefono=?,Direccion=?,Fecha_Creacion=? where Id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,per.getNombres());
            ps.setString(2,per.getApe_Paterno());
            ps.setString(3,per.getApe_Materno());
            ps.setString(4,per.getDni());
            ps.setString(5,per.getGenero());
            ps.setString(6,per.getCorreo());
            ps.setString(7,per.getTelefono());
            ps.setString(8,per.getDireccion());
            ps.setString(9,per.getFecha_Creacion());
            ps.setInt(10, per.getId());
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
        String sql="delete from persona where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    
    
}
