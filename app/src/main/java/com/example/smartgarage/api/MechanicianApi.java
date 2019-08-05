package com.example.smartgarage.api;

import com.example.smartgarage.Model.Mechanician;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MechanicianApi {
    @GET("mechanicians")
    Call<List<Mechanician>> getMechanicians();
//
//    @GET("mechanicians/{id}/specialities")
//    Call<List<Speciality>> getSpecialities(@Path("id") int MechanicianId);
//@GET("posts")
//Call<List<Post>> getPosts(
//        @Query("userId") Integer[] userId,
//        @Query("_sort") String sort,
//        @Query("_order") String order
//);
}

