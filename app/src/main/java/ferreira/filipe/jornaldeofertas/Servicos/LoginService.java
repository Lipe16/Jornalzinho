package ferreira.filipe.jornaldeofertas.Servicos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import ferreira.filipe.jornaldeofertas.MainActivity;
import ferreira.filipe.jornaldeofertas.OfertasActivity;
import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.dao.ClienteDAO;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

/**
 * Created by filipe on 09/08/17.
 */

public class LoginService extends AsyncTask {
    private  ProgressBar progressBar;
    private Context context;
    private  Cliente cliente;
    int i = 0;
    boolean pronto;

    public LoginService(ProgressBar progressBar, Context context, Cliente cliente){
        this.progressBar = progressBar;
        this.context = context;
        this.cliente = cliente;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ClienteDAO clienteDAO = new ClienteDAO();

        cliente = clienteDAO.login(context, cliente);


        while(!clienteDAO.pronto && i < 12){
            try {
                Log.d("Thread","passou +1 vez");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

            i++;
        }

        Log.d("while login service",String.valueOf(cliente.getId()));

        publishProgress(i);
        pronto = clienteDAO.pronto;

        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(i);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        progressBar.setProgress(20);

        Log.d("LoginService", cliente.getEmail());
        Log.d("LoginService", cliente.getEmail());

        if(!pronto) {
            Toast.makeText(context, "Erro: Verifique se os dados estão corretos", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        }else{
            Toast.makeText(context, "Parabéns você está Logado!", Toast.LENGTH_LONG).show();

            SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", cliente.getId());
            editor.putString("email", cliente.getEmail());
            editor.commit();

            Intent intent = new Intent(context, OfertasActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


    }
}
