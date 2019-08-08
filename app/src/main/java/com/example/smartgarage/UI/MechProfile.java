package com.example.smartgarage.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarage.MainActivity;
import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.Model.Speciality;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;
import com.example.smartgarage.api.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechProfile extends AppCompatActivity {

    SmartGarageApi smartGarageApi;
    ImageButton call_mech;
    ImageButton text_mech;
    ImageButton email_mech;

    String phoneNumber, mail;

    TextView name, phone, email, address, list_specialities ,names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mech_profile);
        smartGarageApi = APIClient.getClient().create(SmartGarageApi.class);

        initObjects();
        getMechanician();
        getSpecialities();


        text_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    Log.i("Sending SMS","");
                    Intent I =new Intent(Intent.ACTION_VIEW);

                    I.setData(Uri.parse("smsto:"));
                    I.setType("vnd.android-dir/mms-sms");
                    I.putExtra("address", new String (phone.getText().toString()));
                    I.putExtra("sms_body","Enter your Sms here..");

                    try
                    {
                        startActivity(I);
                        finish();
                        Log.i("Sms Send","");
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(MechProfile.this,"Sms not send",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        email_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",mail = email.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        call_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber= phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                try
                {
                    startActivity(intent);
                    finish();
                }
                catch(Exception e)
                {
                    Toast.makeText(MechProfile.this,"call failed",Toast.LENGTH_LONG).show();
                }
            }
        });

//        Toast.makeText(getApplicationContext(), String.valueOf(intNum), Toast.LENGTH_SHORT).show();

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String value = extras.getString("mechanician_id");
//            //The key argument here must match that used in the other activity
//            Toast.makeText(getApplicationContext(), "")
//        }
    }

    private void getMechanician() {
        Intent intent = getIntent();
        int intNum = intent.getIntExtra("mechanician_id", 1);

        Call<Mechanician> call = smartGarageApi.singleMechanician(intNum);

        call.enqueue(new Callback<Mechanician>() {
            @Override
            public void onResponse(Call<Mechanician> call, Response<Mechanician> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "request sent please wait", Toast.LENGTH_SHORT).show();
                }
                Mechanician postResponse = response.body();
                name.setText(postResponse.getNames());
                names.setText(postResponse.getNames());
                email.setText(postResponse.getEmail());
                phone.setText(postResponse.getPhone());
                address.setText(postResponse.getAddress());

//                String content = "";
//                content += "Code: " + response.code() + "\n";
//                content += "ID: " + postResponse.getId() + "\n";
//
//
//
//                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Mechanician> call, Throwable t) {

            }
        });
    }

    private void initObjects() {
        call_mech = (ImageButton)findViewById(R.id.call_mech);
        text_mech = (ImageButton)findViewById(R.id.text_mech);
        email_mech = (ImageButton)findViewById(R.id.email_mech);
        name =(TextView)findViewById(R.id.occupation);
        phone =(TextView)findViewById(R.id.mobileNumber);
        email =(TextView)findViewById(R.id.gender);
        address =(TextView)findViewById(R.id.marriage);
        list_specialities =(TextView)findViewById(R.id.dob);
        names =(TextView)findViewById(R.id.name);
    }

    public  void  getSpecialities(){
        Intent intent = getIntent();
        int intNum = intent.getIntExtra("mechanician_id", 1);

        Call<List<Speciality>> call = smartGarageApi.getMechSpecialities(intNum);

        call.enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {

                List<Speciality> specialities = response.body();

                for (Speciality speciality : specialities) {

//                    Toast.makeText(getApplicationContext(), speciality.getName(), Toast.LENGTH_SHORT).show();
                    String content = "";
                    content += speciality.getName() + "- ";
//                    content += "User ID: " + mechanician.getNames() + "\n";
//                    content += "Title: " + mechanician.getAddress() + "\n";
//                    content += "Text: " + mechanician.getPhone() + "\n\n";

                    list_specialities.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {

            }
        });
    }

}
