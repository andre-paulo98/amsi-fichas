package me.andrepaulo.bookmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

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
    private Livro livro;
    //private ArrayList<Livro> livros;

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
        //livros = SingletonLivros.getInstance(getApplicationContext()).getLivrosBD();
        livro = SingletonLivros.getInstance(this).getLivro(idLivro);

        if(idLivro > -1){
            fab.setImageResource(R.drawable.ic_actions_save);
            posicao = SingletonLivros.getInstance(getApplicationContext()).pesquisarLivro(idLivro);
            tx_titulo.setText(livro.getTitulo());
            tx_serie.setText(livro.getSerie());
            tx_autor.setText(livro.getAutor());
            tx_ano.setText(livro.getAno()+"");
            setTitle(getString(R.string.detalhes)+livro.getTitulo()+"\"");
            /*Glide.with(getApplicationContext())
                    .load(livroSelecionado.getCapa())
                    .placeholder(R.drawable.ipl_semfundo)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(linearLayout);*/
            //linearLayout.setBackgroundResource(livroSelecionado.getCapa());
        } else {
            setTitle(getString(R.string.adicionarnovolivro));
        }

    }

    public void onClickFABDetalhesLivro(View view) {
        if(idLivro > -1){

        } else {
            Livro tempLivro = livro;
            tempLivro.setTitulo(tx_titulo.getText().toString());
            tempLivro.setSerie(tx_serie.getText().toString());
            tempLivro.setAutor(tx_autor.getText().toString());
            tempLivro.setAno(Integer.parseInt(tx_ano.getText().toString()));
            SingletonLivros.getInstance(getApplicationContext()).editarLivroBD(tempLivro);
            setResult(RESULT_OK);
            finish();
        }
    }

    private Livro criarLivro(){
        String url = "http://amsi201718.ddns.net/img/ipl_semfundo.png";
        Livro auxLivro = new Livro(0,
                tx_titulo.getText().toString(),
                tx_serie.getText().toString(),
                tx_autor.getText().toString(),
                Integer.parseInt(tx_ano.getText().toString()),
                url);
        return auxLivro;
    }
}
