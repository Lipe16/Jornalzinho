package ferreira.filipe.jornaldeofertas.dao;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ferreira.filipe.jornaldeofertas.entidades.Cliente;
import ferreira.filipe.jornaldeofertas.entidades.Pessoa;

/**
 * Created by filipe on 31/07/17.
 */

public class PessoaDAO extends Pessoa {
    public boolean pronto = false;

    public boolean cadastrarPessoaCliente(int cidade_id, Cliente cliente, final Context context){
        pronto = false;

        Ion.with(context).load("http://techsolucoes.com/webservicos/inserirPessoaCliente.php")
                .setBodyParameter("nome", cliente.getPessoa().getNome().toString())
                .setBodyParameter("email", cliente.getEmail().toString())
                .setBodyParameter("telefone",cliente.getPessoa().getTelefone().toString())
                .setBodyParameter("rua", cliente.getPessoa().getRua().toString())
                .setBodyParameter("numero", String.valueOf(cliente.getPessoa().getNumero()))
                .setBodyParameter("bairro", cliente.getPessoa().getBairro().toString())
                .setBodyParameter("senha", cliente.getSenha())
                .setBodyParameter("cidade_id", String.valueOf(cidade_id))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if(result.get("retorno").getAsString().equals("YES")){


                            pronto = true;

                        }else{

                        }



                    }
                });

        return pronto;
    }
}
