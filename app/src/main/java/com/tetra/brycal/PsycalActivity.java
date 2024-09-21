package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tetra.brycal.databinding.ActivityPsycalBinding;

public class PsycalActivity extends AppCompatActivity {
 ActivityPsycalBinding binding;
    String selectedOption = "SI";
    String[] siSelected = {"°C"};
    String[] ipSelected = {"°F db"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_psycal);
        binding=ActivityPsycalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txt1.setText("°C db");
        binding.txt2.setText("°C db");
        binding.txt3.setText("°C db");
        binding.txt4.setText("°C db");
        binding.txt5.setText("°C db");
        binding.txt6.setText("°C db");
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
                binding.txt1.setText("°F db");
                binding.txt2.setText("°F db");
                binding.txt3.setText("°F db");
                binding.txt4.setText("°F db");
                binding.txt5.setText("°F db");
                binding.txt6.setText("°F db");
            } else {
                binding.txt1.setText("°C db");
                binding.txt2.setText("°C db");
                binding.txt3.setText("°C db");
                binding.txt4.setText("°C db");
                binding.txt5.setText("°C db");
                binding.txt6.setText("°C db");            }
        });
       /* ArrayAdapter ad1 = new ArrayAdapter(PsycalActivity.this, android.R.layout.simple_spinner_item, siSelected);
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
        binding.spinner6.setAdapter(ad6);*/

        if (!binding.et1.getText().toString().isEmpty() && !binding.et2.getText().toString().isEmpty()){

        } else if (!binding.et1.getText().toString().isEmpty() && !binding.et3.getText().toString().isEmpty()){

        } else if (!binding.et1.getText().toString().isEmpty() && !binding.et4.getText().toString().isEmpty()){

        }
        else if (!binding.et1.getText().toString().isEmpty() && !binding.et5.getText().toString().isEmpty()){

        }
        else if (!binding.et1.getText().toString().isEmpty() && !binding.et6.getText().toString().isEmpty()){

        }
    }
}