package modelo;


public class Matricula {
    
    int id;
    String fecha_Matricula;
    String estado;
    String alumno;
    String grado;

    public Matricula() {
    }

    public Matricula(int id, String fecha_Matricula, String estado, String alumno, String grado) {
        this.id = id;
        this.fecha_Matricula = fecha_Matricula;
        this.estado = estado;
        this.alumno = alumno;
        this.grado = grado;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_Matricula() {
        return fecha_Matricula;
    }

    public void setFecha_Matricula(String fecha_Matricula) {
        this.fecha_Matricula = fecha_Matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    
    
}
