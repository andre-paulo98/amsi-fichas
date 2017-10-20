package me.andrepaulo.ficha2_guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SobreActivit extends AppCompatActivity {

    public static final String RESULTADO = "me.andrepaulo.RESULTADO";

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        tvResultado = (TextView) findViewById(R.id.tvQtVezes);
        int numero = getIntent().getIntExtra(RESULTADO, 0);
        tvResultado.setText("Quantidade de vezes certas: "+numero);
    }
}
