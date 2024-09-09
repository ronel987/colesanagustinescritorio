package modelo;


public class Horario {
    
    int id;
    String dia;
    String hora_Inicio;
    String hora_Fin;
    String grupo;

    public Horario() {
    }

    public Horario(int id, String dia, String hora_Inicio, String hora_Fin, String grupo) {
        this.id = id;
        this.dia = dia;
        this.hora_Inicio = hora_Inicio;
        this.hora_Fin = hora_Fin;
        this.grupo = grupo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora_Inicio() {
        return hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio) {
        this.hora_Inicio = hora_Inicio;
    }

    public String getHora_Fin() {
        return hora_Fin;
    }

    public void setHora_Fin(String hora_Fin) {
        this.hora_Fin = hora_Fin;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    
    
    
}
