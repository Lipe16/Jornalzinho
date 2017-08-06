package ferreira.filipe.jornaldeofertas.bean;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.entidades.Cidade;

/**
 * Created by filipe on 01/08/17.
 */

public class CidadeAdapterBean extends ArrayAdapter<Cidade>{
    private Context context;
    private Cidade[] cidades;

    public CidadeAdapterBean(@NonNull Context context, @LayoutRes int resource, Cidade[] cidades) {
        super(context, resource, cidades);
        this.cidades = cidades;
        this.context = context;
    }

    public int getCount(){
        return cidades.length;
    }
    public Cidade getItem(int position){
        return cidades[position];
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);

        label.setText(cidades[position].getNome());

        return  label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);

        label.setText(cidades[position].getNome());

        return  label;
    }


}
