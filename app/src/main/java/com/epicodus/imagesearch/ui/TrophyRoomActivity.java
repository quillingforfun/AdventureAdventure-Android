package com.epicodus.imagesearch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.imagesearch.Constants;
import com.epicodus.imagesearch.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrophyRoomActivity extends AppCompatActivity implements View.OnClickListener{


    @Bind(R.id.eyeButton) Button mEyeButton;
    @Bind(R.id.hatButton) Button mHatButton;
    @Bind(R.id.handprintButton) Button mHandprintButton;
    @Bind(R.id.yarnButton) Button mYarnButton;
    @Bind(R.id.pinsButton) Button mPinsButton;
    @Bind(R.id.hookButton) Button mHookButton;
    @Bind(R.id.timerView) TextView mTimerView;
    @Bind(R.id.hintView) TextView mHintView;
    private Integer youWin;
    private Integer winNumber;
    Timer mTimer;
    TimerTask task;
    Integer timeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_room);
        ButterKnife.bind(this);
        youWin = 0;
        winNumber = 6;

        mEyeButton.setOnClickListener(this);
        mHatButton.setOnClickListener(this);
        mHandprintButton.setOnClickListener(this);
        mPinsButton.setOnClickListener(this);
        mYarnButton.setOnClickListener(this);
        mHookButton.setOnClickListener(this);
        mTimerView = (TextView) findViewById(R.id.timerView);
        timeRemaining = 60;

        advance(youWin);
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeRemaining --;
                        System.out.println(timeRemaining);
                        mTimerView.setText(timeRemaining.toString());
                        if (timeRemaining == 0) {
                            mTimer.cancel();
                            mTimer.purge();
                            Toast.makeText(getApplicationContext(), "FAILURE!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        };

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(task, 1000, 1000);
    }


    @Override
    public void onClick(View view) {
        if (view == mEyeButton ) {
            Toast.makeText(getApplicationContext(), "OW MY EYE", Toast.LENGTH_SHORT).show();
            mHandprintButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mHandprintButton) {
            Toast.makeText(getApplicationContext(), "Such ominous", Toast.LENGTH_SHORT).show();
            mHatButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mHatButton) {
            Toast.makeText(getApplicationContext(), "topo'themornintoya", Toast.LENGTH_SHORT).show();
            mYarnButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mYarnButton){
            Toast.makeText(getApplicationContext(), "Very Yarn", Toast.LENGTH_SHORT).show();
            mHookButton.setOnClickListener(this);
            advance(youWin);
        }

        if(view == mHookButton){
            Toast.makeText(getApplicationContext(), "Hooky Wooky", Toast.LENGTH_SHORT).show();
            mPinsButton.setOnClickListener(this);
            advance(youWin);
        }

        if(view == mPinsButton){
            Toast.makeText(getApplicationContext(), "TALKIN BOUT PINS", Toast.LENGTH_SHORT).show();
            advance(youWin);
        }

        view.setOnClickListener(null);
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mTimer.cancel();
        Intent intent = new Intent(TrophyRoomActivity.this, EgyptActivity.class);
        startActivity(intent);
    }
    private void advance(int stage) {
        if (stage == 6 && timeRemaining != 0) {
            winFunction();
        } else if (timeRemaining != 0) {
            mHintView.setText(Constants.TROPHY_HINTS[stage]);
            youWin ++;
        }
    }
}
