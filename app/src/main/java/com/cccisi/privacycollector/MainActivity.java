package com.cccisi.privacycollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cccisi.privacycollector.czh.CzhActivity;
import com.cccisi.privacycollector.lyc.LycActivity;
import com.cccisi.privacycollector.xsy.XsyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.imageView_Introduction)
    ImageView mImageViewIntroduction;
    @BindView(R.id.imageButton_Password)
    ImageButton mImageButtonPassword;
    @BindView(R.id.imageButton_BasicInformation)
    ImageButton mImageButtonBasicInformation;
    @BindView(R.id.imageButton_Recorder)
    ImageButton mImageButtonRecorder;
    @BindView(R.id.imageButton_Note)
    ImageButton mImageButtonNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.imageButton_Password:
//                Intent intent = new Intent(MainActivity.this, CzhActivity.class);
//                intent.putExtra("MainActivity", TAG);
//                MainActivity.this.startActivity(intent);
//                break;
//            default:
//        }
//
//    }

    @OnClick({R.id.imageView_Introduction, R.id.imageButton_Password, R.id.imageButton_BasicInformation, R.id.imageButton_Recorder, R.id.imageButton_Note})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton_Password:
                Intent intent_czh = new Intent(MainActivity.this, CzhActivity.class);
                intent_czh.putExtra("CzhActivity", TAG);
                MainActivity.this.startActivity(intent_czh);
                break;
            case R.id.imageButton_BasicInformation:
                Intent intent_xsy = new Intent(MainActivity.this, XsyActivity.class);
                intent_xsy.putExtra("XsyActivity", TAG);
                MainActivity.this.startActivity(intent_xsy);
                break;
            case R.id.imageButton_Recorder:
                Intent intent_lyc = new Intent(MainActivity.this, LycActivity.class);
                intent_lyc.putExtra("LycActivity", TAG);
                MainActivity.this.startActivity(intent_lyc);
                break;
            case R.id.imageButton_Note:
                Intent intent_note = new Intent(MainActivity.this, NoteActivity.class);
                intent_note.putExtra("NoteActivity", TAG);
                MainActivity.this.startActivity(intent_note);
                break;
            case R.id.imageView_Introduction:
                Intent intent_introduction = new Intent(MainActivity.this, IntroductionActivity.class);
                intent_introduction.putExtra("NoteActivity", TAG);
                MainActivity.this.startActivity(intent_introduction);
                break;
        }
    }
}
