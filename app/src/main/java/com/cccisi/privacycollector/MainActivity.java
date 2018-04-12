package com.cccisi.privacycollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.cccisi.privacycollector.Czh.CzhActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";

    private ImageButton mImageButton_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        mImageButton_Password = (ImageButton) findViewById(R.id.imageButton_Password);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_Password:
                Intent intent = new Intent(MainActivity.this, CzhActivity.class);
                intent.putExtra("MainActivity", TAG);
                startActivity(intent);
        }

    }
}
