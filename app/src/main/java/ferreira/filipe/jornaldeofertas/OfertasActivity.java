package ferreira.filipe.jornaldeofertas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OfertasActivity extends AppCompatActivity {
    int id;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

        final SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        email = sharedPreferences.getString("email", null);


        TextView txvEmail = (TextView)findViewById(R.id.textViewEmail);
        Button btnSair = (Button) findViewById(R.id.btnSair);

        //verifica se login é válido
        if(id > 0 && email != null){
        }else{
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }

        txvEmail.setText(email);


        //deslogar
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
}
