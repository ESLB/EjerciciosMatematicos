package com.frontlib.eslb.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Button OkEmpezar;
    Button OkCalificar;
    Button OkDigitos;
    TextView Problema;
    TextView RespuestaCorrecta;
    TextView CantDigitos;
    EditText NumeroPreguntas;
    EditText ET_Respuesta;
    EditText ET_Digitos;

    int Contador = 0;
    int cont1= 0;
    int NumeroDePreguntas;
    int NumeroDeDigitos;
    double Numero1 = 0;
    double Numero2 = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Problema = (TextView) findViewById(R.id.TV_Problema);
        ET_Respuesta = (EditText) findViewById(R.id.ET_Respuesta);
        OkCalificar = (Button) findViewById(R.id.Bttn_Calificar);

        OkEmpezar = (Button) findViewById(R.id.Bttn_Empezar);
        NumeroPreguntas = (EditText) findViewById(R.id.ET_NumeroPreguntas);

        RespuestaCorrecta = (TextView) findViewById(R.id.TV_RespuestaCorrecta);

        CantDigitos = (TextView) findViewById(R.id.TV_CantDigitos);
        ET_Digitos = (EditText) findViewById(R.id.ET_CantDigitos);
        OkDigitos = (Button) findViewById(R.id.Bttn_CantDigitos);

        Problema.setVisibility(View.INVISIBLE);
        ET_Respuesta.setVisibility(View.INVISIBLE);
        OkCalificar.setVisibility(View.INVISIBLE);
        RespuestaCorrecta.setVisibility(View.INVISIBLE);
        NumeroPreguntas.setVisibility(View.INVISIBLE);
        OkEmpezar.setVisibility(View.INVISIBLE);

        OkDigitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    NumeroDeDigitos = Integer.parseInt(ET_Digitos.getText().toString());
                    if(NumeroDeDigitos>6||NumeroDeDigitos<2)
                    {
                        throw new Exception();
                    }else{
                        OkEmpezar.setVisibility(View.VISIBLE);
                        NumeroPreguntas.setVisibility(View.VISIBLE);
                        NumeroPreguntas.setFocusableInTouchMode(true);
                        ET_Digitos.setVisibility(View.INVISIBLE);
                        CantDigitos.setVisibility(View.INVISIBLE);
                        OkDigitos.setVisibility(View.INVISIBLE);

                    }
                }catch (Exception e)
                {
                    MakeToast(getApplicationContext(), "Entrada inválida");
                }
            }
        });


        OkEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    NumeroDePreguntas = Integer.parseInt(NumeroPreguntas.getText().toString());
                    OkEmpezar.setVisibility(View.INVISIBLE);
                    NumeroPreguntas.setVisibility(View.INVISIBLE);
                    Problema.setVisibility(View.VISIBLE);
                    OkCalificar.setVisibility(View.VISIBLE);
                    ET_Respuesta.setVisibility(View.VISIBLE);
                    CambiarPregunta();
                }catch (Exception e)
                {
                    MakeToast(getApplicationContext(), "Entrada inválida");
                }

            }
        });

        OkCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cont1 == 1)
                {
                    cont1 = 0;
                    CambiarPregunta();
                    ET_Respuesta.setText("");
                    RespuestaCorrecta.setVisibility(View.INVISIBLE);
                    ET_Respuesta.setFocusableInTouchMode(true);
                }
                BigInteger rpta = BigInteger.valueOf((long)(Numero1*Numero2));
                try{

                    BigInteger RespuestaObtenida  = new BigInteger(ET_Respuesta.getText().toString());
                    if (RespuestaObtenida.equals(rpta))
                    {
                        MakeToast(getApplicationContext(), "¡Bien!");
                        Contador++;
                        ET_Respuesta.setText("");
                        if(Contador==NumeroDePreguntas){
                            MakeToast(getApplicationContext(), "Listo");
                            finish();
                        }else
                        {
                            CambiarPregunta();
                        }

                    }
                    else{
                        cont1++;
                        MakeToast(getApplicationContext(), "¡Mal!");
                        RespuestaCorrecta.setText("Respuesta Correcta: " + rpta);
                        RespuestaCorrecta.setVisibility(View.VISIBLE);
                        ET_Respuesta.setFocusable(false);
                    }
                }catch (Exception e){

                }



            }
        });






    }

    protected void CambiarPregunta()
    {
        Numero1 = 0;
        Numero2 = 0;
        Numero1 = numRandom(Numero1, NumeroDeDigitos);
        Numero2 = numRandom(Numero2, NumeroDeDigitos);

        Problema.setText((Contador+1) +") ¿"+ (int)Numero1 +" x "+ (int)Numero2+"?");
    }

    public static double numRandom(double result,int length){
    //Result Donde guardo el Resultado Length variable que indica el tamaño de mi numero.
    //Array donde guardo los randoms
            int[] arrayRandom=new int[length];
    //Variable donde genero numeros por separado
            int pos;
    //Variable donde eligo el Rango de Numeros
            int nNums = 9;//En este caso del 1 al 9.
    //Creo un objeto Pila
            Stack< Integer> pila = new Stack< Integer>();
    //For para generar los numeros
            for (int i = 0; i < arrayRandom.length; i++) {
    //Genero un numero random
                pos = (int) Math.floor(Math.random() * nNums+1);
    //Lo guardo en el array
                arrayRandom[i] = pos;

    //Si la pila ya contiene el numero
                while (pila.contains(pos)) {
    //Vuelvo a generar un numero random  hasta que no se repita
                    pos = (int) Math.floor(Math.random() * nNums);
                    arrayRandom[i] = pos;
                }
    //Guardo en el Stack (pila)
                pila.push(pos);
            }
    //y los muestro
    //System.out.println("Núm. aleatorios sin repetición:");
            System.out.println(pila.toString());

    //Lo convierto a un solo numero entero
    //Variable para Conseguir decena/centena/ Etc...
            int multiplicador = 1;
    //Segun el tamaño del numero (length)
            for (int i = 1; i < length; i++) {

                multiplicador=multiplicador*10;
            }
    //Convierto el Array de numeros aleatorios en un solo entero
            for (int i = 0; i < length; i++) {
    //Multiplicando por el mas alto
                result += (arrayRandom[i]*multiplicador);
    //Decremento el multiplicador
                multiplicador = multiplicador/10;

            }

            return result;

        }

    public static void MakeToast(Context context, String men)
        {
            Toast.makeText(context, men, Toast.LENGTH_SHORT).show();
        }

    }


