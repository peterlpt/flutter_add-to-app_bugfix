package com.peter.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // click button to jump flutter activity
        findViewById(R.id.tv_test).setOnClickListener((view) -> {
            Intent intent = BridgeActivity.withNewEngine()
                    .build(MainActivity.this);
            //reset class to flutterActivity's sub class, to make my BridgeActivity.configureFlutterEngine effective
            intent.setClass(MainActivity.this, BridgeActivity.class);
            startActivity(intent);
        });
    }
}
