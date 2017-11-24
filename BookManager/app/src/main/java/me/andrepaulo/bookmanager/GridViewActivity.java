package me.andrepaulo.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.GestorLivros;
import me.andrepaulo.bookmanager.modelo.GrelhaLivrosAdapter;
import me.andrepaulo.bookmanager.modelo.Livro;

public class GridViewActivity extends AppCompatActivity {

    private GridView grelhaLivros;
    private GrelhaLivrosAdapter grelhaLivrosAdapter;
    private ArrayList<Livro> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        GestorLivros gestor = new GestorLivros();

        livros = new ArrayList(gestor.gerarListaLivrosFake());

        grelhaLivrosAdapter = new GrelhaLivrosAdapter(this, livros);

        grelhaLivros = (GridView) findViewById(R.id.gv_grelhaLivros);
        grelhaLivros.setAdapter(grelhaLivrosAdapter);

        grelhaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalheLivro.class);
                intent.putExtra(DetalheLivro.LIVRO_SELECIONADO, position);
                startActivity(intent);
            }
        });


    }
}
