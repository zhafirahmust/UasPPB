package com.example.uas_ppb;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.uas_ppb.R;

public class MainActivity extends AppCompatActivity  {

    private BottomNavigationView menu_bawah;
    private TextView tulisan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu_bawah = findViewById(R.id.menu_bawah);
        menu_bawah.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.home){
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,fragment);
                    fragmentTransaction.commit();
                }if (id == R.id.dashboard){
                    DashboardFragment fragment = new DashboardFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,fragment);
                    fragmentTransaction.commit();
                }if (id == R.id.profile){
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
        menu_bawah.setSelectedItemId(R.id.home);

    }

}