package modelo;

public class Grupo {
    
    int id;
    int cant_Alumnos;
    String docente;
    String curso;

    public Grupo() {
    }

    public Grupo(int id, int cant_Alumnos, String docente, String curso) {
        this.id = id;
        this.cant_Alumnos = cant_Alumnos;
        this.docente = docente;
        this.curso = curso;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCant_Alumnos() {
        return cant_Alumnos;
    }

    public void setCant_Alumnos(int cant_Alumnos) {
        this.cant_Alumnos = cant_Alumnos;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    

    
    
    
    
}
