package com.example.maybequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    TextView tvres;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;
    FrameLayout frlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvres = findViewById(R.id.tv);
        frlayout = findViewById(R.id.frlayout);
        Button btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(v->{
            FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
            tran.add(R.id.frlayout, new BlankFragment1());
            tran.addToBackStack(null);
            tran.commit();
        });
        tvres.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.home1_anim));
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.i1){
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));

                }
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

            tvres.setText("Name: " + bundle.getString("name")+"\nAge:" + bundle.getInt("age"));
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
}