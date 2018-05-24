package Threads;

public class MainThread implements Runnable{
    private double numberImaginary;
    private double numberActual;
    private double degree;
    private double module;
    private double fi;
    private int k;

    private Threadd threadd;

    private double result;

    private void Calculate(double numberActual, double numberImaginary, double degree){

        double numberAct = Math.pow(numberActual, 2);
        double numberImag = Math.pow(numberImaginary, 2);
        double module = numberAct + numberImag;
        this.module = module;
        double fi = Math.acos(numberAct / module);
        this.fi = fi;
    }

    public MainThread(double numberActual, double numberImaginary, double degree, int k) {
        this.numberActual = numberActual;
        this.numberImaginary = numberImaginary;
        this.degree = degree;
        this.k = k;
        Calculate(numberActual,numberImaginary, degree);
    }

    public double getResult(){
        return result;
    }

    @Override
    public void run(){
        threadd = new Threadd(numberImaginary, numberActual, module, degree, fi, k);
        result = threadd.cal();
    }


}