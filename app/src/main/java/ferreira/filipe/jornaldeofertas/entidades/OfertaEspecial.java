package ferreira.filipe.jornaldeofertas.entidades;

/**
 * Created by filipe on 31/07/17.
 */

public class OfertaEspecial {
    private int id;
    private Produto produto;
    private boolean vizualidade;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isVizualidade() {
        return vizualidade;
    }

    public void setVizualidade(boolean vizualidade) {
        this.vizualidade = vizualidade;
    }
}
