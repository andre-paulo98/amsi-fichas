package me.andrepaulo.bookmanager.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.andrepaulo.bookmanager.modelo.Livro;

/**
 * Created by andre on 15/12/2017.
 */

public class LivroJsonParser {

    public static ArrayList<Livro> parserJsonLivros(JSONArray resposta, Context context){
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            for (int i = 0; i < resposta.length(); i++) {
                JSONObject livro = (JSONObject) resposta.get(i);
                long id = livro.getLong("id");
                String titulo = livro.getString("titulo");
                String serie = livro.getString("serie");
                String autor = livro.getString("autor");
                String capa = livro.getString("capa");
                int ano = livro.getInt("ano");
                Livro tLivro = new Livro(id, titulo, serie, autor, ano, capa);
                livros.add(tLivro);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro parserJsonLivros: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return livros;
    }

    public static Livro parserJsonLivros(String resposta, Context context){
        Livro livro = null;
        try {
            JSONObject livroObj = new JSONObject(resposta);
            long id = livroObj.getLong("id");
            String titulo = livroObj.getString("titulo");
            String serie = livroObj.getString("serie");
            String autor = livroObj.getString("autor");
            String capa = livroObj.getString("capa");
            int ano = livroObj.getInt("ano");
            livro = new Livro(id, titulo, serie, autor, ano, capa);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro parserJsonLivro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return livro;

    }

    public static String parserJsonLogin(String resposta, Context context){
        String token = null;
        try {
            JSONObject login = new JSONObject(resposta);
            token = login.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro parserJsonLogin: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return token;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
