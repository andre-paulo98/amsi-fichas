package me.andrepaulo.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.GrelhaLivrosAdapter;
import me.andrepaulo.bookmanager.modelo.Livro;
import me.andrepaulo.bookmanager.modelo.SingletonLivros;

public class GridViewActivity extends AppCompatActivity {

    private GridView grelhaLivros;
    private GrelhaLivrosAdapter grelhaLivrosAdapter;
    private ArrayList<Livro> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        livros = SingletonLivros.getInstance(getApplicationContext()).getLivros();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grelha_livros, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClickAdicionarLivro(View view) {
    }
}
