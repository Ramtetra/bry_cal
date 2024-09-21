package com.tetra.brycal;

public class PsyCal {
    private double OneAtmPSI = 14.695948775513;
    private double OneAtminHg = 29.921299597519;
    private double OneAtminH20 = 406.782461732239;
    private double OneAtmmmHg = 760.001009776983;
    double num19, num18,num20,num17,num21,num23,num22,num24,num26,num25,num27,num29,num28,num30;
    public double LCSI_WETBULB(double Temp_Cdb, double grams, double meters) {
        // Convert Celsius to Fahrenheit
        double num = Temp_Cdb * 9.0 / 5.0 + 32.0;

        // Convert grams to another unit (based on some 7x factor)
        double num2 = grams * 7.0;

        // Convert meters to feet (approximation using inches and feet conversion)
        double num3 = meters * 39.37 / 12.0;

        // Call LCWETBULB function and convert back from Fahrenheit to Celsius
        return (LCWETBULB(num, num2, num3) - 32.0) * 5.0 / 9.0;
    }
    public double LCWETBULB(double Temp_Fdb, double grains, double feet) {
        // Calculate atmospheric pressure based on altitude in feet
        double num = (1.3598E-08 * Math.pow(feet, 2.0) - 0.0010717 * feet + 29.921) / 2.036;

        // Call LCPWETBULB with the dry-bulb temperature, grains, and calculated atmospheric pressure
        return LCPWETBULB(Temp_Fdb, grains, num);
    }
    public double LCPWETBULB(double Temp_Fdb, double grains, double psia) {
        double num = psia;
        double num2 = grains / 7000.0;
        double num3 = num * 1.0;
        double num7 = Temp_Fdb + 10.0;
        double num6 = XTDP(num3) - 5.0;

        if (num7 > num6) {
            num7 = num6;
        }

        double num8, num5, num9;

        do {
            num7 -= 10.0;
            num8 = XPWSAPP(num, num7);
            num5 = 0.62198 * num8 / (num - num8);
            num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);
        } while (!(num9 < num2));

        num7 += 10.0;

