package ferreira.filipe.jornaldeofertas.Utilitarios;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.List;

import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.entidades.Produto;

/**
 * Created by Filipe on 12/08/2017.
 */

public class AdapterPersonalizado extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity act;

    public AdapterPersonalizado(List<Produto> produtos, Activity act) {
        this.produtos = produtos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return produtos.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        View view = act.getLayoutInflater()
                .inflate(R.layout.modelo_list_view, viewGroup, false);

        Produto produto = produtos.get(i);

        TextView nome = (TextView)
                view.findViewById(R.id.textViewModeloNome);
        TextView id = (TextView)
                view.findViewById(R.id.textViewModeloId);
        TextView preco = (TextView)
                view.findViewById(R.id.textViewModeloPreco);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.imageViewModelo);

        nome.setText(produto.getNome().toString());
        id.setText(String.valueOf(produto.getId()));
        preco.setText("R$ "+String.valueOf(produto.getPreco()));

        String idNome= String.valueOf(produto.getId()) + produto.getNome().toString();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

            byte[] imagemEmbytes = Base64.decode(produto.getImagem(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imagemEmbytes, 0, imagemEmbytes.length, options);



        if(bmp != null)
        {

            //reduce to 70% size; bitmaps produce larger than actual image size
            bmp = Bitmap.createScaledBitmap(
                    bmp,
                    bmp.getWidth()/10*7,
                    bmp.getHeight()/10*7,
                    false);

            imagem.setImageBitmap(bmp);
        }





        return view;

    }
/*
    public void saveBitmap(Bitmap pBitmap, String nome){

        try{

            File file = new File(Environment.getExternalStorageDirectory() + "/imgsApp");
            file.mkdir();

            File ifile= new File(Environment.getExternalStorageDirectory() + "/imgsApp/", nome+".png");
            FileOutputStream outStream = new FileOutputStream(ifile);
            pBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();

        }
        catch(Exception e){
            Log.e("Não consigo salvar", e.toString());
        }
    }
    */
}
