package modelo;


public class Persona {
    
    int id;
    String nombres;
    String ape_Paterno;
    String ape_Materno;
    String dni;
    String genero;
    String correo;
    String telefono;
    String direccion;
    String fecha_Creacion;

    public Persona() {
    }

    public Persona(int id, String nombres, String ape_Paterno, String ape_Materno, String dni, String genero, String correo, String telefono, String direccion, String fecha_Creacion) {
        this.id = id;
        this.nombres = nombres;
        this.ape_Paterno = ape_Paterno;
        this.ape_Materno = ape_Materno;
        this.dni = dni;
        this.genero = genero;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_Creacion = fecha_Creacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApe_Paterno() {
        return ape_Paterno;
    }

    public void setApe_Paterno(String ape_Paterno) {
        this.ape_Paterno = ape_Paterno;
    }

    public String getApe_Materno() {
        return ape_Materno;
    }

    public void setApe_Materno(String ape_Materno) {
        this.ape_Materno = ape_Materno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_Creacion() {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(String fecha_Creacion) {
        this.fecha_Creacion = fecha_Creacion;
    }
    
    
    
    
}
