package com.example.a1_2_reemplazarelementosdearregloint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView arrOrgnl;
    private EditText numReemplazar;
    private EditText numNuevo;
    private Button btnArrOrgnl;
    private Button btnReemplazar;
    private Button btnCancelar;
    private TextView arrReemplazo;
    private int[] aleatorio;
    private static final int VAL_MAX = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrOrgnl = findViewById(R.id.arrOrgnl);
        arrReemplazo = findViewById(R.id.arrReemplazo);
        btnArrOrgnl = findViewById(R.id.btnArrOrgnl);
        btnReemplazar = findViewById(R.id.btnReemplazar);
        btnCancelar = findViewById(R.id.btnCancelar);
        numReemplazar = findViewById(R.id.numReemplazar);
        numNuevo = findViewById(R.id.numNuevo);

        InputFilter filtroMaximo = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                try {
                    int valor = Integer.parseInt(dest.toString() + charSequence.toString());

                    if (valor <= VAL_MAX){
                        return null;
                    }else{
                        arrReemplazo.setText("Ingresa un valor menor o igual a " + VAL_MAX);
                        return "";
                    }
                }
                catch (NumberFormatException e){
                    arrReemplazo.setText("Ingresa un valor numérico válido");
                    return "";
                }
            }
        };

        numReemplazar.setFilters(new InputFilter[]{filtroMaximo});
        numNuevo.setFilters(new InputFilter[]{filtroMaximo});

        btnArrOrgnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aleatorio = generarAleatorio(10, 1, 10);
                mostrarArreglo(aleatorio);
            }
        });

        btnReemplazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int elementoReemplazar = Integer.parseInt(numReemplazar.getText().toString());
                    int elementoNuevo = Integer.parseInt(numNuevo.getText().toString());

                    reemplazarElementos(aleatorio, elementoReemplazar, elementoNuevo);
                    mostrarResultado(aleatorio);
                }
                catch (NumberFormatException e){
                    arrReemplazo.setText("Ingresa valores numéricos válidos");
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //asignamos valor original a variables y textViews
                arrOrgnl.setText("");
                arrReemplazo.setText("");
                numReemplazar.setText("");
                numNuevo.setText("");
            }
        });

    }

    private void mostrarResultado(int[] arreglo) {
        StringBuilder construirResultadoTxtV = new StringBuilder();

        for (int num : arreglo){
            construirResultadoTxtV.append(num).append(" ");
        }

        arrReemplazo.setText("Resultado: " + construirResultadoTxtV.toString());
    }

    private void reemplazarElementos(int[] arreglo, int reemplazar, int nuevo) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == reemplazar){
                arreglo[i] = nuevo;
            }
        }
    }

    private void mostrarArreglo(int[] arreglo) {
        StringBuilder construirArrOrgnlTxtV = new StringBuilder();

        for(int num : arreglo){
            construirArrOrgnlTxtV.append(num).append(" ");
        }

        arrOrgnl.setText("Arreglo: " + construirArrOrgnlTxtV.toString());
    }

    private int[] generarAleatorio(int longitud, int minimo, int maximo) {
        int[] arrAleatorio = new int[longitud];
        Random r = new Random();

        for (int i = 0; i < longitud; i++) {
            arrAleatorio[i] = r.nextInt((maximo - minimo) + 1) + minimo;
        }

        return arrAleatorio;
    }
}