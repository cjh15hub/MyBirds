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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStorageState;
import static android.os.Environment.isExternalStorageEmulated;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_BIRD_ID = "com.cjh15hub.mybirds.BIRD_ID";
    public static File CACHE_DIR;

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

        CACHE_DIR = getExternalCacheDir();
        if(isExternalStorageEmulated(CACHE_DIR)) CACHE_DIR = getCacheDir();
        //Log.i("main activity",CACHE_DIR.getAbsolutePath());

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
        recyclerView.addItemDecoration(new RVItemBackgroundDecoration());
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
        Log.e("","deleting all");
        db.deleteAll(); //debug only
        db.addBird(new Bird("American Goldfinch", "About 5 inches in size, the male changes from a yellowish brown, with light yellow on the face and chin, wings black with white bars.","http://www.wild-bird-watching.com/images/Goldfinch-at-Wetlands.jpg"));
        db.addBird(new Bird("Bald Eagle", "Full grown, these birds weigh between 9-12 pounds and have a wing span of up to 8 feet. A very large dark bird with a white head and white tail. Length between 35-40 inches.","http://www.wild-bird-watching.com/images/xbaldeagleflying.jpg.pagespeed.ic.NZio60v0ry.jpg"));
        db.addBird(new Bird("Baltimore Oriole", "These birds measure 7 - 8 1/2 inches long. The male bird is black with orange underparts, rump, shoulders, and sides of tail. His wings have 2 white wing bars.","http://www.wild-bird-watching.com/images/Oriole.jpg"));

        db.addBird(new Bird("Eastern Bluebird", "Eastern Bluebirds measure 6 1/2 inches in length. The male has bright blue upperparts, reddish breast, and white belly.","http://www.wild-bird-watching.com/images/xbluebird-folks-park.jpg.pagespeed.ic.dzwi9jLnrp.jpg"));
        db.addBird(new Bird("Northern Cardinal", "The male is a bright red bird with a pointed crest on the top of his head. The female is mostly buff brown in color with some red on her head, wings, and tail. Both have small black masks on their faces that surround the bill and eyes with the males more pronounced. They measure 8 1/2 inches long.","http://www.wild-bird-watching.com/images/xNorthern-Cardinal.jpg.pagespeed.ic.t3bFprJ8i8.jpg"));
        db.addBird(new Bird("Gray Catbird", "This bird measures about 8 to 9 1/2 inches long. Mainly gray, darkest on wings and tail. Look for a black cap and a rust colored patch under the tail.","http://www.wild-bird-watching.com/images/catbird-2014.jpg"));

        db.addBird(new Bird("Cedar Waxwing", "The striking head sports an elongate crest with a black face mask and the generally brown and gray plumage has a black chin-throat and a soft yellow belly.","http://www.wild-bird-watching.com/images/cedar%20waxwing%20nest.jpg"));
        db.addBird(new Bird("American Crow", "The American Crow is 17 to 21 inches in length. All black with a fan-shaped tail. Both male and female are similar in appearance. This birds voice is the best way to distinguish it from other all black birds.","http://www.wild-bird-watching.com/images/crow-2014.jpg"));
        db.addBird(new Bird("Barred Owl", "These birds of prey are large in size. The length of the bird is about 18 inches to 2 feet with females a little larger than males.","http://www.wild-bird-watching.com/images/barred-ow.jpg"));

        db.addBird(new Bird("House Finch", "The male House Finch has a length of about 5 1/2 inches, with red on the head, upper breast and flanks. In some regions the color red may be replaced with yellow or orange. This is due to the differences in regional diets.","http://www.wild-bird-watching.com/images/House-Finch-350.jpg"));
        db.addBird(new Bird("Pigeon", "Pigeons have different colors due to breeding by humans. They are the descendants of the wild Rock Dove of Europe.","http://www.wild-bird-watching.com/images/xpigeontop1.jpg.pagespeed.ic.lPHRh26exd.jpg"));
        db.addBird(new Bird("American Robin", "American Robins have gray upperparts and the familiar reddish breast, varying from pale rust to a dark brick red. Male and females look nearly identical. The females colors are less vibrant.","http://www.wild-bird-watching.com/images/xRobin.jpg.pagespeed.ic.lnxfKTh9zK.jpg"));
        db.addBird(new Bird("Fake Bird","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\",comes from a line in section 1.10.32. \r\n\t There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",""));
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
