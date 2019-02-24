package com.example.amin08.transport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fragments.OrderFragment;
import fragments.ProfileFragment;
import app.spref;
import app.*;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView ;
    OrderFragment    orderFragment    = new OrderFragment();
    ProfileFragment  profileFragment  = new ProfileFragment();
    boolean doubleBackToExit = false;

    public static Typeface font;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        try {
            font = Typeface.createFromAsset(getAssets() , "fonts/" + spref.get().getString("font" , "roboto.ttf"));
        }catch (Exception e) {

        }

        bottomNavigationView.setSelectedItemId(R.id.orders);
        openFragment(orderFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.profile : {

                openFragment(profileFragment);


                break;
            }
            case R.id.orders : {
                openFragment(orderFragment);


                break;
            }
            case R.id.map: {






                Intent intent = new Intent(MainActivity.this , MapsActivity.class );
                startActivity(intent);

                break;
            }
        }

        return true;
    }

    private void  openFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container , fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        if (bottomNavigationView.getSelectedItemId() != R.id.orders) {
            bottomNavigationView.setSelectedItemId(R.id.orders);
        }
        else {

            if (doubleBackToExit) {
                moveTaskToBack(true);

                System.exit(1);

                super.onBackPressed();
            }
            else {
                this.doubleBackToExit =true;
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
