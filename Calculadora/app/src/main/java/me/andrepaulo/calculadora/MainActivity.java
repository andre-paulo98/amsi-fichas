package me.andrepaulo.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

import bsh.EvalError;
import bsh.Interpreter;

public class MainActivity extends AppCompatActivity {

    private int num1 = -1, num2 = -1;
    private String inScreen = "", operacao = "";
    TextView resultado;

    private int[] btsNumeros = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9};
    private int[] btAcoes = {R.id.btDIVIDIR, R.id.btVEZES, R.id.btMAIS, R.id.btMENOS, R.id.btLIMPA, R.id.btAC, R.id.btENTER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.tvResultado);
        for (int i = 0; i < btsNumeros.length; i++) {
            findViewById(btsNumeros[i]).setOnClickListener(btHandler);
        }
        for (int i = 0; i < btAcoes.length; i++) {
            findViewById(btAcoes[i]).setOnClickListener(btHandler);
        }

        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval("result = 1+1");
            System.out.println(interpreter.get("result"));
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        }

    }

    View.OnClickListener btHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Button bt = (Button) findViewById(id);
            if(checkInArray(btsNumeros, id)){
                String clicado = bt.getText().toString();
                inScreen += clicado;
                refreshScreen();
            } else {
                //desativarAcao(false);
                System.out.println(bt.getText().toString());
                if(bt.getText().toString().equals("AC")){
                    reset(); refreshScreen();
                } else if(bt.getText().toString().equals("--")){
                    System.out.println("ACCCCC");
                    System.out.println(inScreen);
                    System.out.println(inScreen.length());
                    if(inScreen.length()>0){
                        inScreen = inScreen.substring(0, inScreen.length() - 1);
                        refreshScreen();
                    }
                } else if(num1 < 0){
                    num1 = Integer.parseInt(inScreen);
                    operacao = bt.getText().toString();
                    inScreen = "";
                    System.out.println("desativar 1");
                    desativarAcao(false);
                } else if (bt.getText().toString().equals("ENTER")){ // if (bt.getText().toString() == "ENTER")
                    num2 = Integer.parseInt(inScreen);
                    int resultadoOperacao = 0;
                    switch (operacao){
                        case "+":
                            resultadoOperacao = num1 + num2;
                            break;
                        case "-":
                            resultadoOperacao = num1 - num2;
                            break;
                        case "X":
                            resultadoOperacao = num1 * num2;
                            break;
                        case "/":
                            resultadoOperacao = (num1 / num2);
                            break;
                    }
                    inScreen = resultadoOperacao+"";
                    System.out.println(resultadoOperacao);
                    refreshScreen();
                    System.out.println("desativar 2");
                    reset();
                } else if (bt.getText().toString().equals("AC")){

                }
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

    private void refreshScreen(){
        resultado.setText(inScreen + "");
    }

    private void desativarAcao(boolean a){
        System.out.println("A desativar");
        findViewById(R.id.btDIVIDIR).setEnabled(a);
        findViewById(R.id.btVEZES).setEnabled(a);
        findViewById(R.id.btMAIS).setEnabled(a);
        findViewById(R.id.btMENOS).setEnabled(a);
    }

    private void reset(){
        desativarAcao(true); num1 = -1; num2 = -1; inScreen = "";
    }
}
