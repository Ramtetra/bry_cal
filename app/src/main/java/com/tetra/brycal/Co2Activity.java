package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.tetra.brycal.databinding.ActivityCo2Binding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Co2Activity extends AppCompatActivity {
    ActivityCo2Binding binding;
    String[] siSelected = {"ASIA", "EUROPE","USA"};
   String region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_co2);
        binding=ActivityCo2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter ad1 = new ArrayAdapter(Co2Activity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.etRegion.setAdapter(ad1);
        binding.etRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String regionValue=(String) parent.getItemAtPosition(position);
               if (regionValue.equalsIgnoreCase("ASIA")){
                  region="0.73";
               }else if (regionValue.equalsIgnoreCase("EUROPE")){
                   region="0.24";
               }else if (regionValue.equalsIgnoreCase("USA")){
                   region="0.43";
               }
               if (binding.et1.getText().toString().isEmpty() && binding.et2.getText().toString().isEmpty() && binding.et3.getText().toString().isEmpty() && binding.et4.getText().toString().isEmpty()){

               }else {
                   double et1Value= Double.parseDouble(binding.et1.getText().toString());
                   double et2Value= Double.parseDouble(binding.et2.getText().toString());
                   double et3Value= Double.parseDouble(binding.et3.getText().toString());
                   double et4Value= Double.parseDouble(binding.et4.getText().toString());
                   double totalKwh=et1Value+et2Value+et3Value+et4Value;
                   double commValue=(totalKwh*Float.parseFloat(region))/1000;
                   BigDecimal num1 = new BigDecimal(totalKwh);
                   BigDecimal roundedValue1 = num1.setScale(1, RoundingMode.HALF_UP); // Returns 12.35

                   BigDecimal num2 = new BigDecimal(commValue);
                   BigDecimal roundedValue2 = num2.setScale(1, RoundingMode.HALF_UP); // Returns 12.35

                   binding.etKwhConsume.setText(String.valueOf(roundedValue1));
                   binding.etCo2Commision.setText(String.valueOf(roundedValue2));
               }

            }
        });
        binding.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Co2Activity.this, PsycalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Co2Activity.this, MixedAirActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Co2Activity.this, UnitConverterActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Co2Activity.this, DuctulatorActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Co2Activity.this, Co2Activity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Co2Activity.this, WeatherActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });

    }
}