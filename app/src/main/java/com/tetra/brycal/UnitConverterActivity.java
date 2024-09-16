package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tetra.brycal.databinding.ActivityUnitConverterBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UnitConverterActivity extends AppCompatActivity {
    ActivityUnitConverterBinding binding;
    String[] unitsList={"Meter","Foot","Inch","Kilometer"};
    String[] unitsArea={"Square Meter","Square Foot","Square Inch","Square Yard"};
    String[] unitsVolume={"Cubic Meter","Liter","Milliliter","Us Gallon"};
    String[] unitsTemp={"Celsius","Kelvin","Fahrenheit"};

   String inputUnits,outputUnits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /// setContentView(R.layout.activity_unit_converter);
        binding=ActivityUnitConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter inputAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitsList);
        binding.etUnitInput.setAdapter(inputAdapter);

        ArrayAdapter outputAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitsList);
        binding.etUnitOutput.setAdapter(outputAdapter);

        binding.cardArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsArea);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsArea);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsVolume);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsVolume);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsTemp);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsTemp);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.etUnitInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              inputUnits=(String) parent.getItemAtPosition(position);
                  /* if (inputUnits.equalsIgnoreCase("Meter")){
                 
               }*/
            }
        });
        binding.etUnitOutput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                outputUnits=(String) parent.getItemAtPosition(position);
                double inputValue= Double.parseDouble(binding.etFrom.getText().toString());
                if (outputUnits.equalsIgnoreCase("Meter")){
                 binding.etTo.setText(String.valueOf(inputValue*1000));
                } else if (outputUnits.equalsIgnoreCase("Foot")){
                    binding.etTo.setText(String.valueOf(inputValue*3281));
                }
                else if (outputUnits.equalsIgnoreCase("Inch")){
                    binding.etTo.setText(String.valueOf(inputValue*39370));
                }
                else if (outputUnits.equalsIgnoreCase("Kilometer")){
                    double kilometerValue=inputValue/39370;
                    BigDecimal num2 = new BigDecimal(kilometerValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                }
                else if (outputUnits.equalsIgnoreCase("Square Meter")){
                    double meterValue=inputValue/39.37;
                    BigDecimal num2 = new BigDecimal(meterValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                }
                else if (outputUnits.equalsIgnoreCase("Square Foot")){
                    double footValue=inputValue/12;
                    BigDecimal num2 = new BigDecimal(footValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                }
                else if (outputUnits.equalsIgnoreCase("Square Inch")){
                    double footValue=inputValue*12;
                    BigDecimal num2 = new BigDecimal(footValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                }
                else if (outputUnits.equalsIgnoreCase("Square Yard")){
                    double footValue=inputValue/36;
                    BigDecimal num2 = new BigDecimal(footValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                }
            }
        });
        binding.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(UnitConverterActivity.this, PsycalActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(UnitConverterActivity.this, MixedAirActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(UnitConverterActivity.this, UnitConverterActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(UnitConverterActivity.this, DuctulatorActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(UnitConverterActivity.this, Co2Activity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(UnitConverterActivity.this, WeatherActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
    }
}