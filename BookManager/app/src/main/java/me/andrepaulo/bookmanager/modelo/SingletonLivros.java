package me.andrepaulo.bookmanager.modelo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.andrepaulo.bookmanager.ActivityDadosDinamicos;
import me.andrepaulo.bookmanager.R;
import me.andrepaulo.bookmanager.listeners.LivrosListener;
import me.andrepaulo.bookmanager.utils.LivroJsonParser;

/**
 * Created by andre on 27/11/2017.
 */

public class SingletonLivros implements LivrosListener{
    private static final int ADICIONAR_BD = 1;
    private static final int EDITAR_BD = 2;
    private static final int REMOVER_BD = 3;
    private static SingletonLivros INSTANCE = null;
    private String mUrlAPILivros = "http://amsi201718.ddns.net/api/livros";
    private String mUrlAPILogin = "http://amsi201718.ddns.net/api/auth/login";
    private String tokenAPI = "";
    private LivroBDHelper livroBDHelper;

    private static RequestQueue volleyQueue = null;

    private LivrosListener livrosListener;

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
        return livroBDHelper.getAllLivros();
    }

    public void adicionarLivrosBD(ArrayList<Livro> listaLivros){
        livroBDHelper.removerAllLivrosBD();
        for (Livro livro : listaLivros){
            adicionarLivroDB(livro);
        }
    }

    public void adicionarLivroDB(Livro livro){
        livroBDHelper.adicionarLivroBD(livro);
    }

    public Livro getLivro(long id){
        return livroBDHelper.getLivroBD(id);
    }


    public void removerLivroBD(long id){
        livroBDHelper.removerLivroBD(id);
    }

    public void editarLivroBD(Livro livro){
        livroBDHelper.atualizarLivroBD(livro);
    }

    public int pesquisarLivro(long idLivro){
        for (int i = 0; i < livros.size(); i++) {
            System.out.println("A verificar a posição "+i+ " o livro é "+ livros.get(i).getId() + " e verificar se é igual a"+idLivro );
            if(livros.get(i).getId() == idLivro)
                return i;
        }
        return -1;
    }

    @Override
    public void onRefreshListaLivros(ArrayList<Livro> livros) {

    }

    @Override
    public void onUpdateListaLivrosBD(Livro livro, int operacao) {
        switch (operacao){
            case 1:
                adicionarLivroDB(livro);
                break;
            case 2:
                editarLivroBD(livro);
                break;
            case 3:
                removerLivroBD(livro.getId());
                break;
        }
    }

    public void setLivrosListener(LivrosListener livrosListener) {
        this.livrosListener = livrosListener;
    }

    // API

    public void getAllLivrosAPI(final Context context, boolean isConnected) {
        if(!isConnected){
            livros = livroBDHelper.getAllLivros();
            if(livrosListener != null){
                livrosListener.onRefreshListaLivros(livros);
            }
        } else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPILivros, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    livros = LivroJsonParser.parserJsonLivros(response, context);
                    adicionarLivrosBD(livros);
                    if(livrosListener != null){
                        livrosListener.onRefreshListaLivros(livros);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(" -->> ERRO GETALLLIVROSAPI <<-- \n\n\n\n"+error.getMessage());
                }
            });
            volleyQueue.add(request);
        }

    }

    public void adicionarLivroAPI(final Livro livro, final Context context){
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPILivros, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> REPOSTA ADD POST "+response);
                if(livrosListener != null){
                    livrosListener.onUpdateListaLivrosBD(LivroJsonParser.parserJsonLivros(response, context), ADICIONAR_BD);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(" -->> ERRO ACIDIONARLIVROAPI <<-- \n\n\n\n"+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", tokenAPI);
                params.put("titulo", livro.getTitulo());
                params.put("autor", livro.getAutor());
                params.put("serie", livro.getSerie());
                params.put("ano", livro.getAno()+"");
                params.put("capa", livro.getCapa());
                return params;
            }
        };
        volleyQueue.add(request);
    }

}
