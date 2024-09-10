package com.tetra.brycal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tetra.brycal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String selectedOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.txtTitle.setText("BryCal");

       binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               int selectedOptionId = binding.radioGroup1.getCheckedRadioButtonId();
               RadioButton selectedRadioButton = findViewById(selectedOptionId);
               selectedOption = selectedRadioButton.getText().toString();
               Toast.makeText(MainActivity.this, ""+selectedOption, Toast.LENGTH_SHORT).show();
               if (selectedOption.equalsIgnoreCase("Psycal")){
                   Intent intent=new Intent(MainActivity.this, PsycalActivity.class);
                   startActivity(intent);
               } else if (selectedOption.equalsIgnoreCase("Mixed Air Conditions")){
                   Intent intent=new Intent(MainActivity.this, MixedAirActivity.class);
                   startActivity(intent);
               }
           }
       });

    }
/*    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }*/
}