package com.example.smartgarage;

import com.example.smartgarage.Model.Mechanician;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SmartGarageApi {

    @GET("mechanicians")
    Call<List<Mechanician>> getMechanicians();

}
