package com.example.minhaprimeiraaplicacao;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText objPeso;
    private EditText objAltura;
    private Button objBtnCalcular;
    private TextView objResultado;
    private TextView objTxtCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetarObjetosDaActivity();
    }

    protected void SetarObjetosDaActivity() {
        objPeso = (EditText) findViewById(R.id.txtPeso);
        objAltura = (EditText) findViewById(R.id.txtAltura);
        objBtnCalcular = (Button) findViewById(R.id.btnCalcular);
        objResultado = (TextView) findViewById(R.id.txtResultado);
        objTxtCalculo = (TextView) findViewById(R.id.txtCalculo);
        objResultado.setText("");
        objTxtCalculo.setText("");
        objBtnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcularIMC();
            }
        });
    }

    protected void CalcularIMC() {
        if (objPeso.getText().toString().isEmpty())
        {
            EnviarMensagem("Você deve informar seu peso");
            return;
        }
        if (objAltura.getText().toString().isEmpty())
        {
            EnviarMensagem("Você deve informar sua altura");
            return;
        }
        double peso = Double.parseDouble(objPeso.getText().toString());
        double altura = Double.parseDouble(objAltura.getText().toString());
        double result = peso / (altura * altura);
        objTxtCalculo.setText(String.format("%.1f",result));
        if (result < 18.5) {
            objResultado.setText("Magreza total - Precisa engordar");
        }
        else if (result <= 24.9) {
            objResultado.setText("Normal. Parabéns!!!");
            objTxtCalculo.setTextColor(Color.BLUE);
        }
        else if (result <= 29.9) {
            objResultado.setText("opa, Atenção!!! Você está com sobrepeso ajustado");
            objTxtCalculo.setTextColor(Color.GREEN);
        }
        else if (result <= 39.9) {
            objResultado.setText("Cuidado!!! você está Obeso");
            objTxtCalculo.setTextColor(Color.YELLOW);
        }
        else {
            objResultado.setText("Muito Cuidado!!! Obesidade morbida");
            objTxtCalculo.setTextColor(Color.RED);
        }
    }

    private void EnviarMensagem(String mens)
    {
        Toast toast = Toast.makeText(this, mens, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
