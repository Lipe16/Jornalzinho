package ferreira.filipe.jornaldeofertas.Servicos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ferreira.filipe.jornaldeofertas.LogandoActivity;
import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.Utilitarios.AdapterPersonalizado;
import ferreira.filipe.jornaldeofertas.dao.PessoaDAO;
import ferreira.filipe.jornaldeofertas.dao.ProdutosDAO;
import ferreira.filipe.jornaldeofertas.entidades.Produto;

/**
 * Created by Filipe on 12/08/2017.
 */

public class BuscarProdutosService extends AsyncTask {
    List<Produto> produtosAd = new ArrayList<>();
    String[] dadosAd;

    int progresso = 0;
    Context context;
    int categoria = 0;
    int i = 0;
    boolean resposta;
    ListView listView;
    Activity act;
    ProgressBar bar;

    public BuscarProdutosService(Activity act, Context context, int categoria, ListView listView, ProgressBar bar){
        this.categoria = categoria;
        this.context = context;
        this.listView = listView;
        this.act = act;
        this.bar = bar;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        boolean pronto = false;
        ProdutosDAO produtosDAO = new ProdutosDAO();
        produtosAd = produtosDAO.listar(categoria, context);


        while(!produtosDAO.pronto && i < 12){
            try {

                progresso++;
                Log.d("Thread","aguardando retornar produtos");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

            i++;
            publishProgress(i);
        }

        resposta = produtosDAO.pronto;
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
        bar.setProgress(12);
        bar.setVisibility(View.INVISIBLE);


        if(!resposta) {
            Toast.makeText(context, "Erro ao pesquisar: verifique conexão com a internet", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(context, "Pronto, ai estão os resultados que você pediu!", Toast.LENGTH_LONG).show();

            //chamada da nossa implementação
            AdapterPersonalizado adapter =
                    new AdapterPersonalizado(produtosAd, act);

            listView.setAdapter(adapter);
        }

    }


}


