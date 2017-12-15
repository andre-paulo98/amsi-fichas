package me.andrepaulo.bookmanager.modelo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/11/2017.
 */

public class SingletonLivros {
    private static SingletonLivros INSTANCE = null;
    private String mUrlAPILivros = "http://amsi201718.ddns.net/api/livros";
    private LivroBDHelper livroBDHelper;

    private static RequestQueue volleyQueue = null;

    private ArrayList<Livro> livros;

    public static synchronized SingletonLivros getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonLivros(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private SingletonLivros(Context context) {
        this.livros = new ArrayList<>();
        this.livroBDHelper = new LivroBDHelper(context);
    }

    public ArrayList<Livro> getLivrosBD(){
        return livros = livroBDHelper.getAllLivros();
    }

    public void adicionarLivrosBD(ArrayList<Livro> listaLivros){
        livroBDHelper.removerAllLivrosBD();
        for (Livro livro : listaLivros){
            adicionarLivroDB(livro);
        }
    }

    public void adicionarLivroDB(Livro livro){
        Livro tempLivro = livroBDHelper.adicionarLivroBD(livro);
        if(tempLivro != null){
            this.livros.add(livro);
        } else {
            System.out.println(">>> [ERRO] O livro com o nome: "+livro.getTitulo() + " não foi adicionado. <<<");
        }
    }


    public void removerLivroBD(int posicao){
        if(livros.get(posicao) != null){
            if(livroBDHelper.removerLivroBD(livros.get(posicao).getId())){
                livros.remove(posicao);
            } else {
                System.out.println(">>> [ERRO] o livro não foi removido <<<");
            }

        }
    }

    public void editarLivroBD(Livro livro){
        if(livros.contains(livro)){
            int posicao = pesquisarLivro(livro.getId());
            Livro tLivro = livros.get(posicao);
            tLivro.setTitulo(livro.getTitulo());
            tLivro.setAutor(livro.getAutor());
            tLivro.setAno(livro.getAno());
            tLivro.setSerie(livro.getSerie());
            if(livroBDHelper.atualizarLivroBD(tLivro)){
                System.out.println("Livro guardado na base de dados com sucesso.");
            }
        }
    }

    public int pesquisarLivro(long idLivro){
        for (int i = 0; i < livros.size(); i++) {
            System.out.println("A verificar a posição "+i+ " o livro é "+ livros.get(i).getId() + " e verificar se é igual a"+idLivro );
            if(livros.get(i).getId() == idLivro)
                return i;
        }
        return -1;
    }
}
