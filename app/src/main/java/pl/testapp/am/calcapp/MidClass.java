package pl.testapp.am.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MidClass extends AppCompatActivity {
    Button btDalej;
    Button btGdzie;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_class);
        btDalej = findViewById(R.id.btDalej);
        btGdzie = findViewById(R.id.btGdzie);
        btBack = findViewById(R.id.btPowrot2);

        btDalej.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CalcActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btGdzie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddressActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WelcomeActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }

}
