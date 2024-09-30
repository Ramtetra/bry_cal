package com.tetra.brycal;

import com.tetra.brycal.model.CalculationResult2;
import com.tetra.brycal.model.CalculationResult3;
import com.tetra.brycal.model.CalculationResult6;
import com.tetra.brycal.model.CalculationResult5;
import com.tetra.brycal.model.CalculationResult4;

public class PsyLib {
    CodeTranslation codeTranslation=new CodeTranslation();
   // Function with Parameter unit,DB,WB,Altitude)
   public CalculationResult2 formulaDBTWBTLinric2(int units, double tdbt, double wbt, double tAlt) {
       double absHum = 0;
       double relH = 0;
       double enth = 0;
       double density = 0;
       int errCode = 0;

       if (units != 0) {
           absHum = codeTranslation.LCSI_WBTOGRAMS(tdbt, wbt, tAlt);
           relH = codeTranslation.LCSI_RH(tdbt, absHum, tAlt);
           enth = codeTranslation.LCSI_ENTHALPY(tdbt, absHum);
           density = codeTranslation.LCSI_DEWPOINT(absHum, tAlt);

       }else {
           absHum = codeTranslation.LCWBTOGRAINS(tdbt, wbt, tAlt);
           relH = codeTranslation.LCRH(tdbt, absHum, tAlt);
           enth = codeTranslation.LCENTHALPY(tdbt, absHum);
           density = codeTranslation.LCDEWPOINT(absHum, tAlt);
       }
       if (relH < 0f || relH > 99.9f) {
           relH = 99.9f;
           formulaDBTRHTLinric3(units, tdbt, relH, tAlt);
           wbt = Math.round(wbt * 10) / 10.0;

       }
       errCode = 0;
       // Rounding
       absHum = Math.round(absHum * 10) / 10.0;
       relH = Math.round(relH * 10) / 10.0;
       enth = Math.round(enth * 10) / 10.0;
       density = Math.round(density * 10) / 10.0;

       return new CalculationResult2(wbt, relH, enth, absHum, density, errCode);
   }

    // Function with Parameter unit,DB,RH,Altitude)
    public CalculationResult3 formulaDBTRHTLinric3(int units, double tdbt, double tRelH, double tAlt) {
        double absHum = 0;
        double enth = 0;
        double density = 0;
        double wbt = 0;
        int errCode = 0;

        if (units != 0) {
                tRelH /= 100f;
                absHum = codeTranslation.LCSI_RHTOGRAMS(tdbt, tRelH, tAlt);
                enth = codeTranslation.LCSI_ENTHALPY(tdbt, absHum);
                density = codeTranslation.LCSI_DEWPOINT(absHum, tAlt);
                wbt = codeTranslation.LCSI_WETBULB(tdbt, absHum, tAlt);
                errCode = 0;
                // Rounding
                absHum = Math.round(absHum * 10) / 10.0;
                tRelH = Math.round(tRelH * 10) / 10.0;
                enth = Math.round(enth * 10) / 10.0;
                density = Math.round(density * 10) / 10.0;
                wbt = Math.round(wbt * 10) / 10.0;
            }else {
            absHum = codeTranslation.LCSI_RHTOGRAMS(tdbt, tRelH, tAlt);
            enth = codeTranslation.LCENTHALPY(tdbt, absHum);
            density = codeTranslation.LCDEWPOINT(absHum, tAlt);
            wbt = codeTranslation.LCWETBULB(tdbt, absHum, tAlt);
            errCode = 0;
            // Rounding
            absHum = Math.round(absHum * 10) / 10.0;
            tRelH = Math.round(tRelH * 10) / 10.0;
            enth = Math.round(enth * 10) / 10.0;
            density = Math.round(density * 10) / 10.0;
            wbt = Math.round(wbt * 10) / 10.0;
        }
             if (tRelH > 99.9) {
                tRelH = 99.9;
                tRelH /= 100f;
                absHum = codeTranslation.LCSI_RHTOGRAMS(tdbt, tRelH, tAlt);
                enth = codeTranslation.LCSI_ENTHALPY(tdbt, absHum);
                density = codeTranslation.LCDEWPOINT(absHum, tAlt);
                wbt = codeTranslation.LCSI_WETBULB(tdbt, absHum, tAlt);
                errCode = 0;

                // Rounding
                absHum = Math.round(absHum * 10) / 10.0;
                tRelH = Math.round(tRelH * 10) / 10.0;
                enth = Math.round(enth * 10) / 10.0;
                density = Math.round(density * 10) / 10.0;
                wbt = Math.round(wbt * 10) / 10.0;
            }

        return new CalculationResult3(absHum, enth, density, wbt, errCode);
    }

