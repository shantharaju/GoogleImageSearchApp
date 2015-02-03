package com.example.sjayanna.googleimagesearch.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.sjayanna.googleimagesearch.adapters.ImageResultsAdapter;
import com.example.sjayanna.googleimagesearch.helpers.ImageSearchClient;
import com.example.sjayanna.googleimagesearch.interfaces.EndlessScrollListener;
import com.example.sjayanna.googleimagesearch.models.ImageFilter;
import com.example.sjayanna.googleimagesearch.models.ImageResult;
import com.example.sjayanna.googleimagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    private EditText etQuery;
    // private GridView gvResults;
    private StaggeredGridView gvResults;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;
    String query;
    public static final int FILTER_REQUEST = 50;
    private ImageFilter imageFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, android.R.layout.simple_list_item_1, imageResults);
        gvResults.setAdapter(aImageResults);
        imageFilter = new ImageFilter();
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        // gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults = (StaggeredGridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch Image Display Activity
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                ImageResult result = imageResults.get(position);
                i.putExtra("image", result);
                startActivity(i);
            }
        });
        gvResults.setOnScrollListener( new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });
    }

    private void customLoadMoreDataFromApi(int offset) {
        imageFilter.start= offset;
        ImageSearchClient client = new ImageSearchClient();
        client.getImageResults(imageFilter, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray imageResultJson = response.getJSONObject("responseData").getJSONArray("results");

                    // When you make changes to adapter, it modifies underlying data data. Notifies view too
                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultJson));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Method called whenever btnSearch is clicked
    public void onImageSearch(View v) {
        imageFilter.query = etQuery.getText().toString();
        ImageSearchClient client = new ImageSearchClient();
        client.getImageResults(imageFilter, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray imageResultJson = response.getJSONObject("responseData").getJSONArray("results");

                    imageResults.clear(); // clear only for initial search. Avoid it for pagination
                    // When you make changes to adapter, it modifies underlying data data. Notifies view too
                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultJson));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.miFilter) {
            Intent i = new Intent(SearchActivity.this, FilterActivity.class);
            i.putExtra("filter", imageFilter);
            startActivityForResult(i, FILTER_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == FILTER_REQUEST) {
            if(resultCode == RESULT_OK) {
                imageFilter = (ImageFilter) data.getSerializableExtra("filter");
            }
            /* Toast.makeText(this, imageFilter.query
                         + " " + imageFilter.start
                        + " " + imageFilter.rsz
                        + " " + imageFilter.imgc
                        + " " + imageFilter.as_filetype
                        + " " + imageFilter.as_rights
                        + " " + imageFilter.as_sitesearch
                        + " " + imageFilter.imgtype
                        , Toast.LENGTH_LONG).show();
            */

        }
    }
}
