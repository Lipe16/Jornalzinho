package ferreira.filipe.jornaldeofertas;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.Servicos.CadastrarPessoaClienteService;
import ferreira.filipe.jornaldeofertas.Servicos.CidadePopulaSpinnerService;
import ferreira.filipe.jornaldeofertas.bean.VerificaCamposBean;
import ferreira.filipe.jornaldeofertas.dao.CidadeDAO;
import ferreira.filipe.jornaldeofertas.dao.PessoaDAO;
import ferreira.filipe.jornaldeofertas.entidades.Cliente;

/**
 * A login screen that offers login via email/password.
 */
public class CadastroActivity extends AppCompatActivity {

    Spinner spinnerCidade;
    int spinnerPosition;

    CidadeDAO cidadeDAO = new CidadeDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        // Set up the login form.

        spinnerCidade = (Spinner) findViewById(R.id.spinnerCidade);

        //campos
        final EditText edNome = (EditText) findViewById(R.id.editTextNome);
        final EditText edEmail = (EditText) findViewById(R.id.editTextEmail);
        final EditText edTelefone = (EditText) findViewById(R.id.editTextTelefone);
        final EditText edRua = (EditText) findViewById(R.id.editTextRua);
        final EditText edNumero = (EditText) findViewById(R.id.editTextNumero);
        final EditText edBairro = (EditText) findViewById(R.id.editTextBairro);
        final EditText edSenha = (EditText) findViewById(R.id.editTextSenha);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarSalvarCliente);
        final Button btnConfirma = (Button) findViewById(R.id.btnConfirma);

        CidadePopulaSpinnerService populaSpinner = new CidadePopulaSpinnerService(getBaseContext(), spinnerCidade, btnConfirma);
        populaSpinner.execute();




        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerCidade.getSelectedItem() != null) {
                    ArrayList<EditText> campos = new ArrayList<EditText>();
                    campos.add(edNome);
                    campos.add(edEmail);
                    campos.add(edTelefone);
                    campos.add(edRua);
                    campos.add(edNumero);
                    campos.add(edBairro);
                    campos.add(edSenha);

                    VerificaCamposBean verificaCamposBean = new VerificaCamposBean();
                    Cliente cliente = new Cliente();
                    PessoaDAO pessoaDAO = new PessoaDAO();

                    if(verificaCamposBean.verificaCampos(campos, getBaseContext())){
                        if(!spinnerCidade.getSelectedItem().equals("") && spinnerCidade.getSelectedItem() != null) {
                            btnConfirma.setClickable(false);
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.animate();
                            progressBar.setProgress(0);
                            progressBar.setMax(20);
                            progressBar.setFocusable(true);


                            cliente = verificaCamposBean.popularPessoaCliente(campos);

                            //pessoaDAO.cadastrarPessoaCliente(Integer.parseInt(spinnerCidade.getSelectedItem().toString()), cliente, getBaseContext());
                            CadastrarPessoaClienteService cadastrarPessoaClienteService = new CadastrarPessoaClienteService(cliente, Integer.parseInt(spinnerCidade.getSelectedItem().toString()), getBaseContext(), progressBar);
                            cadastrarPessoaClienteService.execute();
                        }else{

                            Toast.makeText(getBaseContext(), "ERRO: Verifique a conexão com a internet, antes de fazer o cadastro!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });



    }








}
