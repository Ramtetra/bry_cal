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
                              Double rhValues=psyCal.LCSI_RH(firstValue, secondValue, altitude);
                              BigDecimal num1 = new BigDecimal(rhValues);
                              BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                              isUpdatingThird = true;
                              binding.et3.setText(String.valueOf(roundedRHValue));
                             isUpdatingThird = false; // Reset flag
                              Double kgValue=psyCal.LCSI_RHTOGRAMS(firstValue,rhValues,altitude);
                              isUpdatingFourth=true;
                              binding.et4.setText(String.valueOf(kgValue));
                              isUpdatingFourth=false;
                              Double dpValue=psyCal.LCSI_DEWPOINT(firstValue,kgValue);
                              BigDecimal num2 = new BigDecimal(dpValue);
                              BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                              isUpdatingFive=true;
                              binding.et5.setText(String.valueOf(roundedDpValue));
                             isUpdatingFive=false;
                              Double gkgValue=psyCal.LCSI_WBTOGRAMS(firstValue,dpValue,altitude);
                              isUpdatingSix=true;
                              binding.et6.setText(String.valueOf(gkgValue));
                              isUpdatingSix=false;
                          }else {
                         Double rhValues = psyCal.LCRH(firstValue, secondValue, altitude);
                         isUpdatingThird = true;
                         binding.et3.setText(String.valueOf(rhValues));
                         isUpdatingThird = false;
                         Double btuValue = psyCal.LCRHTOGRAINS(firstValue, rhValues, altitude);
                         BigDecimal num3 = new BigDecimal(btuValue);
                         BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                         isUpdatingFourth=true;
                         binding.et4.setText(String.valueOf(roundedBtuValue));
                         isUpdatingFourth=false;
                         Double dpValue = psyCal.LCDEWPOINT(firstValue, btuValue);
                         BigDecimal num4 = new BigDecimal(dpValue);
                         BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                         isUpdatingFive=true;
                         binding.et5.setText(String.valueOf(roundedDpValue));
                         isUpdatingFive=false;
                         Double lbValue = psyCal.LCWBTOGRAINS(firstValue, secondValue, dpValue);
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
                            Double rhValues = psyCal.LCSI_RH(firstValue, thirdValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = psyCal.LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(kgValue));
                            isUpdatingFourth = false;
                            Double dpValue = psyCal.LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = psyCal.LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(gkgValue));
                            isUpdatingSix = false;
                        } else {
                            Double rhValues = psyCal.LCRH(firstValue, thirdValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(rhValues));
                            isUpdatingSecond = false;
                            Double btuValue = psyCal.LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(roundedBtuValue));
                            isUpdatingFourth = false;
                            Double dpValue = psyCal.LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = psyCal.LCWBTOGRAINS(firstValue, secondValue, dpValue);
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
                            Double rhValues = psyCal.LCSI_RH(firstValue, fourthValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = psyCal.LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(kgValue));
                            isUpdatingThird = false;
                            Double dpValue = psyCal.LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = psyCal.LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingSix = true;
                            binding.et6.setText(String.valueOf(gkgValue));
                            isUpdatingSix = false;
                        } else {
                            Double thirdValue = psyCal.LCRH(firstValue, fourthValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(thirdValue));
                            isUpdatingSecond = false;
                            Double btuValue = psyCal.LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(roundedBtuValue));
                            isUpdatingThird = false;
                            Double dpValue = psyCal.LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = psyCal.LCWBTOGRAINS(firstValue, secondValue, dpValue);
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

                        Double rhValues = psyCal.LCSI_RH(firstValue, fiveValue, altitude);
                        BigDecimal num1 = new BigDecimal(rhValues);
                        BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingSecond = true;
                        binding.et2.setText(String.valueOf(roundedRHValue));
                        isUpdatingSecond = false;
                        Double kgValue = psyCal.LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                        isUpdatingThird = true;
                        binding.et3.setText(String.valueOf(kgValue));
                        isUpdatingThird = false;
                        Double dpValue = psyCal.LCSI_DEWPOINT(firstValue, kgValue);
                        BigDecimal num2 = new BigDecimal(dpValue);
                        BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        isUpdatingFourth = true;
                        binding.et4.setText(String.valueOf(roundedDpValue));
                        isUpdatingFourth = false;
                        Double gkgValue = psyCal.LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                        isUpdatingSix = true;
                        binding.et6.setText(String.valueOf(gkgValue));
                        isUpdatingSix = false;
                    } else {
                        Double thirdValue = psyCal.LCRH(firstValue, fiveValue, altitude);
                        //BigDecimal num1 = new BigDecimal(rhValues);
                        //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingSecond = true;
                        binding.et2.setText(String.valueOf(thirdValue));
                        isUpdatingSecond = false;
                        Double btuValue = psyCal.LCRHTOGRAINS(firstValue, thirdValue, altitude);
                        BigDecimal num3 = new BigDecimal(btuValue);
                        BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingThird = true;
                        binding.et3.setText(String.valueOf(roundedBtuValue));
                        isUpdatingThird = false;
                        Double dpValue = psyCal.LCDEWPOINT(firstValue, btuValue);
                        BigDecimal num4 = new BigDecimal(dpValue);
                        BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                        isUpdatingFourth = true;
                        binding.et4.setText(String.valueOf(roundedDpValue));
                        isUpdatingFourth = false;
                        Double lbValue = psyCal.LCWBTOGRAINS(firstValue, secondValue, dpValue);
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
                            Double rhValues = psyCal.LCSI_RH(firstValue, sixTValue, altitude);
                            BigDecimal num1 = new BigDecimal(rhValues);
                            BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(roundedRHValue));
                            isUpdatingSecond = false;
                            Double kgValue = psyCal.LCSI_RHTOGRAMS(firstValue, rhValues, altitude);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(kgValue));
                            isUpdatingThird = false;
                            Double dpValue = psyCal.LCSI_DEWPOINT(firstValue, kgValue);
                            BigDecimal num2 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double gkgValue = psyCal.LCSI_WBTOGRAMS(firstValue, dpValue, altitude);
                            isUpdatingFourth = true;
                            binding.et4.setText(String.valueOf(gkgValue));
                            isUpdatingFourth = false;

                        } else {
                            Double thirdValue = psyCal.LCRH(firstValue, fourthValue, altitude);
                            //BigDecimal num1 = new BigDecimal(rhValues);
                            //BigDecimal roundedRHValue = num1.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingSecond = true;
                            binding.et2.setText(String.valueOf(thirdValue));
                            isUpdatingSecond = false;
                            Double btuValue = psyCal.LCRHTOGRAINS(firstValue, thirdValue, altitude);
                            BigDecimal num3 = new BigDecimal(btuValue);
                            BigDecimal roundedBtuValue = num3.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingThird = true;
                            binding.et3.setText(String.valueOf(roundedBtuValue));
                            isUpdatingThird = false;
                            Double dpValue = psyCal.LCDEWPOINT(firstValue, btuValue);
                            BigDecimal num4 = new BigDecimal(dpValue);
                            BigDecimal roundedDpValue = num4.setScale(1, RoundingMode.HALF_UP);
                            isUpdatingFive = true;
                            binding.et5.setText(String.valueOf(roundedDpValue));
                            isUpdatingFive = false;
                            Double lbValue = psyCal.LCWBTOGRAINS(firstValue, secondValue, dpValue);
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

}