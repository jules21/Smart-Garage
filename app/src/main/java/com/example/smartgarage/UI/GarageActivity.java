package com.example.smartgarage.UI;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.Constants;
import com.example.smartgarage.Model.Garage;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageActivity extends AppCompatActivity {

    SmartGarageApi smartGarageApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        textViewResult = (TextView)findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        smartGarageApi = retrofit.create(SmartGarageApi.class);
    }

    private void getGarages() {


        Call<List<Garage>> call = smartGarageApi.getGarages();

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Garage> Garages = response.body();

                for(Garage Garage : Garages){
                    String content = "";
//                    content += "names: "+Garage.getNames() + "\n";
//                    content += "email: "+Garage.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getGarage() {


        Call<List<Garage>> call = smartGarageApi.getGarage(1);

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Garage> Garages = response.body();

                for(Garage Garage : Garages){
                    String content = "";
//                    content += "names: "+Garage.getNames() + "\n";
//                    content += "email: "+Garage.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private  void createGarage(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Garage Garage = new Garage(fields);
        Garage Garage = new Garage("smart garage", "kigali rwanda kG st 234");
        Call<Garage> call = smartGarageApi.createGarage(Garage);
        call.enqueue(new Callback<Garage>() {
            @Override
            public void onResponse(Call<Garage> call, Response<Garage> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Garage> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void updateGarage(){
        Map<String, String> fields = new HashMap<>();
        fields.put("name","2");
        fields.put("address","2");
        fields.put("phone","2");
        fields.put("description","2");
        fields.put("address","2");
//        Garage Garage = new Garage(fields);
//        Garage Garage = new Garage("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Garage> call = smartGarageApi.updateGarage(1, fields);
        call.enqueue(new Callback<Garage>() {
            @Override
            public void onResponse(Call<Garage> call, Response<Garage> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Garage> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteGarage() {


        Call<List<Garage>> call = smartGarageApi.deleteGarage(1);

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Garage> Garages = response.body();

                for(Garage Garage : Garages){
                    String content = "";
//                    content += "names: "+Garage.getNames() + "\n";
//                    content += "email: "+Garage.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


}

