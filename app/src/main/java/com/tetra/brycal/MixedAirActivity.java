package com.tetra.brycal;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.tetra.brycal.databinding.ActivityMixedAirBinding;

public class MixedAirActivity extends AppCompatActivity {
    private ActivityMixedAirBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = ActivityMixedAirBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        binding.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MixedAirActivity.this, PsycalActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MixedAirActivity.this, MixedAirActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MixedAirActivity.this, UnitConverterActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MixedAirActivity.this, DuctulatorActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MixedAirActivity.this, Co2Activity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MixedAirActivity.this, WeatherActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        binding.switchBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.txt1.setText("°C db");
                binding.txt2.setText("°C db");
                binding.txt3.setText("°C wb");
                binding.txt4.setText("°C wb");
                binding.txt5.setText("%RH");
                binding.txt6.setText("°%RH");
                binding.txt7.setText("KJ/ib");
                binding.txt8.setText("KJ/ib");
                binding.txt9.setText("°C dp");
                binding.txt10.setText("°F dp");
                binding.txt11.setText("°g/kg");
                binding.txt12.setText("°g/kg");
            } else {
                binding.txt1.setText("°F db");
                binding.txt2.setText("°F db");
                binding.txt3.setText("°F wb");
                binding.txt4.setText("°F wb");
                binding.txt5.setText("%RH");
                binding.txt6.setText("°%RH");
                binding.txt7.setText("Btu/ib");
                binding.txt8.setText("Btu/ib");
                binding.txt9.setText("°F dp");
                binding.txt10.setText("°F dp");
                binding.txt11.setText("°Gr/ib");
                binding.txt12.setText("°Gr/ib");
            }
        });

    }

}