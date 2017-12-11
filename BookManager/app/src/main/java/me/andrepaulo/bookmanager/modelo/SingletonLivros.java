package me.andrepaulo.bookmanager.modelo;

import android.content.Context;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/11/2017.
 */

public class SingletonLivros {
    private static SingletonLivros INSTANCE = null;
    private LivroBDHelper livroBDHelper;

    private ArrayList<Livro> livros;

    public static synchronized SingletonLivros getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonLivros(context);
        }
        return INSTANCE;
    }

    private SingletonLivros(Context context) {
        this.livros = new ArrayList<>();
        this.livroBDHelper = new LivroBDHelper(context);
        gerarFakeData();
    }

    public ArrayList<Livro> getLivros(){
        return livros = livroBDHelper.getAllLivros();
    }

    private void gerarFakeData(){
        adicionar(new Livro("Programar em Android AMSI", "Android Saga", "Equipa de AMSI", 2017, R.drawable.programarandroid1));
        adicionar(new Livro("Programar em Android AMSI", "2º Temporada", "AMSI TEAM", 2017, R.drawable.programarandroid2));
        adicionar(new Livro("Origem", "1º Temporada", "Dan Brown", 2017, R.drawable.origem));
        adicionar(new Livro("Sinal de Vida", "1º Temporada", "José Rodrigues dos Santos", 2017, R.drawable.sinaldevida));
        adicionar(new Livro("O Gigante Enterrado", "1º Temporada", "Kazuo Ishiguro", 2017, R.drawable.ogiganteenterrado));
        adicionar(new Livro("Astérix e a Transitálica", "Volume 37", "Jean-Yves", 2017, R.drawable.asterixeatransitalica));
        adicionar(new Livro("O Regresso da Primavera", "1º Temporada", "Sveva Casati Modignani", 2017, R.drawable.oregressodaprimavera));

    }

    public void adicionar(Livro livro){
        Livro tempLivro = livroBDHelper.adicionarLivroBD(livro);
        if(tempLivro != null){
            this.livros.add(livro);
        } else {
            System.out.println(">>> [ERRO] O livro com o nome: "+livro.getTitulo() + " não foi adicionado. <<<");
        }
    }


    public void removerLivro(int posicao){
        if(livros.get(posicao) != null){
            if(livroBDHelper.removerLivroBD(livros.get(posicao).getId())){
                livros.remove(posicao);
            } else {
                System.out.println(">>> [ERRO] o livro não foi removido <<<");
            }

        }
    }

    public void editarLivro(Livro livro){
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
