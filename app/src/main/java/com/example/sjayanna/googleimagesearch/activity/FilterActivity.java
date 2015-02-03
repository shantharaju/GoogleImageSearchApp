package com.example.sjayanna.googleimagesearch.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sjayanna.googleimagesearch.R;
import com.example.sjayanna.googleimagesearch.models.ImageFilter;

public class FilterActivity extends ActionBarActivity {
    ImageFilter imageFilter;

    Spinner spImgsz;
    Spinner spImgc;
    Spinner spAsFileType;
    Spinner spAsRights;
    Spinner spImgType;
    EditText etAsSiteSearch;

    // EditText etColorFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setupViews();
        // get the image filter parameters
        imageFilter = (ImageFilter) getIntent().getSerializableExtra("filter");
        populateView();
    }

    // return index of the given string in spinner
    int getIndex(Spinner spinner, String value) {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).equals(value)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void populateView() {

        // Filters should persist between calls
        if(imageFilter.imgsz != null) {
            spImgsz.setSelection(getIndex(spImgsz, imageFilter.imgsz));
        }

        if(imageFilter.imgc != null) {
            spImgc.setSelection(getIndex(spImgc, imageFilter.imgc));
        }
        if(imageFilter.as_filetype != null) {
            spAsFileType.setSelection(getIndex(spAsFileType, imageFilter.as_filetype));
        }
        if(imageFilter.as_rights != null) {
            spAsRights.setSelection(getIndex(spAsRights, imageFilter.as_rights));
        }
        if(imageFilter.imgtype != null) {
            spImgType.setSelection(getIndex(spImgType, imageFilter.imgtype));
        }
        if(imageFilter.as_sitesearch != null) {
            etAsSiteSearch.setText(imageFilter.as_sitesearch);
        }

    }

    private void setupViews() {
        spImgsz = (Spinner) findViewById(R.id.spImgsz);
        spImgc = (Spinner) findViewById(R.id.spImgc);
        spAsFileType = (Spinner) findViewById(R.id.spAsFileType);
        spAsRights = (Spinner) findViewById(R.id.spAsRights);
        spImgType = (Spinner) findViewById(R.id.spImgType);
        etAsSiteSearch = (EditText) findViewById(R.id.etAsSiteSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);

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

    public void onSave(View view) {
        // populate imageFilter model object. If any of these set to 'any*' set respective attribute to null
        imageFilter.imgsz = (spImgsz.getSelectedItemPosition() == 0) ? null : spImgsz.getSelectedItem().toString();
        imageFilter.imgc = (spImgc.getSelectedItemPosition() == 0) ? null : spImgc.getSelectedItem().toString();
        imageFilter.as_filetype = (spAsFileType.getSelectedItemPosition() == 0) ? null : spAsFileType.getSelectedItem().toString();
        imageFilter.as_rights = (spAsRights.getSelectedItemPosition() == 0) ? null : spAsRights.getSelectedItem().toString();
        imageFilter.imgtype = (spImgType.getSelectedItemPosition() == 0) ? null : spImgType.getSelectedItem().toString();
        imageFilter.as_sitesearch = (etAsSiteSearch.getText().toString() == "") ? null : etAsSiteSearch.getText().toString();

        Intent i = new Intent();
        i.putExtra("filter", imageFilter);
        setResult(RESULT_OK, i);
        this.finish();
    }

    public void onClear(View view) {
        imageFilter.clear();
        clearView();
    }

    private void clearView() {
        spImgsz.setSelection(0);
        spImgc.setSelection(0);
        spAsFileType.setSelection(0);
        spAsRights.setSelection(0);
        spImgType.setSelection(0);
        etAsSiteSearch.setText("");
    }
}
