package com.logstacksdk.statsdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tamic on 2016-03-16.
 */
public class SecondActivity extends BaseActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_activity_second);
        button = (Button) findViewById(R.id.second_button);
        button.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

    }
}
