package com.example.smartgarage.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartgarage.R;
import com.example.smartgarage.UI.FindMeMapsActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout locate = (LinearLayout )findViewById(R.id.findme);

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(Home.this, FindMeMapsActivity.class);
                startActivity(picture_intent );
            }
        });
    }
}
