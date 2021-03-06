package com.cjh15hub.mybirds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Selected_Bird extends AppCompatActivity {


    TextView birdName;
    TextView descText;
    ImageView primaryImage;

    Button activy_mover;
    Activity _mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected__bird);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bird Record");
        setSupportActionBar(toolbar);
        _mActivity = this;

        activy_mover = (Button) (findViewById(R.id.location_button));
        activy_mover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedActivity = new Intent(v.getContext(),MapsActivity.class);
                selectedActivity.putExtra("birdId",0);
                _mActivity.startActivity(selectedActivity);
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        birdName =(TextView) findViewById(R.id.birdName);
        descText =(TextView) findViewById(R.id.desc_Text);
        primaryImage= (ImageView) findViewById(R.id.primaryImage);
        //Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        int receivedID = extras.getInt(MainActivity.EXTRA_BIRD_ID);

        DBAdapter db = new DBAdapter(this);

        Bird this_bird =  db.getBird(receivedID);
        birdName.setText(this_bird.getName());
        descText.setText(this_bird.getDescription());
        String s = this_bird.getImageURL();
        new DownloadImageTask(primaryImage,this_bird)
                .execute(s);

    }

}
