package me.andrepaulo.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.ListaLivrosAdapter;
import me.andrepaulo.bookmanager.modelo.Livro;
import me.andrepaulo.bookmanager.modelo.SingletonLivros;

public class ActivityDadosDinamicos extends AppCompatActivity {

    private String mEmail;

    private ListView listaLivros;
    private ListaLivrosAdapter listaLivrosAdapter;
    private ArrayList<Livro> livros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_dinamicos);

        mEmail = getIntent().getStringExtra(LoginActivity.DADOS_EMAIL);

        livros = SingletonLivros.getInstance(getApplicationContext()).getLivros();

        listaLivrosAdapter = new ListaLivrosAdapter(this, livros);

        listaLivros = (ListView) findViewById(R.id.listaLivros);
        listaLivros.setAdapter(listaLivrosAdapter);

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalheLivro.class);
                Livro tempLivro = livros.get(position);
                intent.putExtra(DetalheLivro.LIVRO_SELECIONADO, tempLivro.getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemGrelha:
                Toast.makeText(this, "Item Grelha", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GridViewActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemEmail:
                Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_livros, menu);

        MenuItem itemPesquisa =  menu.findItem(R.id.itemPesquisa);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemPesquisa);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<Livro> tempLivros = new ArrayList<Livro>();
                for (Livro temp : livros){
                    if(temp.getTitulo().toLowerCase().contains(newText.toString()))
                        tempLivros.add(temp);
                }

                listaLivros.setAdapter(new ListaLivrosAdapter(getApplicationContext(), tempLivros));
                listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Livro tempLivro = tempLivros.get(position);

                        Intent intent = new Intent(getApplicationContext(), DetalheLivro.class);
                        intent.putExtra(DetalheLivro.LIVRO_SELECIONADO, tempLivro.getId());
                        startActivity(intent);
                        startActivityForResult(intent, DetalheLivro.REQUEST_EDIT);
                    }
                });
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void onClickAdicionarLivro(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == DetalheLivro.REQUEST_EDIT){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Livro editado com sucesso", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        livros.clear();
        livros = SingletonLivros.getInstance(this).getLivros();
        listaLivrosAdapter.refresh(livros);

    }
}
