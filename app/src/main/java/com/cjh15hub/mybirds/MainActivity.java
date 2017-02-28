package com.cjh15hub.mybirds;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button activy_mover;
    Activity _mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _mActivity = this;
//        activy_mover = (Button) (findViewById(R.id.activity_mover));
//        activy_mover.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent selectedActivity = new Intent(v.getContext(),Bird_Selected.class);
//                selectedActivity.putExtra("birdId",0);
//                _mActivity.startActivity(selectedActivity);
//            }
//        });
        DBAdapter db = new DBAdapter(this);
        List<Bird> birds = db.getAllBirds();
        for (Bird bird : birds) {
            String log = "Id: "+bird.getID()+" ,Name: " + bird.getName() + " ,Desc: " + bird.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

    }

    public void clearDB(){
        DBAdapter db = new DBAdapter(this);
        List<Bird> birds = db.getAllBirds();
        for (Bird bird : birds) {
            db.deleteBird(bird);
        }
    }

}
