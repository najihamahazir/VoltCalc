package com.example.assignment_najihamahazir;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

EditText etUnits;
SeekBar sliderRebates;
TextView tvTotalC, tvTotalCAR, tvRebate;
Button btnCalculate, btnReset;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etUnits = findViewById(R.id.etUnits);
        tvTotalC = findViewById(R.id.tvTotalC);
        tvTotalCAR = findViewById(R.id.tvTotalCAR);
        tvRebate = findViewById(R.id.tvRebate);
        sliderRebates = findViewById(R.id.sliderRebates);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        etUnits.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                // Already in MainActivity, do nothing or close the drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_aboutme) {
                // Navigate to AboutMeActivity
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_info) {
                // Navigate to InstructionActivity
                Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });

        sliderRebates.setMax(5);

        sliderRebates.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Display the rebate percentage in textView4
                tvRebate.setText(String.format("Rebate: %d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed
            }
        });

        btnCalculate.setOnClickListener(v -> calculateBill());
        btnReset.setOnClickListener(v -> resetFields());
    }
            public void calculateBill() {
                String unitsInput = etUnits.getText().toString();

                if (unitsInput.isEmpty()) {
                    Toast.makeText(this, "Please enter the number of units used", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double unitsUsed = Double.parseDouble(unitsInput);

                    if (unitsUsed < 0) {
                        Toast.makeText(this, "Units cannot be negative.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                int rebatePercentage = sliderRebates.getProgress();
                double totalCharges = calculateChargesByBlock(unitsUsed);

                double rebate = totalCharges * rebatePercentage / 100;
                double finalCharges = totalCharges - rebate;

                tvTotalC.setText(String.format("RM%.2f", totalCharges/100)); // Convert sen to RM
                tvTotalCAR.setText(String.format(" RM%.2f", finalCharges / 100));

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            }
            private double calculateChargesByBlock(double unitsUsed) {
                double charges = 0;

                if (unitsUsed <= 200) {
                    // First 200 kWh
                    charges += unitsUsed * 21.8;
                } else if (unitsUsed <= 300) {
                    // First 200 kWh
                    charges += 200 * 21.8;
                    // Next 100 kWh
                    charges += (unitsUsed - 200) * 33.4;
                } else if (unitsUsed <= 600) {
                    // First 200 kWh
                    charges += 200 * 21.8;
                    // Next 100 kWh
                    charges += 100 * 33.4;
                    // Next 300 kWh
                    charges += (unitsUsed - 300) * 51.6;
                } else {
                    // First 200 kWh
                    charges += 200 * 21.8;
                    // Next 100 kWh
                    charges += 100 * 33.4;
                    // Next 300 kWh
                    charges += 300 * 51.6;
                    // Beyond 600 kWh
                    charges += (unitsUsed - 600) * 54.6;
                }

                return charges;
            }

            private void resetFields() {
                etUnits.setText("");
                tvTotalC.setText("RM0.00");
                tvTotalCAR.setText("RM0.00");
                sliderRebates.setProgress(0);
                tvRebate.setText("Rebate: 0%");

        }

}
