package pl.testapp.am.calcapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalcActivity extends Activity {
    TextView tvKwotaKredytu;
    TextView tvIleDni;
    SeekBar sbKwotaKredytu;
    SeekBar sbIleDni;
    EditText etProwizja;
    EditText etOdsetki;
    Button btToMap;
    Button btPowrot;
    EditText etRRSO;
    EditText etDoSplaty;
    private int progressKwotaKr;
    private double kwotaKredytu;
    private int iledni;

    public static float PROWIZJA = 0.05f;
    public static float OPROCENTOWANIE = 0.016f;
    public static String WALUTA = " zł";

    DecimalFormat formatter = new DecimalFormat("#.00");
    DecimalFormat formatterPercent = new DecimalFormat("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tvKwotaKredytu = (TextView) findViewById(R.id.tvKwotaKredytu);
        sbKwotaKredytu = (SeekBar) findViewById(R.id.sbKwotaKredytu);

        tvIleDni = findViewById(R.id.tvIleDni);
        sbIleDni = findViewById(R.id.sbIleDni);

        btToMap = findViewById(R.id.btToMap);
        btPowrot = findViewById(R.id.btPowrot);

        etProwizja = findViewById(R.id.etProwizja);
        etOdsetki = findViewById(R.id.etOdsetki);
        etRRSO = findViewById(R.id.etRRSO);
        etDoSplaty = findViewById(R.id.etDoSplaty);


        setInitialSbAndTxtValues();
        setParametryKredytu();

        sbKwotaKredytu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                kwotaKredytu = (i+1)*50;
                setParametryKredytu();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        sbIleDni.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                iledni = i;
                setParametryKredytu();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btToMap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btPowrot.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MidClass.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }
    public void setParametryKredytu(){
        tvKwotaKredytu.setText(""+String.valueOf(kwotaKredytu)+WALUTA);
        etProwizja.setText(formatter.format(kwotaKredytu*PROWIZJA)+WALUTA);
        tvIleDni.setText(""+String.valueOf(iledni));
        etOdsetki.setText(formatter.format((kwotaKredytu*OPROCENTOWANIE)*iledni)+WALUTA);
        etRRSO.setText(
                formatterPercent.format((((kwotaKredytu + (kwotaKredytu*PROWIZJA)+((kwotaKredytu*OPROCENTOWANIE)*iledni))/kwotaKredytu)-1)*100) + " %"
        );
        etDoSplaty.setText(formatter.format(kwotaKredytu + kwotaKredytu*PROWIZJA + (kwotaKredytu*OPROCENTOWANIE)*iledni)+ WALUTA);
    }
    public void setInitialSbAndTxtValues(){
        progressKwotaKr = sbKwotaKredytu.getProgress();
        kwotaKredytu = (progressKwotaKr+1)*50;
        iledni = sbIleDni.getProgress();
    }


}
