package ferreira.filipe.jornaldeofertas.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ferreira.filipe.jornaldeofertas.entidades.Cidade;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;
import ferreira.filipe.jornaldeofertas.entidades.Pessoa;
import ferreira.filipe.jornaldeofertas.entidades.Produto;

/**
 * Created by Filipe on 11/08/2017.
 */

public class ProdutosDAO extends Pessoa {
    List<Produto> produtosList = new ArrayList<>();
    public boolean pronto = false;

    public List<Produto> listar(int categoria, Context context) {
        pronto = false;
        Ion.with(context)
                .load("http://techsolucoes.com/webservicos/listarProdutos.php")
                .setBodyParameter("categoria", String.valueOf(categoria))
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                try{
                    for (int i = 0; i < result.size(); i++) {
                        JsonObject objAux = result.get(i).getAsJsonObject();
                        Produto produtoAux = new Produto();

                        produtoAux.setId(objAux.get("id").getAsInt());
                        produtoAux.setNome(objAux.get("nome").getAsString());
                        produtoAux.setPreco(objAux.get("preco").getAsDouble());
                        produtoAux.setImagem(objAux.get("imagem").getAsString());



                        produtosList.add(produtoAux);

                    }
                    pronto = true;


                }catch(Exception ex){
                    Log.d("Erro", "sem internet");
                }
            }
        });
        return produtosList;
    }

}
