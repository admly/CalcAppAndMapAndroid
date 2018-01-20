package pl.testapp.am.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvWelcome;
    Button btWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        btWelcome = (Button) findViewById(R.id.btWelcome);

        btWelcome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MidClass.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}
