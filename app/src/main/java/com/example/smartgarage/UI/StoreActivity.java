package com.example.smartgarage.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.smartgarage.Model.Sparepart;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SmartGarageApi smartGarageApi;
    private TextView textViewResult;
    private LocationManager locationManager;
    private LocationListener listener;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        b = (Button) findViewById(R.id.btnShowLocation);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(), "\n " + location.getLongitude() + " " + location.getLatitude(), Toast.LENGTH_SHORT).show();
                Log.i("address","\n " + location.getLongitude() + " " + location.getLatitude() );
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {

        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // first check for permissions
                if (ActivityCompat.checkSelfPermission(StoreActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                                ,10);
                    }
                    return;
                }
                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void getSpareparts() {


        Call<List<Sparepart>> call = smartGarageApi.getSpareparts();

        call.enqueue(new Callback<List<Sparepart>>() {
            @Override
            public void onResponse(Call<List<Sparepart>> call, Response<List<Sparepart>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Sparepart> Spareparts = response.body();

                for(Sparepart Sparepart : Spareparts){
                    String content = "";
//                    content += "names: "+Sparepart.getNames() + "\n";
//                    content += "email: "+Sparepart.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getSparepart() {


        Call<List<Sparepart>> call = smartGarageApi.getSparepart(1);

        call.enqueue(new Callback<List<Sparepart>>() {
            @Override
            public void onResponse(Call<List<Sparepart>> call, Response<List<Sparepart>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Sparepart> Spareparts = response.body();

                for(Sparepart Sparepart : Spareparts){
                    String content = "";
//                    content += "names: "+Sparepart.getNames() + "\n";
//                    content += "email: "+Sparepart.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private  void createSparepart(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Sparepart Sparepart = new Sparepart(fields);
        Sparepart Sparepart = new Sparepart("smart Sparepart", "kigali rwanda kG st 234");
        Call<Sparepart> call = smartGarageApi.createSparepart(Sparepart);
        call.enqueue(new Callback<Sparepart>() {
            @Override
            public void onResponse(Call<Sparepart> call, Response<Sparepart> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Sparepart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void updateSparepart(){
        Map<String, String> fields = new HashMap<>();
        fields.put("name","2");
        fields.put("address","2");
        fields.put("phone","2");
        fields.put("description","2");
        fields.put("address","2");
//        Sparepart Sparepart = new Sparepart(fields);
//        Sparepart Sparepart = new Sparepart("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Sparepart> call = smartGarageApi.updateSparepart(1, fields);
        call.enqueue(new Callback<Sparepart>() {
            @Override
            public void onResponse(Call<Sparepart> call, Response<Sparepart> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Sparepart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteSparepart() {


        Call<List<Sparepart>> call = smartGarageApi.deleteSparepart(1);

        call.enqueue(new Callback<List<Sparepart>>() {
            @Override
            public void onResponse(Call<List<Sparepart>> call, Response<List<Sparepart>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Sparepart> Spareparts = response.body();

                for(Sparepart Sparepart : Spareparts){
                    String content = "";
//                    content += "names: "+Sparepart.getNames() + "\n";
//                    content += "email: "+Sparepart.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
