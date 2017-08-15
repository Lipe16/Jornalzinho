package ferreira.filipe.jornaldeofertas.bean;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.entidades.Categoria;
import ferreira.filipe.jornaldeofertas.entidades.Cidade;

/**
 * Created by Filipe on 15/08/2017.
 */

public class CategoriaAdapterBean  extends ArrayAdapter<Categoria> {
    private Context context;
    private Categoria[] categorias;
    private Activity atividade;

    public CategoriaAdapterBean(@NonNull Context context, @LayoutRes int resource, Categoria[] categoria, Activity atividade) {
        super(context, resource, categoria);
        this.categorias = categoria;
        this.context = context;
        this.atividade = atividade;
    }

    public int getCount(){
        return categorias.length;
    }
    public Categoria getItem(int position){
        return categorias[position];
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        convertView = LayoutInflater.from(this.context).inflate(R.layout.spinner_item_selected,null);
        final View layout = convertView;

        TextView label = (TextView) convertView.findViewById(R.id.linhaParaSpinner);
        label.setText(categorias[position].getNome());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(this.context).inflate(R.layout.spinner_dropdown,null);
        final View layout = convertView;

        TextView label = (TextView) convertView.findViewById(R.id.linhaParaSpinnerDropdown);
        label.setText(categorias[position].getNome());

        return convertView;
    }


}