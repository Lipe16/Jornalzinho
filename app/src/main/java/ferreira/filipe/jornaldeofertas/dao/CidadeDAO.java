package ferreira.filipe.jornaldeofertas.dao;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.entidades.Cidade;
import ferreira.filipe.jornaldeofertas.entidades.Pessoa;

/**
 * Created by filipe on 31/07/17.
 */

public class CidadeDAO extends Cidade {
    ArrayList<Cidade> cidadesAd = new ArrayList<Cidade>();
    public boolean pronto = false;

    public ArrayList<Cidade> listar(Context context) {
        pronto = false;
        Ion.with(context)
                .load("http://techsolucoes.com/webservicos/listarcidades.php")
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                try{
                    for (int i = 0; i < result.size(); i++) {
                        JsonObject objAux = result.get(i).getAsJsonObject();
                        Cidade cidadeAux = new Cidade();

                        cidadeAux.setId(objAux.get("id").getAsInt());
                        cidadeAux.setNome(objAux.get("nome").getAsString());
                        cidadeAux.setEstado_id(objAux.get("estado_id").getAsInt());
                        cidadesAd.add(cidadeAux);

                    }
                    pronto = true;


                }catch(Exception ex){
                    Log.d("Erro", "sem internet");
                }
            }
        });
        return cidadesAd;
    }



}
