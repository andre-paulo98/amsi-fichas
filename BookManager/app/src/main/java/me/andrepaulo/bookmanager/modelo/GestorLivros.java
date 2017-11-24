package me.andrepaulo.bookmanager.modelo;


import java.util.ArrayList;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/10/2017.
 */

public class GestorLivros {

    public GestorLivros(){

    }

    public Livro gerarLivroFake(){
        Livro livro = new Livro("Programar em Android AMSI", "2º Temporada", "AMSI TEAM", 2017, R.drawable.programarandroid2);
        return livro;
    }

    public ArrayList<Livro> gerarListaLivrosFake(){
        ArrayList<Livro> listaLivros = new ArrayList();
        listaLivros.add(new Livro("Programar em Android AMSI", "Android Saga", "Equipa de AMSI", 2017, R.drawable.programarandroid1));
        listaLivros.add(new Livro("Programar em Android AMSI", "2º Temporada", "AMSI TEAM", 2017, R.drawable.programarandroid2));
        listaLivros.add(new Livro("Origem", "1º Temporada", "Dan Brown", 2017, R.drawable.origem));
        listaLivros.add(new Livro("Sinal de Vida", "1º Temporada", "José Rodrigues dos Santos", 2017, R.drawable.sinaldevida));
        listaLivros.add(new Livro("O Gigante Enterrado", "1º Temporada", "Kazuo Ishiguro", 2017, R.drawable.ogiganteenterrado));
        listaLivros.add(new Livro("Astérix e a Transitálica", "Volume 37", "Jean-Yves", 2017, R.drawable.asterixeatransitalica));
        listaLivros.add(new Livro("O Regresso da Primavera", "1º Temporada", "Sveva Casati Modignani", 2017, R.drawable.oregressodaprimavera));
        return new ArrayList(listaLivros);
    }
}
