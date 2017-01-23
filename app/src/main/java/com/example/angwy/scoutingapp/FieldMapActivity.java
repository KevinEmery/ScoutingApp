package com.example.angwy.scoutingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class FieldMapActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_map);

        View view = findViewById(R.id.activity_field_map);
        view.setOnTouchListener(handleTouch);
    }

    private OnTouchListener handleTouch = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent(FieldMapActivity.this, TeleOpActivity.class);
            startActivity(intent);
            return true;
        }
    };
}
