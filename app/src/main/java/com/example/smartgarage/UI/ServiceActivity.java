package com.example.smartgarage.UI;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.Model.Service;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    SmartGarageApi smartGarageApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }
    private void getServices() {


        Call<List<Service>> call = smartGarageApi.getServices();

        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Service> Services = response.body();

                for(Service Service : Services){
                    String content = "";
//                    content += "names: "+Service.getNames() + "\n";
//                    content += "email: "+Service.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getService() {


        Call<List<Service>> call = smartGarageApi.getService(1);

        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Service> Services = response.body();

                for(Service Service : Services){
                    String content = "";
//                    content += "names: "+Service.getNames() + "\n";
//                    content += "email: "+Service.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private  void createService(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Service Service = new Service(fields);
        Service Service = new Service("smart Service", "kigali rwanda kG st 234");
        Call<Service> call = smartGarageApi.createService(Service);
        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void updateService(){
        Map<String, String> fields = new HashMap<>();
        fields.put("name","2");
        fields.put("description","2");
//        Service Service = new Service(fields);
//        Service Service = new Service("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Service> call = smartGarageApi.updateService(1, fields);
        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteService() {


        Call<List<Service>> call = smartGarageApi.deleteService(1);

        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Service> Services = response.body();

                for(Service Service : Services){
                    String content = "";
//                    content += "names: "+Service.getNames() + "\n";
//                    content += "email: "+Service.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
