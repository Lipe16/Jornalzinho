package ferreira.filipe.jornaldeofertas.entidades;

/**
 * Created by filipe on 31/07/17.
 */

public class Categoria {
    private int id;
    private String nome;



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

    public String toString(){
        return String.valueOf(this.id);//esse metodo é para o spiner retorna id da categoria
    }
}
