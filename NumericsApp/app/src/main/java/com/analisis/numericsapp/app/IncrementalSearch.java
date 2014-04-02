package com.analisis.numericsapp.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IncrementalSearch extends ActionBarActivity
{

    public int xValue;
    public int delta;
    public int iterations;

    private static int contadorFilas=0;
    public static Funcion f= null;
    public static String respuesta;

    public static TextView response;
    public static double [][] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incremental_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.incremental_search, menu);

        setupButtonTableButton();
        return true;
    }

    private void setupButtonTableButton()
    {
        Button bt1 =(Button) findViewById(R.id.TableButton);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent();
                Bundle b = new Bundle();
                b.putSerializable("clase", "IncrementalSearch");
                myIntent.setClass(getApplicationContext(), AnswerTable.class);
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void CalculateIncrementalSearch(View v)
    {
        response = (TextView)findViewById(R.id.textView5);
        GetValues();

        matrix = incrementalSearch(xValue, delta, iterations);
        WrapperMatrix.matrix = matrix;
    }

    public void GetValues()
    {
        EditText xvalueText = (EditText)findViewById(R.id.editText2);
        xValue = Integer.parseInt(xvalueText.getText().toString());

        EditText deltaText = (EditText)findViewById(R.id.editText3);
        delta = Integer.parseInt(deltaText.getText().toString());

        EditText iterationsText = (EditText)findViewById(R.id.editText4);
        iterations = Integer.parseInt(iterationsText.getText().toString());

        EditText functionText = (EditText)findViewById(R.id.editText);
        f = new Funcion(functionText.getText().toString());
    }

    public static double[][] incrementalSearch(double xanterior, double incremento, int iteraciones)
    {
        //EL unico al que no le sobra una llave abajo
        double i[][] = new double[iteraciones][3];
        //double yanterior = funcion(xanterior);
        double yanterior = f.evaluarFuncion(xanterior);
        response.setText("Hola2");
        if (yanterior == 0)
        {
            System.out.println(xanterior + "es raiz");
            respuesta=xanterior + "es raiz";
        }
        else
        {
            int contador=0;
            double xactual = (xanterior + incremento);
//              double yactual = funcion(xactual);
            double yactual = f.evaluarFuncion(xactual);
            yactual = f.evaluarFuncion(xactual);
            i[contador][0] = contador;
            i[contador][1] = xanterior;
            i[contador][2] = yanterior;
            System.out.println(contador);
            contadorFilas++;
            contador = 1;
            while (yactual * yanterior > 0 && contador <= iteraciones)
            {
                xanterior = xactual;
                yanterior = yactual;
                i[contador][0] = contador;
                i[contador][1] = xactual;
                i[contador][2] = yactual;
                xactual = xanterior + incremento;
                //yactual = funcion(xactual);
                yactual = f.evaluarFuncion(xactual);
                System.out.println(contador);
                contadorFilas++;
                contador = contador + 1;
            }
            i[contador][0] = contador;
            i[contador][1] = xactual;
            i[contador][2] = yactual;
            System.out.println(contador);
            contadorFilas++;
            if (yactual == 0)
            {
                System.out.println(xactual + "es una raiz");
                respuesta= xactual + "es una raiz";
                response.setText(respuesta);
            }
            else
            {
                if (yactual * yanterior < 0)
                {
                    respuesta="los puntos:" + xanterior + ";" + xactual + "forman un intervalo";
                    response.setText(respuesta);
                    // System.out.println(i);
                }
                else
                {
                    System.out.println("No se encontro raiz ");
                    respuesta="No se encontro raiz ";
                    response.setText(respuesta);
                    // System.out.println(i);
                }
            }
            // System.out.println("xanterior:"+xanterior);
            // System.out.println("yanterior:"+yanterior);
        }
        return i;
    }

    public static void SetContador(){

        contadorFilas=0;

    }

    public static int getContador(){

        return contadorFilas;

    }
    public static String SetRespuesta(){
        return respuesta;
    }

    public static void SetFuncion(Funcion funcion){

        f=funcion;

    }
}
