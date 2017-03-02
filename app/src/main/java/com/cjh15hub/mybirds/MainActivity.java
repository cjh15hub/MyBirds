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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_BIRD_ID = "com.cjh15hub.mybirds.BIRD_ID";

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
        recyclerView.addItemDecoration(new BackgroundItemDecoration(R.color.colorRowEven,R.color.colorRowOdd));
        recyclerView.setAdapter(bAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bird bird = birds.get(position);
                //Toast.makeText(getApplicationContext(),bird.getName() + " is selected.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Selected_Bird.class);
                Bundle databundle = new Bundle();
                databundle.putInt(EXTRA_BIRD_ID,bird.getID());
                intent.putExtras(databundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        DBAdapter db = new DBAdapter(this);
//                db.deleteAll(); //debug only
//        db.addBird(new Bird("American Goldfinch", "About 5 inches in size, the male changes from a yellowish brown, with light yellow on the face and chin, wings black with white bars."));
//        db.addBird(new Bird("Bald Eagle", "Full grown, these birds weigh between 9-12 pounds and have a wing span of up to 8 feet. A very large dark bird with a white head and white tail. Length between 35-40 inches."));
//        db.addBird(new Bird("Baltimore Oriole", "These birds measure 7 - 8 1/2 inches long. The male bird is black with orange underparts, rump, shoulders, and sides of tail. His wings have 2 white wing bars."));
//
//        db.addBird(new Bird("Eastern Bluebird", "Eastern Bluebirds measure 6 1/2 inches in length. The male has bright blue upperparts, reddish breast, and white belly."));
//        db.addBird(new Bird("Northern Cardinal", "The male is a bright red bird with a pointed crest on the top of his head. The female is mostly buff brown in color with some red on her head, wings, and tail. Both have small black masks on their faces that surround the bill and eyes with the males more pronounced. They measure 8 1/2 inches long."));
//        db.addBird(new Bird("Gray Catbird", "This bird measures about 8 to 9 1/2 inches long. Mainly gray, darkest on wings and tail. Look for a black cap and a rust colored patch under the tail."));
//
//        db.addBird(new Bird("Cedar Waxwing", "The striking head sports an elongate crest with a black face mask and the generally brown and gray plumage has a black chin-throat and a soft yellow belly."));
//        db.addBird(new Bird("American Crow", "The American Crow is 17 to 21 inches in length. All black with a fan-shaped tail. Both male and female are similar in appearance. This birds voice is the best way to distinguish it from other all black birds."));
//        db.addBird(new Bird("Barred Owl", "These birds of prey are large in size. The length of the bird is about 18 inches to 2 feet with females a little larger than males."));
//
//        db.addBird(new Bird("House Finch", "The male House Finch has a length of about 5 1/2 inches, with red on the head, upper breast and flanks. In some regions the color red may be replaced with yellow or orange. This is due to the differences in regional diets."));
//        db.addBird(new Bird("Pigeon", "Pigeons have different colors due to breeding by humans. They are the descendants of the wild Rock Dove of Europe."));
//        db.addBird(new Bird("American Robin", "American Robins have gray upperparts and the familiar reddish breast, varying from pale rust to a dark brick red. Male and females look nearly identical. The females colors are less vibrant."));
        birds.addAll(db.getAllBirds());
        bAdapter.notifyDataSetChanged();
//        db.deleteAll(); //debug only
        Log.d("Count: ", "" + db.getBirdCount());

        for (Bird bird : birds) {
            String log = "Id: "+bird.getID()+" ,Name: " + bird.getName() + " ,Desc: " + bird.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


    }// onCreate end

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}
