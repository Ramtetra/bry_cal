package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.tetra.brycal.databinding.ActivityPsycalBinding;
import com.tetra.brycal.model.CalculationResult2;
import com.tetra.brycal.model.CalculationResult3;
import com.tetra.brycal.model.CalculationResult6;
import com.tetra.brycal.model.CalculationResult5;
import com.tetra.brycal.model.CalculationResult4;

public class PsycalActivity extends AppCompatActivity {
 ActivityPsycalBinding binding;
    PsyLib psyCal;
    boolean checkedValue=false;
    double firstValue,secondValue,thirdValue,fourthValue,fiveValue,sixValue;

    boolean isUpdatingSecond = false;
    boolean isUpdatingThird = false;
    boolean isUpdatingFourth = false;
    boolean isUpdatingFive = false;
    boolean isUpdatingSix = false;
    int unitValue=1;
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
        psyCal=new PsyLib();
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
                unitValue=0;
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
                unitValue=1;

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
                         isUpdatingSecond = true; // Prevent recursion*/
                         double firstValue = Double.parseDouble(binding.et1.getText().toString());
                         double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                         double secondValue = Double.parseDouble(s.toString());
                         if (!s.toString().isEmpty()) {
                             CalculationResult2 result = psyCal.formulaDBTWBTLinric2(unitValue, firstValue, secondValue, altitude);
                             isUpdatingThird = true;
                             binding.et3.setText(String.valueOf(result.getRelH()));
                             isUpdatingThird = false;
                             isUpdatingFourth = true;
                             binding.et4.setText(String.valueOf(result.getEnth()));
                             isUpdatingFourth = false;
                             isUpdatingFive = true;
                             binding.et5.setText(String.valueOf(result.getDensity()));
                             isUpdatingFive = false;
                             isUpdatingSix = true;
                             binding.et6.setText(String.valueOf(result.getAbsHum()));
                             isUpdatingSix = false;
                         }
                         isUpdatingSecond = false; // Reset flag in case of exception
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
                         isUpdatingThird = true; // Prevent recursion*//*
                    double firstValue = Double.parseDouble(binding.et1.getText().toString());
                    double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                    double thirdValue = Double.parseDouble(s.toString());
                    CalculationResult3 result =psyCal.formulaDBTRHTLinric3(unitValue, firstValue, thirdValue, altitude);
                         if (!s.toString().isEmpty()) {
                        isUpdatingSecond=true;
                        binding.et2.setText(String.valueOf(result.getWbt()));
                        isUpdatingSecond=false;
                        isUpdatingFourth=true;
                        binding.et4.setText(String.valueOf(result.getEnth()));
                        isUpdatingFourth=false;
                        isUpdatingFive=true;
                        binding.et5.setText(String.valueOf(result.getDensity()));
                        isUpdatingFive=false;
                        isUpdatingSix=true;
                        binding.et6.setText(String.valueOf(result.getAbsHum()));
                        isUpdatingSix=false;
                    }
                         isUpdatingThird = false; // Reset flag in case of exception
                    }
                } catch (NumberFormatException e) {
                    isUpdatingThird = false; // Reset flag in case of exception

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
                        isUpdatingFourth = true; // Prevent recursion*//*
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        double fiveValue = Double.parseDouble(s.toString());
                        CalculationResult4 result =psyCal.formulaDBTENTHELPYLinric4(unitValue, firstValue, fiveValue, altitude);
                        if (!s.toString().isEmpty()) {
                            isUpdatingSecond=true;
                            binding.et2.setText(String.valueOf(result.getWbt()));
                            isUpdatingSecond=false;
                            isUpdatingThird=true;
                            binding.et3.setText(String.valueOf(result.getRelH()));
                            isUpdatingThird=false;
                            isUpdatingSix=true;
                            binding.et6.setText(String.valueOf(result.getAbsHum()));
                            isUpdatingSix=false;
                            isUpdatingFive=true;
                            binding.et5.setText(String.valueOf(result.getDensity()));
                            isUpdatingFive=false;
                        }
                        isUpdatingFourth = false; // Reset flag in case of exception
                    }
                } catch (NumberFormatException e) {
                    isUpdatingFourth = false; // Reset flag in case of exception

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
                try {
                    if (!isUpdatingFive) {
                        isUpdatingFive = true; // Prevent recursion*//*
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        double fiveValue = Double.parseDouble(s.toString());
                        CalculationResult5 result =psyCal.formulaDBTDEWPOINTLinric5(unitValue, firstValue, fiveValue, altitude);
                        if (!s.toString().isEmpty()) {
                            isUpdatingSecond=true;
                            binding.et2.setText(String.valueOf(result.getEnth()));
                            isUpdatingSecond=false;
                            isUpdatingThird=true;
                            binding.et3.setText(String.valueOf(result.getRelH()));
                            isUpdatingThird=false;
                            isUpdatingFourth=true;
                            binding.et4.setText(String.valueOf(result.getAbsHum()));
                            isUpdatingFourth=false;
                            isUpdatingSix=true;
                            binding.et6.setText(String.valueOf(result.getWbt()));
                            isUpdatingSix=false;
                        }
                        isUpdatingFive = false; // Reset flag in case of exception
                    }
                } catch (NumberFormatException e) {
                    isUpdatingFive = false; // Reset flag in case of exception

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
                        isUpdatingSix = true; // Prevent recursion*//*
                        double firstValue = Double.parseDouble(binding.et1.getText().toString());
                        double altitude = Double.parseDouble(binding.etAltitude.getText().toString());
                        double fourthValue = Double.parseDouble(s.toString());
                        CalculationResult6 result =psyCal.formulaDBTGRAINSTLinric6(unitValue, firstValue, fourthValue, altitude);
                        if (!s.toString().isEmpty()) {
                            isUpdatingSecond=true;
                            binding.et2.setText(String.valueOf(result.gettWbt()));
                            isUpdatingSecond=false;
                            isUpdatingThird=true;
                            binding.et3.setText(String.valueOf(result.gettRelH()));
                            isUpdatingThird=false;
                            isUpdatingFive=true;
                            binding.et5.setText(String.valueOf(result.gettDencity()));
                            isUpdatingFive=false;
                            isUpdatingFourth=true;
                            binding.et4.setText(String.valueOf(result.gettEnth()));
                            isUpdatingFourth=false;
                        }
                        isUpdatingSix = false; // Reset flag in case of exception
                    }
                } catch (NumberFormatException e) {
                    isUpdatingSix = false; // Reset flag in case of exception

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}