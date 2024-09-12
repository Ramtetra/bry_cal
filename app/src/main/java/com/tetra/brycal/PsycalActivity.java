package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tetra.brycal.databinding.ActivityPsycalBinding;

public class PsycalActivity extends AppCompatActivity {
 ActivityPsycalBinding binding;
    String selectedOption = "SI";
    String[] siSelected = {"°C", "°C wb", "% RH", "KJ/kg", "°C dp", "g/kg"};
    String[] ipSelected = {"°F db", "°F wb", "% RH", "Btu/Ib", "°F dp", "Gr/Lb"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_psycal);
        binding=ActivityPsycalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedOptionId = binding.radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = binding.getRoot().findViewById(selectedOptionId);
                selectedOption = selectedRadioButton.getText().toString();
                if (selectedOption.equalsIgnoreCase("SI")){
                    ArrayAdapter ad1 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner1.setAdapter(ad1);
                    ArrayAdapter ad2 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner2.setAdapter(ad2);
                    ArrayAdapter ad3 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner3.setAdapter(ad3);
                    ArrayAdapter ad4 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner4.setAdapter(ad4);
                    ArrayAdapter ad5 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner5.setAdapter(ad5);
                    ArrayAdapter ad6 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
                    binding.spinner6.setAdapter(ad6);
                }else{
                    ArrayAdapter ad1 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner1.setAdapter(ad1);
                    ArrayAdapter ad2 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner2.setAdapter(ad2);
                    ArrayAdapter ad3 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner3.setAdapter(ad3);
                    ArrayAdapter ad4 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner4.setAdapter(ad4);
                    ArrayAdapter ad5 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner5.setAdapter(ad5);
                    ArrayAdapter ad6 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, ipSelected);
                    binding.spinner6.setAdapter(ad6);
                }
            }
        });
        ArrayAdapter ad1 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner1.setAdapter(ad1);
        ArrayAdapter ad2 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner2.setAdapter(ad2);
        ArrayAdapter ad3 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner3.setAdapter(ad3);
        ArrayAdapter ad4 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner4.setAdapter(ad4);
        ArrayAdapter ad5 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner5.setAdapter(ad5);
        ArrayAdapter ad6 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
        binding.spinner6.setAdapter(ad6);

    }
}