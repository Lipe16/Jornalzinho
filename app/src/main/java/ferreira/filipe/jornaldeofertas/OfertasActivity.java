package ferreira.filipe.jornaldeofertas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import ferreira.filipe.jornaldeofertas.bean.CategoriaAdapterBean;
import ferreira.filipe.jornaldeofertas.servicos.BuscarProdutosService;
import ferreira.filipe.jornaldeofertas.servicos.PopularSpinnerCategoriaService;

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


    }


    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }







    //carrega os botões da tela e seus respoctivos listeners
    public void carregaComponentes() {
        /*
        final Button btnCategoriaAlimentos = (Button) findViewById(R.id.btnCategoriaAlimentos);
        final Button btnCategoriaMedicamentos = (Button) findViewById(R.id.btnCategoriaMedicamentos);
        final Button btnCategoriaVitaminas = (Button) findViewById(R.id.btnCategoriaVitaminas);
        final Button btnCategoriaOutros = (Button) findViewById(R.id.btnCategoriaOutros);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBarOfertas);


         */
        final Spinner spinerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        final ProgressBar barCategoria = (ProgressBar) findViewById(R.id.progressBarCategoria);
        final ProgressBar barOfertas = (ProgressBar) findViewById(R.id.progressBarOfertas);
        Button btnSair = (Button) findViewById(R.id.btnSair);
        final ListView listView = (ListView) findViewById(R.id.listViewProdutos);

        barOfertas.setMax(15);
        barOfertas.animate();
        PopularSpinnerCategoriaService popularSpinnerCategoriaService = new PopularSpinnerCategoriaService(getBaseContext(), spinerCategoria, barCategoria, barOfertas, atividade,  listView);
        popularSpinnerCategoriaService.execute();


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