package me.andrepaulo.bookmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.Livro;
import me.andrepaulo.bookmanager.modelo.SingletonLivros;


public class DetalheLivro extends AppCompatActivity {

    public static final String LIVRO_SELECIONADO = "livroselecionado";
    private EditText tx_titulo, tx_serie, tx_autor, tx_ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_livro);
        int posicaoLivro = getIntent().getIntExtra(LIVRO_SELECIONADO, 0);
        tx_titulo = (EditText) findViewById(R.id.editTitulo);
        tx_serie = (EditText) findViewById(R.id.editSerie);
        tx_autor = (EditText) findViewById(R.id.editAutor);
        tx_ano = (EditText) findViewById(R.id.editAno);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        ArrayList<Livro> livros = SingletonLivros.getInstance().getLivros();
        Livro livroSelecionado = livros.get(posicaoLivro);
        tx_titulo.setText(livroSelecionado.getTitulo());
        tx_serie.setText(livroSelecionado.getSerie());
        tx_autor.setText(livroSelecionado.getAutor());
        tx_ano.setText(livroSelecionado.getAno()+"");
        setTitle("Detalhes \""+livroSelecionado.getTitulo()+"\"");

        linearLayout.setBackgroundResource(livroSelecionado.getCapa());
    }
}
