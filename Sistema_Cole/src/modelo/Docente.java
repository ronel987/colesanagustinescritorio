package modelo;


public class Docente {
    
    int id;
    String nombres;
    String telefono;
    String dni;
    String correo;
    String profesion;
    String genero;
    String estado;
    String persona;

    public Docente() {
    }

    public Docente(int id, String nombres, String telefono, String dni, String correo, String profesion, String genero, String estado, String persona) {
        this.id = id;
        this.nombres = nombres;
        this.telefono = telefono;
        this.dni = dni;
        this.correo = correo;
        this.profesion = profesion;
        this.genero = genero;
        this.estado = estado;
        this.persona = persona;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    
    
    
    
}
