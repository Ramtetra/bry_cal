package com.tetra.brycal.model;

public class CalculationResult2 {
    private double wbt;
    private double relH;
    private double enth;
    private double absHum;
    private double density;
    private int errCode;

    public CalculationResult2(double wbt, double relH, double enth, double absHum, double density, int errCode) {
        this.wbt = wbt;
        this.relH = relH;
        this.enth = enth;
        this.absHum = absHum;
        this.density = density;
        this.errCode = errCode;
    }

    // Getters
    public double getWbt() {
        return wbt;
    }

    public double getRelH() {
        return relH;
    }

    public double getEnth() {
        return enth;
    }

    public double getAbsHum() {
        return absHum;
    }

    public double getDensity() {
        return density;
    }

    public int getErrCode() {
        return errCode;
    }
}
