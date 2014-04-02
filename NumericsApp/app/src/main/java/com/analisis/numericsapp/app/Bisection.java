package com.analisis.numericsapp.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bisection extends ActionBarActivity {

    public int xValue;
    public int iterations;
    public int XsValue;
    public double tolerance;

    private static int contadorFilas=0;
    public static Funcion f= null;
    private static String respuesta;

    public static TextView response;
    public static double [][] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bisection, menu);

        setupButtonTableButton();
        return true;
    }

    private void setupButtonTableButton() {

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

    public void CalculateBisection(View v)
    {
        response = (TextView)findViewById(R.id.textView5);
        GetValues();

        matrix = bisection(xValue, XsValue, iterations, tolerance);
        WrapperMatrix.matrix = matrix;
    }

    public void GetValues()
    {
        EditText xvalueText = (EditText)findViewById(R.id.editText2);
        xValue = Integer.parseInt(xvalueText.getText().toString());

        EditText iterationsText = (EditText)findViewById(R.id.editText5);
        iterations = Integer.parseInt(iterationsText.getText().toString());

        EditText XsValueText = (EditText)findViewById(R.id.editText3);
        XsValue = Integer.parseInt(XsValueText.getText().toString());

        EditText ToleranceText = (EditText)findViewById(R.id.editText4);
        tolerance = Integer.parseInt(ToleranceText.getText().toString());

        EditText functionText = (EditText)findViewById(R.id.editText);
        f = new Funcion(functionText.getText().toString());
    }


    public static double[][] bisection(double xInf, double xSup, int iterations, double tolerance) {
        double i[][] = new double[iterations][6];
        double yInf = f.evaluarFuncion(xInf);
        double ySup = f.evaluarFuncion(xSup);
        if (yInf == 0) {
            System.out.println(xInf + "xInf es raiz");
            respuesta=xInf + "xInf es raiz";
        } else if (ySup == 0) {
            System.out.println(xSup + "xSup es raiz");
            respuesta=xSup + "xSup es raiz";
        } else if ((yInf * ySup) > 0) {
            System.out.println("Posiblemente");
            respuesta="Posiblemente";
        } else {

            int contador=0;
            double xMedio = (xInf + xSup) / 2;
            double yMedio = f.evaluarFuncion(xMedio);
            double E = tolerance + 1;
            i[contador][0] = contador;
            i[contador][1] = xInf;
            i[contador][2] = xSup;
            i[contador][3] = xMedio;
            i[contador][4] = yMedio;
            i[contador][5] = E;
            contadorFilas++;
            contador = 1;
            while (E > tolerance && yMedio != 0 && contador <= iterations) {
                if ((yInf * yMedio) < 0) {
                    xSup = xMedio;
                    ySup = yMedio;
                } else {
                    xInf = xMedio;
                    yInf = yMedio;
                }
                double Aux = xMedio;


                xMedio = (xSup + xInf) / 2;
                yMedio = f.evaluarFuncion(xMedio);
                E = Math.abs(xMedio - Aux);
                i[contador][0] = contador;
                i[contador][1] = xInf;
                i[contador][2] = xSup;
                i[contador][3] = xMedio;
                i[contador][4] = yMedio;
                i[contador][5] = E;
                contadorFilas++;
                contador = contador + 1;
            }
            for (int j = 0; j < iterations; j++) {

                for (int k = 0; k < 6; k++) {
                    System.out.print(i[j][k] + "                       ");
                }
                System.out.println("");
            }
            if (yMedio == 0) {
                System.out.println("xMedio=" + xMedio + "es raiz");
                respuesta="xMedio=" + xMedio + "es raiz";
            } else if (E < tolerance) {
                System.out.println("xMedio=" + xMedio + "es raiz con error " + E);
                respuesta="xMedio=" + xMedio + "es raiz con error " + E;
            } else {
                System.out.println("fracaso");
                respuesta="fracaso";
            }
        }
        return i;
    }
    public static String SetRespuesta(){
        return respuesta;
    }
    public static int getContador(){
        return contadorFilas;
    }
    public static void SetContador(){
        contadorFilas=0;
    }
    public static void SetFuncion(Funcion funcion){
        f=funcion;
    }

}
