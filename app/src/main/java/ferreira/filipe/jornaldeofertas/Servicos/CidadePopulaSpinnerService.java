package ferreira.filipe.jornaldeofertas.Servicos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.CadastroActivity;
import ferreira.filipe.jornaldeofertas.bean.CidadeAdapterBean;
import ferreira.filipe.jornaldeofertas.dao.CidadeDAO;
import ferreira.filipe.jornaldeofertas.entidades.Cidade;

import static ferreira.filipe.jornaldeofertas.R.id.spinnerCidade;

/**
 * Created by filipe on 02/08/17.
 */

public class CidadePopulaSpinnerService extends AsyncTask{

    ArrayList<Cidade> cidadesList = new ArrayList<Cidade>();
    CidadeDAO cidadeDAO;
    private Context context;
    private Spinner spinner;
    private Button btnConfirma;


    public CidadePopulaSpinnerService(Context context, Spinner spinner, Button btnConfirma){
        this.context = context;
        this.spinner = spinner;
        this.btnConfirma = btnConfirma;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        cidadeDAO = new CidadeDAO();
        cidadesList = cidadeDAO.listar(context);


        int i = 0;

        while(!cidadeDAO.pronto && i < 12){
            try {
                Log.d("Thread","passou +1 vez");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }


            i++;
        }
        publishProgress(i);


        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        //super.onProgressUpdate(values);
        try {
            Cidade[] cidades = new Cidade[cidadesList.size()];

            for (int i = 0; i < cidadesList.size(); i++) {
                cidades[i] = cidadesList.get(i);
            }

            Log.d("teste: ", cidades[0].getNome());

            CidadeAdapterBean cidadeAdapterBean = new CidadeAdapterBean(context, android.R.layout.simple_spinner_item, cidades);
            spinner.setAdapter(cidadeAdapterBean);
        }catch (Exception ex){
            Toast.makeText(context, "ERRO: sem internet não é possivel cadastrar-se", Toast.LENGTH_SHORT).show();
            btnConfirma.setClickable(false);
        }


    }
}
