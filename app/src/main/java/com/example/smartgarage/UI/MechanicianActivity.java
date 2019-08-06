package com.example.smartgarage.UI;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;
import com.example.smartgarage.api.APIClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechanicianActivity extends AppCompatActivity {

    SmartGarageApi smartGarageApi;
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanician);

        textViewResult = (TextView)findViewById(R.id.text_view_result);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.apiUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        smartGarageApi = retrofit.create(SmartGarageApi.class);

        smartGarageApi = APIClient.getClient().create(SmartGarageApi.class);


        getMechanicians();
//        getMechanicianSpecialities();

    }

//    private void getMechanicianSpecialities() {
//        Call<List<Speciality>>
//    }

    private void getMechanicians() {


        Call<List<Mechanician>> call = smartGarageApi.getMechanicians();

        call.enqueue(new Callback<List<Mechanician>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<List<Mechanician>> call, Response<List<Mechanician>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                String teamString = response.body().toString();
                try {
                    JSONObject object = new JSONObject(teamString);
                    JSONArray array = new JSONArray(object);
                    for(int i = 0; i<array.length(); i++){
                        LOGGER.info(array[i].names);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                List<Mechanician> mechanicians = response.body();

//                for(Mechanician mechanician : mechanicians){
//                    String content = "";
////                    content += "names: "+mechanician.getNames() + "\n";
////                    content += "email: "+mechanician.getEmail() + "\n";
//                    content = "it's working";
//
                    textViewResult.append(teamString);
//                    log.e()
//                }
            }

            @Override
            public void onFailure(Call<List<Mechanician>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getMechanician() {


        Call<List<Mechanician>> call = smartGarageApi.getMechanician(1);

        call.enqueue(new Callback<List<Mechanician>>() {
            @Override
            public void onResponse(Call<List<Mechanician>> call, Response<List<Mechanician>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Mechanician> mechanicians = response.body();

                for(Mechanician mechanician : mechanicians){
                    String content = "";
//                    content += "names: "+mechanician.getNames() + "\n";
//                    content += "email: "+mechanician.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Mechanician>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private  void createMechanician(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Mechanician mechanician = new Mechanician(fields);
        Mechanician mechanician = new Mechanician("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Mechanician> call = smartGarageApi.createMechanician(mechanician);
        call.enqueue(new Callback<Mechanician>() {
            @Override
            public void onResponse(Call<Mechanician> call, Response<Mechanician> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Mechanician> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void updateMechanician(){
        Map<String, String> fields = new HashMap<>();
        fields.put("names","2");
        fields.put("email","2");
        fields.put("phone","2");
        fields.put("secret","2");
        fields.put("address","2");
//        Mechanician mechanician = new Mechanician(fields);
//        Mechanician mechanician = new Mechanician("jules", "jules@gmail.com", "087323334","secret","kigali rwanda");
        Call<Mechanician> call = smartGarageApi.updateMechanician(1, fields);
        call.enqueue(new Callback<Mechanician>() {
            @Override
            public void onResponse(Call<Mechanician> call, Response<Mechanician> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Mechanician> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, check ur network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteMechanician() {


        Call<List<Mechanician>> call = smartGarageApi.deleteMechanician(1);

        call.enqueue(new Callback<List<Mechanician>>() {
            @Override
            public void onResponse(Call<List<Mechanician>> call, Response<List<Mechanician>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Mechanician> mechanicians = response.body();

                for(Mechanician mechanician : mechanicians){
                    String content = "";
//                    content += "names: "+mechanician.getNames() + "\n";
//                    content += "email: "+mechanician.getEmail() + "\n";
                    content = "it's working";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Mechanician>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


}
