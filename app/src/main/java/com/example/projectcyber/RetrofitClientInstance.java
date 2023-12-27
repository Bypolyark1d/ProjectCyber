package com.example.projectcyber;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://192.168.100.11:8080";
    public static final String SOCKET_URL = "ws://192.168.146.39:8080";
    public static String JSESSION_ID;

    public static String USER_ID = null;

    public static String USERNAME = null;
    public static String ACCESS_TOKEN = null;


    public static MyApi getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MyApi.class);
    }
    public static class ImageBitmapUriTask extends AsyncTask<String, Void, Void> {

        private ImageView imageView;
        private Activity activity;

        public ImageBitmapUriTask(Activity acitivty, ImageView imageView) {
            this.activity = acitivty;
            this.imageView = imageView;
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                activity.runOnUiThread(new SetImageTask(imageView, myBitmap));
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

    }
    public static class SetImageTask implements Runnable {
        private ImageView imageView;
        private Bitmap bitmap;
        public SetImageTask(ImageView imageView, Bitmap bitmap) {
            this.imageView = imageView;
            this.bitmap = bitmap;
        }

        @Override
        public void run() {
            imageView.setImageBitmap(bitmap);
        }
    }
}

