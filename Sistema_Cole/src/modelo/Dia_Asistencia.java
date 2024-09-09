package modelo;

public class Dia_Asistencia {
    
    int id;
    String fecha;
    String estado;
    String horario;

    public Dia_Asistencia() {
    }

    public Dia_Asistencia(int id, String fecha, String estado, String horario) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.horario = horario;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    
    
    
    
}
