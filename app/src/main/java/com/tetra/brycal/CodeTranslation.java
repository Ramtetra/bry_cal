package com.tetra.brycal;
import java.io.File;
import java.util.Arrays;

public class CodeTranslation {

    private static double OneAtmPSI = 101.325 / 6.894757;  // = 14.695949400392

    private static double OneAtminHg = 101.325 / 3.38638; // = 29.921331923765

    private static double OneAtminH2O = 101.325 / 0.249082; // = 406.793746637653

    private static double OneAtmmmHg = 101.325 / 0.1333224; // = 759.999819985239

    private static double OneAtmkPa = 101.325;

    private static double MinDB = -80;

    private static double MaxDB = 1800;

    private static double MinDP = -140;

    private static double MaxDP = 200;

    private static double MinAtmPressIP = 0.7;

    private static double MaxAtmPressIP = 400.0;

    private static double FeetToMeters = 0.3048;

    private static double MetersToFeet = 1 / 0.3048;

    private static double FeetToMetersCubic = 0.3048 * 0.3048 * 0.3048;

    private static double MetersToFeetCubic = 1 / (0.3048 * 0.3048 * 0.3048);

    private static double PSITOkPa=6.894757;


    public static String Version() {
        String myVersion = "";
        try {
            File file = new File(CodeTranslation.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String[] versionInfo = file.getName().split("\\.");
            myVersion = versionInfo[0].trim() + "." + versionInfo[1].trim() + "." + versionInfo[2].trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myVersion;
    }

    private static double XPWS(double temp) {
        double answer = 0;
        double pws = 0;
        double Tr = 0;
        double C1 = 0;
        double C2 = 0;
        double C3 = 0;
        double C4 = 0;
        double C5 = 0;
        double C6 = 0;
        double C7 = 0;

        if (temp < -148 || temp > MaxDB) {
            answer = Double.NaN;
        } else {
            Tr = temp + 459.67;
            if (temp < 32.018) {
                C1 = -10214.165;
                C2 = -4.8932428;
                C3 = -0.0053765794;
                C4 = 0.00000019202377;
                C5 = 0.00000000035575832;
                C6 = -0.000000000000090344688;
                C7 = 4.1635019;
            } else {
                C1 = -10440.397;
                C2 = -11.29465;
                C3 = -0.027022355;
                C4 = 0.00001289036;
                C5 = -0.0000000024780681;
                C6 = 0;
                C7 = 6.5459673;
            }
            pws = C1 / Tr + C2 + C3 * Tr + C4 * Tr * Tr + C5 * Math.pow(Tr, 3) + C6 * Math.pow(Tr, 4) + C7 * Math.log(Tr);
            answer = Math.exp(pws);
        }
        return answer;
    }

    private static double XFS(double atm, double T) {
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

        y = 0.000000019578 * Math.pow(T, 4) - 0.0000061977 * Math.pow(T, 3) + 0.0010557 * Math.pow(T, 2) - 0.069597 * T + 1.5286;

        if (atm < 0.98 * y) {
            return 1;
        }

        p = atm * 6894.8;
        tk = (T - 32) * 5 / 9 + 273.15;

        if (tk < 173) {
            tk = 173;
        }

        ps = XPWS(T) * 6894.8;

        Baa = 34.9568 - 6687.72 / tk - 2101410 / tk / tk + 92474600 / tk / tk / tk;
        Caa = 1259.75 - 190905 / tk + 63246700 / tk / tk;
        R = 8314410;
        Bprime = 0.000000007 - 0.00000000147184 * Math.exp(1734.29 / tk);
        Cprime = 0.00000000000000104 - 3.35297E-18 * Math.exp(3645.09 / tk);
        Bww = R * tk * Bprime;
        Cwww = R * R * tk * tk * (Cprime + (Bprime * Bprime));
        Baw = 32.366097 - 14113.8 / tk - 1244535 / tk / tk - 2348789000.0 / Math.pow(tk, 4);
        Caaw = 483.737 + 105678 / tk - 65639400 / tk / tk + 29444200000.0 / Math.pow(tk, 3) - 3193170000000.0 / Math.pow(tk, 4);
        Caww = -1000000 * Math.exp(-10.728876 + 3478.02 / tk - 383383 / tk / tk + 33406000 / Math.pow(tk, 3));

        J[0] = 0;
        kappa = (8.875 + 0.0165 * tk) * Math.pow(10, -11);
        if (273.15 < tk && tk < 373.15) {
            J[0] = 50.88496;
            J[1] = 0.6163813;
            J[2] = 0.001459187;
            J[3] = 0.00002008438;
            J[4] = -0.5847727 * Math.pow(10, -7);
            J[5] = 0.410411 * Math.pow(10, -9);
            J[6] = 0.01967348;
        }
        if (373.16 < tk) {
            J[0] = 50.884917;
            J[1] = 0.62590623;
            J[2] = 0.0013848668;
            J[3] = 0.21603427 * Math.pow(10, -4);
            J[4] = -0.72087667 * Math.pow(10, -7);
            J[5] = 0.46545054 * Math.pow(10, -9);
            J[6] = 0.019859983;
        }
        if (J[0] > 0) {
            x = J[0];
            for (i = 1; i <= 5; i++) {
                x = x + J[i] * Math.pow(tk, i);
            }
            kappa = (x / (1 + J[6] * tk)) * Math.pow(10, -11);
        }

        K = 0;
        if (tk > 273.15) {
            ag = -0.0005943;
            bg = -0.147;
            cg = -0.0512;
            dg = -0.1076;
            eg = 0.8447;
            A = ag;
            b = cg * 1000 / tk + dg;
            c = bg * Math.pow((1000 / tk), 2) + eg * 1000 / tk - 1;

            log10KO = (-b + Math.sqrt(Math.pow(b, 2) - 4 * A * c)) / 2 * A;
            lnKO = log10KO * Math.log(10);
            KO = Math.exp(lnKO);

            ag = -0.1021;
            bg = -0.1482;
            cg = -0.019;
            dg = -0.03741;
            eg = 0.851;
            A = ag;
            b = cg * 1000 / tk + dg;
            c = bg * Math.pow((1000 / tk), 2) + eg * 1000 / tk - 1;

            log10KN = (-b + Math.sqrt(Math.pow(b, 2) - 4 * A * c)) / 2 * A;
            lnKN = log10KN * Math.log(10);
            KN = Math.exp(lnKN);

            KA = 1 / (0.22 / KO + 0.78 / KN);
            K = 0.0001 / KA / 101325;
        }

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

        Fsnew = 1;
        i = 0;
        do {
            Fs = Fsnew;
            xas = (p - Fsnew * ps) / p;
            lnFs = ((1 + kappa * ps) * (p - ps) - 0.5 * kappa * (Math.pow(p, 2) - Math.pow(ps, 2))) * vc * R * tk;
            lnFs = lnFs + Math.log(1 - K * xas * p) * Rcon;
            lnFs = lnFs + Math.pow(xas, 2) * p * Baa * R * tk;
            lnFs = lnFs - 2 * Math.pow(xas, 2) * p * Baw * R * tk;
            lnFs = lnFs - (p - ps - xas * xas * p) * Bww * R * tk;
            lnFs = lnFs + Math.pow(xas, 3) * p * p * Caa;
            lnFs = lnFs + (3 * xas * xas * (1 - 2 * xas) * p * p) * 0.5 * Caaw;
            lnFs = lnFs - 3 * xas * xas * (1 - xas) * p * p * Caww;
            lnFs = lnFs - ((1 + 2 * xas) * Math.pow((1 - xas), 2) * p * p - ps * ps) * 0.5 * Cwww;
            lnFs = lnFs - xas * xas * (1 - 3 * xas) * (1 - xas) * p * p * Baa * Bww;
            lnFs = lnFs - 2 * Math.pow(xas, 3) * (2 - 3 * xas) * p * p * Baa * Baw;
            lnFs = lnFs + 6 * xas * xas * Math.pow((1 - xas), 2) * p * p * Bww * Baw;
            lnFs = lnFs - 3 * Math.pow(xas, 4) * p * p * 0.5 * Baa * Baa;
            lnFs = lnFs - 2 * xas * xas * (1 - xas) * (1 - 3 * xas) * p * p * Baw * Baw;
            lnFs = lnFs - (ps * ps - (1 + 3 * xas) * Math.pow((1 - xas), 3) * p * p) * 0.5 * Bww * Bww;

            lnFs = lnFs / R / R / tk / tk;

            Fsnew = Math.exp(lnFs);

            if (Double.isNaN(Fsnew)) {
                return Double.NaN;
            }

            if (Math.abs(Fs - Fsnew) < 0.000001) {
                break;
            }

        } while (true);

        if (Fsnew > 1) {
            return Fsnew;
        } else {
            return 1;
        }
    }

    private static double XPWSAPP(double atm, double Tf) {
        double Fs = 0;
        Fs = XFS(atm, Tf);
        return Fs * XPWS(Tf);
    }

    private static double XTDP(double pws) {
        double answer = 0;
        double Rconst = 0;
        double LowTdpF = 0;
        double HiTdpF = 0;
        double LowXpws = 0;
        double HiXpws = 0F;
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
            HiTdpF = 392;
            LowTdpF = -148;

            LowXpws = XPWS(LowTdpF);

            if (pws < LowXpws) {
                answer = -Rconst + (LowTdpF + Rconst) / (LowXpws - pws) * pws;
                return answer;
            }

            HiXpws = XPWS(HiTdpF);
            if (pws > HiXpws) {
                answer = HiTdpF;
                return answer;
            }

            alpha = Math.log(pws * 2.036);
            if (pws >= 0.08865) {
                Tdpf = 79.047 + 30.579 * alpha + 1.8893 * Math.pow(alpha, 2);
            } else {
                Tdpf = 71.98 + 24.873 * alpha + 0.87 * Math.pow(alpha, 2);
            }
            Tdpr = Tdpf + Rconst;
            answer = Tdpf;

            DPold = Tdpr;
            T = Tdpr;
            Pwsold = XPWS(T - Rconst);
            del = -1;
            do {
                DPnew = DPold + del;
                T = DPnew;
                Pwsnew = XPWS(T - Rconst);
                x = Pwsnew - Pwsold;
                if (Pwsnew - Pwsold == 0) {
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

    private static double XTDPAPP(double atm, double pwsapp) {
        double answer = 0F;
        double pws = 0;
        double Tdp = 0F;
        double Pwsnew = 0;
        double Fs = 0;
        double e = 0;
        double errfac = 0;

        if (pwsapp < 0) {
            answer = Double.NaN;
        } else {
            pws = pwsapp;
            Tdp = XTDP(pws);
            Fs = XFS(atm, Tdp);
            pws = pwsapp / Fs;

            e = 1;
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

    private static double ZFACTOR(double Tdb, double p) {
        return (-2.2128 * Math.pow(Tdb, 4) * Math.pow(10, -10) + 3.2829 * Math.pow(Tdb, 3) * Math.pow(10, -7) - 1.9732 * Math.pow(Tdb, 2) * Math.pow(10, -4) + 6.3605 * Tdb * 0.01 - 8.6533) * Math.pow(10, -5) * p + 0.999375;
    }

    private static double MODIDEALV(double Tdb, double p, double Z, double r, double m) {
        return Z * m * r * (Tdb + 459.67) / (p * 144);
    }

    public static double AtmPSI() {
        return OneAtmPSI;
    }

    public static double AtmInHg() {
        return OneAtminHg;
    }

    public static double LCALTTOHG(double feet) {
        double r = OneAtminHg * Math.pow((1 - 0.00000687554 * feet), 5.2559);
        return r;
    }

    public static double LCALTTOPSI(double feet) {
        double r = OneAtmPSI * Math.pow((1 - 0.00000687554 * feet), 5.2559);
        return r;
    }

    public static double LCSI_ALTTOKPA(double meters) {
        double r = OneAtmkPa * Math.pow((1 - 0.00000687554 * meters), 5.2559);
        return r;
    }

    public static double LCHGTOALT(double inHg) {
        double r = (1 - Math.pow((inHg / OneAtminHg), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    public static double LCPSITOALT(double psia) {
        double r = (1 - Math.pow((psia / OneAtmPSI), (1 / 5.2559))) / 0.0000068754;
        return r;
    }

    public static double LCSI_KPATOALT(double kPa) {
        double r = 0.3048 * (1 - Math.pow((kPa / OneAtmkPa), (1 / 5.2559))) / 0.0000068754;
        return r;
    }



    public static double LCSATGRAINS(double Temp_Fdb, double feet) {
        if (Double.isNaN(Temp_Fdb * feet)) {
            return Double.NaN;
        }

        if (Temp_Fdb < -120 || Temp_Fdb > 200) {
            return Double.NaN;
        } else {
            double atm = LCALTTOPSI(feet);
            double pwsapp = XPWSAPP(atm, Temp_Fdb);
            if (pwsapp < atm && Temp_Fdb <= 200) {
                return 0.621945 * pwsapp * 7000 / (atm - pwsapp);
            } else { // Temp_Fdb > 200 assign arbitrary saturation point
                return 20000F; // otherwise wsat will go negative
            }
        }
    }

    public static double LCPSATGRAINS(double Temp_Fdb, double psia) {
        if (Double.isNaN(Temp_Fdb * psia)) {
            return Double.NaN;
        }
        //if (Temp_Fdb < -120 | Temp_Fdb > 200 | psia < 4 | psia > 400)
        if (Temp_Fdb < -120 || Temp_Fdb > 200 || psia < MinAtmPressIP || psia > 400) {
            return Double.NaN;
        } else {
            double pwsapp = XPWSAPP(psia, Temp_Fdb);
            if (pwsapp < psia && Temp_Fdb <= 200) {
                return 0.621945 * pwsapp * 7000 / (psia - pwsapp);
            } else { // Temp > 200 assign arbitrary saturation point
                return Double.NaN; // otherwise wsat will go negative
            }
        }
    }

    public static double LCSI_SATGRAMS(double Temp_Cdb, double meters) {
        if (Double.isNaN(Temp_Cdb * meters)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double F = meters * MetersToFeet;

        return LCSATGRAINS(T, F) / 7;
    }

    public static double LCSI_PSATGRAMS(double Temp_Cdb, double kPa) {
        if (Double.isNaN(Temp_Cdb * kPa)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double p = kPa / 6.8948;

        return LCPSATGRAINS(T, p) / 7;
    }

    public static double LCDENSITY(double Temp_Fdb, double grains, double feet) {
        double psia = LCALTTOPSI(feet);
        return 144 * psia / ((53.35 * (Temp_Fdb + 459.67) * (1 + 1.607858 * grains / 7000))) * (1 + grains / 7000);
    }

    public static double LCPDENSITY(double Temp_Fdb, double grains, double psia) {
        return 144 * psia / (53.35 * (Temp_Fdb + 459.67) * (1 + 1.607858 * grains / 7000)) * (1 + grains / 7000);
    }

    public static double LCSI_DENSITY(double Temp_Cdb, double grams, double meters) {
        double temp = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double feet = meters * MetersToFeet;
        double psia = LCALTTOPSI(feet);

        return 144 * psia / (53.35 * (temp + 460) * (1 + 1.607858 * g / 7000)) / (1 + g / 7000) * MetersToFeetCubic * 0.45359237;
    }

    public static double LCSI_PDENSITY(double Temp_Cdb, double grams, double kPa) {
        double temp = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double psia = kPa / 6.8948;

        return 144 * psia / (53.35 * (temp + 460) * (1 + 1.607858 * g / 7000)) / (1 - g / 7000) * MetersToFeetCubic * 0.45359237;
    }

    public static double Cp_air_IP(double Temp_F) {
        double Temp_Rdb = Temp_F + 459.67;
        double C0 = 0.25257981;
        double C1 = -0.000059565592;
        double C2 = 0.000000084090076;
        double C3 = -0.000000000032763128;
        double C4 = 4.3973675E-15;
        return C4 * Temp_Rdb * Temp_Rdb * Temp_Rdb * Temp_Rdb + C3 * Temp_Rdb * Temp_Rdb * Temp_Rdb + C2 * Temp_Rdb * Temp_Rdb + C1 * Temp_Rdb + C0;
    }

    public static double Cp_air_SI(double Temp_C) {
        double Temp_Kdb = Temp_C + 273.15;
        double C0 = 1057.5;
        double C1 = -0.4489;
        double C2 = 0.0011407;
        double C3 = -0.00000079999;
        double C4 = 0.00000000019327;
        double Cp_SI = C4 * Temp_Kdb * Temp_Kdb * Temp_Kdb * Temp_Kdb + C3 * Temp_Kdb * Temp_Kdb * Temp_Kdb + C2 * Temp_Kdb * Temp_Kdb + C1 * Temp_Kdb + C0;
        return Cp_SI / 1000;
    }

    public static double LCENTHALPY(double Temp_Fdb, double grains) {
        double Cp = Cp_air_IP(Temp_Fdb);

        return Cp * Temp_Fdb + (grains / 7000) * (1061 + 0.444 * Temp_Fdb);
    }

    public static double LCSI_ENTHALPY(double Temp_Cdb, double grams) {
        double Cp = Cp_air_SI(Temp_Cdb);
        return Cp * Temp_Cdb + (grams / 1000) * (2501 + 1.805 * Temp_Cdb);  //SI calc using ASHRAE constants
    }

    public static double LCDEWPOINT(double grains, double feet) {
        if (Double.isNaN(grains * feet)) {
            return Double.NaN;
        }
        double atm = 0;
        double W = 0;
        double pwsapp = 0;

        if (grains < 0) {
            return Double.NaN;
        } else {
            W = grains / 7000;
            atm = LCALTTOPSI(feet);
            pwsapp = atm * W / (0.621945 + W);
            return XTDPAPP((double)(atm), pwsapp);
        }
    }

    public static double LCPDEWPOINT(double grains, double psia) {
        if (Double.isNaN(grains * psia)) {
            return Double.NaN;
        }
        double W = 0;
        double pwsapp = 0;

        if (grains < 0 || psia < MinAtmPressIP || psia > 400) {
            return Double.NaN;
        } else {
            W = grains / 7000;
            pwsapp = psia * W / (0.621945 + W);
            return XTDPAPP(psia, pwsapp);
        }
    }

    public static double LCSI_DEWPOINT(double grams, double meters) {
        if (Double.isNaN(grams * meters)) {
            return Double.NaN;
        }
        double atm = 0;
        double x = grams * 7;
        double W = grams / 1000;
        double feet = meters * MetersToFeet;
        double pwsapp = 0;

        if (x < 0 || x > 16300) {
            return Double.NaN;
        } else {
            atm = LCALTTOPSI(feet);
            pwsapp = atm * W / (0.621945 + W);
            return (XTDPAPP((double)(atm), pwsapp) - 32) * 5 / 9;
        }
    }


    public static double LCSI_PDEWPOINT(double grams, double kPa) {
        if (Double.isNaN(grams * kPa)) {
            return Double.NaN;
        }
        double psia = kPa / 6.8948;
        double x = grams * 7;
        double W = grams / 1000;
        double pwsapp = 0;

        if (x < 0 || x > 16300 || psia < MinAtmPressIP || psia > 400) {
            return Double.NaN;
        } else {
            pwsapp = psia * W / (0.621945 + W);
            return (XTDPAPP((double)(psia), pwsapp) - 32) * 5 / 9;
        }
    }

    public static double LCRH(double Temp_Fdb, double grains, double feet) {
        double wsat = 0;
        double atm = 0;
        double mu = 0;
        double pwsatm = 0F;
        double rh = 0F;
        double pwsapp = 0;
        if (Temp_Fdb < MinDP || Temp_Fdb > MaxDB || grains < 0) {
            return Double.NaN;
        } else {
            atm = LCALTTOPSI(feet);
            pwsapp = XPWSAPP((double)(atm), Temp_Fdb);
            wsat = 0.621945 * pwsapp * 7000 / (atm - pwsapp);
            pwsatm = pwsapp / atm;
            mu = grains / wsat;
            rh = (mu / (1 - (1 - mu) * pwsatm)) * 100;
            if (rh > 100) {
                return Double.NaN;
            } else {
                return rh;
            }
        }
    }

    public static double LCPRH(double Temp_Fdb, double grains, double psia) {
        if (Double.isNaN(Temp_Fdb * grains * psia)) {
            return Double.NaN;
        }
        double wsat = 0;
        double rh = 0;
        double mu = 0;
        double pwsatm = 0F;
        double pwsapp = 0;
        if (Temp_Fdb < MinDP || Temp_Fdb > MaxDB || grains < 0) {
            return Double.NaN;
        } else {
            pwsapp = XPWSAPP(psia, Temp_Fdb);
            wsat = 0.621945 * pwsapp * 7000 / (psia - pwsapp);
            pwsatm = pwsapp / psia;

            mu = grains / wsat;
            rh = (mu / (1 - (1 - mu) * pwsatm)) * 100;
            if (rh > 100) {
                return Double.NaN;
            } else {
                return rh;
            }
        }
    }

    public static double LCSI_RH(double Temp_Cdb, double grams, double meters) {
        if (Double.isNaN(Temp_Cdb * grams * meters)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double F = meters * MetersToFeet;

        return LCRH(T, g, F);
    }

    public static double LCSI_PRH(double Temp_Cdb, double grams, double kPa) {
        if (Double.isNaN(Temp_Cdb * grams * kPa)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double p = kPa / 6.8948;

        if (T < MinDP || T > MaxDB) {
            return Double.NaN;
        } else {
            return LCPRH(T, g, p);
        }
    }


    public static double LCPRHTOGRAINS(double Temp_Fdb, double RH, double psia) {
        if (Double.isNaN(Temp_Fdb * RH * psia)) {
            return Double.NaN;
        }
        double pws = 0F;
        double pwsapp = 0;
        double answer = 0;
        if (Temp_Fdb < MinDP || Temp_Fdb > MaxDB || RH < 0 || RH > 1) {
            return Double.NaN;
        } else {
            pwsapp = XPWSAPP(psia, Temp_Fdb);
            pws = pwsapp * RH;

            if (pws > psia) {
                return Double.NaN;
            } else {
                answer = 0.621945 * pws * 7000 / (psia - pws);
                return answer;
            }
        }
    }

    public static double LCSI_PRHTOGRAMS(double Temp_Cdb, double RH, double kPa) {
        if (Double.isNaN(Temp_Cdb * RH * kPa)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double p = kPa / 6.8948;

        if (T < MinDP || T > MaxDB || RH < 0 || RH > 1 || p < 4 || p > 400) {
            return Double.NaN;
        } else {
            return LCPRHTOGRAINS(T, RH, p) / 7;
        }
    }

    public static double LCSATVP(double Temp_Fdb) {
        if (Double.isNaN(Temp_Fdb)) {
            return Double.NaN;
        }
        if (Temp_Fdb < -148 || Temp_Fdb > MaxDB) {
            return Double.NaN;
        } else {
            return XPWS(Temp_Fdb) / 0.491154;
        }
    }

    public static double LCSI_SATVP(double Temp_Cdb) {
        if (Double.isNaN(Temp_Cdb)) {
            return Double.NaN;
        }
        double temp = 32 + Temp_Cdb * 9 / 5;

        if (temp < -148 || temp > MaxDB) {
            return Double.NaN;
        } else {
            return 25.4 * XPWS(temp) / 0.491154;
        }
    }

    public static double LCPWETBULB_OLD(double Temp_Fdb, double grains, double psia) {
        if (Double.isNaN(Temp_Fdb * grains * psia)) {
            return Double.NaN;
        }

        double atm = 0;
        double W = 0;
        double atmd = 0;
        double Wsstar = 0;
        double tboil = 0;
        double wetold = 0;
        double pwsapp = 0;
        double Wold = 0;
        double wetnew = 0;
        double del = 0;
        double Wnew = 0;

        atm = psia;
        W = grains / 7000;
        atmd = atm * 1;
        wetold = Temp_Fdb + 10;
        tboil = XTDP(atmd) - 5;
        if (wetold > tboil) {
            wetold = tboil;
        }
        do {
            wetold = wetold - 10;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);
        wetold = wetold + 10;

        do {
            wetold = wetold - 1;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);

        wetold = wetold + 1;
        pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
        Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
        if (wetold >= 32.018) {
            Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
        } else {
            Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
        }

        del = 0.1;

        double wetoldSave = wetold;

        do {
            wetnew = wetold + del;
            wetnew = Math.min(Math.max(wetnew, MinDP), MaxDB);
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetnew)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetnew >= 32.018) {
                Wnew = ((1093 - 0.556 * wetnew) * Wsstar - 0.24 * (Temp_Fdb - wetnew)) / (1093 + 0.444 * Temp_Fdb - wetnew);
            } else {
                Wnew = ((1220 - 0.04 * wetnew) * Wsstar - 0.24 * (Temp_Fdb - wetnew)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetnew);
            }
            if (Wnew - Wold == 0) {
                break;
            }

            del = (wetnew - wetold) / (Wnew - Wold) * (W - Wnew);

            if (Math.abs(del) < 0.00001) {
                break;
            }
            if (Math.abs(Wnew - Wold) < 0.00001) {
                break;
            }
            wetold = wetnew;
            Wold = Wnew;
        } while (true);

        if (wetnew > Temp_Fdb) {
            return Temp_Fdb;
        } else {
            return wetnew;
        }
    }

    public static double LCPWETBULB(double Temp_Fdb, double grains, double psia) {
        if (Double.isNaN(Temp_Fdb * grains * psia)) {
            return Double.NaN;
        }

        double atm = 0;
        double W = 0;
        double atmd = 0;
        double Wsstar = 0;
        double tboil = 0;
        double wetold = 0;
        double pwsapp = 0;
        double Wold = 0;
        double wetnew = 0;
        double del = 0;
        double Wnew = 0;

        int loopCounter = 0;
        int loopLimits = 100;
        boolean resultOK = false;

        atm = psia;
        W = grains / 7000;
        atmd = atm * 1;
        wetold = Temp_Fdb + 10;
        tboil = XTDP(atmd) - 5;
        if (wetold > tboil) {
            wetold = tboil;
        }

        wetold = wetold + 10;
        do {
            wetold = wetold - 10;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);
        wetold = wetold + 10;

        do {
            wetold = wetold - 1;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);

        wetold = wetold + 1;
        do {
            wetold = wetold - 0.1;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);

        double wetoldSave = wetold;

        wetold = wetold + 0.1;
        do {
            wetold = wetold - 0.01;
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetold)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetold >= 32.018) {
                Wold = ((1093 - 0.556 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1093 + 0.444 * Temp_Fdb - wetold);
            } else {
                Wold = ((1220 - 0.04 * wetold) * Wsstar - 0.24 * (Temp_Fdb - wetold)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetold);
            }
            if (Wold < W) {
                break;
            }
        } while (true);

        del = 0.01;

        do {
            wetnew = wetold + del;
            wetnew = Math.min(Math.max(wetnew, MinDP), Temp_Fdb);
            pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wetnew)));
            Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
            if (wetnew >= 32.018) {
                Wnew = ((1093 - 0.556 * wetnew) * Wsstar - 0.24 * (Temp_Fdb - wetnew)) / (1093 + 0.444 * Temp_Fdb - wetnew);
            } else {
                Wnew = ((1220 - 0.04 * wetnew) * Wsstar - 0.24 * (Temp_Fdb - wetnew)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wetnew);
            }
            if (Wnew - Wold == 0) {
                resultOK = true;
                break;
            }

            del = (wetnew - wetold) / (Wnew - Wold) * (W - Wnew);

            if (Math.abs(del) < 0.00001) {
                resultOK = true;
                break;
            }
            if (Math.abs(Wnew - Wold) < 0.00001) {
                resultOK = true;
                break;
            }
            wetold = wetnew;
            Wold = Wnew;
            loopCounter += 1;
            if (loopCounter >= loopLimits) {
                resultOK = false;
                break;
            }
        } while (true);

        if (!resultOK) {
            wetold = wetoldSave;
            wetnew = wetold + 1;

            int incEx = -1;
            double inc = 0;
            boolean notYet = true;
            double wb = wetnew;
            while (notYet) {
                for (int j = 0; j <= 10; j++) {
                    wb = wetnew - j * (wetnew - wetold) * (Math.pow(10, incEx));
                    pwsapp = Math.min((double) (atm) - 0.1, XPWSAPP((double) (atm), (double) (wb)));
                    Wsstar = 0.621945 * pwsapp / (atm - pwsapp);
                    if (wb >= 32.018) {
                        Wold = ((1093 - 0.556 * wb) * Wsstar - 0.24 * (Temp_Fdb - wb)) / (1093 + 0.444 * Temp_Fdb - wb);
                    } else {
                        Wold = ((1220 - 0.04 * wb) * Wsstar - 0.24 * (Temp_Fdb - wb)) / (1220 + 0.444 * Temp_Fdb - 0.48 * wb);
                    }

                    if (Math.abs(Wold - W) < 0.00001) {
                        wetnew = Math.max(wb, Temp_Fdb);
                        notYet = false;
                        break;
                    }

                    if (Wold < W) {
                        inc = (wetnew - wetold) * (Math.pow(10, incEx));
                        wetold = wb;
                        wetnew = wetold + inc;
                        incEx -= 1;
                        break;
                    }

                    loopCounter += 1;

                    if (loopCounter >= loopLimits) {
                        break;
                    }
                }
            }
        }

        if (wetnew > Temp_Fdb) {
            return Temp_Fdb;
        } else {
            return wetnew;
        }
    }

    public static double LCWETBULB(double Temp_Fdb, double grains, double feet) {
        if (Double.isNaN(Temp_Fdb * grains * feet)) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        return LCPWETBULB(Temp_Fdb, grains, psia);
    }

    public static double LCSI_WETBULB(double Temp_Cdb, double grams, double meters) {
        if (Double.isNaN(Temp_Cdb * grams * meters)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double F = meters * MetersToFeet;

        return (LCWETBULB(T, g, F) - 32) * 5 / 9;
    }

    public static double LCSI_PWETBULB(double Temp_Cdb, double grams, double kPa) {
        if (Double.isNaN(Temp_Cdb * grams * kPa)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double g = grams * 7;
        double p = kPa / 6.8948;

        return (LCPWETBULB(T, g, p) - 32) * 5 / 9;
    }

    public static double LCWBTOGRAINS(double Temp_Fdb, double Temp_Fwb, double feet) {
        //validate arguments
        if (Double.isNaN(Temp_Fdb * Temp_Fwb * feet)) {
            return Double.NaN;
        }
        // Calc Gr/Lb given wet & dry bulb temps
        double answer = 0;
        double Ws = 0;
        double W = 0F;

        if (feet < -500 | feet > 30000 | Temp_Fdb < MinDP | Temp_Fdb > MaxDB | Temp_Fwb > Temp_Fdb) {
            answer = Double.NaN;
        } else {
            if (Temp_Fdb < Temp_Fwb) {
                answer = Double.NaN;
            } else {
                Ws = LCSATGRAINS(Temp_Fwb, feet) / 7000;
                if (Temp_Fwb >= 32.018) {
                    W = ((1093 - 0.556 * Temp_Fwb) * Ws - 0.24 * (Temp_Fdb - Temp_Fwb)) / (1093 + 0.444 * Temp_Fdb - Temp_Fwb);
                } else {
                    W = ((1220 - 0.04 * Temp_Fwb) * Ws - 0.24 * (Temp_Fdb - Temp_Fwb)) / (1220 + 0.444 * Temp_Fdb - 0.48 * Temp_Fwb);
                }
                if (W <= 0) // Gr/Lb
                {
                    answer = Double.NaN;
                } else {
                    answer = W * 7000;
                    if (answer > LCSATGRAINS(Temp_Fdb, feet)) {
                        answer = LCSATGRAINS(Temp_Fdb, feet);
                    }
                }
            }
        }
        return answer;
    }

    public static double LCPWBTOGRAINS(double Temp_Fdb, double Temp_Fwb, double psia) {
        //validate arguments
        if (Double.isNaN(Temp_Fdb * Temp_Fwb * psia)) {
            return Double.NaN;
        }
        double answer = 0;
        double Ws = 0;
        double W = 0F;

        if (Temp_Fdb < MinDP | Temp_Fdb > MaxDB | Temp_Fwb > Temp_Fdb | psia < MinAtmPressIP | psia > 400) {
            answer = Double.NaN;
        } else {
            if (Temp_Fdb < Temp_Fwb) {
                answer = Double.NaN;
            } else {
                Ws = LCPSATGRAINS(Temp_Fwb, psia) / 7000;
                if (Temp_Fwb >= 32.018) {
                    W = ((1093 - 0.556 * Temp_Fwb) * Ws - 0.24 * (Temp_Fdb - Temp_Fwb)) / (1093 + 0.444 * Temp_Fdb - Temp_Fwb);
                } else {
                    W = ((1220 - 0.04 * Temp_Fwb) * Ws - 0.24 * (Temp_Fdb - Temp_Fwb)) / (1220 + 0.444 * Temp_Fdb - 0.48 * Temp_Fwb);
                }
                if (W <= 0) // Gr/Lb
                {
                    answer = Double.NaN;
                } else {
                    answer = W * 7000;
                    if (answer > LCPSATGRAINS(Temp_Fdb, psia)) {
                        answer = LCPSATGRAINS(Temp_Fdb, psia);
                    }
                }
            }
        }
        return answer;
    }

    public static double LCSI_WBTOGRAMS(double Temp_Cdb, double Temp_Cwb, double meters) {
        //validate arguments
        if (Double.isNaN(Temp_Cdb * Temp_Cwb * meters)) {
            return Double.NaN;
        }
        double temp_wb = Temp_Cwb * 9 / 5 + 32;
        double temp_db = Temp_Cdb * 9 / 5 + 32;
        double feet = meters * MetersToFeet;
        double answer = 0;
        double Ws = 0;
        double W = 0;

        if (Temp_Cdb < Temp_Cwb) {
            answer = Double.NaN;
        } else {
            Ws = LCSATGRAINS(temp_wb, feet) / 7000;
            if (temp_wb >= 32.018) {
                W = ((1093 - 0.556 * temp_wb) * Ws - 0.24 * (temp_db - temp_wb)) / (1093 + 0.444 * temp_db - temp_wb);
            } else {
                W = ((1220 - 0.04 * temp_wb) * Ws - 0.24 * (temp_db - temp_wb)) / (1220 + 0.444 * temp_db - 0.48 * temp_wb);
            }
            if (W <= 0) // grams/kg
            {
                answer = Double.NaN;
            } else {
                answer = W * 7000 / 7;
                if (answer > LCSATGRAINS(temp_db, feet) / 7) {
                    answer = LCSATGRAINS(temp_db, feet) / 7;
                }
            }
        }
        return answer;
    }

    public static double LCSI_PWBTOGRAMS(double Temp_Cdb, double Temp_Cwb, double kPa) {
        //validate arguments
        if (Double.isNaN(Temp_Cdb * Temp_Cwb * kPa)) {
            return Double.NaN;
        }
        double temp_wb = Temp_Cwb * 9 / 5 + 32;
        double temp_db = Temp_Cdb * 9 / 5 + 32;
        double p = kPa / 6.8948;
        double answer = 0;
        double Ws = 0;
        double W = 0F;

        if (Temp_Cdb < Temp_Cwb) {
            answer = Double.NaN;
        } else {
            Ws = LCPSATGRAINS(temp_wb, p) / 7000;
            if (temp_wb >= 32.018) {
                W = ((1093 - 0.556 * temp_wb) * Ws - 0.24 * (temp_db - temp_wb)) / (1093 + 0.444 * temp_db - temp_wb);
            } else {
                W = ((1220 - 0.04 * temp_wb) * Ws - 0.24 * (temp_db - temp_wb)) / (1220 + 0.444 * temp_db - 0.48 * temp_wb);
            }
            if (W <= 0) // grams/kg
            {
                answer = Double.NaN;
            } else {
                answer = W * 7000 / 7;
                if (answer > LCPSATGRAINS(temp_db, p) / 7) {
                    answer = LCPSATGRAINS(temp_db, p) / 7;
                }
            }
        }
        return answer;
    }

    public static double LCPWB_RHTOGRAINS(double Temp_Fwb, double RH, double psia) {
        //validate arguments
        if (Double.isNaN(Temp_Fwb * RH * psia)) {
            return Double.NaN;
        }
        double T = 0F;
        double x = 0F;
        double answer = 0;
        double RHc = 0F;

        if (RH > 1 | RH < 0) {
            answer = Double.NaN;
        } else {
            T = Temp_Fwb;
            do {
                T = T + 10;
                x = LCPWBTOGRAINS(T, Temp_Fwb, psia);
                RHc = LCPRH(T, x, psia) / 100;
                if (RHc < RH) {
                    T = T - 10;
                    break;
                }
            } while (true);
            do {
                T = T + 1;
                x = LCPWBTOGRAINS(T, Temp_Fwb, psia);
                RHc = LCPRH(T, x, psia) / 100;
                if (RHc < RH) {
                    T = T - 1;
                    break;
                }
            } while (true);
            do {
                T = T + 0.1;
                x = LCPWBTOGRAINS(T, Temp_Fwb, psia);
                RHc = LCPRH(T, x, psia) / 100;
                if (RHc < RH) {
                    T = T - 0.1;
                    break;
                }
            } while (true);
            do {
                T = T + 0.01;
                x = LCPWBTOGRAINS(T, Temp_Fwb, psia);
                RHc = LCPRH(T, x, psia) / 100;
                if (RHc < RH) {
                    T = T - 0.01;
                    break;
                }
            } while (true);
            do {
                T = T + 0.001;
                x = LCPWBTOGRAINS(T, Temp_Fwb, psia);
                RHc = LCPRH(T, x, psia) / 100;
                if (RHc < RH) {
                    T = T - 0.001;
                    break;
                }
            } while (true);
            answer = x;
        }
        return answer;
    }

    public static double LCWB_RHTOGRAINS(double Temp_Fwb, double RH, double feet) {
        //validate arguments
        if (Double.isNaN(Temp_Fwb * RH * feet)) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        double answer = 0F;
        answer = LCPWB_RHTOGRAINS(Temp_Fwb, RH, psia);
        return answer;
    }

    public static double LCSI_WB_RHTOGRAMS(double Temp_Cwb, double RH, double meters) {
        //validate arguments
        if (Double.isNaN(Temp_Cwb * RH * meters)) {
            return Double.NaN;
        }
        double tw = Temp_Cwb * 9 / 5 + 32;
        double feet = meters * MetersToFeet;

        return (LCWB_RHTOGRAINS(tw, RH, feet)) / 7;
    }

    public static double LCSI_PWB_RHTOGRAMS(double Temp_Cwb, double RH, double kPa) {
        //validate arguments
        if (Double.isNaN(Temp_Cwb * RH * kPa)) {
            return Double.NaN;
        }
        double tw = Temp_Cwb * 9 / 5 + 32;
        double psia = kPa / 6.8948;

        return (LCPWB_RHTOGRAINS(tw, RH, psia)) / 7;
    }


    public static int LCCALCALL(int UofM, int codes, double t, double tw, double RelH, double w, double tdp, double h, double v, double vp, double d, double absW, double ppmw, double ppmv, double abspressure) {
        // Implementation of LCCALCALL method
        return 0;
    }

    public static class StatePoint {
        public double t;
        public double tw;
        public double RelH;
        public double w;
        public double tdp;
        public double h;
        public double v;
        public double vp;
        public double d;
        public double absW;
        public double ppmw;
        public double ppmv;
        public int r;

        public StatePoint(double t, double tw, double RelH, double w, double tdp, double h, double v, double vp, double d, double absW, double ppmw, double ppmv, int r) {
            this.t = t;
            this.tw = tw;
            this.RelH = RelH;
            this.w = w;
            this.tdp = tdp;
            this.h = h;
            this.v = v;
            this.vp = vp;
            this.d = d;
            this.absW = absW;
            this.ppmw = ppmw;
            this.ppmv = ppmv;
            this.r = r;
        }
    }

    private static void ZeroOutput(double[] t, double[] tw, double[] RelH, double[] w, double[] tdp, double[] h, double[] v, double[] vp, double[] d, double[] absW, double[] ppmw, double[] ppmv) {
        Arrays.fill(t, 0);
        Arrays.fill(tw, 0);
        Arrays.fill(tdp, 0);
        Arrays.fill(RelH, 0);
        Arrays.fill(h, 0);
        Arrays.fill(w, 0);
        Arrays.fill(v, 0);
        Arrays.fill(vp, 0);
        Arrays.fill(d, 0);
        Arrays.fill(absW, 0);
        Arrays.fill(ppmw, 0);
        Arrays.fill(ppmv, 0);
    }

    /**
     * IP - Adiabatic Mixing Calculation using STANDARD air flows, temperature and humidity ratio.
     * Any number of air flow streams can be mixed using this routine.
     *
     * @param STDFlow        Array of flow rates in Standard Cubic Feet per minute SCFM
     * @param Temp_Fdb       Array of temperatures corresponding to the flows °F
     * @param grains         Array of humidity ratios corresponding to the flows gr/lb
     * @param STDFlowTotal   Sum of flows in arrays SCFM
     * @param Temp_FdbMixed  Adiabatic mixed air temperature °F
     * @param grainsMixed    Adiabatic mixed air humidity ratio gr/lb
     * @return Results code where 0 = success
     */
    public static boolean LCMIX(double[] STDFlow, double[] Temp_Fdb, double[] grains, double[] STDFlowTotal, double[] Temp_FdbMixed, double[] grainsMixed) {
        int i = 0;
        double k = 0;
        double j = 0;
        double t = 0;

        STDFlowTotal[0] = 0;
        Temp_FdbMixed[0] = 0;
        grainsMixed[0] = 0;

        int ArraySize = STDFlow.length - 1;
        //validate input values
        for (i = 0; i <= ArraySize; i++) {
            if (Double.isNaN(STDFlow[i] * Temp_Fdb[i] * grains[i])) {
                return false;
            }
        }

        //total air flow
        int countFlows = 0;
        int nonZeroFlowIndex = 0;
        for (i = 0; i <= ArraySize; i++) {
            STDFlowTotal[0] = STDFlowTotal[0] + STDFlow[i];
            if (STDFlow[i] > 0) {
                countFlows += 1;
                nonZeroFlowIndex = i;
            }
        }

        if (countFlows == 0) {
            return false;
        }

        if (countFlows == 1) {
            Temp_FdbMixed[0] = Temp_Fdb[nonZeroFlowIndex];
            grainsMixed[0] = grains[nonZeroFlowIndex];
            return true;
        }

        //check for same T and w values in all constituents
        Temp_FdbMixed[0] = Temp_Fdb[0];
        grainsMixed[0] = grains[0];
        boolean IsSame = true;
        for (i = 1; i <= ArraySize; i++) {
            if (Temp_Fdb[i] != Temp_FdbMixed[0] || grains[i] != grainsMixed[0]) {
                IsSame = false;
                break;
            }
        }

        //if all values are the same then just return (we already set the mixed values :-)
        if (IsSame) {
            return true;
        }

        double[] Enthalpy = new double[ArraySize + 1];

        double EnthalpyMixed = 0;
        grainsMixed[0] = 0;

        //OK to calculate Mix...
        //enthalpy for all airstreams
        for (i = 0; i <= ArraySize; i++) {
            Enthalpy[i] = LCENTHALPY(Temp_Fdb[i], grains[i]);
        }

        //sum enthalpies and humidity ratios...
        for (i = 0; i <= ArraySize; i++) {
            EnthalpyMixed = EnthalpyMixed + Enthalpy[i] * STDFlow[i];
            grainsMixed[0] = grainsMixed[0] + grains[i] * STDFlow[i];
        }

        //weighted average
        EnthalpyMixed = EnthalpyMixed / STDFlowTotal[0];
        grainsMixed[0] = grainsMixed[0] / STDFlowTotal[0];

        //mixing calculations
        if (STDFlowTotal[0] > 0) {

            k = MinDP;
            while (k <= MaxDB) {
                if (LCENTHALPY(k, grainsMixed[0]) > EnthalpyMixed) {
                    t = k - 10;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 10;
            }
            j = t + 10;
            k = t;
            while (k <= j + 1) {
                if (LCENTHALPY(k, grainsMixed[0]) > EnthalpyMixed) {
                    t = k - 1;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 1;
            }
            j = t + 1;
            k = t;
            while (k <= j + 0.1) {
                if (LCENTHALPY(k, grainsMixed[0]) > EnthalpyMixed) {
                    t = k - 0.1;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 0.1;
            }
            j = t + 0.1;
            k = t;
            while (k <= j + 0.01) {
                if (LCENTHALPY(k, grainsMixed[0]) > EnthalpyMixed) {
                    t = k - 0.01;
                    break; // TODO: might not be correct. Was : Exit While
                }

                k = k + 0.01;
            }
            Temp_FdbMixed[0] = t;
            return true;
        } else {
            return false;
        }
    }

    /**
     * SI - Adiabatic Mixing Calculation using STANDARD air flows, temperature and humidity ratio.
     * Any number of air flow streams can be mixed using this routine.
     *
     * @param STDFlow        Array of flow rates in Standard Cubic Meters per hour, SCMH
     * @param Temp_Cdb       Array of temperatures corresponding to the flows °C
     * @param grams          Array of humidity ratios corresponding to the flows g/kg
     * @param STDFlowTotal   Sum of flows in arrays SCMH
     * @param Temp_CdbMixed  Adiabatic mixed air temperature °C
     * @param gramsMixed     Adiabatic mixed air humidity ratio g/kg
     * @return Results code where 0 = success
     */
    public static boolean LCSI_MIX(double[] STDFlow, double[] Temp_Cdb, double[] grams, double[] STDFlowTotal, double[] Temp_CdbMixed, double[] gramsMixed) {
        int i = 0;
        double k = 0;
        double j = 0;
        double t = 0;

        STDFlowTotal[0] = 0;
        Temp_CdbMixed[0] = 0;
        gramsMixed[0] = 0;

        int ArraySize = STDFlow.length - 1;
        //validate input values
        for (i = 0; i <= ArraySize - 1; i++) {
            if (Double.isNaN(STDFlow[i] * Temp_Cdb[i] * grams[i])) {
                return false;
            }
        }

        int countFlows = 0;
        int nonZeroFlowIndex = 0;
        //total air flow
        for (i = 0; i <= ArraySize; i++) {
            STDFlowTotal[0] = STDFlowTotal[0] + STDFlow[i];
            if (STDFlow[i] > 0) {
                countFlows += 1;
                nonZeroFlowIndex = i;
            }
        }

        if (countFlows == 0) {
            return false;
        }

        if (countFlows == 1) {
            Temp_CdbMixed[0] = Temp_Cdb[nonZeroFlowIndex];
            gramsMixed[0] = grams[nonZeroFlowIndex];
            return true;
        }

        //check for same T and w values in all constituents
        Temp_CdbMixed[0] = Temp_Cdb[0];
        gramsMixed[0] = grams[0];
        boolean IsSame = true;
        for (i = 1; i <= ArraySize; i++) {
            if (Temp_Cdb[i] != Temp_CdbMixed[0] || grams[i] != gramsMixed[0]) {
                IsSame = false;
                break;
            }
        }

        //if all values are the same then just return (we already set the mixed values :-)
        if (IsSame) {
            return true;
        }

        double[] Enthalpy = new double[ArraySize + 1];
        double[] Temp_Fdb = new double[ArraySize + 1];
        double[] grains = new double[ArraySize + 1];
        double grainsMixed = 0;

        double EnthalpyMixed = 0;
        grainsMixed = 0;

        //convert to IP
        for (i = 0; i <= ArraySize; i++) {
            Temp_Fdb[i] = Temp_Cdb[i] * 9 / 5 + 32;

            grains[i] = grams[i] * 7;
        }

        //enthalpy for all airstreams
        for (i = 0; i <= ArraySize; i++) {
            Enthalpy[i] = LCENTHALPY(Temp_Fdb[i], grains[i]);
        }

        //sum enthalpies and humidity ratios...
        for (i = 0; i <= ArraySize; i++) {
            EnthalpyMixed = EnthalpyMixed + Enthalpy[i] * STDFlow[i];
            grainsMixed = grainsMixed + grains[i] * STDFlow[i];
        }

        //weighted average
        EnthalpyMixed = EnthalpyMixed / STDFlowTotal[0];
        grainsMixed = grainsMixed / STDFlowTotal[0];

        //mixing calculations
        if (STDFlowTotal[0] > 0) {

            k = MinDP;
            while (k <= MaxDB) {
                if (LCENTHALPY(k, grainsMixed) > EnthalpyMixed) {
                    t = k - 10;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 10;
            }
            j = t + 10;
            k = t;
            while (k <= j) {
                if (LCENTHALPY(k, grainsMixed) > EnthalpyMixed) {
                    t = k - 1;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 1;
            }
            j = t + 1;
            k = t;
            while (k <= j) {
                if (LCENTHALPY(k, grainsMixed) > EnthalpyMixed) {
                    t = k - 0.1;
                    break; // TODO: might not be correct. Was : Exit While
                }
                k = k + 0.1;
            }
            j = t + 0.1;
            k = t;
            while (k <= j) {
                if (LCENTHALPY(k, grainsMixed) > EnthalpyMixed) {
                    t = k - 0.01;
                    break; // TODO: might not be correct. Was : Exit While
                }

                k = k + 0.01;
            }
            Temp_CdbMixed[0] = (t - 32) * 5 / 9;
            gramsMixed[0] = grainsMixed / 7;
            return true;
        } else {
            return false;
        }
    }

    //region("Air Flow")
    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature and elevation. Uses 70°F and 1 atmosphere as standard conditions.
     *
     * @param ACFM       Air Flow in Actual Cubic Feet per Minute
     * @param Temp_Fdb   dry bulb temperature in °F
     * @param feet       elevation above sea level in feet
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCSCFM(double ACFM, double Temp_Fdb, double feet) {
        //validate arguments
        if (Double.isNaN(ACFM * Temp_Fdb * feet) || ACFM == 0) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        return ACFM * (529.67 / (459.67 + Temp_Fdb)) * psia / OneAtmPSI;
    }

    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature, elevation and the Standard Temperature. Uses 1 atmosphere as standard pressure.
     *
     * @param ACFM           Air Flow in Actual Cubic Feet per Minute
     * @param Temp_Fdb       dry bulb temperature in °F
     * @param feet           elevation above sea level in feet
     * @param STDTemp_Fdb    standard temperature in °F
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCSCFM(double ACFM, double Temp_Fdb, double feet, double STDTemp_Fdb) {
        //validate arguments
        if (Double.isNaN(ACFM * Temp_Fdb * feet * STDTemp_Fdb) || ACFM == 0) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        return ACFM * ((459.67 + STDTemp_Fdb) / (459.67 + Temp_Fdb)) * psia / OneAtmPSI;
    }

    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature and absolute pressure. Uses 70°F and 1 atmosphere as standard conditions.
     *
     * @param ACFM       Air Flow in Actual Cubic Feet per Minute
     * @param Temp_Fdb   dry bulb temperature in °F
     * @param psia       absolute pressure in psi
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCPSCFM(double ACFM, double Temp_Fdb, double psia) {
        //validate arguments
        if (Double.isNaN(ACFM * Temp_Fdb * psia) || ACFM * psia == 0) {
            return Double.NaN;
        }
        return ACFM * (529.67 / (459.67 + Temp_Fdb)) * psia / OneAtmPSI;
    }

    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature, absolute pressure and the Standard Temperature. Uses 1 atmosphere as standard pressure.
     *
     * @param ACFM           Air Flow in Actual Cubic Feet per Minute
     * @param Temp_Fdb       dry bulb temperature in °F
     * @param psia           absolute pressure in psi
     * @param STDTemp_Fdb    standard temperature in °F
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCPSCFM(double ACFM, double Temp_Fdb, double psia, double STDTemp_Fdb) {
        //validate arguments
        if (Double.isNaN(ACFM * Temp_Fdb * psia * STDTemp_Fdb) || ACFM * psia == 0) {
            return Double.NaN;
        }
        return ACFM * ((459.67 + STDTemp_Fdb) / (459.67 + Temp_Fdb)) * psia / OneAtmPSI;
    }

    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature and elevation. Uses 70°F and 1 atmosphere as standard conditions.
     *
     * @param          in Actual Cubic Feet per Minute
     * @param Temp_Fdb   dry bulb temperature in °F
     * @param feet       elevation above sea level in feet
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCACFM(double SCFM, double Temp_Fdb, double feet) {
        //validate arguments
        if (Double.isNaN(SCFM * Temp_Fdb * feet) || SCFM == 0) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        return SCFM * ((459.67 + Temp_Fdb) / 529.67) * psia / OneAtmPSI;
    }

    /**
     * IP - Calculates Standard Cubic Feet per Minute (SCFM) given Actual Cubic Feet per Minute (ACFM)
     * dry bulb temperature, elevation and the Standard Temperature. Uses 1 atmosphere as standard pressure.
     *
     * @param             Actual Cubic Feet per Minute
     * @param Temp_Fdb       dry bulb temperature in °F
     * @param feet           elevation above sea level in feet
     * @param STDTemp_Fdb    standard temperature in °F
     * @return Air Flow in Standard Cubic Feet per Minute (SCFM)
     */
    public static double LCACFM(double SCFM, double Temp_Fdb, double feet, double STDTemp_Fdb) {
        //validate arguments
        if (Double.isNaN(SCFM * Temp_Fdb * feet * STDTemp_Fdb) || SCFM == 0) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        return SCFM * ((459.67 + Temp_Fdb) / (459.67 + STDTemp_Fdb)) * OneAtmPSI / psia;
    }

    /**
     * IP - Calculates Actual Cubic Feet per Minute (ACFM) given Standard Cubic Feet per Minute (SCFM)
     * dry bulb temperature, absolute pressure and the Standard Temperature. Uses 1 atmosphere as standard pressure.
     *
     * @param SCFM           Air Flow in Standard Cubic Feet per Minute
     * @param Temp_Fdb       dry bulb temperature in °F
     * @param psia           absolute pressure in psi
     * @param                    °F
     * @return Air Flow in Actual Cubic Feet per Minute (SCFM)
     */
    public static double LCPACFM(double SCFM, double Temp_Fdb, double psia) {
        //validate arguments
        if (Double.isNaN(SCFM * Temp_Fdb * psia) || SCFM * psia == 0) {
            return Double.NaN;
        }

        return SCFM * ((459.67 + Temp_Fdb) / 529.67) * OneAtmPSI / psia;

    }

    /**
     * IP - Calculates Actual Cubic Feet per Minute (ACFM) given Standard Cubic Feet per Minute (SCFM)
     * dry bulb temperature, absolute pressure and the Standard Temperature. Uses 1 atmosphere as standard pressure.
     *
     * @param SCFM           Air Flow in Standard Cubic Feet per Minute
     * @param Temp_Fdb       dry bulb temperature in °F
     * @param psia           absolute pressure in psi
     * @param STDTemp_Fdb    standard temperature in °F
     * @return Air Flow in Actual Cubic Feet per Minute (SCFM)
     */
    public static double LCPACFM(double SCFM, double Temp_Fdb, double psia, double STDTemp_Fdb) {
        //validate arguments
        if (Double.isNaN(SCFM * Temp_Fdb * psia * STDTemp_Fdb) || SCFM * psia == 0) {
            return Double.NaN;
        }

        return SCFM * ((459.67 + Temp_Fdb) / (459.67 + STDTemp_Fdb)) * OneAtmPSI / psia;

    }


    public static double LCSI_SCMH(double ACMH, double Temp_Cdb, double meters) {
        // validate arguments
        if (Double.isNaN(ACMH * Temp_Cdb * meters)) {
            return Double.NaN;
        }
        double atm = LCSI_ALTTOKPA(meters);
        return ACMH * (293.15 / (273.15 + Temp_Cdb)) * atm / OneAtmkPa;
    }

    public static double LCSI_SCMH(double ACMH, double Temp_Cdb, double meters, double STDTemp_Cdb) {
        // validate arguments
        if (Double.isNaN(ACMH * Temp_Cdb * meters * STDTemp_Cdb)) {
            return Double.NaN;
        }
        double atm = LCSI_ALTTOKPA(meters);
        return ACMH * ((STDTemp_Cdb + 273.15) / (273.15 + Temp_Cdb)) * atm / OneAtmkPa;
    }

    public static double LCSI_PSCMH(double ACMH, double Temp_Cdb, double kPa) {
        // validate arguments
        if (Double.isNaN(ACMH * Temp_Cdb * kPa)) {
            return Double.NaN;
        }
        return ACMH * (293.15 / (273.15 + Temp_Cdb)) * kPa / OneAtmkPa;
    }

    public static double LCSI_PSCMH(double ACMH, double Temp_Cdb, double kPa, double STDTemp_Cdb) {
        // validate arguments
        if (Double.isNaN(ACMH * Temp_Cdb * kPa * STDTemp_Cdb)) {
            return Double.NaN;
        }
        return ACMH * ((273.15 + STDTemp_Cdb) / (273.15 + Temp_Cdb)) * kPa / OneAtmkPa;
    }

    public static double LCSI_ACMH(double SCMH, double Temp_Cdb, double meters) {
        // validate arguments
        if (Double.isNaN(SCMH * Temp_Cdb * meters) || SCMH == 0) {
            return Double.NaN;
        }
        double kPa = LCSI_ALTTOKPA(meters);
        return SCMH * ((273.15 + Temp_Cdb) / 293.15) * OneAtmkPa / kPa;
    }

    public static double LCSI_ACMH(double SCMH, double Temp_Cdb, double meters, double STDTemp_Cdb) {
        // validate arguments
        if (Double.isNaN(SCMH * Temp_Cdb * meters * STDTemp_Cdb) || SCMH == 0) {
            return Double.NaN;
        }
        double kPa = LCSI_ALTTOKPA(meters);
        return SCMH * ((273.15 + Temp_Cdb) / (STDTemp_Cdb + 273.15)) * OneAtmkPa / kPa;
    }

    public static double LCSI_PACMH(double SCMH, double Temp_Cdb, double kPa) {
        // validate arguments
        if (Double.isNaN(SCMH * Temp_Cdb * kPa) || SCMH * kPa == 0) {
            return Double.NaN;
        }
        return SCMH * ((273.15 + Temp_Cdb) / 293.15) * OneAtmkPa / kPa;
    }

    public static double LCSI_PACMH(double SCMH, double Temp_Cdb, double kPa, double STDTemp_Cdb) {
        // validate arguments
        if (Double.isNaN(SCMH * Temp_Cdb * kPa * STDTemp_Cdb) || SCMH * kPa == 0) {
            return Double.NaN;
        }
        return SCMH * ((273.15 + Temp_Cdb) / (STDTemp_Cdb + 273.15)) * OneAtmkPa / kPa;
    }

    public static double LCTOTALLOAD(double SCFM, double hIN, double hOUT) {
        // validate arguments
        if (Double.isNaN(SCFM * hIN * hOUT)) {
            return Double.NaN;
        }

        double d = LCDENSITY(70, 0, 0);

        return SCFM * d * 60 * (hIN - hOUT);
    }

    public static double LCTOTALLOAD(double SCFM, double Temp_FdbIN, double GrainsIN, double Temp_FdbOUT, double GrainsOUT) {
        if (Double.isNaN(SCFM * Temp_FdbIN * GrainsIN * Temp_FdbOUT * GrainsOUT)) {
            return Double.NaN;
        }
        double hIN = LCENTHALPY(Temp_FdbIN, GrainsIN);
        double hOUT = LCENTHALPY(Temp_FdbOUT, GrainsOUT);
        double d = LCDENSITY(70, 0, 0);

        return SCFM * d * 60 * (hIN - hOUT);
    }

    public static double LCTOTALLOAD(double ACFM, double Temp_FdbIN, double GrainsIN, double Temp_FdbOUT, double GrainsOUT, double feet) {
        if (Double.isNaN(ACFM * Temp_FdbIN * GrainsIN * Temp_FdbOUT * GrainsOUT * feet)) {
            return Double.NaN;
        }
        double psia = LCALTTOPSI(feet);
        double SCFM = LCPSCFM(ACFM, Temp_FdbIN, psia);
        double hIN = LCENTHALPY(Temp_FdbIN, GrainsIN);
        double hOUT = LCENTHALPY(Temp_FdbOUT, GrainsOUT);
        double d = LCDENSITY(70, 0, 0);

        return SCFM * d * 60 * (hIN - hOUT);
    }

    public static double LCPTOTALLOAD(double ACFM, double Temp_FdbIN, double GrainsIN, double Temp_FdbOUT, double GrainsOUT, double psia) {
        if (Double.isNaN(ACFM * Temp_FdbIN * GrainsIN * Temp_FdbOUT * GrainsOUT * psia)) {
            return Double.NaN;
        }
        double SCFM = LCPSCFM(ACFM, Temp_FdbIN, psia);
        double hIN = LCENTHALPY(Temp_FdbIN, GrainsIN);
        double hOUT = LCENTHALPY(Temp_FdbOUT, GrainsOUT);
        double d = LCDENSITY(70, 0, 0);

        return SCFM * d * 60 * (hIN - hOUT);
    }

    public static int LCLOADS(double ACFM, double Temp_FdbIN, double GrainsIN, double Temp_FdbOUT, double GrainsOUT, double feet, double[] TotalLoad, double[] SensibleLoad, double[] LatentLoad) {
        if (Double.isNaN(ACFM * Temp_FdbIN * GrainsIN * Temp_FdbOUT * GrainsOUT * feet)) {
            return 1;
        }
        double psia = LCALTTOPSI(feet);
        double SCFM = LCPSCFM(ACFM, Temp_FdbIN, psia);
        double hIN = LCENTHALPY(Temp_FdbIN, GrainsIN);
        double hOUT = LCENTHALPY(Temp_FdbOUT, GrainsOUT);
        double Cp = Cp_air_IP(70);
        double d = LCDENSITY(70, 0, 0);
        TotalLoad[0] = SCFM * d * 60 * (hIN - hOUT);
        SensibleLoad[0] = SCFM * Cp * d * 60 * (Temp_FdbIN - Temp_FdbOUT);
        LatentLoad[0] = TotalLoad[0] - SensibleLoad[0];
        return 0;
    }

    public static int LCPLOADS(double ACFM, double Temp_FdbIN, double GrainsIN, double Temp_FdbOUT, double GrainsOUT, double psia, double[] TotalLoad, double[] SensibleLoad, double[] LatentLoad) {
        if (Double.isNaN(ACFM * Temp_FdbIN * GrainsIN * Temp_FdbOUT * GrainsOUT * psia)) {
            return 1;
        }
        double SCFM = LCPSCFM(ACFM, Temp_FdbIN, psia);
        double hIN = LCENTHALPY(Temp_FdbIN, GrainsIN);
        double hOUT = LCENTHALPY(Temp_FdbOUT, GrainsOUT);
        double Cp = Cp_air_IP(70);
        double d = LCDENSITY(70, 0, 0);
        TotalLoad[0] = SCFM * d * 60 * (hIN - hOUT);
        SensibleLoad[0] = SCFM * Cp * d * 60 * (Temp_FdbIN - Temp_FdbOUT);
        LatentLoad[0] = TotalLoad[0] - SensibleLoad[0];
        return 0;
    }
    public static double LCSI_RHTOGRAMS(double Temp_Cdb, double RH, double meters) {
        if (Double.isNaN(Temp_Cdb * RH * meters)) {
            return Double.NaN;
        }
        double T = Temp_Cdb * 9 / 5 + 32;
        double F = meters * MetersToFeet;

        if (T < MinDP || T > MaxDB || RH < 0 || RH > 1) {
            return Double.NaN;
        } else {
            return LCRHTOGRAINS(T, RH, F) / 7;
        }
    }
    public static double LCRHTOGRAINS(double Temp_Fdb, double RH, double feet) {
        if (Double.isNaN(Temp_Fdb * RH * feet)) {
            return Double.NaN;
        }
        double psia = 0;
        double pws = 0F;
        double pwsapp = 0;
        double answer = 0;
        if (Temp_Fdb < MinDP || Temp_Fdb > MaxDB || RH < 0 || RH > 1) {
            return Double.NaN;
        } else {
            psia = LCALTTOPSI(feet);
            pwsapp = XPWSAPP(psia, Temp_Fdb);
            pws = pwsapp * RH;

            if (pws > XPWSAPP(psia, MaxDP)) {
                return Double.NaN;
            } else {
                answer = 0.621945 * pws * 7000 / (psia - pws);
                return answer;
            }
        }
    }

}
