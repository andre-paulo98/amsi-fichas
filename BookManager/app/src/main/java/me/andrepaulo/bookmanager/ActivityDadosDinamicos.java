package me.andrepaulo.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import me.andrepaulo.bookmanager.modelo.Livro;

import me.andrepaulo.bookmanager.modelo.GestorLivros;

public class ActivityDadosDinamicos extends AppCompatActivity {

    private Livro livro;
    private TextView tv_titulo, tv_autor, tv_ano, tv_serie;
    private ImageView iv_capa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GestorLivros gestor = new GestorLivros();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_dinamicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        livro = gestor.gerarFakeData();

        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_autor = (TextView) findViewById(R.id.tv_autor);
        tv_ano = (TextView) findViewById(R.id.tv_ano);
        tv_serie = (TextView) findViewById(R.id.tv_serie);
        iv_capa = (ImageView) findViewById(R.id.iv_capa);


        tv_titulo.setText(livro.getTitulo());
        tv_autor.setText(livro.getAutor());
        tv_ano.setText(livro.getAno()+"");
        tv_serie.setText(livro.getSerie());
        iv_capa.setImageResource(livro.getCapa());
    }

}
