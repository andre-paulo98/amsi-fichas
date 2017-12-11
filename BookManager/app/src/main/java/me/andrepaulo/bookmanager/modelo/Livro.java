package me.andrepaulo.bookmanager.modelo;

/**
 * Created by andre on 27/10/2017.
 */

public class Livro {

    private long id;
    private String titulo;
    private String serie;
    private String autor;
    private int ano;
    private int capa;

    private static long autoIncrementId = 1;

    public Livro(String titulo, String serie, String autor, int ano, int capa) {
        this.id = autoIncrementId++;
        this.titulo = titulo;
        this.serie = serie;
        this.autor = autor;
        this.ano = ano;
        this.capa = capa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
