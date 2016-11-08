package com.example.solar.week4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by solar on 2016-11-07.
 */

public class ProfileDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);

        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("name");
        TextView textView = (TextView) findViewById(R.id.tV_profileName);
        textView.setText(s);
    }
}
