package ferreira.filipe.jornaldeofertas.Servicos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import ferreira.filipe.jornaldeofertas.CadastroActivity;
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



        while(!pessoaDAO.pronto && i < 12){
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

        bar.setProgress(15);


        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
