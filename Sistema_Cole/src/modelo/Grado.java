package modelo;


public class Grado {
    
    int id;
    String nombre;
    String estado;
    String anio;

    public Grado() {
    }

    public Grado(int id, String nombre, String estado, String anio) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.anio = anio;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    

    
    
}
