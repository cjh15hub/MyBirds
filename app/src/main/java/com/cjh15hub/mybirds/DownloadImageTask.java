package com.cjh15hub.mybirds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by cjh15 on 3/2/2017.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Bird bird;

        public DownloadImageTask(ImageView bmImage, Bird bird) {
            this.bmImage = bmImage;
            this.bird = bird;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            //Log.i("test",MainActivity.CACHE_DIR.getAbsolutePath());
            String cache_path = MainActivity.CACHE_DIR.getAbsolutePath();
            //FileOutputStream fileOutputStream = new FileOutputStream(cache_path);
        }

}
