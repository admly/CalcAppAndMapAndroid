package pl.testapp.am.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddressActivity extends AppCompatActivity {
    Button powrot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        powrot = findViewById(R.id.btCofnij);
        powrot.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MidClass.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}