    // Function with Parameter unit,DB,WB,Altitude)

    public CalculationResult6 formulaDBTGRAINSTLinric6(int units, double tdbt, double tAbsHum, double tAlt) {
        double tWbt = 0;
        double tRelH = 0;
        double tEnth = 0;
        double tDencity = 0;
        int errCode = 0;

        if (units != 0) {
            // Perform calculations
            tWbt = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCSI_RH(tdbt, tAbsHum, tAlt);
            tEnth = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum);
            tDencity = codeTranslation.LCSI_DEWPOINT(tAbsHum, tAlt);
        } else {
            // Perform calculations
            tWbt = codeTranslation.LCWETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCRH(tdbt, tAbsHum, tAlt);
            tEnth = codeTranslation.LCENTHALPY(tdbt, tAbsHum);
            tDencity = codeTranslation.LCDEWPOINT(tAbsHum, tAlt);


        }
        // Handle edge case where tRelH is out of range
        if (tRelH < 0f || tRelH > 99.9f) {
            tRelH = 99.9f;

            formulaDBTRHTLinric3(units, tdbt, tRelH, tAlt);
            tWbt = Math.round(tWbt * 10) / 10.0;
            tRelH = Math.round(tRelH * 10) / 10.0;
            tEnth = Math.round(tEnth * 10) / 10.0;
            tDencity = Math.round(tDencity * 10) / 10.0;

        }
        errCode = 0; // Set error code to 0 for success
        tWbt = Math.round(tWbt * 10) / 10.0;
        tRelH = Math.round(tRelH * 10) / 10.0;
        tEnth = Math.round(tEnth * 10) / 10.0;
        tDencity = Math.round(tDencity * 10) / 10.0;
        tAbsHum = Math.round(tAbsHum * 10) / 10.0;

