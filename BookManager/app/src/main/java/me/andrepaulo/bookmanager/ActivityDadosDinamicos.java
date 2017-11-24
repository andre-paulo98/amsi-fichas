package me.andrepaulo.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import me.andrepaulo.bookmanager.modelo.ListaLivrosAdapter;
import me.andrepaulo.bookmanager.modelo.Livro;

import me.andrepaulo.bookmanager.modelo.GestorLivros;

public class ActivityDadosDinamicos extends AppCompatActivity {

    private ListView listaLivros;
    private ListaLivrosAdapter listaLivrosAdapter;
    private ArrayList<Livro> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_dinamicos);

        GestorLivros gestor = new GestorLivros();

        livros = new ArrayList(gestor.gerarListaLivrosFake());

        listaLivrosAdapter = new ListaLivrosAdapter(this, livros);

        listaLivros = (ListView) findViewById(R.id.listaLivros);
        listaLivros.setAdapter(listaLivrosAdapter);

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalheLivro.class);
                intent.putExtra(DetalheLivro.LIVRO_SELECIONADO, position);
                startActivity(intent);
            }
        });

    }
}
