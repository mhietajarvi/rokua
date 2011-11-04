package com.boogiesoftware.hackdays.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    PhysicsWorld mWorld;
    private Handler mHandler;


    TextView textView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                mWorld = new PhysicsWorld();
                mWorld.create();


                //for (int i = 0; i < 50; i++) {
                    mWorld.addBall();
                //}

                // Start Regular Update
                mHandler = new Handler();
                mHandler.post(update);
            }
        });
        textView = (TextView) findViewById(R.id.foo);
        // Add 50 Balls


    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(update);
    }

    private Runnable update = new Runnable() {
        public void run() {
            mWorld.update();
            mHandler.postDelayed(update, (long) (mWorld.timeStep * 10));
        }
    };
}
