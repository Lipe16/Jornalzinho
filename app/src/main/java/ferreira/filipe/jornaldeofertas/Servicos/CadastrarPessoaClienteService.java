package ferreira.filipe.jornaldeofertas.Servicos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import ferreira.filipe.jornaldeofertas.CadastroActivity;
import ferreira.filipe.jornaldeofertas.LogandoActivity;
import ferreira.filipe.jornaldeofertas.MainActivity;
import ferreira.filipe.jornaldeofertas.dao.PessoaDAO;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

import static ferreira.filipe.jornaldeofertas.R.id.spinnerCidade;

/**
 * Created by filipe on 05/08/17.
 */

public class CadastrarPessoaClienteService extends AsyncTask {

    private  Cliente cliente;
    private int cidade_id;
    private ProgressBar bar;
    private Context context;
    int progresso = 0;
    int i = 0;
    boolean resposta;



    public CadastrarPessoaClienteService(Cliente cliente, int cidade_id, Context context, ProgressBar bar){
        this.context = context;
        this.bar = bar;
        this.cliente = cliente;
        this.cidade_id = cidade_id;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        boolean pronto = false;


        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.cadastrarPessoaCliente(cidade_id, cliente, context);



        while(!pessoaDAO.pronto && i < 20){
            try {

                progresso++;
                Log.d("Thread","aguardando cadastrar");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }


            i++;
            publishProgress(i);
        }

        resposta = pessoaDAO.pronto;

        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        bar.setProgress(i);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        bar.setProgress(20);

        if(!resposta) {
            Toast.makeText(context, "Erro ao cadastrar: verifique sua conexão com a internet", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Parabéns você está cadastrado!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(context, LogandoActivity.class);
        intent.putExtra("email",cliente.getEmail().toString());
        intent.putExtra("senha",cliente.getSenha().toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
