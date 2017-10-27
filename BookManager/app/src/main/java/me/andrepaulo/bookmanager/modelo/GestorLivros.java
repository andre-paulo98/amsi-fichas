package me.andrepaulo.bookmanager.modelo;


import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/10/2017.
 */

public class GestorLivros {

    public GestorLivros(){

    }

    public Livro gerarFakeData(){
        Livro livro = new Livro("Programar em Android AMSI", "2º Temporada", "AMSI TEAM", 2017, R.drawable.programarandroid2);
        return livro;
    }
}
