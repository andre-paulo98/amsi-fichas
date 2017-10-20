package me.andrepaulo.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bsh.EvalError;
import bsh.Interpreter;

public class MainActivity extends AppCompatActivity {

    private String inScreen = "", total = "";
    TextView resultado, contas;

    private int[] btsSimples = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9};
    private int[] btsOperacoes = {R.id.btDIVIDIR, R.id.btVEZES, R.id.btMAIS, R.id.btMENOS};
    private int[] btsAvancados = {R.id.btLIMPA, R.id.btAC, R.id.btENTER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.tvTotal);
        contas = (TextView) findViewById(R.id.tvContas);

        clickListener(btsSimples);
        clickListener(btsAvancados);
        clickListener(btsOperacoes);



    }

    View.OnClickListener btHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        int id = v.getId();
        Button bt = (Button) findViewById(id);
        contas.setText("");
        if(checkInArray(btsSimples, id)){
            String clicado = bt.getText().toString();
            inScreen += clicado;
            refreshScreen();
        } else if(checkInArray(btsOperacoes, id)) {
            String clicado = bt.getText().toString();
            if(clicado.equalsIgnoreCase("x")) clicado = "*";
            if(!lastChar(inScreen).matches("[+-/*]")){
                inScreen += clicado;
                refreshScreen();
            }
        } else if(bt.getText().toString().equalsIgnoreCase("enter")){
            if(lastChar(inScreen).matches("[+-/*]")){
                inScreen = removerLastChar(inScreen);
                refreshScreen();
            }
            Interpreter interpreter = new Interpreter();
            try {
                interpreter.eval("result = "+inScreen);
                total = interpreter.get("result").toString();
                resultado.setText(total);
                contas.setText(inScreen);
                inScreen = total;
            } catch (EvalError evalError) {
                String erro = evalError.toString();
                if(erro.toLowerCase().contains("divide by zero")){
                    Toast.makeText(MainActivity.this, "Erro. Tentativa de dividir por zero.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro desconhecido.", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (bt.getText().toString().equalsIgnoreCase("AC")){
            inScreen = ""; total = "";
            refreshScreen(); contas.setText("");
        } else if (bt.getText().toString().equalsIgnoreCase("--")){
            inScreen = removerLastChar(inScreen);
            refreshScreen();
        }
        }
    };

    private boolean checkInArray(int[] arr, int toFind){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == toFind)
                return true;
        }
        return false;
    }

    private void clickListener(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            findViewById(arr[i]).setOnClickListener(btHandler);
        }
    }

    private void refreshScreen(){
        resultado.setText(inScreen + "");
    }

    private String lastChar(String s){
        return s.substring(s.length() - 1);
    }

    private String removerLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Método onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Método onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Método onResumo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Método onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Método onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Método onDestroy");
    }
}
