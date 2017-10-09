package me.andrepaulo.euromilhoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText n1, n2, n3, n4, n5, e1, e2;
    TextView euroNumeros, euroEstrelas;
    private int minNumero = 1, maxNumero = 50, minEstrela = 1, maxEstrela = 9;
    private int[] numerosuser = new int[5];
    private int[] estrelasuser = new int[2];
    private int[] numerosGerados = new int[5];
    private int[] estrelasGerados = new int[2];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1 = (EditText) findViewById(R.id.n1);
        n2 = (EditText) findViewById(R.id.n2);
        n3 = (EditText) findViewById(R.id.n3);
        n4 = (EditText) findViewById(R.id.n4);
        n5 = (EditText) findViewById(R.id.n5);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        euroNumeros = (TextView) findViewById(R.id.euromilhoesNumeros);
        euroEstrelas = (TextView) findViewById(R.id.euromilhoesEstrelas);
    }

    public void btGerarChave(View view) {
        limpaArrays();
        if(verificarPreenchidos()){
            if(!numerosusertoArray()){
                gerarEuromilhoes();
                euroNumeros.setText(arrayToString(numerosGerados));
                euroEstrelas.setText(arrayToString(estrelasGerados));

            } else {
                Toast.makeText(this, "Erro! Existe um número repetido. Por favor verifique!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos com os números obrigatórios!", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean numerosusertoArray(){
        int n_1 = getNum(n1), n_2 = getNum(n2), n_3 = getNum(n3), n_4 = getNum(n4), n_5 = getNum(n5);
        int e_1 = getNum(e1), e_2 = getNum(e2);
        boolean erro = false;
        numerosuser[0] = n_1;
        if(!verificaExiste(n_2, numerosuser))
            numerosuser[1] = n_2;
        else
            erro = true;
        if(!verificaExiste(n_3, numerosuser))
            numerosuser[2] = n_3;
        else
            erro = true;
        if(!verificaExiste(n_4, numerosuser))
            numerosuser[3] = n_4;
        else
            erro = true;
        if(!verificaExiste(n_5, numerosuser))
            numerosuser[4] = n_5;
        else
            erro = true;
        if(!verificaExiste(e_1, estrelasuser))
            estrelasuser[0] = e_1;
        else
            erro = true;
        if(!verificaExiste(e_2, estrelasuser))
            estrelasuser[1] = e_2;
        else
            erro = true;
        return erro;
    }

    private void gerarEuromilhoes(){
        int numeroGerado = 0;
        for (int i = 0; i < 5; i++) {
            do{
                numeroGerado = numeroAleatorio(minNumero, maxNumero);
            } while (verificaExiste(numeroGerado, numerosGerados));
            numerosGerados[i] = numeroGerado;
        }
        for (int i = 0 ; i < 2; i++) {
            estrelasGerados[i] = numeroAleatorio(minNumero, maxEstrela);
        }
    }

    private boolean verificaExiste(int n, int[] array){
        boolean existe = false;
        for (int i = 0; i < array.length; i++) {
            if(n == array[i]){
                existe = true;
            }
        }
        return existe;
    }

    private int numeroAleatorio(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private boolean verificarPreenchidos(){
        int n_1 = getNum(n1), n_2 = getNum(n2), n_3 = getNum(n3), n_4 = getNum(n4), n_5 = getNum(n5);
        int e_1 = getNum(e1), e_2 = getNum(e2);
        return ((n_1 >= minNumero && n_1 <= maxNumero) && (n_2 >= minNumero && n_2 <= maxNumero) &&
                (n_3 >= minNumero && n_3 <= maxNumero) && (n_4 >= minNumero && n_4 <= maxNumero) &&
                (n_5 >= minNumero && n_5 <= maxNumero) &&
                (e_1 >= minEstrela && e_1 <= maxEstrela) && (e_2 >= minEstrela && e_2 <= maxEstrela));
    }

    private int getNum(EditText ab){
        if(ab.getText().toString().trim().isEmpty()){
            return 0;
        } else {
            return Integer.parseInt(ab.getText().toString());
        }
    }

    private void limpaArrays(){
        for (int i = 0; i < numerosGerados.length; i++) {
            numerosGerados[i] = 0;
        }
        for (int i = 0; i < estrelasGerados.length; i++) {
            estrelasGerados[i] = 0;
        }
        for (int i = 0; i < numerosuser.length; i++) {
            numerosuser[i] = 0;
        }
        for (int i = 0; i < estrelasuser.length; i++) {
            estrelasuser[i] = 0;
        }
    }

    private String arrayToString(int[] t){
        String temp = "";
        for (int i = 0; i < t.length; i++) {
            temp += t[i] + " ";
        }
        return temp;
    }
}
