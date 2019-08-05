package com.example.smartgarage.UI;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.Model.Speciality;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialityActivity extends AppCompatActivity {

    SmartGarageApi smartGarageApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality);
    }
    private void getSpecialities() {


        Call<List<Speciality>> call = smartGarageApi.getSpecialities();

        call.enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Speciality> Specialities = response.body();

                for(Speciality Specialitie : Specialities){
                    String content = "";
//                    content += "names: "+Specialitie.getNames() + "\n";
//                    content += "email: "+Specialitie.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getSpeciality() {


        Call<List<Speciality>> call = smartGarageApi.getSpeciality(1);

        call.enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Speciality> Specialities = response.body();

                for(Speciality Specialitie : Specialities){
                    String content = "";
//                    content += "names: "+Specialitie.getNames() + "\n";
//                    content += "email: "+Specialitie.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private  void createSpeciality(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Specialitie Specialitie = new Specialitie(fields);
        Speciality Specialitie = new Speciality("smart Specialitie", "kigali rwanda kG st 234");
        Call<Speciality> call = smartGarageApi.createSpeciality(Specialitie);
        call.enqueue(new Callback<Speciality>() {
            @Override
            public void onResponse(Call<Speciality> call, Response<Speciality> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Speciality> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void updateSpeciality(){
        Map<String, String> fields = new HashMap<>();
        fields.put("name","2");
        fields.put("description","2");
//        Specialitie Specialitie = new Specialitie(fields);
//        Specialitie Specialitie = new Specialitie("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Speciality> call = smartGarageApi.updateSpeciality(1, fields);
        call.enqueue(new Callback<Speciality>() {
            @Override
            public void onResponse(Call<Speciality> call, Response<Speciality> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Speciality> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteSpeciality() {


        Call<List<Speciality>> call = smartGarageApi.deleteSpeciality(1);

        call.enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Speciality> Specialities = response.body();

                for(Speciality Specialitie : Specialities){
                    String content = "";
//                    content += "names: "+Specialitie.getNames() + "\n";
//                    content += "email: "+Specialitie.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
