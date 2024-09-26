package com.tetra.brycal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PsyLib {
    /// <summary>Constant - one atmosphere in psia</summary>
    private static double OneAtmPSI = 101.325 / 6.894757;  // = 14.695949400392
    /// <summary>Constant - one atmosphere in inches Hg</summary>
    private static double OneAtminHg = 101.325 / 3.38638; // = 29.921331923765
    /// <summary>Constant - one atmosphere in inches of water</summary>
    private static double OneAtminH2O = 101.325 / 0.249082; // = 406.793746637653
    /// <summary>Constant - one atmosphere in mm Hg</summary>
    private static double OneAtmmmHg = 101.325 / 0.1333224; // = 759.999819985239
    /// <summary>Constant - one atmosphere in kPa</summary>
    private static double OneAtmkPa = 101.325;
    /// <summary>Max dry bulb °F for functions in this assembly</summary>
    private static double MinDB = -80;
    /// <summary>Max dry bulb °F for functions in this assembly</summary>
    private static double MaxDB = 1800;
    /// <summary>Min dew point °F for functions in this assembly</summary>
    private static double MinDP = -140;
    /// <summary>Max dew point °F for functions in this assembly</summary>
    private static double MaxDP = 200;
    /// <summary>Minimum atmospheric pressure in psia</summary>
    private static double MinAtmPressIP = 0.7;
    /// <summary>Maximum atmospheric pressure in psia</summary>
    private static double MaxAtmPressIP = 400.0;
    /// <summary>Conversion meters = Feet * 0.3048</summary>
    private static double FeetToMeters = 0.3048;
    /// <summary>Conversion meters = Feet / 0.3048</summary>
    private static double MetersToFeet = 1 / 0.3048;
    /// <summary>Conversion meters = Feet * 0.3048</summary>
    private static double FeetToMetersCubic = 0.3048 * 0.3048 * 0.3048;
    /// <summary>Conversion meters = Feet * 0.3048</summary>
    private static double MetersToFeetCubic = 1 / (0.3048 * 0.3048 * 0.3048);
    /// <summary>Conversion kPa = PSI * 6.8948 </summary>
    private static double PSITOkPa = 6.894757;


    /// <summary>Universal Gas constant 1545.349 ft-lbf / (lbm-°R)</summary>
    //private const double _Ru = 1545.349;    //ASHRAE 2017
    /// <summary>MW of water</summary>
    //private const double _MWw = 18.015268;  //ASHRAE 2017
    /// <summary>MW of air</summary>
    //private const double _MWa = 28.966;     //ASHRAE 2017
    /// <summary>MW of gas</summary>
    //private double _MWg;
    /// <summary>MW ratio MWw / MWg</summary>
    //private double _MWratio;

    /// <summary>Gas Constant for specific mix of gases</summary>
    //private double _Rg;


    /// <summary>Assembly Version</summary>
    /// <returns>the version of the assembly</returns>
    public static String getVersion(Context context) {
        String myVersion = "";
        try {
            // Get the package manager to retrieve information about the package
            PackageManager pm = context.getPackageManager();

            // Get the package info for the current context
            PackageInfo pInfo = pm.getPackageInfo(context.getPackageName(), 0);
            // Get the version components: major, minor, build
            String versionName = pInfo.versionName;  // e.g., "1.0.3"
            myVersion = versionName.trim();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return myVersion;
    }
    /// <summary>
    /// Private Function - Calculates vapor pressure given a temperature
    /// </summary>
    /// <param name="temp">temperature in °F</param>
    /// <returns>vapor pressure (pws) in psia</returns>
    // Private method to calculate vapor pressure given a temperature
    private static double XPWS(double temp) {
        // Constants and variables
        double answer = 0;
        double pws = 0;
        double Tr = 0;
        double C1 = 0, C2 = 0, C3 = 0, C4 = 0, C5 = 0, C6 = 0, C7 = 0;

        // Define the maximum temperature (MaxDB) if applicable or replace it with a constant
        double MaxDB = 250.0;  // Example maximum temperature

        // Check temperature bounds
        if (temp < -148 || temp > MaxDB) {
            answer = Double.NaN;  // Invalid temperature, return NaN
        } else {
            // Convert Fahrenheit to Rankine
            Tr = temp + 459.67;

            // Determine coefficients based on the temperature range
            if (temp < 32.018) {
                // Use the constants for temperatures below freezing (32°F)
                C1 = -10214.165;
                C2 = -4.8932428;
                C3 = -0.0053765794;
                C4 = 0.00000019202377;
                C5 = 0.00000000035575832;
                C6 = -0.000000000000090344688;
                C7 = 4.1635019;
            } else {
                // Use the constants for temperatures above freezing
                C1 = -10440.397;
                C2 = -11.29465;
                C3 = -0.027022355;
                C4 = 0.00001289036;
                C5 = -0.0000000024780681;
                C6 = 0;
                C7 = 6.5459673;
            }

            // Calculate vapor pressure (pws)
            pws = C1 / Tr + C2 + C3 * Tr + C4 * Tr * Tr + C5 * Math.pow(Tr, 3);
            pws += C6 * Math.pow(Tr, 4) + C7 * Math.log(Tr);

            // Final result of vapor pressure in psia
            answer = Math.exp(pws);
        }

        return answer;
    }

    /// <summary>
    /// Private Function - Enhancement factor Fs used to correct humidity ratio calculations
    /// </summary>
    /// <param name="atm">atmospheric pressure in psia</param>
    /// <param name="T">temperature °F</param>
    /// <returns>Fs</returns>
    private static double XFS(double atm, double T) {
        // Variables initialization
        double x = 0;
        double y = 0;
        double tk = 0;
        double p = 0;
        double ps = 0;
        double[] J = new double[11];
        double[] F = new double[11];
        double Bprime = 0;
        double Caa = 0;
        double Baa = 0;
        double R = 0;
        double Cprime = 0;
        double Caww = 0;
        double Baw = 0;
        double Bww = 0;
        double Cwww = 0;
        double Caaw = 0;
        double kappa = 0;
        double dg = 0;
        double bg = 0;
        double ag = 0;
        double cg = 0;
        double eg = 0;
        double b = 0;
        double A = 0;
        double c = 0;
        double lnKO = 0;
        double log10KO = 0;
        double KO = 0;
        double lnKN = 0;
        double log10KN = 0;
        double KN = 0;
        double K = 0;
        double KA = 0;
        double vc = 0;
        double Rcon = 0;
        double xas = 0;
        double Fsnew = 0;
        double Fs = 0;
        double lnFs = 0;
        short i = 0;

        // Saturation pressure calculation (y)
        y = 0.000000019578 * Math.pow(T, 4) - 0.0000061977 * Math.pow(T, 3) + 0.0010557 * Math.pow(T, 2) - 0.069597 * T + 1.5286;

        // Check for boiling and set XFS to 1.0 if near boiling
        if (atm < 0.98 * y) {
            return 1;
        }

        p = atm * 6894.8; // Convert atm to pascal
        tk = (T - 32) * 5 / 9 + 273.15; // Convert Fahrenheit to Kelvin

        // Ensure minimum value of tk
        if (tk < 173) {
            tk = 173;
        }

        // Calculate saturation vapor pressure (ps) in Pascal using XPWS method
        ps = XPWS(T) * 6894.8;

        // Calculate constants as a function of temperature (tk)
        Baa = 34.9568 - 6687.72 / tk - 2101410 / (tk * tk) + 92474600 / (tk * tk * tk);
        Caa = 1259.75 - 190905 / tk + 63246700 / (tk * tk);
        R = 8314410; // Ideal gas constant in Pa cm3/mol/K
        Bprime = 0.000000007 - 0.00000000147184 * Math.exp(1734.29 / tk);
        Cprime = 0.00000000000000104 - 3.35297E-18 * Math.exp(3645.09 / tk);
        Bww = R * tk * Bprime;
        Cwww = R * R * tk * tk * (Cprime + (Bprime * Bprime));
        Baw = 32.366097 - 14113.8 / tk - 1244535 / (tk * tk) - 2348789000.0 / (Math.pow(tk, 4));
        Caaw = 483.737 + 105678 / tk - 65639400 / (tk * tk) + 29444200000.0 / (Math.pow(tk, 3)) - 3193170000000.0 / (Math.pow(tk, 4));
        Caww = -1000000 * Math.exp(-10.728876 + 3478.02 / tk - 383383 / (tk * tk) + 33406000 / (Math.pow(tk, 3)));

        // Calculate kappa based on temperature
        kappa = (8.875 + 0.0165 * tk) * Math.pow(10, -11);

        // Temperature-specific calculations
        if ((273.15 < tk) && (tk < 373.15)) {
            J[0] = 50.88496;
            J[1] = 0.6163813;
            J[2] = 0.001459187;
            J[3] = 0.00002008438;
            J[4] = -0.5847727 * Math.pow(10, -7);
            J[5] = 0.410411 * Math.pow(10, -9);
            J[6] = 0.01967348;
        } else if (tk > 373.16) {
            J[0] = 50.884917;
            J[1] = 0.62590623;
            J[2] = 0.0013848668;
            J[3] = 0.21603427 * Math.pow(10, -4);
            J[4] = -0.72087667 * Math.pow(10, -7);
            J[5] = 0.46545054 * Math.pow(10, -9);
            J[6] = 0.019859983;
        }

        // Further adjustments to kappa based on the J array
        if (J[0] > 0) {
            x = J[0];
            for (i = 1; i <= 5; i++) {
                x = x + J[i] * Math.pow(tk, i);
            }
            kappa = (x / (1 + J[6] * tk)) * Math.pow(10, -11);
        }

        // Additional calculations for KO, KN, and KA
        if (tk > 273.15) {
            // KO calculation
            ag = -0.0005943;
            bg = -0.147;
            cg = -0.0512;
            dg = -0.1076;
            eg = 0.8447;
            A = ag;
            b = cg * 1000 / tk + dg;
            c = bg * Math.pow(1000 / tk, 2) + eg * 1000 / tk - 1;
            log10KO = (-b + Math.sqrt(Math.pow(b, 2) - 4 * A * c)) / (2 * A);
            lnKO = log10KO * Math.log(10);
            KO = Math.exp(lnKO);

            // KN calculation
            ag = -0.1021;
            bg = -0.1482;
            cg = -0.019;
            dg = -0.03741;
            eg = 0.851;
            A = ag;
            b = cg * 1000 / tk + dg;
            c = bg * Math.pow(1000 / tk, 2) + eg * 1000 / tk - 1;
            log10KN = (-b + Math.sqrt(Math.pow(b, 2) - 4 * A * c)) / (2 * A);
            lnKN = log10KN * Math.log(10);
            KN = Math.exp(lnKN);

            // KA calculation
            KA = 1 / (0.22 / KO + 0.78 / KN);
            K = 0.0001 / KA / 101325;
        }

        // vc calculation based on temperature
        if (tk < 273.15) {
            vc = 0.001070003 - 0.249936e-7 * tk + 0.371611e-9 * tk * tk;
        } else {
            F[0] = -2403.60201;
            F[1] = -1.40758895;
            F[2] = 0.1068287657;
            F[3] = -0.0002914492351;
            F[4] = 0.373497936 * Math.pow(10, -6);
            F[5] = -0.21203787 * Math.pow(10, -9);
            F[6] = -3.424442728;
            F[7] = 0.01619785;
            vc = F[0];
            for (i = 1; i <= 5; i++) {
                vc = vc + F[i] * Math.pow(tk, i);
            }
            vc = (F[6] + F[7] * tk) / vc;
        }
        vc = vc * 18015.28;

        Rcon = R * R * tk * tk;

        // Iteration to solve for Fs
        Fsnew = 1;
        do {
            Fs = Fsnew;
            xas = (p - Fsnew * ps) / p;

            lnFs = ((1 + kappa * ps) * (p - ps) - 0.5 * kappa * (Math.pow(p, 2) - Math.pow(ps, 2))) * vc * R * tk;
            lnFs += Math.log(1 - K * xas * p) * Rcon;
            lnFs += Math.pow(xas, 2) * Bww;
            lnFs += Math.pow(xas, 3) * Cwww;
            lnFs += (1 - xas) * xas * Baw;
            lnFs += Math.pow(1 - xas, 2) * xas * Caww;
            lnFs += Math.pow(1 - xas, 3) * Caaw;
            lnFs += Math.pow(1 - xas, 2) * Baa;
            lnFs += Math.pow(1 - xas, 3) * Caa;

            Fsnew = Math.exp(lnFs);
        } while (Math.abs(Fsnew - Fs) > 0.001);

        return Fsnew;
    }
    /**
     * Private Function - Intermediate calculation
     *
     * @param atm atmospheric pressure in psia
     * @param Tf temperature in °F
     * @return the intermediate calculation result
     */
    private static double XPWSAPP(double atm, double Tf) {
        double Fs = 0;
        // Call the XFS function
        Fs = XFS(atm, Tf);
        // Return the product of Fs and XPWS(Tf)
        return Fs * XPWS(Tf);
    }

    /**
     * Private Function - Intermediate calculation
     *
     * @param pws vapor pressure in psia
     * @return calculated temperature in °F
     */
    private static double XTDP(double pws) {
        // returns Tdp as function of Pws (inverse of Pws function)
        // enters pws in psi, exits tdp in deg F

        double answer = 0;
        double Rconst = 0;
        double LowTdpF = 0;
        double HiTdpF = 0;
        double LowXpws = 0;
        double HiXpws = 0;
        double Pwsold = 0;
        double DPold = 0;
        double alpha = 0;
        double Tdpf = 0;
        double T = 0;
        double del = 0;
        double Tdpr = 0;
        double DPnew = 0;
        double Pwsnew = 0;
        double x = 0;

        if (pws < 0) {
            answer = Double.NaN;
        } else {
            Rconst = 459.67;
            HiTdpF = 392; // This is the range of validity for Wexler's work
            LowTdpF = -148;

            LowXpws = XPWS(LowTdpF);

            if (pws < LowXpws) {
                // Linearized function for very low temp area to maintain continuity
                answer = -Rconst + (LowTdpF + Rconst) / (LowXpws - pws) * pws;
                return answer;
            }

            HiXpws = XPWS(HiTdpF);
            if (pws > HiXpws) {
                answer = HiTdpF;
                return answer;
            }

            // First guess from Kusuda
            alpha = Math.log(pws * 2.036); // inches Hg here
            if (pws >= 0.08865) {
                Tdpf = 79.047 + 30.579 * alpha + 1.8893 * Math.pow(alpha, 2);
            } else {
                Tdpf = 71.98 + 24.873 * alpha + 0.87 * Math.pow(alpha, 2);
            }
            Tdpr = Tdpf + Rconst;
            answer = Tdpf;

            // Newton search for dew point
            DPold = Tdpr;
            T = Tdpr;
            Pwsold = XPWS(T - Rconst);
            del = -1;

            do {
                DPnew = DPold + del;
                T = DPnew;
                Pwsnew = XPWS(T - Rconst);
                x = Pwsnew - Pwsold;

                if (x == 0) {
                    Tdpr = DPnew;
                    break;
                }

                del = (DPnew - DPold) / (Pwsnew - Pwsold) * (pws - Pwsnew);

                if (Double.isNaN(del)) {
                    return Double.NaN;
                }

                if (Math.abs(del) < 0.00001) {
                    Tdpr = DPnew;
                    break;
                }

                DPold = DPnew;
                Pwsold = Pwsnew;
            } while (true);

            answer = Tdpr - Rconst;
        }
        return answer;
    }


    /**
     * Private Function - Intermediate calculation
     *
     * @param atm absolute pressure in psi
     * @param pwsapp vapor pressure in psia
     * @return calculated dew point in °F
     */
    private static double XTDPAPP(double atm, double pwsapp) {
        // To find dew point from the apparent vapor pressure
        // which increases with atmospheric pressure and decreases with temperature
        // returns Tdpapp as function of atmospheric pressure and Pwsapp
        // units are psia and deg F

        double answer = 0;
        double pws = 0;
        double Tdp = 0;
        double Pwsnew = 0;
        double Fs = 0;
        double e = 0;
        double errfac = 0;

        if (pwsapp < 0) {
            answer = Double.NaN;
        } else {
            // We are looking for the real vapor pressure here from which we find the real dew point
            pws = pwsapp; // First guess and should be very close
            Tdp = XTDP(pws);
            Fs = XFS(atm, Tdp);
            pws = pwsapp / Fs; // Real vapor pressure is less than apparent

            // Successive approximation to find dew point which has apparent vapor pressure of pwsapp
            e = 1; // Initialize e to allow loop to continue
            while (!(e < 0.00001)) {
                Tdp = XTDP(pws);
                Fs = XFS(atm, Tdp);
                Pwsnew = pwsapp / Fs;
                errfac = 1;
                if (pws > errfac) {
                    errfac = pws;
                }
                e = Math.abs((pws - Pwsnew) / errfac);

                if (Double.isNaN(e)) {
                    return Double.NaN;
                }

                pws = Pwsnew;
            }

            answer = Tdp;
        }

        return answer;
    }

    /**
     * Private Function - Calculates Compressibility Factor (Z) for air
     *
     * @param Tdb dry bulb temperature in °F
     * @param p absolute pressure in psi
     * @return Z factor
     */
    private static double ZFACTOR(double Tdb, double p) {
        // Compressibility factor for air
        return (-2.2128 * Math.pow(Tdb, 4) * Math.pow(10, -10) +
                3.2829 * Math.pow(Tdb, 3) * Math.pow(10, -7) -
                1.9732 * Math.pow(Tdb, 2) * Math.pow(10, -4) +
                6.3605 * Tdb * 0.01 - 8.6533) * Math.pow(10, -5) * p + 0.999375;
    }

    /**
     * Private Function - Calculates Actual Air Volume using compressed air factor (assumes ideal gas)
     *
     * @param Tdb dry bulb temperature in °F
     * @param p absolute pressure in psi
     * @param Z Compressibility Factor
     * @param r Gas Constant
     * @param m mass of air
     * @return Actual air volume
     */
    private static double MODIDEALV(double Tdb, double p, double Z, double r, double m) {
        return Z * m * r * (Tdb + 459.67) / (p * 144);
    }
    /**
     * Private Function - Altitude to Atmospheric Pressure curve fit from http://exploration.grc.nasa.gov/education/rocket/atmos.html
     *
     * @param m Altitude in meters
     * @return Atmospheric pressure in kPa
     */

    private static double AtmosphericPressureSI(double m) {
        // Formula: 5.9395383E-21 * m^5 + 3.7299681E-16 * m^4 - 1.60134E-14 * m^3 + 5.7790912E-7 * m^2 - 0.011981257 * m + 101.325
        return 5.9395383E-21 * Math.pow(m, 5) +
                3.7299681E-16 * Math.pow(m, 4) +
                -1.60134E-14 * Math.pow(m, 3) +
                5.7790912E-7 * Math.pow(m, 2) +
                -0.011981257 * m +
                101.325;
    }

    //-----------Start Public Functions -------------------------

    /**
     * Standard absolute atmospheric pressure at sea level in psi
     * @return Atmospheric pressure in psi
     */
    public static double AtmPSI() {
        return OneAtmPSI;
    }

    /**
     * Standard absolute atmospheric pressure at sea level in inches of Hg
     * @return Atmospheric pressure in inches of Hg
     */
    public static double AtmInHg() {
        return OneAtminHg;
    }

    /**
     * Standard absolute atmospheric pressure at sea level in mm of Hg
     * @return Atmospheric pressure in mm of Hg
     */
    public static double AtmMmHg() {
        return OneAtmmmHg;
    }

    /**
     * Standard absolute atmospheric pressure at sea level in inches of Water
     * @return Atmospheric pressure in inches of Water
     */
    public static double AtmInH2O() {
        return OneAtminH2O;
    }
    /**
     * Calculates Atmospheric Pressure given elevation above sea level
     *
     * @param feet elevation above sea level in feet
     * @return atmospheric pressure in inches Hg
     */
    public static double LCALTTOHG(double feet) {
        double r = OneAtminHg * Math.pow((1 - 0.00000687554 * feet), 5.2559);
        return r;
    }
    /**
     * Calculates Atmospheric Pressure given elevation above sea level
     *
     * @param feet elevation above sea level in feet
     * @return atmospheric pressure in psi
     */
    public static double LCALTTOPSI(double feet) {
        double r = OneAtmPSI * Math.pow((1 - 0.00000687554 * feet), 5.2559);
        return r;
    }

    /**
     * Calculates Atmospheric Pressure given elevation above sea level
     *
     * @param meters elevation in meters above sea level
     * @return atmospheric pressure in kPa
     */
    public static double LCSI_ALTTOKPA(double meters) {
        double r = OneAtmkPa * Math.pow((1 - 0.00000687554 * meters), 5.2559);
        return r;
    }

    /**
     * Calculates Atmospheric Pressure given elevation
     *
     * @param meters elevation above sea level in meters
     * @return atmospheric pressure in mm Hg
     */
    public static double LCSI_ALTTOHG(double meters) {
        double r = OneAtmmmHg * Math.pow((1 - 0.00000687554 * meters), 5.2559);
        return r;
    }

    //---------------------------------------------------------------------------------------

    /**
     * Calculates elevation above sea level given Atmospheric Pressure
     *
     * @param inHg atmospheric pressure in inches Hg
     * @return elevation above sea level in feet
     */
    public static double LCHGTOALT(double inHg) {
        double r = (1 - Math.pow((inHg / OneAtminHg), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    /**
     * Calculates elevation above sea level given Atmospheric Pressure
     *
     * @param psia atmospheric pressure in psi
     * @return elevation above sea level in feet
     */
    public static double LCPSITOALT(double psia) {
        double r = (1 - Math.pow((psia / OneAtmPSI), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    /**
     * Calculates elevation above sea level given Atmospheric Pressure
     *
     * @param kPa atmospheric pressure in kPa
     * @return elevation in meters above sea level
     */
    public static double LCSI_KPATOALT(double kPa) {
        double r = 0.3048 * (1 - Math.pow((kPa / OneAtmkPa), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    /**
     * Calculates elevation above sea level given Atmospheric Pressure
     *
     * @param mmHg atmospheric pressure in mm Hg
     * @return elevation above sea level in meters
     */
    public static double LCSI_HGTOHG(double mmHg) {
        double r = 0.3048 * (1 - Math.pow((mmHg / OneAtmmmHg), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    //---------------------------------------------------------------------------------------

    /**
     * Calculates Saturation Humidity Ratio given saturation temperature and absolute pressure.
     *
     * @param tempFdb dry bulb temperature or dew point temperature in °F
     * @param psia absolute pressure in psi
     * @return saturation humidity ratio in grains/lb dry air
     */
    public static double LCPSATGRAINS(double tempFdb, double psia) {
        if (Double.isNaN(tempFdb * psia)) {
            return Double.NaN;
        }

        // Replace MinAtmPressIP with its Java equivalent constant
        if (tempFdb < -120 || tempFdb > 200 || psia < MinAtmPressIP || psia > 400) {
            return Double.NaN;
        } else {
            double pwsapp = XPWSAPP(psia, tempFdb);
            if (pwsapp < psia && tempFdb <= 200) {
                return 0.621945 * pwsapp * 7000 / (psia - pwsapp);
            } else { // Temp > 200 assign arbitrary saturation point
                return Double.NaN; // otherwise wsat will go negative
            }
        }
    }

    /**
     * Calculates Saturation Humidity Ratio given saturation temperature and elevation.
     *
     * @param tempCdb dry bulb temperature or dew point temperature in °C
     * @param meters elevation above sea level in meters
     * @return saturation humidity ratio in grams/kilogram dry air
     */
    public static double LCSI_SATGRAMS(double tempCdb, double meters) {
        if (Double.isNaN(tempCdb * meters)) {
            return Double.NaN;
        }

        double t = tempCdb * 9 / 5 + 32; // Convert °C to °F
        double f = meters * MetersToFeet; // Assuming MetersToFeet is defined

        return LCSATGRAINS(t, f) / 7; // Assuming lcSatGrains is the translated method
    }

    /**
     * Calculates Saturation Humidity Ratio given saturation temperature and absolute pressure.
     *
     * @param tempCdb dry bulb temperature or dew point temperature in °C
     * @param kPa absolute pressure in kPa
     * @return saturation humidity ratio in grams/kilogram dry air
     */
    public static double LCSI_PSATGRAMS(double tempCdb, double kPa) {
        if (Double.isNaN(tempCdb * kPa)) {
            return Double.NaN;
        }

        double t = tempCdb * 9 / 5 + 32; // Convert °C to °F
        double p = kPa / 6.8948; // Convert kPa to psi

        return LCPSATGRAINS(t, p) / 7; // Assuming lcPsatGrains is the translated method
    }


    /**
     * Calculates air density given temperature humidity ratio and elevation.
     *
     * @param tempFdb dry bulb temperature in °F
     * @param grains humidity ratio in grains/lb dry air
     * @param feet elevation above sea level in feet
     * @return air density in lb/ft³
     */
    public static double LCDENSITY(double tempFdb, double grains, double feet) {
        double psia = LCALTTOPSI(feet); // Assuming lcAltToPsi is defined
        return (144 * psia) / (53.35 * (tempFdb + 459.67) * (1 + (1.607858 * grains / 7000))) * (1 + grains / 7000);
    }

    /**
     * Calculates air density given temperature humidity ratio and absolute pressure.
     *
     * @param tempFdb dry bulb temperature in °F
     * @param grains humidity ratio in grains/lb dry air
     * @param psia absolute pressure in psi
     * @return air density in lb/ft³
     */
    public static double LCPDENSITY(double tempFdb, double grains, double psia) {
        return (144 * psia) / (53.35 * (tempFdb + 459.67) * (1 + (1.607858 * grains / 7000))) * (1 + grains / 7000);
    }

    /**
     * Calculates air density given temperature humidity ratio and elevation.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param grams humidity ratio in grams/kg dry air
     * @param meters elevation above sea level in meters
     * @return air density in kg/m³
     */
    public static double LCSI_DENSITY(double tempCdb, double grams, double meters) {
        double temp = tempCdb * 9 / 5 + 32; // Convert °C to °F
        double g = grams * 7; // Convert grams/kg to grains/lb
        double feet = meters * MetersToFeet; // Convert meters to feet
        double psia = LCALTTOPSI(feet); // Assuming lcAltToPsi is defined

        return (144 * psia) / (53.35 * (temp + 460) * (1 + (1.607858 * g / 7000))) / (1 + g / 7000) * MetersToFeetCubic * 0.45359237;
    }


    /**
     * Calculates air density given temperature humidity ratio and absolute pressure.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param grams humidity ratio in grams/kg dry air
     * @param kPa absolute pressure in kPa
     * @return air density in kg/m³
     */
    public static double LCSI_PDENSITY(double tempCdb, double grams, double kPa) {
        double temp = tempCdb * 9 / 5 + 32; // Convert °C to °F
        double g = grams * 7; // Convert grams/kg to grains/lb
        double psia = kPa / 6.8948; // Convert kPa to psi

        return (144 * psia) / (53.35 * (temp + 460) * (1 + (1.607858 * g / 7000))) / (1 - g / 7000) * MetersToFeetCubic * 0.45359237;
    }

    /**
     * Calculates the specific heat of dry air for a given temperature in °F.
     *
     * @param tempF dry bulb temperature in °F
     * @return specific heat of dry air in Btu/(lb·°F)
     */
    public static double Cp_air_IP(double tempF) {
        // WPI values converted to IP then curve fit by JJ
        // = 4.3973675E-15*T^4 - 0.000000000032763128*T^3 + 0.000000084090076*T^2 - 0.000059565592*T + 0.25257981
        double tempRdb = tempF + 459.67; // Convert °F to °R
        double c0 = 0.25257981;
        double c1 = -0.000059565592;
        double c2 = 0.000000084090076;
        double c3 = -0.000000000032763128;
        double c4 = 4.3973675E-15;

        return c4 * Math.pow(tempRdb, 4) +
                c3 * Math.pow(tempRdb, 3) +
                c2 * Math.pow(tempRdb, 2) +
                c1 * tempRdb +
                c0;
    }
    /**
     * Calculates the specific heat of dry air for a given temperature in °C.
     *
     * @param tempC dry bulb temperature in °C
     * @return specific heat of dry air in kJ/(kg·°C)
     */
    public static double Cp_air_SI(double tempC) {
        // WPI
        double tempKdb = tempC + 273.15; // Convert °C to K
        double c0 = 1057.5;
        double c1 = -0.4489;
        double c2 = 0.0011407;
        double c3 = -0.00000079999;
        double c4 = 0.00000000019327;

        double cpSI = c4 * Math.pow(tempKdb, 4) +
                c3 * Math.pow(tempKdb, 3) +
                c2 * Math.pow(tempKdb, 2) +
                c1 * tempKdb +
                c0;
        return cpSI / 1000; // Convert from J/(kg·K) to kJ/(kg·°C)
    }

    /**
     * Calculates Saturation Humidity Ratio given saturation temperature and elevation in IP units.
     *
     * @param tempFdb saturation temperature in °F
     * @param feet elevation above sea level in feet
     * @return saturation humidity ratio in grains/lb dry air
     */
    public static double LCSATGRAINS(double tempFdb, double feet) {
        if (Double.isNaN(tempFdb * feet)) {
            return Double.NaN;
        }

        if (tempFdb < -120 || tempFdb > 200) {
            return Double.NaN;
        } else {
            double atm = LCALTTOPSI(feet); // Conversion of elevation to psi
            double pwsapp = XPWSAPP(atm, tempFdb); // Calculation of pressure using your method
            if (pwsapp < atm && tempFdb <= 200) {
                return 0.621945 * pwsapp * 7000 / (atm - pwsapp); // Formula to calculate saturation humidity ratio
            } else {
                return 20000.0; // Arbitrary value when temperature is above 200°F
            }
        }
    }

    /**
     * Calculates Humidity Ratio given dry bulb temperature, wet bulb temperature, and elevation in SI units.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param tempCwb wet bulb temperature in °C
     * @param meters elevation above sea level in meters
     * @return humidity ratio in grams/kg dry air
     */
    public static double LCSI_WBTOGRAMS (double tempCdb, double tempCwb, double meters) {
        // Validate arguments
        if (Double.isNaN(tempCdb * tempCwb * meters)) {
            return Double.NaN;
        }

        double tempWbF = tempCwb * 9 / 5 + 32; // Convert wet bulb temperature to °F
        double tempDbF = tempCdb * 9 / 5 + 32; // Convert dry bulb temperature to °F
        double feet = meters * MetersToFeet;   // Convert meters to feet

        double answer = 0;
        double Ws = 0;
        double W = 0;

        if (tempCdb < tempCwb) {
            answer = Double.NaN;
        } else {
            Ws = LCSATGRAINS(tempWbF, feet) / 7000;  // Calculate saturation humidity ratio
            if (tempWbF >= 32.018) {
                W = ((1093 - 0.556 * tempWbF) * Ws - 0.24 * (tempDbF - tempWbF)) /
                        (1093 + 0.444 * tempDbF - tempWbF);
            } else {
                W = ((1220 - 0.04 * tempWbF) * Ws - 0.24 * (tempDbF - tempWbF)) /
                        (1220 + 0.444 * tempDbF - 0.48 * tempWbF);
            }

            if (W <= 0) {
                answer = Double.NaN;
            } else {
                answer = W * 7000 / 7; // Convert from grains to grams/kg
                if (answer > LCSATGRAINS(tempDbF, feet) / 7) {
                    answer = LCSATGRAINS(tempDbF, feet) / 7; // Adjust based on saturation humidity ratio
                }
            }
        }

        return answer;
    }
    /**
     * Calculates Relative Humidity given Temperature, Humidity Ratio, and Elevation in SI units.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param grams humidity ratio in grams/kg dry air
     * @param meters elevation above sea level in meters
     * @return Relative Humidity in %
     */
    public static double LCSI_RH(double tempCdb, double grams, double meters) {
        // Validate arguments
        if (Double.isNaN(tempCdb * grams * meters)) {
            return Double.NaN;
        }

        double tempFdb = tempCdb * 9 / 5 + 32;  // Convert dry bulb temperature to °F
        double humidityRatioGrains = grams * 7; // Convert grams/kg to grains/lb
        double elevationFeet = meters * MetersToFeet; // Convert meters to feet

        return LCRH(tempFdb, humidityRatioGrains, elevationFeet);
    }
    /**
     * Calculates Relative Humidity given Temperature, Humidity Ratio, and Elevation in IP units.
     *
     * @param tempFdb dry bulb temperature in °F
     * @param grains humidity ratio in grains/lb dry air
     * @param feet elevation above sea level in feet
     * @return Relative Humidity in %
     */
    public static double LCRH(double tempFdb, double grains, double feet) {
        // Declare variables
        double wsat;
        double atm;
        double mu;
        double pwsatm;
        double rh;
        double pwsapp;

        // Validate input range for temperature and humidity ratio
        if (tempFdb < MinDP || tempFdb > MaxDB || grains < 0) {
            return Double.NaN;
        } else {
            // Calculate atmospheric pressure at elevation
            atm = LCALTTOPSI(feet);

            // Calculate saturation pressure at given temperature
            pwsapp = XPWSAPP(atm, tempFdb);

            // Calculate saturation humidity ratio (wsat)
            wsat = 0.621945 * pwsapp * 7000 / (atm - pwsapp);

            // Calculate saturation vapor pressure ratio
            pwsatm = pwsapp / atm;

            // Calculate humidity ratio as a fraction of saturation
            mu = grains / wsat;

            // Calculate relative humidity
            rh = (mu / (1 - (1 - mu) * pwsatm)) * 100;

            // Ensure relative humidity doesn't exceed 100%
            if (rh > 100) {
                return Double.NaN;
            } else {
                return rh;
            }
        }
    }
    /**
     * Calculates Dew Point Temperature given humidity ratio and elevation in SI units.
     *
     * @param grams humidity ratio in grams/kg dry air
     * @param meters elevation above sea level in meters
     * @return Dew Point Temperature in °C
     */
    public static double LCSI_DEWPOINT(double grams, double meters) {
        // Validate the input
        if (Double.isNaN(grams * meters)) {
            return Double.NaN;
        }

        // Initialize variables
        double atm;
        double x = grams * 7; // Convert humidity ratio
        double W = grams / 1000;
        double feet = meters * MetersToFeet;
        double pwsapp;

        // Check if the humidity ratio is within bounds
        if (x < 0 || x > 16300) {
            return Double.NaN;
        } else {
            // Calculate atmospheric pressure at elevation
            atm = LCALTTOPSI(feet);

            // Calculate saturation pressure
            pwsapp = atm * W / (0.621945 + W);

            // Calculate and return the dew point temperature in °C
            return (XTDPAPP(atm, pwsapp) - 32) * 5 / 9;
        }
    }
    /**
     * Calculates Enthalpy given temperature and humidity ratio in SI units.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param grams humidity ratio in grams/kg dry air
     * @return enthalpy in kJ/kg dry air
     */
    public static double LCSI_ENTHALPY(double tempCdb, double grams) {
        // Calculate specific heat of air at the given temperature
        double Cp = Cp_air_SI(tempCdb);

        // Return the enthalpy calculation using ASHRAE constants
        return Cp * tempCdb + (grams / 1000) * (2501 + 1.805 * tempCdb);
    }

    /**
     * Calculates Humidity Ratio given dry bulb temperature, relative humidity, and elevation in IP units.
     *
     * @param tempFdb dry bulb temperature in °F
     * @param RH Relative Humidity as a decimal (e.g., 0.50 for 50% RH)
     * @param feet elevation above sea level in feet
     * @return Humidity Ratio in grains/lb dry air
     */
    public static double LCRHTOGRAINS(double tempFdb, double RH, double feet) {
        // Validate inputs
        if (Double.isNaN(tempFdb * RH * feet)) {
            return Double.NaN;
        }

        double psia = 0;
        double pws = 0;
        double pwsApp = 0;
        double answer = 0;

        // Temperature and RH validity check
        if (tempFdb < MinDP || tempFdb > MaxDB || RH < 0 || RH > 1) {
            return Double.NaN;
        } else {
            // Get pressure based on elevation
            psia = LCALTTOPSI(feet);

            // Calculate saturated vapor pressure
            pwsApp = XPWSAPP(psia, tempFdb);
            pws = pwsApp * RH;

            // Validate that pws doesn't exceed the maximum dew point
            if (pws > XPWSAPP(psia, MaxDP)) {
                return Double.NaN;
            } else {
                // Calculate humidity ratio in grains/lb dry air
                answer = 0.621945 * pws * 7000 / (psia - pws);
                return answer;
            }
        }
    }

    /**
     * Calculates Dew Point Temperature given humidity ratio and elevation.
     *
     * @param grains humidity ratio in grains/lb dry air
     * @param feet elevation above sea level in feet
     * @return Dew Point Temperature in °F
     */
    public static double LCDEWPOINT(double grains, double feet) {
        // Validate inputs
        if (Double.isNaN(grains * feet)) {
            return Double.NaN;
        }
        double atm = 0;
        double W = 0;
        double pwsApp = 0;

        // Check if grains are valid (no negative values)
        if (grains < 0) {
            return Double.NaN;
        } else {
            // Calculate humidity ratio in lb/lb
            W = grains / 7000;

            // Get atmospheric pressure based on elevation
            atm = LCALTTOPSI(feet);

            // Calculate saturated vapor pressure
            pwsApp = atm * W / (0.621945 + W);

            // Return the dew point temperature in °F
            return XTDPAPP(atm, pwsApp);
        }
    }
    /**
     * Calculates Humidity Ratio given dry bulb temperature, wet bulb temperature, and elevation.
     *
     * @param tempFdb dry bulb temperature in °F
     * @param tempFwb wet bulb temperature in °F
     * @param feet elevation above sea level in feet
     * @return humidity ratio in grains/lb dry air
     */
    public static double LCWBTOGRAINS(double tempFdb, double tempFwb, double feet) {
        // Validate inputs
        if (Double.isNaN(tempFdb * tempFwb * feet)) {
            return Double.NaN;
        }

        double answer = 0;
        double Ws = 0;
        double W = 0;

        // Validate boundary conditions for input parameters
        if (feet < -500 || feet > 30000 || tempFdb < MinDP || tempFdb > MaxDB || tempFwb > tempFdb) {
            return Double.NaN;
        } else {
            if (tempFdb < tempFwb) {
                return Double.NaN;
            } else {
                // Calculate the saturation humidity ratio in grains
                Ws = LCSATGRAINS(tempFwb, feet) / 7000;

                if (tempFwb >= 32.018) {
                    // Use specific formula for temperatures above freezing
                    W = ((1093 - 0.556 * tempFwb) * Ws - 0.24 * (tempFdb - tempFwb)) / (1093 + 0.444 * tempFdb - tempFwb);
                } else {
                    // Use specific formula for temperatures below freezing
                    W = ((1220 - 0.04 * tempFwb) * Ws - 0.24 * (tempFdb - tempFwb)) / (1220 + 0.444 * tempFdb - 0.48 * tempFwb);
                }

                // Check if calculated humidity ratio is valid
                if (W <= 0) {
                    return Double.NaN;
                } else {
                    // Convert to grains/lb and ensure it does not exceed saturation value
                    answer = W * 7000;
                    if (answer > LCSATGRAINS(tempFdb, feet)) {
                        answer = LCSATGRAINS(tempFdb, feet);
                    }
                }
            }
        }
        return answer;
    }

    /**
     * Calculates Humidity Ratio given dry bulb temperature, relative humidity, and elevation.
     *
     * @param tempCdb dry bulb temperature in °C
     * @param RH Relative Humidity as a decimal (e.g., use 0.50 for 50% RH)
     * @param meters elevation above sea level in meters
     * @return Humidity Ratio in grams/kg dry air
     */
    public static double LCSI_RHTOGRAMS(double tempCdb, double RH, double meters) {
        if (Double.isNaN(tempCdb * RH * meters)) {
            return Double.NaN;
        }

        // Convert °C to °F
        double tempFdb = tempCdb * 9 / 5 + 32;
        // Convert meters to feet
        double feet = meters * MetersToFeet;

        // Boundary checks
        if (tempFdb < MinDP || tempFdb > MaxDB || RH < 0 || RH > 1) {
            return Double.NaN;
        } else {
            // Call the IP version and convert the result back to grams/kg
            return LCRHTOGRAINS(tempFdb, RH, feet) / 7;
        }
    }


}