        // Return the result as a FormulaResult object
        return new CalculationResult6(tAbsHum, tRelH, tWbt, tEnth, tDencity, errCode);
    }


    public CalculationResult5 formulaDBTDEWPOINTLinric5(int units, double tdbt, double tDensity, double tAlt) {
        double tWbt = 0;
        double tRelH = 0;
        double tEnth = 0;
        double tAbsHum = 0;
        int errCode = 0;
        double x=0;

        if (units != 0) {
            // Perform calculations
            tAbsHum = codeTranslation.LCSI_SATGRAMS(tDensity, tAlt);
           /// tw = LCSI_WETBULB(t, X, Alitude);
            tWbt = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCSI_RH(tdbt, tAbsHum, tAlt);
            tEnth = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum);
          //  tAbsHum = codeTranslation.LCSI_DEWPOINT(tAbsHum, tAlt);

        } else {
            // Perform calculations
            tWbt = codeTranslation.LCWETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCRH(tdbt, tAbsHum, tAlt);
            tEnth = codeTranslation.LCENTHALPY(tdbt, tAbsHum);
           // tAbsHum = codeTranslation.LCDEWPOINT(tAbsHum, tAlt);
            tAbsHum = codeTranslation.LCSATGRAINS(tdbt, tAlt);


        }
        // Handle edge case where tRelH is out of range
        if (tRelH < 0f || tRelH > 99.9f) {
            tRelH = 99.9f;

           // formulaDBTDEWPOINTLinric5(units, tdbt, tRelH, tAlt);
            tWbt = Math.round(tWbt * 10) / 10.0;
            tRelH = Math.round(tRelH * 10) / 10.0;
            tEnth = Math.round(tEnth * 10) / 10.0;
            tAbsHum = Math.round(tAbsHum * 10) / 10.0;

        }
        errCode = 0; // Set error code to 0 for success
        tWbt = Math.round(tWbt * 10) / 10.0;
        tRelH = Math.round(tRelH * 10) / 10.0;
        tEnth = Math.round(tEnth * 10) / 10.0;
        tAbsHum = Math.round(tAbsHum * 10) / 10.0;

        // Return the result as a FormulaResult object
        return new CalculationResult5(tAbsHum, tRelH, tWbt, tEnth, tAbsHum, errCode);
    }

    public CalculationResult4 formulaDBTENTHELPYLinric4(int units, double tdbt, double tEnthalpy, double tAlt) {
        double tWbt = 0;
        double tRelH = 0;
        double tDensity = 0;
        double tAbsHum = 0;
        int errCode = 0;
        double temp_tdbt=0;
        double Temp_tEnthalpy=0;
        double temp_tAbsHum=0;

        if (units != 0) {
            // Perform calculations

            temp_tdbt = tdbt * 9 / 5 + 32;
            Temp_tEnthalpy = tEnthalpy / 2.326 + 7.712;

            temp_tAbsHum=(Temp_tEnthalpy-0.24 * temp_tdbt)/(1061 + 0.444 * temp_tdbt) *7000;

            tdbt = (temp_tdbt - 32) * 5 / 9;

            tAbsHum=temp_tAbsHum/7;

            tDensity = codeTranslation.LCSI_DEWPOINT(tAbsHum, tAlt);
            tWbt = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCSI_RH(tdbt, tAbsHum, tAlt);


        } else {
            // Perform calculations
            tWbt = codeTranslation.LCWETBULB(tdbt, tAbsHum, tAlt);
            tRelH = codeTranslation.LCRH(tdbt, tAbsHum, tAlt);
            tDensity = codeTranslation.LCRHTOGRAINS(tdbt, tRelH,tAlt);
            tAbsHum = codeTranslation.LCDEWPOINT(tAbsHum, tAlt);


        }
        // Handle edge case where tRelH is out of range
        if (tRelH < 0f || tRelH > 99.9f) {
            tRelH = 99.9f;

            //formulaDBTGRAINSTLinric4(units, tdbt, tRelH, tAlt);
            tWbt = Math.round(tWbt * 10) / 10.0;
            tRelH = Math.round(tRelH * 10) / 10.0;
            tDensity = Math.round(tDensity * 10) / 10.0;
            tAbsHum = Math.round(tAbsHum * 10) / 10.0;

        }
        errCode = 0; // Set error code to 0 for success
        tWbt = Math.round(tWbt * 10) / 10.0;
        tRelH = Math.round(tRelH * 10) / 10.0;
        tDensity = Math.round(tDensity * 10) / 10.0;
        tAbsHum = Math.round(tAbsHum * 10) / 10.0;

        // Return the result as a FormulaResult object
        return new CalculationResult4(tWbt, tRelH, tWbt,tAbsHum,tDensity, errCode);
    }

