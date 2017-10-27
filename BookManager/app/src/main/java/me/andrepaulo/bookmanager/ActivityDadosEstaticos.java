package me.andrepaulo.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityDadosEstaticos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_estaticos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
