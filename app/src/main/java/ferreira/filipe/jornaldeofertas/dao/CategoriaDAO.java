package ferreira.filipe.jornaldeofertas.dao;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.entidades.Categoria;
import ferreira.filipe.jornaldeofertas.entidades.Cidade;

/**
 * Created by Filipe on 15/08/2017.
 */

public class CategoriaDAO extends Categoria{
    public boolean pronto = false;
    ArrayList<Categoria> categoriaList = new ArrayList<Categoria>();

    public ArrayList<Categoria> listar(Context context) {
        pronto = false;
        Ion.with(context)
                .load("http://techsolucoes.com/webservicos/listarCategoria.php")
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                try{
                    for (int i = 0; i < result.size(); i++) {

                        JsonObject objAux = result.get(i).getAsJsonObject();
                        Categoria categoriaAux = new CategoriaDAO();

                        categoriaAux.setId(objAux.get("id").getAsInt());
                        categoriaAux.setNome(objAux.get("nome").getAsString());
                        categoriaList.add(categoriaAux);

                    }
                    pronto = true;


                }catch(Exception ex){
                    Log.d("Erro", "sem internet");
                }
            }
        });
        return categoriaList;
    }

}
