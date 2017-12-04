package me.andrepaulo.bookmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.Livro;
import me.andrepaulo.bookmanager.modelo.SingletonLivros;


public class DetalheLivro extends AppCompatActivity {

    public static final String LIVRO_SELECIONADO = "livroselecionado";
    public static final int REQUEST_EDIT = 1;
    private FloatingActionButton fab;
    public static final int REQUEST_ADD = 2;
    private EditText tx_titulo, tx_serie, tx_autor, tx_ano;
    private long idLivro;
    private int posicao;
    private ArrayList<Livro> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_livro);
        idLivro = getIntent().getLongExtra(LIVRO_SELECIONADO, -1);
        tx_titulo = (EditText) findViewById(R.id.editTitulo);
        tx_serie = (EditText) findViewById(R.id.editSerie);
        tx_autor = (EditText) findViewById(R.id.editAutor);
        tx_ano = (EditText) findViewById(R.id.editAno);
        fab = (FloatingActionButton) findViewById(R.id.fabDetalheLivro);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        livros = SingletonLivros.getInstance().getLivros();

        if(idLivro > -1){
            fab.setImageResource(R.drawable.ic_actions_save);
            posicao = SingletonLivros.getInstance().pesquisarLivro(idLivro);


            Livro livroSelecionado = livros.get(posicao);
            tx_titulo.setText(livroSelecionado.getTitulo());
            tx_serie.setText(livroSelecionado.getSerie());
            tx_autor.setText(livroSelecionado.getAutor());
            tx_ano.setText(livroSelecionado.getAno()+"");
            setTitle(getString(R.string.detalhes)+livroSelecionado.getTitulo()+"\"");

            linearLayout.setBackgroundResource(livroSelecionado.getCapa());
        } else {
            setTitle(getString(R.string.adicionarnovolivro));
        }

    }

    public void onClickFABDetalhesLivro(View view) {
        if(idLivro > -1){

        } else {
            Livro tempLivro = livros.get(posicao);
            tempLivro.setTitulo(tx_titulo.getText().toString());
            tempLivro.setSerie(tx_serie.getText().toString());
            tempLivro.setAutor(tx_autor.getText().toString());
            tempLivro.setAno(Integer.parseInt(tx_ano.getText().toString()));
            SingletonLivros.getInstance().editarLivro(tempLivro);
            setResult(RESULT_OK);
            finish();
        }
    }
}
