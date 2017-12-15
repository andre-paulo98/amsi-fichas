package me.andrepaulo.bookmanager.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 10/11/2017.
 */

public class GrelhaLivrosAdapter extends BaseAdapter {

    private ArrayList<Livro> listaLivros;
    private LayoutInflater inflater;
    private Context context;

    public GrelhaLivrosAdapter(Context contexto, ArrayList<Livro> livros) {
        this.listaLivros = livros;
        this.context = contexto;
        this.inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaLivros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaLivros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_grelha_livro, null);
        }
        ImageView imageViewCapaLivro = (ImageView) convertView.findViewById(R.id.imageViewCapaLivro);
        System.out.print(position);
        Glide.with(context)
                .load(listaLivros.get(position).getCapa())
                .placeholder(R.drawable.ipl_semfundo)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewCapaLivro);

        return convertView;
    }

    public void refresh(ArrayList<Livro> livros){
        this.listaLivros = livros;
        notifyDataSetChanged();
    }
}
