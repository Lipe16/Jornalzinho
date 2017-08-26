package ferreira.filipe.jornaldeofertas.servicos;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.bean.CategoriaAdapterBean;
import ferreira.filipe.jornaldeofertas.bean.CidadeAdapterBean;
import ferreira.filipe.jornaldeofertas.dao.CategoriaDAO;
import ferreira.filipe.jornaldeofertas.entidades.Categoria;

/**
 * Created by Filipe on 15/08/2017.
 */

public class PopularSpinnerCategoriaService extends AsyncTask {

        ArrayList<Categoria> categoriaList = new ArrayList<Categoria>();
        private Context context;
        private Spinner spinner;
        private ProgressBar barCategoria, barOfertas;
        private Activity atividade;
        private ListView listView;
        private Button  btn1,btn2,btn3;
        private  int pagina= 1;
        int x = 0;




        public PopularSpinnerCategoriaService(Context context, Spinner spinner, ProgressBar barCategoria, ProgressBar barOfertas, Activity atividade, ListView listView, Button btn1,Button btn2,Button btn3, int pagina){
            this.context = context;
            this.spinner = spinner;
            this.barCategoria = barCategoria;
            this.barOfertas = barOfertas;
            this.atividade = atividade;
            this.listView = listView;
            this.btn1 = btn1;
            this.btn2 = btn2;
            this.btn3 = btn3;
            this.pagina = pagina;

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaList = categoriaDAO.listar(context);


            int i = 0;

            while(!categoriaDAO.pronto && i < 12){
                try {
                    Log.d("Thread","passou +1 vez no popular categoria");
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                x = x+10;
                i++;
                publishProgress(i);
            }



            return null;
        }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        barCategoria.setProgress(x);
    }

    @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            barCategoria.setVisibility(View.INVISIBLE);

            try {
                Log.d("PopularSpinerCategoria","click no lister do spiner");
                //array para implementar no adapter
                Categoria[] categorias = new Categoria[categoriaList.size()];

                for (int i = 0; i < categoriaList.size(); i++) {
                    categorias[i] = categoriaList.get(i);
                }


                final CategoriaAdapterBean categoriaAdapterBean = new CategoriaAdapterBean(context, android.R.layout.simple_spinner_dropdown_item, categorias, atividade);
                //spinner.setPrompt("Selecione uma categoria");
                spinner.setAdapter(categoriaAdapterBean);


                Log.d("PopularSpinerCategoria","click no lister do spiner");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("pressionou botao", "categoria alimentos");
                        barOfertas.setVisibility(View.VISIBLE);
                        BuscarProdutosService buscarProdutosService = new BuscarProdutosService(atividade, context, Integer.parseInt(spinner.getSelectedItem().toString()), listView, barOfertas, btn1, btn2,btn3,pagina);
                        buscarProdutosService.execute();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });




            }catch (Exception ex){
                Log.e("PopularSpinerCategoria", ex.getMessage());
                Toast.makeText(context, "ERRO: sem internet não é possivel listar produtos", Toast.LENGTH_LONG).show();
            }


        }
    }