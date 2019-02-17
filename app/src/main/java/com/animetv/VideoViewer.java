package com.animetv;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.scraper.SiteTools;

public class VideoViewer extends AppCompatActivity {

    private String url ="";
    private VideoView video;
    private MediaController ctlr;
    private ProgressBar progressBar;
    private RequestQueue request;
    private StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_viewer);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            url = bundle.getString("url");
        }
        else{
            //This case causes errors.
        }




        video = (VideoView) findViewById(R.id.videoView);
        video.setVisibility(View.INVISIBLE);
        ctlr = new MediaController(this);
        ctlr.setVisibility(View.INVISIBLE);
        progressBar= findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



        request = Volley.newRequestQueue(this);
        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                video.setVisibility(View.VISIBLE);
                ctlr.setVisibility(View.VISIBLE);

                String videoUrl= SiteTools.getVideoURL(response);
                Uri uri= Uri.parse(videoUrl);

                video.setVideoURI(uri);
                ctlr.setMediaPlayer(video);
                video.setMediaController(ctlr);
                video.requestFocus();
                video.start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        request.add(stringRequest);



    }
}
