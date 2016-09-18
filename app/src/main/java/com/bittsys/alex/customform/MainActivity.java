package com.bittsys.alex.customform;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bittsys.alex.customforms.CustomColorPicker;

public class MainActivity extends AppCompatActivity {
    CustomColorPicker ccp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ccp = (CustomColorPicker)findViewById(R.id.custom_color_picker_1);
        ccp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccp.setColor(Color.parseColor("#C0C0C0"));
            }
        });*/
    }
}
