package ferreira.filipe.jornaldeofertas.servicos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ferreira.filipe.jornaldeofertas.OfertasActivity;
import ferreira.filipe.jornaldeofertas.Utilitarios.AdapterPersonalizado;
import ferreira.filipe.jornaldeofertas.dao.ProdutosDAO;
import ferreira.filipe.jornaldeofertas.entidades.Produto;

/**
 * Created by Filipe on 12/08/2017.
 */

public class BuscarProdutosService extends AsyncTask {
    private AlertDialog alerta;
    List<Produto> produtosList = new ArrayList<>();
    String[] dadosAd;
    private Button  btn1,btn2,btn3;
    private  int pagina= 1;

    int progresso = 0;
    Context context;
    int categoria = 0;
    int i = 0;
    boolean resposta;
    ListView listView;
    Activity act;
    ProgressBar bar;

    public BuscarProdutosService(Activity act, Context context, int categoria, ListView listView, ProgressBar bar, Button btn1, Button btn2, Button btn3, int pagina){
        this.categoria = categoria;
        this.context = context;
        this.listView = listView;
        this.act = act;
        this.bar = bar;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.pagina = pagina;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        boolean pronto = false;
        ProdutosDAO produtosDAO = new ProdutosDAO();

        int linhaInicial = pagina*6;
        linhaInicial = linhaInicial-6;

        produtosList = produtosDAO.listar(categoria, context, linhaInicial);


        while(!produtosDAO.pronto && i < 25){
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
        bar.setProgress(25);
        bar.setVisibility(View.INVISIBLE);
        bar.setProgress(0);

        //regras para arganizar botões de páginação
        if(produtosList.size() > 0){
            if(pagina == 1){
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn2.setBackgroundColor(Color.argb(99,220,220,220));
                btn2.setText(String.valueOf(pagina));
                btn3.setVisibility(View.INVISIBLE);
                if(produtosList.size()>6){
                    btn3.setVisibility(View.VISIBLE);
                    btn3.setText(String.valueOf(pagina+1));
                }
            }else if(pagina > 1){
                btn1.setVisibility(View.VISIBLE);
                btn1.setText(String.valueOf(pagina-1));
                btn2.setVisibility(View.VISIBLE);
                btn2.setText(String.valueOf(pagina));
                btn2.setBackgroundColor(Color.argb(99,220,220,220));
                btn3.setVisibility(View.INVISIBLE);
                if(produtosList.size()> 6*pagina){
                    btn3.setVisibility(View.VISIBLE);
                    btn3.setText(String.valueOf(pagina+1));
                }

            }
        }


        if(!resposta) {
            //Cria o gerador do AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(act);
            //define o titulo
            builder.setTitle("Falha na conexão");
            //define a mensagem
            builder.setMessage("Verifique se sua internet está funcionando normalmente.");
            //define um botão como positivo
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent intent=new Intent(act.getBaseContext(), OfertasActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    act.startActivity(intent);
                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();

        }else{
            Toast.makeText(context, "Pesquisa realizada!", Toast.LENGTH_LONG).show();


            ArrayList<Produto> lisAux = new ArrayList<>();

            //controla o numero de resultados na tela para 6 ou menos
            if(produtosList.size() > 0) {
                //**
                //chamada da nossa implementação
                if(produtosList.size() > 6) {
                    for (int i = 0; i < 6; i++) {
                        lisAux.add(produtosList.get(i));
                    }

                    AdapterPersonalizado adapter =
                            new AdapterPersonalizado(lisAux, act);

                    listView.setAdapter(adapter);
                }else{
                    for (int i = 0; i < produtosList.size(); i++) {
                        lisAux.add(produtosList.get(i));
                    }

                    AdapterPersonalizado adapter =
                            new AdapterPersonalizado(lisAux, act);

                    listView.setAdapter(adapter);
                }
            }


        }

    }


}


