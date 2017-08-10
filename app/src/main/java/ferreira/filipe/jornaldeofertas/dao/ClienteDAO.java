package ferreira.filipe.jornaldeofertas.dao;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ferreira.filipe.jornaldeofertas.entidades.Cidade;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;
import ferreira.filipe.jornaldeofertas.entidades.Usuario;

/**
 * Created by filipe on 09/08/17.
 */

public class ClienteDAO extends Cliente {


    public boolean pronto;

    public Cliente login(Context context, final Cliente cliente){
        Log.d("Cliente DAO",cliente.getEmail());
        pronto = false;
        Ion.with(context)
                .load("http://techsolucoes.com/webservicos/clienteLogar.php")
                .setBodyParameter("email", cliente.getEmail())
                .setBodyParameter("senha", cliente.getSenha())
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {

                try{
                    for (int i = 0; i < result.size(); i++) {
                        JsonObject objAux = result.get(i).getAsJsonObject();


                        cliente.setId(objAux.get("id").getAsInt());
                        cliente.setEmail(objAux.get("email").getAsString());

                        Log.d("ClienteDAO", String.valueOf(cliente.getId()));

                        //verifica se dados vieram corretamente
                        if(cliente.getId() > 0){
                            pronto = true;
                        }

                    }




                }catch(Exception ex){
                    Log.d("Erro em ClienteDAO", "problemas na requisição");
                }
            }
        });


        return cliente;
    }
}
