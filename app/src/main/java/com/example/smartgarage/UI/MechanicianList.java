package com.example.smartgarage.UI;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;
import com.example.smartgarage.adapters.MechanicianAdapter;
import com.example.smartgarage.api.APIClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechanicianList extends AppCompatActivity {
    private MechanicianAdapter adapter;
    private List<Mechanician> exampleList;
    SmartGarageApi smartGarageApi;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanician_list);

        recyclerView = findViewById(R.id.destiny_recycler_view);

        smartGarageApi = APIClient.getClient().create(SmartGarageApi.class);

        Call<List<Mechanician>> call = smartGarageApi.getMechanicians();

        call.enqueue(new Callback<List<Mechanician>>() {
            @Override
            public void onResponse(Call<List<Mechanician>> call, Response<List<Mechanician>> response) {

                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (!response.isSuccessful())
                {
//                    textViewResult.setText("Code " + response.code());
                    return;
                }

//                List<Mechanician> mechanicians = response.body();
                exampleList = new ArrayList<>();
                exampleList.addAll(response.body());

//                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                adapter = new MechanicianAdapter(exampleList, new MechanicianAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Mechanician mechanician) {

//                        List<String> test;
//                        test = new ArrayList<String>();
//                        test.add(Integer.toString(mechanician.getId()));
//                        test.add(mechanician.getNames());
//                        test.add(mechanician.getAddress());
//                        test.add(mechanician.getEmail());
//                        test.add(mechanician.getPhone());

                        Intent intent = new Intent(MechanicianList.this, MechProfile.class);
//                        String mechanicians = mechanician.toString();
//                        intent.putExtra("MECHANICS", mechanicians);
//                        intent.putStringArrayListExtra("test", (ArrayList<String>) test);

                        intent.putExtra("mechanician_id", mechanician.getId());
                        startActivity(intent);
                    }
                });

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Mechanician>> call, Throwable t) {
                Toast.makeText(MechanicianList.this, "slow network! please try again ", Toast.LENGTH_SHORT).show();
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab_addmech);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MechanicianList.this, RegisterMechanician.class);
                startActivity(i);
            }
        });
    }

}
