package ferreira.filipe.jornaldeofertas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ferreira.filipe.jornaldeofertas.Servicos.BuscarProdutosService;

public class OfertasActivity extends AppCompatActivity {
    int id;
    String email;
    private Activity atividade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

        atividade = this;
        verificaLogin();



        //apresenta email do cliente na tela
        TextView txvEmail = (TextView) findViewById(R.id.textViewEmail);
        txvEmail.setText(email);

        carregaComponentes();


    }


    @Override
    protected void onResume() {
        super.onResume();
        verificaLogin();
        carregaComponentes();


    }


    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }







    //carrega os botões da tela e seus respoctivos listeners
    public void carregaComponentes() {
        final Button btnCategoriaAlimentos = (Button) findViewById(R.id.btnCategoriaAlimentos);
        final Button btnCategoriaMedicamentos = (Button) findViewById(R.id.btnCategoriaMedicamentos);
        final Button btnCategoriaVitaminas = (Button) findViewById(R.id.btnCategoriaVitaminas);
        final Button btnCategoriaOutros = (Button) findViewById(R.id.btnCategoriaOutros);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBarOfertas);
        Button btnSair = (Button) findViewById(R.id.btnSair);

        final ListView listView = (ListView) findViewById(R.id.listViewProdutos);

        bar.setMax(12);
        bar.animate();

        // no clique deste botão, produtos são apresentados na tela
        btnCategoriaAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pressionou botao", "categoria alimentos");
                bar.setVisibility(View.VISIBLE);
                BuscarProdutosService buscarProdutosService = new BuscarProdutosService(atividade, getBaseContext(), Integer.parseInt(btnCategoriaAlimentos.getHint().toString()), listView, bar);
                buscarProdutosService.execute();
            }
        });

        btnCategoriaMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pressionou botao", "categoria");
                bar.setVisibility(View.VISIBLE);
                BuscarProdutosService buscarProdutosService = new BuscarProdutosService(atividade, getBaseContext(), Integer.parseInt(btnCategoriaMedicamentos.getHint().toString()), listView, bar);
                buscarProdutosService.execute();
            }
        });

        btnCategoriaVitaminas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pressionou botao", "categoria");
                bar.setVisibility(View.VISIBLE);
                BuscarProdutosService buscarProdutosService = new BuscarProdutosService(atividade, getBaseContext(), Integer.parseInt(btnCategoriaVitaminas.getHint().toString()), listView, bar);
                buscarProdutosService.execute();
            }
        });

        btnCategoriaOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pressionou botao", "categoria");
                bar.setVisibility(View.VISIBLE);
                BuscarProdutosService buscarProdutosService = new BuscarProdutosService(atividade, getBaseContext(), Integer.parseInt(btnCategoriaOutros.getHint().toString()), listView, bar);
                buscarProdutosService.execute();
            }
        });


        //deslogar
        final SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });

    }

    public void verificaLogin(){
        //verifica se cliente já logou no sistenma
        final SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        email = sharedPreferences.getString("email", null);
        //verifica se login é válido
        if (id > 0 && email != null) {
        } else {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
    }
}