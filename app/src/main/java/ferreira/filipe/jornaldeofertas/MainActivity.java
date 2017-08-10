package ferreira.filipe.jornaldeofertas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import ferreira.filipe.jornaldeofertas.Servicos.LoginService;

public class MainActivity extends AppCompatActivity {
    int id;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastro = (Button) findViewById(R.id.btnCadastro);
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);

        final SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        email = sharedPreferences.getString("email", null);


        //verifica se login é válido
        if(id == 0 && email == null){

        }else{

            startActivity(new Intent(getBaseContext(), OfertasActivity.class));

        }




        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CadastroActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



        //verifica se login é válido
        if(id == 0 && email == null){
        }else{
            startActivity(new Intent(getBaseContext(), OfertasActivity.class));
        }
    }
}
