package pl.testapp.am.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends AppCompatActivity {
    Button btZakoncz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btZakoncz = findViewById(R.id.btZakoncz);
        btZakoncz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WelcomeActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
