package com.example.sjayanna.googleimagesearch.helpers;

import com.example.sjayanna.googleimagesearch.models.ImageFilter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by sjayanna on 2/2/15.
 */
public class ImageSearchClient {
    private final String baseUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0";
    private AsyncHttpClient client;

    public ImageSearchClient() {
        client = new AsyncHttpClient();
    }

    public void getImageResults(ImageFilter imageFilter, JsonHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("q", imageFilter.query);
        params.put("rsz", imageFilter.rsz);
        params.put("start", imageFilter.start);
        if(imageFilter.imgsz != null)
            params.put("size", imageFilter.imgsz);
        if (imageFilter.imgc != null)
            params.put("imgc", imageFilter.imgc);
        if(imageFilter.as_filetype != null)
            params.put("as_filetype", imageFilter.as_filetype);
        if(imageFilter.as_rights != null)
            params.put("as_rights", imageFilter.as_rights);
        if(imageFilter.as_sitesearch != null)
            params.put("as_sitesearch", imageFilter.as_sitesearch);
        if(imageFilter.imgtype != null)
            params.put("imgtype", imageFilter.imgtype);

        client.get(baseUrl, params, handler);
    }
}
