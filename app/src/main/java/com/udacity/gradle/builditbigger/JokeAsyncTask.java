package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.joketellingapp.myApi.MyApi;

import java.io.IOException;

import mynanodegreeapps.com.jokeactivity.JokeActivity;

/**
 * Created by inbkumar01 on 9/5/2017.
 */

public class JokeAsyncTask extends AsyncTask<Pair<Context,String>,Void,String> {

    private static MyApi GCEService = null;
    private Context context;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(GCEService == null){
            // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.2.11:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            GCEService = builder.build();
        }

        context = params[0].first;
       // String name = params[0].second;

        try {
            return GCEService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
        }

    @Override
    protected void onPostExecute(String s) {
        Intent jokeIntent = new Intent(context, JokeActivity.class);
        jokeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        jokeIntent.putExtra("joke",s);
        context.startActivity(jokeIntent);
    }
}