package ferreira.filipe.jornaldeofertas.servicos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import ferreira.filipe.jornaldeofertas.LogandoActivity;
import ferreira.filipe.jornaldeofertas.dao.PessoaDAO;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

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
    public boolean emailJaExiste = false;
    Button btnConfirma;



    public CadastrarPessoaClienteService(Cliente cliente, int cidade_id, Context context, ProgressBar bar, Button btnConfirma){
        this.context = context;
        this.bar = bar;
        this.cliente = cliente;
        this.cidade_id = cidade_id;
        this.btnConfirma = btnConfirma;
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

        emailJaExiste = pessoaDAO.emailJaExiste;
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
            btnConfirma.setClickable(true);
        }else{

            if(emailJaExiste) {

                Toast.makeText(context, "Email já existe!", Toast.LENGTH_LONG).show();
                btnConfirma.setClickable(true);

            }else{
                Toast.makeText(context, "Parabéns você está cadastrado!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, LogandoActivity.class);
                intent.putExtra("email", cliente.getEmail().toString());
                intent.putExtra("senha", cliente.getSenha().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }


    }


}
