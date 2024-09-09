package modelo;

public class Curso {
    
    int id;
    String nombre;
    double nota;
    String estado;
    String grado;

    public Curso() {
    }

    public Curso(int id, String nombre, double nota, String estado, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.estado = estado;
        this.grado = grado;
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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    
    
    
    
}