/*
    public int FormulaDBTRHTLinric(int Units, double tdbt, double tRelH, double tAlt, double[] tWbt, double[] tEnth, double[] tAbsHum, double[] tDencity, int[] ErrCode)
    {
        if (Units != 0)
        {
            if (tRelH > 0f)
            {
                tRelH /= 100f;
                tAbsHum[0] =codeTranslation.LCSI_RHTOGRAMS(tdbt, tRelH, tAlt);
                tEnth[0] = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum[0]);
                tDencity[0] = codeTranslation.LCSI_DENSITY(tdbt, tAbsHum[0], tAlt);
                tWbt[0] = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum[0], tAlt);
                ErrCode[0] = 0;
                tAbsHum[0] = (float)Math.round(tAbsHum[0] * 10) / 10;
                tRelH = (float)Math.round(tRelH * 10) / 10;
                tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
                tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
                tWbt[0] = (float)Math.round(tWbt[0] * 10) / 10;
            }
            else if ((double)tRelH > 99.9)
            {
                tRelH = 99.9f;
                tRelH /= 100f;
                tAbsHum[0] = codeTranslation.LCSI_RHTOGRAMS(tdbt, tRelH, tAlt);
                tEnth[0] = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum[0]);
                tDencity[0] = codeTranslation.LCSI_DENSITY(tdbt, tAbsHum[0], tAlt);
                tWbt[0] = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum[0], tAlt);
                ErrCode[0] = 0;
                tAbsHum[0] = (float)Math.round(tAbsHum[0] * 10) / 10;
                tRelH = (float)Math.round(tRelH * 10) / 10;
                tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
                tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
                tWbt[0] = (float)Math.round(tWbt[0] * 10) / 10;
            }
        }

        return 0;
    }

    public int FormulaDBTWBTLinric(int Units, double tdbt, double[] tWbt, int tAlt, double[] tRelH, double[] tEnth, double[] tAbsHum, double[] tDencity, int[] ErrCode)
    {
        if (Units != 0)
        {
            tAbsHum[0] = codeTranslation.LCSI_WBTOGRAMS(tdbt, tWbt[0], tAlt);
            tRelH[0] = codeTranslation.LCSI_RH(tdbt, tAbsHum[0], tAlt);
            tEnth[0] = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum[0]);
            tDencity[0] = codeTranslation.LCSI_DENSITY(tdbt, tAbsHum[0], tAlt);
            tAbsHum[0] = (float)Math.round(tAbsHum[0] * 10) / 10;
            tRelH[0] = (float)Math.round(tRelH[0] * 10) / 10;
            tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
            tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
            ErrCode[0] = 0;
        }

        if (tRelH[0] < 0f || tRelH[0] > 99.9)
        {
            tRelH[0] = 99.9f;
            FormulaDBTRHTLinric(Units, tdbt, tRelH[0], tAlt, tWbt, tEnth, tAbsHum, tDencity, ErrCode);
            tAbsHum[0] = (float)Math.round(tAbsHum[0] * 10) / 10;
            tRelH[0] = (float)Math.round(tRelH[0] * 10) / 10;
            tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
            tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
            tWbt[0] = (float)Math.round(tWbt[0] * 10) / 10;
        }

        return ErrCode[0];
    }

   //Function with Parameter unit,DB,HR,Altitude)

    public int FormulaDBTGRAINSTLinric(int Units, double tdbt, double[] tAbsHum, double[] tRelH, int tAlt, double[] tWbt, double[] tEnth, double[] tDencity, int[] ErrCode)
    {
        if (Units != 0)
        {
            tWbt[0] = codeTranslation.LCSI_WETBULB(tdbt, tAbsHum[0], tAlt);
            tWbt[0] = (float)Math.round(tWbt[0] * 10) / 10;
            tRelH[0] = codeTranslation.LCSI_RH(tdbt, tAbsHum[0], tAlt);
            tRelH[0] = (float)Math.round(tRelH[0] * 10) / 10;
            tEnth[0] = codeTranslation.LCSI_ENTHALPY(tdbt, tAbsHum[0]);
            tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
            tDencity[0] = codeTranslation.LCSI_DENSITY(tdbt, tAbsHum[0], tAlt);
            tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
            tAbsHum[0]= tAbsHum[0];
            ErrCode[0] = 0;
        }

        if (tRelH[0] < 0f || tRelH[0] > 99.9)
        {
            tRelH[0] = 99.9f;
            FormulaDBTRHTLinric(Units, tdbt, tRelH[0], tAlt, tWbt, tEnth, tAbsHum, tDencity, ErrCode);
            tAbsHum[0] = (float)Math.round(tAbsHum[0] * 10) / 10;
            tRelH[0] = (float)Math.round(tRelH[0] * 10) / 10;
            tEnth[0] = (float)Math.round(tEnth[0] * 10) / 10;
            tDencity[0] = (float)Math.round(tDencity[0] * 1000) / 1000;
            tWbt[0] = (float)Math.round(tWbt[0] * 10) / 10;
        }

        return ErrCode[0];
    }
*/


}
