package me.andrepaulo.ficha2_guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView tvresultado, tvtentantivas;
    private int numeroAleatorio = 0;
    private int guess = 0, tentativas = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.editTextNumero);
        tvresultado = (TextView) findViewById(R.id.resultado);
        tvtentantivas = (TextView) findViewById(R.id.tentativas); 
        numeroAleatorio = numAleatorio(1, 3);
        Toast.makeText(this, numeroAleatorio+"", Toast.LENGTH_SHORT).show();
    }


    public void onClickAdivinha(View view) {
        //Toast.makeText(this, edit.getText(), Toast.LENGTH_SHORT).show();
        if(tentativas > 0){
            if(numeroAleatorio == Integer.parseInt(edit.getText().toString())){
                tvresultado.setText(R.string.acertou);
                tvtentantivas.setText(R.string.numtentativas + tentativas +"");
                mostraResultado();
            } else {
                Toast.makeText(this, numeroAleatorio, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void mostraResultado(){
        tvresultado.setVisibility(View.VISIBLE);
        tvtentantivas.setVisibility(View.VISIBLE);
    }

    public int numAleatorio(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
