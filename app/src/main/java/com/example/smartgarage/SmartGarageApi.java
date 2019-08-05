package com.example.smartgarage;

import com.example.smartgarage.Model.Garage;
import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.Model.Service;
import com.example.smartgarage.Model.Sparepart;
import com.example.smartgarage.Model.Speciality;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface SmartGarageApi {

//    mechanician api endpoints

    @GET("mechanicians")
    Call<List<Mechanician>> getMechanicians();

     @GET("mechanicians/{id}")
    Call<List<Mechanician>> getMechanician(@Path("id") int mechId);

    @POST("mechanicians")
    Call<Mechanician> createMechanician(@Body Mechanician mechanician);

//        @POST("mechanicians")
//    Call<Mechanician> createMechanician(@QueryMap Map<String, String> fields);

//    @PUT("mechanicians/{id}"
//    Call<Mechanician> updateMechanicianCall(@Path("id") int mechId, @Body Mechanician mechanician);

//    @PUT("mechanicians/{id}")
//    Call<Mechanician> updateMechanician(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @PATCH("mechanicians/{id}")
    Call<Mechanician> updateMechanician(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @DELETE("mechanicians/{id}")
    Call<List<Mechanician>> deleteMechanician(@Path("id") int mechId);


//    garage api endpoints


    @GET("garages")
    Call<List<Garage>> getGarages();

    @GET("garages/{id}")
    Call<List<Garage>> getGarage(@Path("id") int mechId);

    @POST("garages")
    Call<Garage> createGarage(@Body Garage Garage);

    @PATCH("garages/{id}")
    Call<Garage> updateGarage(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @DELETE("garages/{id}")
    Call<List<Garage>> deleteGarage(@Path("id") int mechId);

    //    Sparepart Store api endpoints

    @GET("spareparts")
    Call<List<Sparepart>> getSpareparts();

    @GET("spareparts/{id}")
    Call<List<Sparepart>> getSparepart(@Path("id") int mechId);

    @POST("spareparts")
    Call<Sparepart> createSparepart(@Body Sparepart Sparepart);

    @PATCH("spareparts/{id}")
    Call<Sparepart> updateSparepart(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @DELETE("spareparts/{id}")
    Call<List<Sparepart>> deleteSparepart(@Path("id") int mechId);

    //    Service Store api endpoints

    @GET("services")
    Call<List<Service>> getServices();

    @GET("services/{id}")
    Call<List<Service>> getService(@Path("id") int mechId);

    @POST("services")
    Call<Service> createService(@Body Service Service);

    @PATCH("services/{id}")
    Call<Service> updateService(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @DELETE("services/{id}")
    Call<List<Service>> deleteService(@Path("id") int mechId);
    //    Specialities  api endpoints

    @GET("specialities")
    Call<List<Speciality>> getSpecialities();

    @GET("specialities/{id}")
    Call<List<Speciality>> getSpeciality(@Path("id") int mechId);

    @POST("specialities")
    Call<Speciality> createSpeciality(@Body Speciality Speciality);

    @PATCH("specialities/{id}")
    Call<Speciality> updateSpeciality(@Path("id") int mechId, @QueryMap Map<String, String> fields);

    @DELETE("specialities/{id}")
    Call<List<Speciality>> deleteSpeciality(@Path("id") int mechId);


}