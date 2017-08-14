package ferreira.filipe.jornaldeofertas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ferreira.filipe.jornaldeofertas.Servicos.LoginService;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

public class LoginActivity extends AppCompatActivity {
    int id;
    Cliente cliente;
    EditText edEmail;
    EditText edSenha;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //verifica se cliente ja esta logado
        final SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        //verifica se login é válido
        if(id == 0){
        }else{
            startActivity(new Intent(getBaseContext(), OfertasActivity.class));
        }



        cliente = new Cliente();

        edEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        edSenha = (EditText) findViewById(R.id.editTextSenhaLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLoginEntrar);





        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!edEmail.getText().toString().equals("")) {
                    if (!edSenha.getText().toString().equals("")) {


                        progressBar.setVisibility(View.VISIBLE);
                        cliente.setEmail(edEmail.getText().toString());
                        cliente.setSenha(edSenha.getText().toString());

                        LoginService loginService = new LoginService(progressBar, getBaseContext(), cliente);
                        loginService.execute();
                    }else {
                        Toast.makeText(getBaseContext(), "ERRO: campos vazios", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getBaseContext(), "ERRO: campos vazios", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



        //verifica se login é válido
        if(id == 0){
        }else{
            startActivity(new Intent(getBaseContext(), OfertasActivity.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //verifica se login é válido
        if(id == 0){
        }else{
            startActivity(new Intent(getBaseContext(), OfertasActivity.class));
        }
    }
}
