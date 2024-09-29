package com.tetra.brycal.model;

public class CalculationResult4 {
    private double wbt;
    private double relH;
    private double enth;
    private double absHum;
    private double density;
    private int errCode;

    public CalculationResult4(double wbt, double relH, double enth, double absHum, double density, int errCode) {
        this.wbt = wbt;
        this.relH = relH;
        this.enth = enth;
        this.absHum = absHum;
        this.density = density;
        this.errCode = errCode;
    }

    public double getWbt() {
        return wbt;
    }

    public void setWbt(double wbt) {
        this.wbt = wbt;
    }

    public double getRelH() {
        return relH;
    }

    public void setRelH(double relH) {
        this.relH = relH;
    }

    public double getEnth() {
        return enth;
    }

    public void setEnth(double enth) {
        this.enth = enth;
    }

    public double getAbsHum() {
        return absHum;
    }

    public void setAbsHum(double absHum) {
        this.absHum = absHum;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
