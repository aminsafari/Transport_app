package com.example.amin08.transport;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import app.app;
import fragments.BolFragment;
import fragments.OrderFragment2;
import fragments.ProfileFragment2;
import app.*;

public class Main2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    OrderFragment2 orderFragment2     = new OrderFragment2();
    ProfileFragment2 profileFragment2 = new ProfileFragment2();
    BolFragment bolFragment           = new BolFragment();
    boolean doubleBackToExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        openFragment(orderFragment2);

        init();
    }

    private void init() {

        bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.order);
        openFragment(orderFragment2);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.profile :

                openFragment(profileFragment2);

                break;

            case R.id.order :

                openFragment(orderFragment2);

                break;

            case R.id.bol :

                openFragment(bolFragment);

                break;
        }
        return true;
    }

    private void openFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container2 , fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (bottomNavigationView.getSelectedItemId() != R.id.order) {
            bottomNavigationView.setSelectedItemId(R.id.order);
        }
        else {

            if (doubleBackToExit) {


                super.onBackPressed();
                moveTaskToBack(true);

                System.exit(1);

            }
            else {
                this.doubleBackToExit = true;
                app.t(  "برای خروج بار دیگر دکمه بازگشت را لمس کنید" , app.ToastType.WARNING);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       doubleBackToExit = false;
                    }
                }, 2000);

            }

        }
    }


}
