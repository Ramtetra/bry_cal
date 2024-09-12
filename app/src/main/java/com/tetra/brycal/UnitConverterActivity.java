package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tetra.brycal.databinding.ActivityUnitConverterBinding;

public class UnitConverterActivity extends AppCompatActivity {
    ActivityUnitConverterBinding binding;
    String[] unitsList={"Meter","Foot","Inch","Kilometer"};
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
        binding.etUnitInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              inputUnits=(String) parent.getItemAtPosition(position);
                Toast.makeText(UnitConverterActivity.this, ""+inputUnits, Toast.LENGTH_SHORT).show();
            }
        });
        binding.etUnitOutput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                outputUnits=(String) parent.getItemAtPosition(position);
                Toast.makeText(UnitConverterActivity.this, ""+outputUnits, Toast.LENGTH_SHORT).show();
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