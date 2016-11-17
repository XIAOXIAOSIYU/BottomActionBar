package com.jackzhao.www.bottomactionbar.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageAsyncTaskHelper extends AsyncTask<String, Void, Bitmap> {

    WeakReference reference;
    ImageView image;

    public GetImageAsyncTaskHelper(ImageView _image) {
        reference = new WeakReference(_image);
        image = (ImageView) reference.get();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        String image_name = strings[0];

        if (image_name.length() > 0 && !image_name.isEmpty() && image_name != null && image_name != "null") {
            try {
                URL url = new URL("http://img.iccyp.com/hizo/businesses/" + strings[0] + ".jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

}
