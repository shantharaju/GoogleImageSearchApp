package com.example.sjayanna.googleimagesearch.models;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sjayanna on 2/1/15.
 */
public class ImageResult implements Serializable {
    public String fullUrl;
    public String tbUrl;
    public String title;

    public String getFullUrl() {
        return fullUrl;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public String getTitle() {
        return title;
    }

    public ImageResult(JSONObject jsonObject) {
        try {
            fullUrl = jsonObject.getString("url");
            tbUrl = jsonObject.getString("tbUrl");
            title = jsonObject.getString("title");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {
        ArrayList<ImageResult> results = new ArrayList<>();
        for(int i = 0; i< array.length(); ++i) {
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

}
