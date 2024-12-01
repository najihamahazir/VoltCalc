package com.example.assignment_najihamahazir;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class AboutMeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView tvGitHubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me); // Ensure this layout file matches your XML.

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Me");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_aboutme);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_aboutme) {
                // Already in AboutMeActivity, do nothing or close the drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_home) {
                // Navigate to MainActivity
                Intent intent = new Intent(AboutMeActivity.this, MainActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (item.getItemId() == R.id.nav_info) {
                // Navigate to InstructionActivity
                Intent intent = new Intent(AboutMeActivity.this, InstructionActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });

        tvGitHubLink = findViewById(R.id.tvGitHubLink); // Make sure your TextView in about_me.xml has this ID
        tvGitHubLink.setOnClickListener(v -> {
            String githubUrl = "https://github.com/najihamahazir/VoltCalc"; // Replace with your actual GitHub link
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(intent);
        });

    }

}
