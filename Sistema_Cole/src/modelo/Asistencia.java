package modelo;


public class Asistencia {
    
    int id;
    String marcacion;
    String asistencia;
    int codigo_Alumno;
    int codigo_Dia;

    public Asistencia() {
    }

    public Asistencia(int id, String marcacion, String asistencia, int codigo_Alumno, int codigo_Dia) {
        this.id = id;
        this.marcacion = marcacion;
        this.asistencia = asistencia;
        this.codigo_Alumno = codigo_Alumno;
        this.codigo_Dia = codigo_Dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarcacion() {
        return marcacion;
    }

    public void setMarcacion(String marcacion) {
        this.marcacion = marcacion;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public int getCodigo_Alumno() {
        return codigo_Alumno;
    }

    public void setCodigo_Alumno(int codigo_Alumno) {
        this.codigo_Alumno = codigo_Alumno;
    }

    public int getCodigo_Dia() {
        return codigo_Dia;
    }

    public void setCodigo_Dia(int codigo_Dia) {
        this.codigo_Dia = codigo_Dia;
    }
    
    
    
}
