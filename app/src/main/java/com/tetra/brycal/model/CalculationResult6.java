package com.tetra.brycal.model;

public class CalculationResult6 {
    public double tAbsHum;
    public double tRelH;
    public double tWbt;
    public double tEnth;
    public double tDencity;
    public int errCode;

    public CalculationResult6(double tAbsHum, double tRelH, double tWbt, double tEnth, double tDencity, int errCode) {
        this.tAbsHum = tAbsHum;
        this.tRelH = tRelH;
        this.tWbt = tWbt;
        this.tEnth = tEnth;
        this.tDencity = tDencity;
        this.errCode = errCode;
    }

    public double gettAbsHum() {
        return tAbsHum;
    }

    public void settAbsHum(double tAbsHum) {
        this.tAbsHum = tAbsHum;
    }

    public double gettRelH() {
        return tRelH;
    }

    public void settRelH(double tRelH) {
        this.tRelH = tRelH;
    }

    public double gettWbt() {
        return tWbt;
    }

    public void settWbt(double tWbt) {
        this.tWbt = tWbt;
    }

    public double gettEnth() {
        return tEnth;
    }

    public void settEnth(double tEnth) {
        this.tEnth = tEnth;
    }

    public double gettDencity() {
        return tDencity;
    }

    public void settDencity(double tDencity) {
        this.tDencity = tDencity;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