        do {
            num7 -= 1.0;
            num8 = XPWSAPP(num, num7);
            num5 = 0.62198 * num8 / (num - num8);
            num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);
        } while (!(num9 < num2));

        num7 += 1.0;
        num8 = XPWSAPP(num, num7);
        num5 = 0.62198 * num8 / (num - num8);
        num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);

        double num10 = 0.0, num11 = 0.1, num12;

        while (true) {
            num10 = num7 + num11;
            num8 = XPWSAPP(num, num10);
            num5 = 0.62198 * num8 / (num - num8);
            num12 = ((1093.0 - 0.556 * num10) * num5 - 0.24 * (Temp_Fdb - num10)) / (1093.0 + 0.444 * Temp_Fdb - num10);

            if (num12 - num9 == 0.0) {
                break;
            }

            num11 = (num10 - num7) / (num12 - num9) * (num2 - num12);

            if (Math.abs(num11) < 1E-05 || Math.abs(num12 - num9) < 1E-05) {
                break;
            }

            num7 = num10;
            num9 = num12;
        }

        if (num10 > Temp_Fdb) {
            num10 = Temp_Fdb;
        }

        return num10;
    }

    private double XTDP(double pws) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        double num6 = 0.0;
        double num7 = 0.0;
        double num8 = 0.0;
        double num9 = 0.0;
        double num10 = 0.0;
        double num11 = 0.0;
        double num12 = 0.0;
        double num13 = 0.0;
        double num14 = 0.0;
        double num15 = 0.0;
        double num16 = 0.0;

        if (pws < 0.0) {
            num = -9999.0;
        } else {
            num2 = 459.67;
            num4 = 392.0;
            num3 = -148.0;
            num5 = XPWS(num3);

            if (pws < num5) {
                return 0.0 - num2 + (num3 + num2) / (num5 - pws) * pws;
            }

            num6 = XPWS(num4);

            if (pws > num6) {
                return num4;
            }

            num9 = Math.log(pws * 2.036);
            num10 = (pws >= 0.08865) ?
                    (79.047 + 30.579 * num9 + 1.8893 * Math.pow(num9, 2.0)) :
                    (71.98 + 24.873 * num9 + 0.87 * Math.pow(num9, 2.0));

            num13 = num10 + num2;
            num = num10;
            num8 = num13;
            num11 = num13;
            num7 = XPWS(num11 - num2);
            num12 = -1.0;

            while (true) {
                num14 = num8 + num12;
                num11 = num14;
                num15 = XPWS(num11 - num2);
                num16 = num15 - num7;

                if (num15 - num7 == 0.0) {
                    num13 = num14;
                    break;
                }

                num12 = (num14 - num8) / (num15 - num7) * (pws - num15);

                if (Math.abs(num12) < 1E-05) {
                    num13 = num14;
                    break;
                }

                num8 = num14;
                num7 = num15;
            }

            num = num13 - num2;
        }

        return num;
    }

    private double XPWSAPP(double atm, double Tf) {
        double num = 0.0;
        num = XFS(atm, Tf);
        return num * XPWS(Tf);
    }
    private double XPWS(double temp) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        double num6 = 0.0;
        double num7 = 0.0;
        double num8 = 0.0;
        double num9 = 0.0;
        double num10 = 0.0;

        if (temp < -148.0 || temp > 400.0) {
            return -9999.0;
        }

        num3 = temp + 459.67; // Convert to absolute temperature (Rankine scale)

        if (temp < 32.018) {
            // Constants for temperature below freezing point
            num4 = -10214.165;
            num5 = -4.8932428;
            num6 = -0.0053765794;
            num7 = 1.9202377E-07;
            num8 = 3.5575832E-10;
            num9 = -9.0344688E-14;
            num10 = 4.1635019;
        } else {
            // Constants for temperature above freezing point
            num4 = -10440.397;
            num5 = -11.29465;
            num6 = -0.027022355;
            num7 = 1.289036E-05;
            num8 = -2.4780681E-09;
            num9 = 0.0;
            num10 = 6.5459673;
        }

        // Calculate the exponential term for pressure
        num2 = num4 / num3 + num5 + num6 * num3 + num7 * num3 * num3 + num8 * Math.pow(num3, 3.0);
        num2 = num2 + num9 * Math.pow(num3, 4.0) + num10 * Math.log(num3);

        return Math.exp(num2); // Return the vapor pressure
    }

    private double XFS(double atm, double T) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        double[] array = new double[11];
        double[] array2 = new double[11];
        double num6 = 0.0;
        double num7 = 0.0;
        double num8 = 0.0;
        double num9 = 0.0;
        double num10 = 0.0;
        double num11 = 0.0;
        double num12 = 0.0;
        double num13 = 0.0;
        double num14 = 0.0;
        double num15 = 0.0;
        double num16 = 0.0;
        double num17 = 0.0;
        double num18 = 0.0;
        double num19 = 0.0;
        double num20 = 0.0;
        double num21 = 0.0;
        double num22 = 0.0;
        double num23 = 0.0;
        double num24 = 0.0;
        double num25 = 0.0;
        double num26 = 0.0;
        double num27 = 0.0;
        double num28 = 0.0;
        double num29 = 0.0;
        double num30 = 0.0;
        double num31 = 0.0;
        double num32 = 0.0;
        double num33 = 0.0;
        double num34 = 0.0;
        double num35 = 0.0;
        double num36 = 0.0;
        double num37 = 0.0;
        double num38 = 0.0;
        int num39;

        num2 = 1.9578E-08 * Math.pow(T, 4.0) - 6.1977E-06 * Math.pow(T, 3.0) + 0.0010557 * Math.pow(T, 2.0) - 0.069597 * T + 1.5286;
        if (atm < 0.98 * num2) {
            return 1.0;
        }

        num4 = atm * 6894.8;
        num3 = (T - 32.0) * 5.0 / 9.0 + 273.67;
        if (num3 < 173.0) {
            num3 = 173.0;
        }

        num5 = XPWS(T) * 6894.8; // Assume XPWS is defined elsewhere
        num8 = 34.9568 - 6687.72 / num3 - 2101410.0 / Math.pow(num3, 2.0) + 92474600.0 / Math.pow(num3, 3.0);
        num7 = 1259.75 - 190905.0 / num3 + 63246700.0 / Math.pow(num3, 2.0);
        num9 = 8314410.0;
        num6 = 7E-09 - 1.47184E-09 * Math.exp(1734.29 / num3);
        num10 = 1.04E-15 - 3.35297E-18 * Math.exp(3645.09 / num3);
        num13 = num9 * num3 * num6;
        num14 = num9 * num9 * Math.pow(num3, 2.0) * (num10 + num6 * num6);
        num12 = 32.366097 - 14113.8 / num3 - 1244535.0 / Math.pow(num3, 2.0) - 2348789000.0 / Math.pow(num3, 4.0);
        num15 = 483.737 + 105678.0 / num3 - 65639400.0 / Math.pow(num3, 2.0) + 29444200000.0 / Math.pow(num3, 3.0) - 3193170000000.0 / Math.pow(num3, 4.0);
        num11 = -1000000.0 * Math.exp(-10.728876 + 3478.02 / num3 - 383383.0 / Math.pow(num3, 2.0) + 33406000.0 / Math.pow(num3, 3.0));

        // Initialize coefficients based on temperature
        if (273.16 < num3 && num3 < 373.16) {
            array[0] = 50.88496;
            array[1] = 0.6163813;
            array[2] = 0.001459187;
            array[3] = 2.008438E-05;
            array[4] = -0.5847727E-7;
            array[5] = 0.410411E-9;
            array[6] = 0.01967348;
        }

        if (num3 > 373.16) {
            array[0] = 50.884917;
            array[1] = 0.62590623;
            array[2] = 0.0013848668;
            array[3] = 0.21603427E-4;
            array[4] = -0.72087667E-7;
            array[5] = 0.46545054E-9;
            array[6] = 0.019859983;
        }

        if (array[0] > 0.0) {
            num = array[0];
            for (num39 = 1; num39 <= 5; num39++) {
                num += array[num39] * Math.pow(num3, num39);
            }
            num16 = num / (1.0 + array[6] * num3) * 1E-11;
        }

        num31 = 0.0;
        if (num3 > 273.16) {
              num19 = -0.0005943;
              num18 = -0.147;
             num20 = -0.0512;
             num17 = -0.1076;
             num21 = 0.8447;
             num23 = num19;
             num22 = num20 * 1000.0 / num3 + num17;
             num24 = num18 * Math.pow(1000.0 / num3, 2.0) + num21 * 1000.0 / num3 - 1.0;
             num26 = (-num22 + Math.sqrt(Math.pow(num22, 2.0) - 4.0 * num23 * num24)) / (2.0 * num23);
             num25 = num26 * Math.log(10.0);
             num27 = Math.exp(num25);
            num19 = -0.1021;
            num18 = -0.1482;
            num20 = -0.019;
            num17 = -0.03741;
            num21 = 0.851;
            num23 = num19;
            num22 = num20 * 1000.0 / num3 + num17;
            num24 = num18 * Math.pow(1000.0 / num3, 2.0) + num21 * 1000.0 / num3 - 1.0;
             num29 = (-num22 + Math.sqrt(Math.pow(num22, 2.0) - 4.0 * num23 * num24)) / (2.0 * num23);
             num28 = num29 * Math.log(10.0);
             num30 = Math.exp(num28);
            num32 = 1.0 / (0.22 / num27 + 0.78 / num30);
            num31 = 0.0001 / num32 / 101325.0;
        }

        if (num3 < 273.16) {
            num33 = 0.001070003 - 2.49936E-08 * num3 + 3.71611E-10 * Math.pow(num3, 2.0);
        } else {
            array2[0] = -2403.60201;
            array2[1] = -1.40758895;
            array2[2] = 0.1068287657;
            array2[3] = -0.0002914492351;
            array2[4] = 0.373497936E-6;
            array2[5] = -0.21203787E-9;
            array2[6] = -3.424442728;
            array2[7] = 0.01619785;
            num33 = array2[0];
            for (num39 = 1; num39 <= 5; num39++) {
                num33 += array2[num39] * Math.pow(num3, num39);
            }
            num33 = (array2[6] + array2[7] * num3) / num33;
        }

        num33 *= 18015.28;
        num34 = num9 * num9 * Math.pow(num3, 2.0);
        num36 = 1.0;
        num39 = 0;

        // Iterative calculation for the final value
        do {
            num37 = num36;
             num35 = (num4 - num36 * num5) / num4;
            num38 = ((1.0 + num16 * num5) * (num4 - num5) - 0.5 * num16 * (Math.pow(num4, 2.0) - Math.pow(num5, 2.0))) * num33 * num9 * num3;
            num38 += Math.log(1.0 - num31 * num35 * num4) * num34;
            num38 += Math.pow(num35, 2.0) * num4 * num8 * num9 * num3;
            num38 -= 2.0 * Math.pow(num35, 2.0) * num4 * num12 * num9 * num3;
            num38 -= (num4 - num5 - num35 * num35 * num4) * num13 * num9 * num3;
            num38 += Math.pow(num35, 3.0) * Math.pow(num4, 2.0) * num7;
            num38 += 3.0 * Math.pow(num35, 2.0) * (1.0 - 2.0 * num35) * Math.pow(num4, 2.0) * 0.5 * num15;
            num38 -= 3.0 * Math.pow(num35, 2.0) * (1.0 - num35) * Math.pow(num4, 2.0) * num11;
            num38 -= ((1.0 + 2.0 * num35) * Math.pow(1.0 - num35, 2.0) * Math.pow(num4, 2.0) - Math.pow(num5, 2.0)) * 0.5 * num14;
            num38 -= num35 * num35 * (1.0 - 3.0 * num35) * (1.0 - num35) * Math.pow(num4, 2.0) * num8 * num13;
            num38 -= 2.0 * Math.pow(num35, 3.0) * (2.0 - 3.0 * num35) * Math.pow(num4, 2.0) * num8 * num12;
            num38 += 6.0 * Math.pow(num35, 2.0) * Math.pow(1.0 - num35, 2.0) * Math.pow(num4, 2.0) * num13 * num12;
            num38 -= 3.0 * Math.pow(num35, 4.0) * Math.pow(num4, 2.0) * 0.5 * Math.pow(num8, 2.0);
            num38 -= 2.0 * Math.pow(num35, 2.0) * (1.0 - num35) * (1.0 - 3.0 * num35) * Math.pow(num4, 2.0) * Math.pow(num12, 2.0);
            num38 -= (Math.pow(num5, 2.0) - (1.0 + 3.0 * num35) * Math.pow(1.0 - num35, 3.0) * Math.pow(num4, 2.0)) * 0.5 * Math.pow(num13, 2.0);
            num38 = num38 / (num9 * num9 * Math.pow(num3, 2.0));
            num36 = Math.exp(num38);
        } while (Math.abs(num37 - num36) >= 1E-06);

        return (num36 > 1.0) ? num36 : 1.0;
    }


    public double LCSI_PWETBULB(double tempCdb, double grams, double kPa) {
        // Convert temperature from Celsius to Fahrenheit
        double fahrenheit = tempCdb * 9.0 / 5.0 + 32.0;

        // Adjust the grams value
        double adjustedGrams = grams * 7.0;

        // Convert kPa to psi
        double pressureInPsi = kPa / 6.8948;

        // Call the LCPWETBULB function and convert the result back to Celsius
        return (LCPWETBULB(fahrenheit, adjustedGrams, pressureInPsi) - 32.0) * 5.0 / 9.0;
    }
    public double LCSI_RH(double tempCdb, double grams, double meters) {
        // Convert temperature from Celsius to Fahrenheit
        double fahrenheit = tempCdb * 9.0 / 5.0 + 32.0;

        // Adjust the grams value
        double adjustedGrams = grams * 7.0;

        // Convert meters to inches
        double heightInInches = meters * 39.37 / 12.0;

        // Call the LCRH function with the converted values
        return LCRH(fahrenheit, adjustedGrams, heightInInches);
    }
    public double LCRH(double tempFdb, double grains, double feet) {
        double result = 0.0;

        // Check for valid input ranges
        if (tempFdb < -80.0 || tempFdb > 1500.0 || grains < 0.0 || grains > 16300.0) {
            return -9999.0; // Invalid input
        }

        // Calculate intermediate values
        double pressureAdjustment = (1.3598E-08 * Math.pow(feet, 2.0) - 0.0010717 * feet + 29.921) / 2.036;
        double vaporPressure = XPWSAPP(pressureAdjustment, tempFdb);

        // Calculate the relative humidity
        double humidityRatio = 0.62198 * vaporPressure * 7000.0 / (pressureAdjustment - vaporPressure);
        double ratioCorrection = vaporPressure / pressureAdjustment;
        double grainRatio = grains / humidityRatio;

        // Final relative humidity calculation
        result = grainRatio / (1.0 - (1.0 - grainRatio) * ratioCorrection) * 100.0;

        // Check for valid humidity value
        if (result > 100.0) {
            return -9999.0; // Invalid humidity
        }

        return result;
    }

    public double LCSI_PRH(double Temp_Cdb, double grams, double kPa)
    {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        num = Temp_Cdb * 9.0 / 5.0 + 32.0;
        num2 = grams * 7.0;
        num3 = kPa / 6.8948;
        return LCPRH(num, num2, num3);
    }
    public double LCPRH(double Temp_Fdb, double grains, double psia)
    {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        if (Temp_Fdb < -80.0 || Temp_Fdb > 1500.0 || grains < 0.0 || grains > 16300.0 || psia < 4.0 || psia > 400.0)
        {
            num2 = -9999.0;
        }
        else
        {
            num5 = XPWSAPP(psia, Temp_Fdb);
            num = 0.62198 * num5 * 7000.0 / (psia - num5);
            num4 = num5 / psia;
            num3 = grains / num;
            num2 = num3 / (1.0 - (1.0 - num3) * num4) * 100.0;
            if (num2 > 100.0)
            {
                num2 = -9999.0;
            }
        }

        return num2;
    }
}
