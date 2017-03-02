package com.cjh15hub.mybirds;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button activy_mover;
    Activity _mActivity;

    List<Bird> birds;
    RecyclerView recyclerView;
    BirdListAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _mActivity = this;
        birds = new ArrayList<>();
//        activy_mover = (Button) (findViewById(R.id.activity_mover));
//        activy_mover.setOnClickListener(new View.OnClickListener() {v
//            @Override
//            public void onClick(View v) {
//                Intent selectedActivity = new Intent(v.getContext(),Bird_Selected.class);
//                selectedActivity.putExtra("birdId",0);
//                _mActivity.startActivity(selectedActivity);
//            }
//        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        bAdapter = new BirdListAdapter(birds);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(bAdapter);

        DBAdapter db = new DBAdapter(this);

        db.addBird(new Bird("Bluejay", "A blue bird"));
        db.addBird(new Bird("Redjay", "A red bird"));
        db.addBird(new Bird("Greenjay", "A green bird"));

        db.addBird(new Bird("Bluejay", "A blue bird"));
        db.addBird(new Bird("Redjay", "A red bird"));
        db.addBird(new Bird("Greenjay", "A green bird"));

        db.addBird(new Bird("Bluejay", "A blue bird"));
        db.addBird(new Bird("Redjay", "A red bird"));
        db.addBird(new Bird("Greenjay", "A green bird"));

        db.addBird(new Bird("Bluejay", "A blue bird"));
        db.addBird(new Bird("Redjay", "A red bird"));
        db.addBird(new Bird("Greenjay", "A green bird"));
        birds.addAll(db.getAllBirds());
        bAdapter.notifyDataSetChanged();


        Log.d("Count: ", "" + db.getBirdCount());

        for (Bird bird : birds) {
            String log = "Id: "+bird.getID()+" ,Name: " + bird.getName() + " ,Desc: " + bird.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        db.deleteAll(); //debug only
    }



}
