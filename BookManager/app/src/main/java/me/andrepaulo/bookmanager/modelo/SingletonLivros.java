package me.andrepaulo.bookmanager.modelo;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/11/2017.
 */

public class SingletonLivros {
    private static SingletonLivros INSTANCE = null;

    private ArrayList<Livro> livros;

    public static synchronized SingletonLivros getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SingletonLivros();
        }
        return INSTANCE;
    }

    private SingletonLivros() {
        this.livros = new ArrayList<>();
        gerarFakeData();
    }

    public ArrayList<Livro> getLivros(){
        return livros;
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
        this.livros.add(livro);
    }


    public void removerLivro(int posicao){
        if(livros.get(posicao) != null)
            livros.remove(posicao);
    }

    public void editarLivro(Livro livro){
        if(livros.contains(livro)){
            int posicao = pesquisarLivro(livro.getId());
            Livro tLivro = livros.get(posicao);
            tLivro.setTitulo(livro.getTitulo());
            tLivro.setAutor(livro.getAutor());
            tLivro.setAno(livro.getAno());
            tLivro.setSerie(livro.getSerie());
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
