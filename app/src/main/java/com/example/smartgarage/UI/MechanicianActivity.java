package com.example.smartgarage.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartgarage.Constants;
import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.Model.Speciality;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MechanicianActivity extends AppCompatActivity {

    SmartGarageApi smartGarageApi;

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanician);

        textViewResult = (TextView)findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        smartGarageApi = retrofit.create(SmartGarageApi.class);


        getMechanicians();
//        getMechanicianSpecialities();

    }

//    private void getMechanicianSpecialities() {
//        Call<List<Speciality>>
//    }

    private void getMechanicians() {


        Call<List<Mechanician>> call = smartGarageApi.getMechanicians();

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
