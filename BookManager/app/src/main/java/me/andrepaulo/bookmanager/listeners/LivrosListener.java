package me.andrepaulo.bookmanager.listeners;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.Livro;

/**
 * Created by andre on 18/12/2017.
 */

public interface LivrosListener {
    void onRefreshListaLivros(ArrayList<Livro> livros);
    void onUpdateListaLivrosBD(Livro livro, int operacao);
}
