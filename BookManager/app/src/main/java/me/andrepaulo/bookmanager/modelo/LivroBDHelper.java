package me.andrepaulo.bookmanager.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by andre on 11/12/2017.
 */

public class LivroBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "livrosBD";

    private static final String TABLE_NAME = "livro";
    private static final String LIVRO_ID = "id";
    private static final String LIVRO_TITULO = "titulo";
    private static final String LIVRO_SERIE = "serie";
    private static final String LIVRO_AUTOR = "autor";
    private static final String LIVRO_ANO = "ano";
    private static final String LIVRO_CAPA = "capa";


    private static final int DB_VERSION = 1;

    private SQLiteDatabase database;

    public LivroBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLivroTable = "CREATE TABLE "+ TABLE_NAME + "(" +
                LIVRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LIVRO_TITULO + " TEXT NOT NULL, " +
                LIVRO_SERIE + " TEXT NOT NULL, " +
                LIVRO_AUTOR + " TEXT NOT NULL, " +
                LIVRO_ANO + " INTEGER NOT NULL, " +
                LIVRO_CAPA + " INTEGER" +
                ");";
        db.execSQL(createLivroTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);

    }

    public Livro adicionarLivroBD(Livro livro){
        ContentValues values = new ContentValues();

        values.put(LIVRO_TITULO, livro.getTitulo());
        values.put(LIVRO_SERIE, livro.getSerie());
        values.put(LIVRO_AUTOR, livro.getAutor());
        values.put(LIVRO_ANO, livro.getAno());
        values.put(LIVRO_CAPA, livro.getCapa());

        long id = this.database.insert(TABLE_NAME, null, values);
        if(id > -1){
            livro.setId(id);
            return livro;
        }
        return null;
    }

    public boolean atualizarLivroBD(Livro livro){
        ContentValues values = new ContentValues();

        values.put(LIVRO_TITULO, livro.getTitulo());
        values.put(LIVRO_SERIE, livro.getSerie());
        values.put(LIVRO_AUTOR, livro.getAutor());
        values.put(LIVRO_ANO, livro.getAno());
        values.put(LIVRO_CAPA, livro.getCapa());

        return this.database.update(TABLE_NAME, values, "id = ?", new String[]{""+livro.getId()}) > 0;
    }

    public boolean removerLivroBD(long id){
        return this.database.delete(TABLE_NAME, "id = ?", new String[]{""+id}) == 1;
    }

    public ArrayList<Livro> getAllLivros(){
        ArrayList<Livro> livros = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Livro tempLivro = new Livro(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
                );
                tempLivro.setId(cursor.getLong(0));
                livros.add(tempLivro);

            } while (cursor.moveToNext());
        }

        return livros;
    }
}
