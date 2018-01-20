package pl.testapp.am.calcapp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final double LAT = 51.75924850;
    public static final double LNG = 19.45598330;
    public static final String TEXT_WELCOME = "Okrąg pokazuje obszar darmowej dostawy, kliknij, by sprawdzić czy jesteś w zasięgu";
    public static final String TEXT_POZA_OBSZAREM = "Poza obszarem, prowadzimy działalność w zasięgu zaznaczonego koła";
    public static final String TEXT_W_OBSZARZE = "W obszarze darmowej dostawy";
    private static final String INITIAL_TEXT_MAP = "Tu pojawią się koszty dostawy, jesli znajdziesz się poza obszarem darmowej dostawy";

    private GoogleMap mMap;
    private Circle circle;
    private LatLng latlng;
    DecimalFormat formatter = new DecimalFormat("#.00");
    TextView tvMap;
    Button btZamawiam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        tvMap = (TextView) findViewById(R.id.tvMap);
        btZamawiam = (Button) findViewById(R.id.btZamawiam);
        tvMap.setText(INITIAL_TEXT_MAP);
        latlng = new LatLng(LAT, LNG);
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15.0f));
        circle = mMap.addCircle(new CircleOptions().center(latlng).radius(300));

        Toast.makeText(getBaseContext(), TEXT_WELCOME, Toast.LENGTH_LONG).show();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                Marker marker1 = mMap.addMarker(new MarkerOptions().position(latLng).title("Twoja lokalizacja"));
                float[] distance = {0};
                Location.distanceBetween(circle.getCenter().latitude, circle.getCenter().longitude,
                        marker1.getPosition().latitude, marker1.getPosition().longitude, distance);
                if(distance[0] > circle.getRadius() && distance[0]< 10*circle.getRadius() ){
                    tvMap.setText("Koszty dostawy: "+formatter.format(getNumber(distance[0]))+CalcActivity.WALUTA);
                    Toast.makeText(getBaseContext(), TEXT_POZA_OBSZAREM, Toast.LENGTH_SHORT).show();
                }if(distance[0]> 10*circle.getRadius() ) {
                    tvMap.setText("Za daleko ! Poza tym obszarem nie dowozimy");
                }
                if(distance[0] < circle.getRadius()) {
                    Toast.makeText(getBaseContext(), TEXT_W_OBSZARZE, Toast.LENGTH_SHORT).show();
                    tvMap.setText(INITIAL_TEXT_MAP);
                }
                circle = mMap.addCircle(new CircleOptions().center(latlng).radius(300));
            }



        });

        btZamawiam.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), OrderActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }

    private float getNumber(float v) {
        return v /1000 *10;
    }


}

