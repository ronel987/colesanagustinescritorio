package modelo;


public class Alumno {
    
    int id;
    String nombres;
    String telefono;
    String dni;
    String genero;
    String estado;

    public Alumno() {
    }

    public Alumno(int id, String nombres, String telefono, String dni, String genero, String estado) {
        this.id = id;
        this.nombres = nombres;
        this.telefono = telefono;
        this.dni = dni;
        this.genero = genero;
        this.estado = estado;
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

    
}
