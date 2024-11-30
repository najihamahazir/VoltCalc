package com.example.assignment_najihamahazir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class InstructionActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction); // Ensure this layout file matches your XML.

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("App Instruction");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_info);

        TextView tvInstructions = findViewById(R.id.tvInstructions);
        tvInstructions.setText(getInstructionsText());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_info) {
                // Already in InstructionActivity, do nothing or close the drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_home) {
                // Navigate to MainActivity
                Intent intent = new Intent(InstructionActivity.this, MainActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_aboutme) {
                // Navigate to InstructionActivity
                Intent intent = new Intent(InstructionActivity.this, AboutMeActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });

    }

    private String getInstructionsText() {
        return
                "1. Enter Units Used: Enter the number of electricity units used in the provided text box at the main screen.\n\n" +
                "2. Adjust Rebate Percentage: Use the slider to set the desired rebate percentage. It will used to calculate total charges after rebates.\n\n" +
                "3. Calculate Total Charges: Tap the 'Calculate' button to compute the total charges based on the units used and the rebate percentage. The app will display both total.\n\n" +
                "4. Reset Fields: If you wish to start over, tap the 'Reset' button to clear all input fields and results.\n\n" +
                "5. Navigation: Use the navigation drawer to switch between different sections of the app:\n\n" +
                "   - Home: Returns you to the main calculation screen.\n\n" +
                "   - About Me: Provides information about the app and its creator.\n\n" +
                "   - Instructions: Displays this set of instructions.";
    }

}
