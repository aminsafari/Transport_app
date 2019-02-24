package com.example.amin08.transport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


public class EnterActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatTextView customer , employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        init();
    }

    private void init() {


        customer = findViewById(R.id.customer);
        employee = findViewById(R.id.employee);

        customer.setOnClickListener(this);
        employee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.customer :

                Intent intent = new Intent(EnterActivity.this , LoginActivity.class);
                startActivity(intent);

                break;

            case R.id.employee :

                Intent intent1 = new Intent(EnterActivity.this , EloginActivity.class);
                startActivity(intent1);

                break;
        }

    }
}
