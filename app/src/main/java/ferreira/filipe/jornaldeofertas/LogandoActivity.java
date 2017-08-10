package ferreira.filipe.jornaldeofertas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import ferreira.filipe.jornaldeofertas.Servicos.LoginService;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

public class LogandoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logando);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarLogando);

        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.animate();

        Cliente cliente = new Cliente();

        cliente.setEmail(getIntent().getExtras().get("email").toString());
        cliente.setSenha(getIntent().getExtras().get("senha").toString());

        LoginService loginService = new LoginService(progressBar, getBaseContext(), cliente);
        loginService.execute();

    }
}
