package me.andrepaulo.ficha2_guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int limiteTentativas = 5;
    private int[] limite = {1, 5};

    EditText edit;
    TextView tvresultado, tvtentantivas;
    private int numeroAleatorio;
    private int guess, tentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        numeroAleatorio = 0; guess = 0; tentativas = limiteTentativas;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.editTextNumero);
        tvresultado = (TextView) findViewById(R.id.resultado);
        tvtentantivas = (TextView) findViewById(R.id.tentativas); 
        numeroAleatorio = numAleatorio(limite[0], limite[1]);
        Toast.makeText(this, numeroAleatorio+"", Toast.LENGTH_SHORT).show();
    }


    public void onClickAdivinha(View view) {
        if(!edit.getText().toString().isEmpty()){
            guess = Integer.parseInt(edit.getText().toString());
            if(tentativas > 0){
                if(numeroAleatorio == guess){
                    tvresultado.setText(R.string.acertou);
                    tvtentantivas.setText(getString(R.string.numtentativas) + tentativas);
                    mostraResultado();
                    novoNumbero();
                } else if (numeroAleatorio > guess){
                    tentativas--;
                    tvresultado.setText(R.string.maiscima);
                    tvtentantivas.setText(getString(R.string.numtentativas) + tentativas);
                    mostraResultado();
                } else if (numeroAleatorio < guess){
                    tentativas--;
                    tvresultado.setText(R.string.maisbaixo);
                    tvtentantivas.setText(getString(R.string.numtentativas) + tentativas);
                    mostraResultado();
                } else {
                    Toast.makeText(this, numeroAleatorio, Toast.LENGTH_SHORT).show();
                }
            } else {
                tvresultado.setText(getString(R.string.numtentativasout));
                novoNumbero();
            }
        }

    }

    public void mostraResultado(){
        tvresultado.setVisibility(View.VISIBLE);
        tvtentantivas.setVisibility(View.VISIBLE);
    }

    private void novoNumbero(){
        tentativas = limiteTentativas;
        numeroAleatorio = numAleatorio(limite[0], limite[1]);
    }

    public int numAleatorio(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
