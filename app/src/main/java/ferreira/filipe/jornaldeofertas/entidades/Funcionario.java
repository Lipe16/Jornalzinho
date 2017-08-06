package ferreira.filipe.jornaldeofertas.entidades;

/**
 * Created by filipe on 31/07/17.
 */

public class Funcionario {
    private int id;
    private String senha;
    private Pessoa pessoa;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
