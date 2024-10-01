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
    String[] unitsPress={"Pascal","Bar","PSI","atm"};
    String[] unitsAirFlow={"CFM","CMH","LPS","Cubic meter per second"};
    String[] unitsWeight={"Kilogram","Gram","Metric Ton","Pound"};
    String[] unitsPower={"Watt","Kilowatt","Gigawatt","Megawatt"};
    String[] unitsEnergy={"Joule","Btu","Calorie","Kilowatt-Hours"};
    String[] unitsHumidity={"Celsius","Kelvin","Fahrenheit"};

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
        binding.cardLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsList);
                binding.etUnitInput.setAdapter(inputAdapter);
                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsList);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });

        binding.cardArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsArea);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsArea);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));

                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsVolume);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsVolume);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsTemp);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsTemp);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsPress);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsPress);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });

        binding.cardAirFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsAirFlow);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsAirFlow);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsWeight);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsWeight);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsPower);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsPower);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsEnergy);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsEnergy);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.cardHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFrom.setText("");
                binding.etTo.setText("");
                binding.etUnitInput.setText(""); // Clear the text
                binding.etUnitOutput.setText(""); // Clear the text
                binding.cardLength.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardArea.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardVolume.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardTemperature.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPressure.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardAirFlow.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardWeight.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardPower.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardEnergy.setBackgroundColor(getResources().getColor(R.color.light_yellow_card));
                binding.cardHumidity.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                ArrayAdapter inputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsHumidity);
                binding.etUnitInput.setAdapter(inputAdapter);

                ArrayAdapter outputAdapter = new ArrayAdapter(UnitConverterActivity.this, android.R.layout.simple_spinner_item, unitsHumidity);
                binding.etUnitOutput.setAdapter(outputAdapter);
            }
        });
        binding.etUnitInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              inputUnits=(String) parent.getItemAtPosition(position);
            
            }
        });

        binding.etUnitOutput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                outputUnits=(String) parent.getItemAtPosition(position);
                if (binding.etFrom.getText().toString().isEmpty()){
                }else {
                    double inputValue= Double.parseDouble(binding.etFrom.getText().toString());
                    ///................Length................///
                  if (inputUnits.equalsIgnoreCase("Meter")){
                      if (outputUnits.equalsIgnoreCase("Meter")){
                          binding.etTo.setText(String.valueOf(inputValue));
                      } else if (outputUnits.equalsIgnoreCase("Foot")){
                          double footValue=inputValue*3.281;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Inch")){
                          binding.etTo.setText(String.valueOf(inputValue*39.37));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilometer")){
                          double kilometerValue=inputValue/1000;
                          BigDecimal num2 = new BigDecimal(kilometerValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Foot")){
                      if (outputUnits.equalsIgnoreCase("Meter")){
                          double footValue=inputValue/3.281;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Foot")){
                          double footValue=inputValue;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Inch")){
                          binding.etTo.setText(String.valueOf(inputValue*12));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilometer")){
                          double kilometerValue=inputValue/3281;
                          BigDecimal num2 = new BigDecimal(kilometerValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                  else if (inputUnits.equalsIgnoreCase("Inch")){
                      if (outputUnits.equalsIgnoreCase("Meter")){
                          double footValue=inputValue/39.37;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Foot")){
                          double footValue=inputValue/12;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Inch")){
                          double footValue=inputValue;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilometer")){
                          double kilometerValue=inputValue/39370;
                          BigDecimal num2 = new BigDecimal(kilometerValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                  else if (inputUnits.equalsIgnoreCase("Kilometer")){
                      if (outputUnits.equalsIgnoreCase("Meter")){
                          double footValue=inputValue*1000;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Foot")){
                          double footValue=inputValue*3281;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Inch")){
                          double footValue=inputValue*39370;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP);
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilometer")){
                          double kilometerValue=inputValue;
                          BigDecimal num2 = new BigDecimal(kilometerValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                  ///.........Area...........///

                   else if (inputUnits.equalsIgnoreCase("Square Meter")){
                     if (outputUnits.equalsIgnoreCase("Square Meter")){
                          double meterValue=inputValue;
                          BigDecimal num2 = new BigDecimal(meterValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Foot")){
                          double footValue=inputValue*10.764;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Inch")){
                          double footValue=inputValue*1550;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Yard")){
                          double footValue=inputValue*1.196;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                    }
                   else if (inputUnits.equalsIgnoreCase("Square Foot")){
                      if (outputUnits.equalsIgnoreCase("Square Meter")){
                          double meterValue=inputValue/10.764;
                          BigDecimal num2 = new BigDecimal(meterValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Foot")){
                          double footValue=inputValue;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Inch")){
                          double footValue=inputValue*144;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Yard")){
                          double footValue=inputValue/9;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                   else if (inputUnits.equalsIgnoreCase("Square Inch")){
                      if (outputUnits.equalsIgnoreCase("Square Meter")){
                          double meterValue=inputValue/10.764;
                          BigDecimal num2 = new BigDecimal(meterValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Foot")){
                          double footValue=inputValue;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                     else if (outputUnits.equalsIgnoreCase("Square Inch")){
                          double footValue=inputValue*144;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Yard")){
                          double footValue=inputValue/9;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                   else if (inputUnits.equalsIgnoreCase("Square Yard")){
                      if (outputUnits.equalsIgnoreCase("Square Meter")){
                          double meterValue=inputValue/1.196    ;
                          BigDecimal num2 = new BigDecimal(meterValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Foot")){
                          double footValue=inputValue*9;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Inch")){
                          double footValue=inputValue*1296;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Square Yard")){
                          double footValue=inputValue;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

       /// .................... Volume.........................//
                else if (inputUnits.equalsIgnoreCase("Cubic Meter")){
                    if (outputUnits.equalsIgnoreCase("Cubic Meter")){
                    double footValue=(inputValue);
                    BigDecimal num2 = new BigDecimal(footValue);
                    BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                    binding.etTo.setText(String.valueOf(roundedValue));
                  }
                    else if (outputUnits.equalsIgnoreCase("Liter")){
                        double footValue=(inputValue*1000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Milliliter")){
                        double footValue=(inputValue*1000000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("US Gallon")){
                        double footValue=(inputValue*264.2);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }
                else if (inputUnits.equalsIgnoreCase("Liter")){
                    if (outputUnits.equalsIgnoreCase("Cubic Meter")){
                        double footValue=(inputValue/1000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Liter")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Milliliter")){
                        double footValue=(inputValue*1000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("US Gallon")){
                        double footValue=(inputValue*3.785);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }
                 else if (inputUnits.equalsIgnoreCase("Milliliter")){
                    if (outputUnits.equalsIgnoreCase("Cubic Meter")){
                        double footValue=(inputValue/1000000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Liter")){
                        double footValue=(inputValue/1000);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Milliliter")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("US Gallon")){
                        double footValue=(inputValue/3785);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }
                else if (inputUnits.equalsIgnoreCase("Us Gallon")){
                    if (outputUnits.equalsIgnoreCase("Cubic Meter")){
                        double footValue=(inputValue/ 264.2);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Liter")){
                        double footValue=(inputValue*3.785);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Milliliter")){
                        double footValue=(inputValue*3785);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("US Gallon")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }

      ///............ Temperature..............................///
                else if (inputUnits.equalsIgnoreCase("Celsius")){
                    if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                        double footValue=(inputValue*9/5)+32;
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                   else if (outputUnits.equalsIgnoreCase("Kelvin")){
                        double footValue=(inputValue+273.15);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Celsius")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    }
                else if (inputUnits.equalsIgnoreCase("Fahrenheit")){
                    if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Kelvin")){
                        double footValue=(inputValue-32*5/9+273.15);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Celsius")){
                        double footValue=(inputValue-32*5/9);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }
                else if (inputUnits.equalsIgnoreCase("Kelvin")){
                    if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                        double footValue=(inputValue-273.15*9/5+32);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Kelvin")){
                        double footValue=(inputValue);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                    else if (outputUnits.equalsIgnoreCase("Celsius")){
                        double footValue=(inputValue-273.15);
                        BigDecimal num2 = new BigDecimal(footValue);
                        BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                        binding.etTo.setText(String.valueOf(roundedValue));
                    }
                }

           ///........... Pressure........................///
                  else if (inputUnits.equalsIgnoreCase("Pascal")){
                      if (outputUnits.equalsIgnoreCase("Pascal")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Bar")){
                          double footValue=(inputValue/100000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("PSI")){
                          double footValue=(inputValue/6895);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("atm")){
                          double footValue=(inputValue/101300);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Bar")){
                      if (outputUnits.equalsIgnoreCase("Pascal")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Bar")){
                          double footValue=(inputValue/100000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("PSI")){
                          double footValue=(inputValue*14.504);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("atm")){
                          double footValue=(inputValue/1.013);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("PSI")){
                      if (outputUnits.equalsIgnoreCase("Pascal")){
                          double footValue=(inputValue*6895);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Bar")){
                          double footValue=(inputValue/14.504);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("PSI")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("atm")){
                          double footValue=(inputValue/14.696);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("atm")){
                      if (outputUnits.equalsIgnoreCase("Pascal")){
                          double footValue=(inputValue*101300);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Bar")){
                          double footValue=(inputValue*1.013);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("PSI")){
                          double footValue=(inputValue*14.696);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("atm")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
          ///..........Air Flow.........................///
                  else if (inputUnits.equalsIgnoreCase("CFM")){
                      if (outputUnits.equalsIgnoreCase("CFM")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("CMH")){
                          double footValue=(inputValue*1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("LPS")){
                          double footValue=(inputValue/6895);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Cubic meter per second")){
                          double footValue=(inputValue/101300);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                  ///..........Weight.........................///
                  else if (inputUnits.equalsIgnoreCase("Kilogram")){
                      if (outputUnits.equalsIgnoreCase("Kilogram")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Gram")){
                          double footValue=(inputValue*1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Metric Ton")){
                          double footValue=(inputValue/6895);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Pound")){
                          double footValue=(inputValue/101300);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                else if (inputUnits.equalsIgnoreCase("Gram")){
                      if (outputUnits.equalsIgnoreCase("Kilogram")){
                          double footValue=(inputValue/1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Gram")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Metric Ton")){
                          double footValue=(inputValue/1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Pound")){
                          double footValue=(inputValue/453.6);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                else if (inputUnits.equalsIgnoreCase("Metric Ton")){
                      if (outputUnits.equalsIgnoreCase("Kilogram")){
                          double footValue=(inputValue*1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Gram")){
                          double footValue=(inputValue*1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Metric Ton")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Pound")){
                          double footValue=(inputValue*2205);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                else if (inputUnits.equalsIgnoreCase("Pound")){
                      if (outputUnits.equalsIgnoreCase("Kilogram")){
                          double footValue=(inputValue/2.205);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Gram")){
                          double footValue=(inputValue*453.6);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Metric Ton")){
                          double footValue=(inputValue/2205);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Pound")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                  ///..........Power.........................///
                  else if (inputUnits.equalsIgnoreCase("Watt")){
                      if (outputUnits.equalsIgnoreCase("Watt")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilowatt")){
                          double footValue=(inputValue/1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Gigawatt")){
                          double footValue=(inputValue/1000000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Megawatt")){
                          double footValue=(inputValue/1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Kilowatt")) {
                      if (outputUnits.equalsIgnoreCase("Watt")) {
                          double footValue = (inputValue*1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Kilowatt")) {
                          double footValue = (inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Gigawatt")) {
                          double footValue = (inputValue/1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Megawatt")) {
                          double footValue = (inputValue/1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Gigawatt")) {
                      if (outputUnits.equalsIgnoreCase("Watt")) {
                          double footValue = (inputValue*1000000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Kilowatt")) {
                          double footValue = (inputValue*1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Gigawatt")) {
                          double footValue = (inputValue/1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Megawatt")) {
                          double footValue = (inputValue/1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Megawatt")) {
                      if (outputUnits.equalsIgnoreCase("Watt")) {
                          double footValue = (inputValue*1000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Kilowatt")) {
                          double footValue = (inputValue*1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Gigawatt")) {
                          double footValue = (inputValue/1000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      } else if (outputUnits.equalsIgnoreCase("Megawatt")) {
                          double footValue = (inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                    ///..........Energy.........................///
                  else if (inputUnits.equalsIgnoreCase("Joule")){
                      if (outputUnits.equalsIgnoreCase("Joule")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Btu")){
                          double footValue=(inputValue/1055);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Calorie")){
                          double footValue=(inputValue/4184);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilowatt-Hours")){
                          double footValue=(inputValue/3.6000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Btu")){
                      if (outputUnits.equalsIgnoreCase("Joule")){
                          double footValue=(inputValue*1055);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Btu")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Calorie")){
                          double footValue=(inputValue/3.966);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilowatt-Hours")){
                          double footValue=(inputValue/3412);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Calorie")){
                      if (outputUnits.equalsIgnoreCase("Joule")){
                          double footValue=(inputValue*4184);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Btu")){
                          double footValue=(inputValue*3.966);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Calorie")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilowatt-Hours")){
                          double footValue=(inputValue/860.4);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Kilowatt-Hours")){
                      if (outputUnits.equalsIgnoreCase("Joule")){
                          double footValue=(inputValue*3.6000000);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Btu")){
                          double footValue=(inputValue/3412);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Calorie")){
                          double footValue=(inputValue*860.4);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kilowatt-Hours")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }

                    ///..........Humidity.........................///
                  else if (inputUnits.equalsIgnoreCase("Celsius")){
                      if (outputUnits.equalsIgnoreCase("Celsius")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kelvin")){
                          double footValue=(inputValue+273.15);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                          double footValue=(inputValue*9/5)+32;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Kelvin")){
                      if (outputUnits.equalsIgnoreCase("Celsius")){
                          double footValue=(inputValue-273.15);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kelvin")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                          double footValue=(inputValue-273.15)*9/5+32;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
                  else if (inputUnits.equalsIgnoreCase("Fahrenheit")){
                      if (outputUnits.equalsIgnoreCase("Celsius")){
                          double footValue=(inputValue-32)*5/9;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Kelvin")){
                          double footValue=(inputValue-32)*5/9+273.15;
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                      else if (outputUnits.equalsIgnoreCase("Fahrenheit")){
                          double footValue=(inputValue);
                          BigDecimal num2 = new BigDecimal(footValue);
                          BigDecimal roundedValue = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35
                          binding.etTo.setText(String.valueOf(roundedValue));
                      }
                  }
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