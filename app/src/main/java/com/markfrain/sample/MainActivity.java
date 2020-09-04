package com.markfrain.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton rbLeft = findViewById(R.id.rb_left);
        RadioButton rbRight = findViewById(R.id.rb_right);

        Drawable drawable = getResources().getDrawable(R.drawable.radio_check);

        rbLeft.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        rbRight.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        rbLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("rbLeft", "" + isChecked);
            }
        });
        rbRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("rbRight", "" + isChecked);
            }
        });

    }
}
