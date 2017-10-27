package me.andrepaulo.bookmanager.modelo;

import me.andrepaulo.bookmanager.R;

/**
 * Created by andre on 27/10/2017.
 */

public class Livro {

    private String titulo;
    private String serie;
    private String autor;
    private int ano;
    private int capa;

    public Livro(String titulo, String serie, String autor, int ano, int capa) {
        this.titulo = titulo;
        this.serie = serie;
        this.autor = autor;
        this.ano = ano;
        this.capa = capa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }
}
