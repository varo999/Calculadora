package com.example.calculadora;

import android.os.Bundle;
import android.telecom.PhoneAccount;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> resultados = new ArrayList<>();
    private TextView resultado;
    private String operando1 = "", operando2 = "", operacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botones numéricos
        Button boton1 = findViewById(R.id.boton_1);
        boton1.setOnClickListener(v -> agregarNumero("1"));

        Button boton2 = findViewById(R.id.boton_2);
        boton2.setOnClickListener(v -> agregarNumero("2"));

        Button boton3 = findViewById(R.id.boton_3);
        boton3.setOnClickListener(v -> agregarNumero("3"));

        Button boton4 = findViewById(R.id.boton_4);
        boton4.setOnClickListener(v -> agregarNumero("4"));

        Button boton5 = findViewById(R.id.boton_5);
        boton5.setOnClickListener(v -> agregarNumero("5"));

        Button boton6 = findViewById(R.id.boton_6);
        boton6.setOnClickListener(v -> agregarNumero("6"));

        Button boton7 = findViewById(R.id.boton_7);
        boton7.setOnClickListener(v -> agregarNumero("7"));

        Button boton8 = findViewById(R.id.boton_8);
        boton8.setOnClickListener(v -> agregarNumero("8"));

        Button boton9 = findViewById(R.id.boton_9);
        boton9.setOnClickListener(v -> agregarNumero("9"));

        Button boton0 = findViewById(R.id.boton_0);
        boton0.setOnClickListener(v -> agregarNumero("0"));

        // Botones de operaciones
        Button botonSuma = findViewById(R.id.boton_suma);
        botonSuma.setOnClickListener(v -> seleccionarOperacion("+"));

        Button botonResta = findViewById(R.id.boton_resta);
        botonResta.setOnClickListener(v -> seleccionarOperacion("-"));

        Button botonMultiplicacion = findViewById(R.id.boton_multiplicacion);
        botonMultiplicacion.setOnClickListener(v -> seleccionarOperacion("×"));

        Button botonDivision = findViewById(R.id.boton_division);
        botonDivision.setOnClickListener(v -> seleccionarOperacion("÷"));

        // Botón de igual
        Button botonIgual = findViewById(R.id.boton_igual);
        botonIgual.setOnClickListener(v -> calcularResultado());

        Button botonBorrar = findViewById(R.id.boton_borrar);
        botonBorrar.setOnClickListener(v -> borrar());

        Button botonlistaResultados = findViewById(R.id.boton_TodRes);
        botonlistaResultados.setOnClickListener(v -> listarResultados());

    }

    private void listarResultados() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultados");

        // Convertir la lista de resultados a un array de cadenas
        String[] resultadosArray = resultados.toArray(new String[0]);

        builder.setItems(resultadosArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción a realizar cuando el usuario selecciona un elemento
                String resultadoSeleccionado = resultadosArray[which];

                // Establecer el número seleccionado como el nuevo operando1
                operando1 = resultadoSeleccionado;
                resultado.setText(operando1);
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }


    private void borrar(){
        // Limpiar los operandos y la operación
        operando1 = "";
        operando2 = "";
        operacion = "";

        // Actualizar el TextView para mostrar un valor vacío o "0"
        resultado.setText("");
    }


    private void agregarNumero(String numero) {
        if (operacion.isEmpty()) {
            operando1 += numero;
            resultado.setText(operando1);
        } else {
            operando2 += numero;
            resultado.setText(operando2);
        }
    }


    private void seleccionarOperacion(String operacionSeleccionada) {
        if (!operando1.isEmpty()) {
            operacion = operacionSeleccionada;
            resultado.setText(operando1 + " " + operacion);
        }
    }

    private void calcularResultado() {
        if (!operando1.isEmpty() && !operando2.isEmpty() && !operacion.isEmpty()) {
            double num1 = Double.parseDouble(operando1);
            double num2 = Double.parseDouble(operando2);
            double resultadoFinal = 0;

            switch (operacion) {
                case "+":
                    resultadoFinal = num1 + num2;
                    break;
                case "-":
                    resultadoFinal = num1 - num2;
                    break;
                case "×":
                    resultadoFinal = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) {
                        resultadoFinal = num1 / num2;
                    } else {
                        resultado.setText("Error");
                        return;
                    }
                    break;
            }
            resultado.setText(String.valueOf(resultadoFinal));
            resultados.add(String.valueOf(resultadoFinal));
            operando1 = String.valueOf(resultadoFinal);
            operando2 = "";
            operacion = "";
        }
    }
}



