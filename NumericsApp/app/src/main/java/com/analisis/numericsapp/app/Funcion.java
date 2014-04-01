package com.analisis.numericsapp.app;

import org.nfunk.jep.JEP;

public class Funcion
{
    private String funcion;
    private String derivada;
    private String derivada2;

    public Funcion(String funcion) {

        this.funcion = funcion;

    }

    public Funcion(String funcion, String derivada) {

        this.funcion = funcion;
        this.derivada = derivada;

    }

    public Funcion(String funcion, String derivada, String derivada2) {

        this.funcion = funcion;
        this.derivada = derivada;
        this.derivada2 = derivada2;

    }

    public Boolean validar(){
        JEP jep = new JEP();
        jep.addStandardFunctions();
        jep.addVariable("x", 0);

        if(jep.parseExpression(this.funcion)!=null){
            return true;
        }
        return false;
    }

    public double evaluarFuncion(double x) {

//        String[] partes = this.funcion.split("x");
//
//        String funct = "";
//
//        for (int i = 0; i < partes.length; i++) {
//            funct = funct + partes[i] + x;
//        }
//        System.out.println(funct);
//
//
//        double y = Double.parseDouble(funct);
//        System.out.println(y);
//
//        return 0;

        JEP jep = new JEP();
        jep.addStandardFunctions();
        jep.addVariable("x", x);
        jep.parseExpression(this.funcion);
        return jep.getValue();

    }
    public double evaluarDerivada(double x) {

//        String[] partes = this.funcion.split("x");
//
//        String funct = "";
//
//        for (int i = 0; i < partes.length; i++) {
//            funct = funct + partes[i] + x;
//        }
//        System.out.println(funct);
//
//
//        double y = Double.parseDouble(funct);
//        System.out.println(y);
//
//        return 0;

        JEP jep = new JEP();
        jep.addStandardFunctions();
        jep.addVariable("x", x);
        jep.parseExpression(this.funcion);
        return jep.getValue();

    }

    public double evaluarDerivada2(double x) {

//        String[] partes = this.funcion.split("x");
//
//        String funct = "";
//
//        for (int i = 0; i < partes.length; i++) {
//            funct = funct + partes[i] + x;
//        }
//        System.out.println(funct);
//
//
//        double y = Double.parseDouble(funct);
//        System.out.println(y);
//
//        return 0;

        JEP jep = new JEP();
        jep.addStandardFunctions();
        jep.addVariable("x", x);
        jep.parseExpression(this.funcion);
        return jep.getValue();

    }



    public String getFuncion() {
        return this.funcion;
    }

    public String getDerivada() {
        return this.derivada;
    }

    public String getDerivada2() {
        return this.derivada2;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setDerivada(String derivada) {
        this.derivada = derivada;
    }

    public void setDerivada2(String derivada2) {
        this.derivada2 = derivada2;
    }
}
