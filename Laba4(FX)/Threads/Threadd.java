package Threads;

import java.util.ArrayList;
import java.util.List;

public class Threadd {
    private double numberActual;
    private double numberImaginary;
    private double degree;
    private double result;
    private double module;
    private double fi;
    private int k;

    public Threadd(double numberActual, double numberImaginary, double module, double degree, double fi, int k){
        this.numberActual = numberActual;
        this.numberImaginary = numberImaginary;
        this.degree = degree;
        this.fi = fi;
        this.k = k;
        this.module = module;
    }

    public double cal(){
        result = Math.pow(module, 1/degree) * (Math.cos((fi+2*numberActual*k)/degree) + Math.sin((fi+2*numberActual*k)/degree));
        setResult(result);
        return result;
    }

    public void setResult(double result){
        this.result = result;
    }

    public double getResult() {
        return result;
    }

}
