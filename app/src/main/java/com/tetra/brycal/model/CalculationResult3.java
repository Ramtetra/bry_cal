package com.tetra.brycal.model;

public class CalculationResult3 {
    private double absHum;
    private double enth;
    private double density;
    private double wbt;
    private int errCode;

    // Constructor
    public CalculationResult3(double absHum, double enth, double density, double wbt, int errCode) {
        this.absHum = absHum;
        this.enth = enth;
        this.density = density;
        this.wbt = wbt;
        this.errCode = errCode;
    }

    // Getters and Setters
    public double getAbsHum() {
        return absHum;
    }

    public double getEnth() {
        return enth;
    }

    public double getDensity() {
        return density;
    }

    public double getWbt() {
        return wbt;
    }
    public int getErrCode() {
        return errCode;
    }
}
