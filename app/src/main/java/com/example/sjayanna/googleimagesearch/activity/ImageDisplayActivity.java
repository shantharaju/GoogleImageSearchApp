package com.example.sjayanna.googleimagesearch.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.diegocarloslima.byakugallery.lib.TouchImageView;
import com.example.sjayanna.googleimagesearch.R;
import com.example.sjayanna.googleimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class ImageDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        // pull out url from the intent
        ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("image");
         ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);
        // TouchImageView ivImageResult = (TouchImageView) findViewById(R.id.ivImageResult);
        Picasso.with(this).load(imageResult.getFullUrl()).into(ivImageResult);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
