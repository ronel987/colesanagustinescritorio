package modelo;


public class Criterio_Evaluacion {
    
    int id;
    String descripcion;
    String curso;

    public Criterio_Evaluacion() {
    }

    public Criterio_Evaluacion(int id, String descripcion, String curso) {
        this.id = id;
        this.descripcion = descripcion;
        this.curso = curso;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    
    
    
    
}
