package ferreira.filipe.jornaldeofertas.entidades;

/**
 * Created by filipe on 31/07/17.
 */

public class Cidade {
    private int id;
    private String nome;
    private int estado_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }
}
