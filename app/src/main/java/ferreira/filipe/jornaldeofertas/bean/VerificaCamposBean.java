package ferreira.filipe.jornaldeofertas.bean;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.entidades.Cliente;
import ferreira.filipe.jornaldeofertas.entidades.Pessoa;

/**
 * Created by filipe on 05/08/17.
 */

public class VerificaCamposBean {
    private Pessoa pessoa = new Pessoa();
    private Cliente cliente = new Cliente();
    private boolean retorno = false;

    public Boolean verificaCampos(ArrayList<EditText> campos, Context context){

        int count = 0;

        for(int i = 0; i < campos.size(); i++){
            campos.get(i).setText(campos.get(i).getText().toString().trim());
            if(!campos.get(i).getText().toString().equals("")){

                count++;

            }else{
                Toast.makeText(context,"ERRO: campo vazio: "+campos.get(i).getHint().toString(), Toast.LENGTH_SHORT ).show();
            }
        }

        if(count == campos.size()){
            retorno = true;
        }


        return retorno;
    }

    public Cliente popularPessoaCliente(ArrayList<EditText> campos){
        try {

            if (campos.size() == 7) {

                pessoa.setNome(campos.get(0).getText().toString());
                Log.d("Erro 0",campos.get(0).getText().toString());

                cliente.setEmail(campos.get(1).getText().toString());
                Log.d("Erro 0",campos.get(1).getText().toString());

                pessoa.setTelefone(campos.get(2).getText().toString());
                Log.d("Erro 0",campos.get(2).getText().toString());

                pessoa.setRua(campos.get(3).getText().toString());
                Log.d("Erro 0",campos.get(3).getText().toString());

                pessoa.setNumero(Integer.parseInt(campos.get(4).getText().toString()));
                Log.d("Erro 0",campos.get(4).getText().toString());

                pessoa.setBairro(campos.get(5).getText().toString());
                Log.d("Erro 0",campos.get(5).getText().toString());

                cliente.setSenha(campos.get(6).getText().toString());
                Log.d("Erro 0",campos.get(6).getText().toString());
            }
            cliente.setPessoa(pessoa);

        }catch (Exception ex){
            Log.d("Erro"," classe VerificaCamposBean");
        }


        return cliente;
    }

}
