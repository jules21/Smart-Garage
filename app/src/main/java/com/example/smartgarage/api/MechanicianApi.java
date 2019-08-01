package com.example.smartgarage.api;

import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.Model.Speciality;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MechanicianApi {
    @GET("mechanicians")
    Call<List<Mechanician>> getMechanicians();
//
//    @GET("mechanicians/{id}/specialities")
//    Call<List<Speciality>> getSpecialities(@Path("id") int MechanicianId);
}

