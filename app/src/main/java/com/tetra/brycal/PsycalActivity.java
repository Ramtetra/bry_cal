package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tetra.brycal.databinding.ActivityPsycalBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PsycalActivity extends AppCompatActivity {
 ActivityPsycalBinding binding;
    PsyCal psyCal;
    double num9,num10;
    boolean checkedValue=false;
    double firstValue,secondValue,thirdValue,fourthValue,fiveValue,sixValue;

    boolean isUpdatingSecond = false;
    boolean isUpdatingThird = false;
    boolean isUpdatingFourth = false;
    boolean isUpdatingFive = false;
    boolean isUpdatingSix = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_psycal);
        binding=ActivityPsycalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txt1.setText("°C db");
        binding.txt2.setText("°C wb");
        binding.txt3.setText("% RH");
        binding.txt4.setText("kJ/kg");
        binding.txt5.setText("°C dp");
        binding.txt6.setText("g/kg");
        psyCal=new PsyCal();
        binding.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PsycalActivity.this, PsycalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PsycalActivity.this, MixedAirActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(PsycalActivity.this, UnitConverterActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(PsycalActivity.this, DuctulatorActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(PsycalActivity.this, Co2Activity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(PsycalActivity.this, WeatherActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.switchBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkedValue = binding.switchBtn.isChecked();
                binding.txt1.setText("°F db");
                binding.txt2.setText("°F wb");
                binding.txt3.setText("% RH");
                binding.txt4.setText("Btu/lb");
                binding.txt5.setText("°F dp");
                binding.txt6.setText("Gr/lb");
                binding.et1.setText("");
                binding.et2.setText("");
                binding.et3.setText("");
                binding.et4.setText("");
                binding.et5.setText("");
                binding.et6.setText("");
                } else {
                checkedValue = binding.switchBtn.isChecked();
                binding.txt1.setText("°C db");
                binding.txt2.setText("°C wb");
                binding.txt3.setText("% RH");
                binding.txt4.setText("kJ/kg");
                binding.txt5.setText("°C dp");
                binding.txt6.setText("g/kg");
                binding.et1.setText("");
                binding.et2.setText("");
                binding.et3.setText("");
                binding.et4.setText("");
                binding.et5.setText("");
                binding.et6.setText("");

            }
        });

          binding.et2.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 try {
                     if (!isUpdatingSecond) {
                         isUpdatingSecond = true; // Prevent recursion
                         double firstValue = Double.parseDouble(binding.et1.getText().toString());
                         double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                         Double secondValue = Double.parseDouble(s.toString());

                     if (!s.toString().isEmpty() && checkedValue==false) {
                              Double rhValues=LCSI_RH(firstValue, secondValue, altitude);
                              BigDecimal num1 = new BigDecimal(rhValues);
                              BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                              isUpdatingThird = true;
                              binding.et3.setText(String.valueOf(roundedRHValue));
                             isUpdatingThird = false; // Reset flag
                              Double kgValue=LCSI_RHTOGRAMS(firstValue,rhValues,altitude);
                              isUpdatingFourth=true;
                              binding.et4.setText(String.valueOf(kgValue));
                              isUpdatingFourth=false;
                              Double dpValue=LCSI_DEWPOINT(firstValue,kgValue);
                              BigDecimal num2 = new BigDecimal(dpValue);
                              BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                              isUpdatingFive=true;
                              binding.et5.setText(String.valueOf(roundedDpValue));
                             isUpdatingFive=false;
                              Double gkgValue=LCSI_WBTOGRAMS(firstValue,dpValue,altitude);
                              isUpdatingSix=true;
                              binding.et6.setText(String.valueOf(gkgValue));
                              isUpdatingSix=false;
                          }else {
                         Double rhValues = LCRH(firstValue, secondValue, altitude);
                         isUpdatingThird = true;
                         binding.et3.setText(String.valueOf(rhValues));
                         isUpdatingThird = false;
                         Double btuValue = LCRHTOGRAINS(firstValue, rhValues, altitude);
                         BigDecimal num3 = new BigDecimal(btuValue);
                         BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                         isUpdatingFourth=true;
                         binding.et4.setText(String.valueOf(roundedBtuValue));
                         isUpdatingFourth=false;
                         Double dpValue = LCDEWPOINT(firstValue, btuValue);
                         BigDecimal num4 = new BigDecimal(dpValue);
                         BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                         isUpdatingFive=true;
                         binding.et5.setText(String.valueOf(roundedDpValue));
                         isUpdatingFive=false;
                         Double lbValue = LCWBTOGRAINS(firstValue, secondValue, dpValue);
                         BigDecimal num5 = new BigDecimal(lbValue);
                         BigDecimal roundedLBValue = num5.setScale(1, RoundingMode.HALF_UP);
                         isUpdatingSix=true;
                         binding.et6.setText(String.valueOf(roundedLBValue));
                         isUpdatingSix=false;

                     }
                         isUpdatingSecond=false; // Reset flag
                     }
                 } catch (NumberFormatException e) {
                     isUpdatingSecond = false; // Reset flag in case of exception

                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

          binding.et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!isUpdatingThird) {
                        isUpdatingThird = true; // Prevent recursion
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        Double thirdValue = Double.parseDouble(s.toString());

                        if (!s.toString().isEmpty() && checkedValue == false) {
                            Double rhValues = LCSI_RH(firstValue, thirdValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(kgValue));
                            isUpdatingFourth = false;
                            Double dpValue = LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(gkgValue));
                            isUpdatingSix = false;
                        } else {
                            Double rhValues = LCRH(firstValue, thirdValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(rhValues));
                            isUpdatingSecond = false;
                            Double btuValue = LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(roundedBtuValue));
                            isUpdatingFourth = false;
                            Double dpValue = LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = LCWBTOGRAINS(firstValue, secondValue, dpValue);
                            BigDecimal num5 = new BigDecimal(lbValue);
                            BigDecimal roundedLBValue = num5.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(roundedLBValue));
                            isUpdatingSix = false;
                        }
                        isUpdatingThird = false; // Reset the flag
                    }
                } catch (NumberFormatException e) {
                    isUpdatingThird = false; // Reset the flag
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

          binding.et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!isUpdatingFourth) {
                        isUpdatingFourth = true; // Prevent recursion
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        Double fourthValue = Double.parseDouble(s.toString());

                        if (!s.toString().isEmpty() && checkedValue == false) {
                            Double rhValues = LCSI_RH(firstValue, fourthValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(kgValue));
                            isUpdatingThird = false;
                            Double dpValue = LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(gkgValue));
                            isUpdatingSix = false;
                        } else {
                            Double thirdValue = LCRH(firstValue, fourthValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(thirdValue));
                            isUpdatingSecond = false;
                            Double btuValue = LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(roundedBtuValue));
                            isUpdatingThird = false;
                            Double dpValue = LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = LCWBTOGRAINS(firstValue, secondValue, dpValue);
                            BigDecimal num5 = new BigDecimal(lbValue);
                            BigDecimal roundedLBValue = num5.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(roundedLBValue));
                            isUpdatingSix = false;

                        }
                        isUpdatingFourth = false; // Reset the flag
                    }
                } catch (NumberFormatException e) {
                    isUpdatingFourth = false; // Reset the flag
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try { if (!isUpdatingFive) {
                    isUpdatingFive = true; // Prevent recursion
                    double firstValue = Double.parseDouble(binding.et1.getText().toString());
                    double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                    Double fiveValue = Double.parseDouble(s.toString());
                    if (!s.toString().isEmpty() && checkedValue == false) {

                        Double rhValues = LCSI_RH(firstValue, fiveValue, altitude);
                        BigDecimal num1 = new BigDecimal(rhValues);
                        BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingSecond = true;
                        binding.et2.setText(String.valueOf(roundedRHValue));
                        isUpdatingSecond = false;
                        Double kgValue = LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                        isUpdatingThird = true;
                        binding.et3.setText(String.valueOf(kgValue));
                        isUpdatingThird = false;
                        Double dpValue = LCSI_DEWPOINT(firstValue, kgValue);
                        BigDecimal num2 = new BigDecimal(dpValue);
                        BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        isUpdatingFourth = true;
                        binding.et4.setText(String.valueOf(roundedDpValue));
                        isUpdatingFourth = false;
                        Double gkgValue = LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                        isUpdatingSix = true;
                        binding.et6.setText(String.valueOf(gkgValue));
                        isUpdatingSix = false;
                    } else {
                        Double thirdValue = LCRH(firstValue, fiveValue, altitude);
                        //BigDecimal num1 = new BigDecimal(rhValues);
                        //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingSecond = true;
                        binding.et2.setText(String.valueOf(thirdValue));
                        isUpdatingSecond = false;
                        Double btuValue = LCRHTOGRAINS(firstValue, thirdValue, altitude);
                        BigDecimal num3 = new BigDecimal(btuValue);
                        BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingThird = true;
                        binding.et3.setText(String.valueOf(roundedBtuValue));
                        isUpdatingThird = false;
                        Double dpValue = LCDEWPOINT(firstValue, btuValue);
                        BigDecimal num4 = new BigDecimal(dpValue);
                        BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingFourth = true;
                        binding.et4.setText(String.valueOf(roundedDpValue));
                        isUpdatingFourth = false;
                        Double lbValue = LCWBTOGRAINS(firstValue, secondValue, dpValue);
                        BigDecimal num5 = new BigDecimal(lbValue);
                        BigDecimal roundedLBValue = num5.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingSix = true;
                        binding.et6.setText(String.valueOf(roundedLBValue));
                        isUpdatingSix = false;

                    }
                    isUpdatingFive = false; // Reset the flag
                }
                } catch (NumberFormatException e) {
                    isUpdatingFive = false; // Reset the flag
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!isUpdatingSix) {
                        isUpdatingSix = true; // Prevent recursion
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        Double sixTValue = Double.parseDouble(s.toString());
                        if (!s.toString().isEmpty() && checkedValue == false) {
                            Double rhValues = LCSI_RH(firstValue, sixTValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(kgValue));
                            isUpdatingThird = false;
                            Double dpValue = LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(gkgValue));
                            isUpdatingFourth = false;

                        } else {
                            Double thirdValue = LCRH(firstValue, fourthValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(thirdValue));
                            isUpdatingSecond = false;
                            Double btuValue = LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(roundedBtuValue));
                            isUpdatingThird = false;
                            Double dpValue = LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = LCWBTOGRAINS(firstValue, secondValue, dpValue);
                            BigDecimal num5 = new BigDecimal(lbValue);
                            BigDecimal roundedLBValue = num5.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(roundedLBValue));
                            isUpdatingFourth = false;

                        }
                        isUpdatingSix = false;  // Reset the flag
                    }
                } catch (NumberFormatException e) {
                    isUpdatingSix=false;  // Reset the flag
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    public double LCDEWPOINT(double grains, double feet) {
        // Validate the grain input
        if (grains < 0.0 || grains > 16300.0) {
            return -9999.0; // Return an error code for invalid grains
        }

        // Calculate the pressure based on feet
        double moistureRatio = grains / 7000.0;
        double pressure = (1.3598E-08 * Math.pow(feet, 2) - 0.0010717 * feet + 29.921) / 2.036;

        // Calculate the dew point
        double dewPoint = pressure * moistureRatio / (0.62198 + moistureRatio);
        return dewPoint; // Return the calculated dew point
    }

    public double LCSI_DEWPOINT(double grams, double meters) {
        double num = 0.0;
        double num2 = grams * 7.0;
        double num5 = meters * 39.37 / 12.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num6 = 0.0;
        double resValue4=0.0;
        if (num2 < 0.0 || num2 > 16300.0) {
            num4 = -9999.0;
        } else {
            num3 = num2 / 7000.0;
            num = (1.3598E-08 * Math.pow(num5, 2.0) - 0.0010717 * num5 + 29.921) / 2.036;
            num6 = num * num3 / (0.62198 + num3);
            //num4 = XTDPAPP(num, num6);  // Ensure XTDPAPP returns a valid value
        }
        double result = (num4*num6 - 32.0) * (5.0 / 9.0);
        return result;  // Convert Fahrenheit to Celsius
    }
    private double XTDPAPP(double atm, double pwsapp) {
        double num = 0.0;
        double num2 = pwsapp;
        double num3 = XTDP(num2); // Assuming XTDP is defined elsewhere
        double num5 = XFS(atm, num3); // Assuming XFS is defined elsewhere
        double num6 = 1.0;
        double num4= 0.0;

        // Check if pwsapp is less than 0
        if (pwsapp < 0.0) {
            return -9999.0;
        }

        // Log initial values
        System.out.println("Initial pwsapp: " + pwsapp + ", atm: " + atm);

        // While loop to adjust the values
        while (num6 >= 1E-06) {
            num2 = pwsapp / num5;
            num3 = XTDP(num2);
            num5 = XFS(atm, num3);
            num4 = pwsapp / num5;

            // Ensure num7 is set to the max of num2 and 1.0
            double num7 = Math.max(num2, 1.0);
            num6 = Math.abs((num2 - num4) / num7);

            // Log values in each iteration
            System.out.println("num2: " + num2 + ", num4: " + num4 + ", num6: " + num6);

            num2 = num4; // Update for next iteration
        }

        return num3;
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
            num10 = (pws >= 0.08865)
                    ? (79.047 + 30.579 * num9 + 1.8893 * Math.pow(num9, 2.0))
                    : (71.98 + 24.873 * num9 + 0.87 * Math.pow(num9, 2.0));

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

        num3 = temp + 459.67;

        if (temp < 32.018) {
            num4 = -10214.165;
            num5 = -4.8932428;
            num6 = -0.0053765794;
            num7 = 1.9202377E-07;
            num8 = 3.5575832E-10;
            num9 = -9.0344688E-14;
            num10 = 4.1635019;
        } else {
            num4 = -10440.397;
            num5 = -11.29465;
            num6 = -0.027022355;
            num7 = 1.289036E-05;
            num8 = -2.4780681E-09;
            num9 = 0.0;
            num10 = 6.5459673;
        }

        num2 = num4 / num3 + num5 + num6 * num3 + num7 * num3 * num3 + num8 * Math.pow(num3, 3.0);
        num2 = num2 + num9 * Math.pow(num3, 4.0) + num10 * Math.log(num3);

        return Math.exp(num2);
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
        int num39 = 0;

        num2 = 1.9578E-08 * Math.pow(T, 4.0) - 6.1977E-06 * Math.pow(T, 3.0) + 0.0010557 * Math.pow(T, 2.0) - 0.069597 * T + 1.5286;

        if (atm < 0.98 * num2) {
            return 1.0;
        }

        num4 = atm * 6894.8;
        num3 = (T - 32.0) * 5.0 / 9.0 + 273.67;

        if (num3 < 173.0) {
            num3 = 173.0;
        }

        num5 = XPWS(T) * 6894.8;
        num8 = 34.9568 - 6687.72 / num3 - 2101410.0 / (num3 * num3) + 92474600.0 / Math.pow(num3, 3.0);
        num7 = 1259.75 - 190905.0 / num3 + 63246700.0 / (num3 * num3);
        num9 = 8314410.0;
        num6 = 7E-09 - 1.47184E-09 * Math.exp(1734.29 / num3);
        num10 = 1.04E-15 - 3.3529699999999998E-18 * Math.exp(3645.09 / num3);
        num13 = num9 * num3 * num6;
        num14 = num9 * num9 * num3 * num3 * (num10 + num6 * num6);
        num12 = 32.366097 - 14113.8 / num3 - 1244535.0 / (num3 * num3) - 2348789000.0 / Math.pow(num3, 4.0);
        num15 = 483.737 + 105678.0 / num3 - 65639400.0 / (num3 * num3) + 29444200000.0 / Math.pow(num3, 3.0) - 3193170000000.0 / Math.pow(num3, 4.0);
        num11 = -1000000.0 * Math.exp(-10.728876 + 3478.02 / num3 - 383383.0 / (num3 * num3) + 33406000.0 / Math.pow(num3, 3.0));
        array[0] = 0.0;

        num16 = (8.875 + 0.0165 * num3) * Math.pow(10.0, -11.0);

        if (273.16 < num3 && num3 < 373.16) {
            array[0] = 50.88496;
            array[1] = 0.6163813;
            array[2] = 0.001459187;
            array[3] = 2.008438E-05;
            array[4] = -0.5847727 * Math.pow(10.0, -7.0);
            array[5] = 0.410411 * Math.pow(10.0, -9.0);
            array[6] = 0.01967348;
        }

        if (373.16 < num3) {
            array[0] = 50.884917;
            array[1] = 0.62590623;
            array[2] = 0.0013848668;
            array[3] = 0.21603427 * Math.pow(10.0, -4.0);
            array[4] = -0.72087667 * Math.pow(10.0, -7.0);
            array[5] = 0.46545054 * Math.pow(10.0, -9.0);
            array[6] = 0.019859983;
        }

        if (array[0] > 0.0) {
            num = array[0];
            for (num39 = 1; num39 <= 5; num39++) {
                num += array[num39] * Math.pow(num3, num39);
            }
            num16 = num / (1.0 + array[6] * num3) * Math.pow(10.0, -11.0);
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
            num33 = 0.001070003 - 2.49936E-08 * num3 + 3.71611E-10 * num3 * num3;
        } else {
            array2[0] = -2403.60201;
            array2[1] = -1.40758895;
            array2[2] = 0.1068287657;
            array2[3] = -0.0002914492351;
            array2[4] = 0.373497936 * Math.pow(10.0, -6.0);
            array2[5] = -0.21203787 * Math.pow(10.0, -9.0);
            array2[6] = 0.424038397 * Math.pow(10.0, -13.0);
            num33 = array2[0];
            for (num39 = 1; num39 <= 5; num39++) {
                num33 += array2[num39] * Math.pow(num3, num39);
            }
        }

        num34 = 0.0001 / num33 / 101325.0;
        num35 = (num4 - num5) / 101325.0;
        num36 = Math.pow(num13 + num14 / num35 + num16 * num35, -1.0);
        num37 = (num12 + num11 / num35) * num36;
        num38 = num31 + num34 + num37;

        return num38;
    }
    public double LCSI_RH(double Temp_Cdb, double grams, double meters) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;

        // Convert Celsius to Fahrenheit
        num = Temp_Cdb * 9.0 / 5.0 + 32.0;

        // Convert grams to the required value (multiplied by 7)
        num2 = grams * 7.0;

        // Convert meters to feet (1 meter = 39.37 inches, 1 foot = 12 inches)
        num3 = meters * 39.37 / 12.0;

        // Assuming LCRH method is defined elsewhere
        return num3;
    }
    public double LCRH(double Temp_Fdb, double grains, double feet) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        double num6 = 0.0;

        // Validate input range
        if (Temp_Fdb < -80.0 || Temp_Fdb > 1500.0 || grains < 0.0 || grains > 16300.0) {
            num5 = -9999.0; // Error case
        } else {
            // Calculate pressure in psi (from feet)
            num2 = (1.3598E-08 * Math.pow(feet, 2.0) - 0.0010717 * feet + 29.921) / 2.036;

            // Calculate the saturated vapor pressure at the given temperature (using XPWSAPP)
            num6 = XPWSAPP(num2, Temp_Fdb);

            // Calculate humidity ratio (num)
            num = 0.62198 * num6 * 7000.0 / (num2 - num6);

            // Calculate relative humidity components
            num4 = num6 / num2;
            num3 = grains / num;

            // Final relative humidity calculation
            num5 = num3 / (1.0 - (1.0 - num3) * num4) * 100.0;
            // Ensure relative humidity is not over 100%
            if (num5 > 100.0) {
                num5 = -9999.0; // Error case
            }
        }

        return num5;
    }
    private double XPWSAPP(double atm, double Tf) {
        double num = 0.0;
        // Calculate the adjustment factor using XFS
        num = XFS(atm, Tf);
        // Return the adjusted saturated vapor pressure by multiplying the result with XPWS
        return num * XPWS(Tf);
    }
    public double LCSI_RHTOGRAMS(double Temp_Cdb, double RH, double meters) {
        double num = 0.0;
        double num2 = 0.0;

        // Convert temperature from Celsius to Fahrenheit
        num = Temp_Cdb * 9.0 / 5.0 + 32.0;
        // Convert meters to feet
        num2 = meters * 39.37 / 12.0;

        // Check if inputs are within valid ranges
        if (num < -80.0 || num > 1500.0 || RH < 0.0 || RH > 1.0) {
            return -9999.0; // Return error code for invalid input
        }
        // Call LCRHTOGRAINS method and convert grains to grams
        return num*RH*num2/7.0;
    }

    public double LCRHTOGRAINS(double Temp_Fdb, double RH, double ft) {
        double num = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;

        // Check if inputs are within valid ranges
        if (Temp_Fdb < -80.0 || Temp_Fdb > 1500.0 || RH < 0.0 || RH > 1.0) {
            return -9999.0; // Return error code for invalid input
        }

        // Calculate vapor pressure using elevation (ft)
        num = (1.3598E-08 * Math.pow(ft, 2.0) - 0.0010717 * ft + 29.921) / 2.036;
        // Calculate partial vapor pressure
        num4 = XPWSAPP(num, Temp_Fdb);
        // Calculate moisture content in grains
        num3 = num4 * RH;

        // Return moisture content in grains
        return 0.62198 * num3 * 7000.0 / (num - num3);
    }

    public double LCWETBULB(double Temp_Fdb, double grains, double feet) {
        double num = 0.0;

        // Calculate saturation vapor pressure using elevation (feet)
        num = (1.3598E-08 * Math.pow(feet, 2.0) - 0.0010717 * feet + 29.921) / 2.036;

        // Call LCPWETBULB to calculate the wet bulb temperature
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

        // Initial step of 10.0
        do {
            num7 -= 10.0;
            double num8 = XPWSAPP(num, num7);
            double num5 = 0.62198 * num8 / (num - num8);
            double num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);
        } while (!(num9 < num2));

        num7 += 10.0;

        // Fine-tuning step of 1.0
        do {
            num7 -= 1.0;
            double num8 = XPWSAPP(num, num7);
            double num5 = 0.62198 * num8 / (num - num8);
            double num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);
        } while (!(num9 < num2));

        num7 += 1.0;
        double num8 = XPWSAPP(num, num7);
        double num5 = 0.62198 * num8 / (num - num8);
        double num9 = ((1093.0 - 0.556 * num7) * num5 - 0.24 * (Temp_Fdb - num7)) / (1093.0 + 0.444 * Temp_Fdb - num7);

        double num11 = 0.1;
        while (true) {
            double num10 = num7 + num11;
            num8 = XPWSAPP(num, num10);
            num5 = 0.62198 * num8 / (num - num8);
            double num12 = ((1093.0 - 0.556 * num10) * num5 - 0.24 * (Temp_Fdb - num10)) / (1093.0 + 0.444 * Temp_Fdb - num10);

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
    public double LCSI_WETBULB(double Temp_Cdb, double grams, double meters) {
        double num = Temp_Cdb * 9.0 / 5.0 + 32.0; // Convert Celsius to Fahrenheit
        double num2 = grams * 7.0; // Convert grams to grains
        double num3 = meters * 39.37 / 12.0; // Convert meters to feet
        return (LCWETBULB(num, num2, num3) - 32.0) * 5.0 / 9.0; // Convert back to Celsius
    }
    public double LCWBTOGRAINS(double Temp_Fdb, double Temp_Fwb, double feet) {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;

        // Validate input parameters
        if (feet < -500.0 || feet > 30000.0 || Temp_Fdb < -80.0 || Temp_Fdb > 1500.0 || Temp_Fwb > Temp_Fdb) {
            return -9999.0; // Return an error code for invalid inputs
        }

        // Calculate the saturation grains at wet bulb temperature
        num2 = LCSATGRAINS(Temp_Fwb, feet) / 7000.0; // Convert grains to pounds

        // Calculate the moisture content based on the wet bulb and dry bulb temperatures
        num3 = ((1093.0 - 0.556 * Temp_Fwb) * num2 - 0.24 * (Temp_Fdb - Temp_Fwb)) / (1093.0 + 0.444 * Temp_Fdb - Temp_Fwb);

        // Check if the calculated moisture content is valid
        if (num3 <= 0.0) {
            return -9999.0; // Return an error code for non-positive moisture content
        }

        return num3 * 7000.0; // Convert back to grains and return
    }
    public double LCSATGRAINS(double Temp_Fdb, double feet) {
        // Validate the temperature input
        if (Temp_Fdb < -80.0 || Temp_Fdb > 200.0) {
            return -9999.0; // Return an error code for invalid temperature
        }

        // Calculate pressure based on height in feet
        double pressure = (1.3598E-08 * Math.pow(feet, 2) - 0.0010717 * feet + 29.921) / 2.036;
        double vaporPressure = XPWSAPP(pressure, Temp_Fdb); // Calculate the water vapor pressure

        // Determine saturation moisture content
        if (!(vaporPressure < pressure && Temp_Fdb <= 200.0)) {
            return 20000.0;
        } else {
            return (0.62198 * vaporPressure * 7000.0) / (pressure - vaporPressure);
        }
    }

    public double LCSI_WBTOGRAMS(double Temp_Cdb, double Temp_Cwb, double meters)
    {
        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;
        double num6 = 0.0;
        num = Temp_Cwb * 9.0 / 5.0 + 32.0;
        num2 = Temp_Cdb * 9.0 / 5.0 + 32.0;
        num6 = meters * 39.37 / 12.0;
        if (Temp_Cdb < Temp_Cwb)
        {
            return -9999.0;
        }

        num4 = LCSATGRAINS(num, num6) / 7000.0;
        num5 = ((1093.0 - 0.556 * num) * num4 - 0.24 * (num2 - num)) / (1093.0 + 0.444 * num2 - num);
        if (num5 <= 0.0)
        {
            return -9999.0;
        }

        return num5 * 7000.0 / 7.0;
    }
}