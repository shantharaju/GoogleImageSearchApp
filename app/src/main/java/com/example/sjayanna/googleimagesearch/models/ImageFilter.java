package com.example.sjayanna.googleimagesearch.models;

import java.io.Serializable;

/**
 * Created by sjayanna on 2/1/15.
 */
public class ImageFilter implements Serializable {
    public String query;
    public int start;
    public int rsz;
    public String imgsz;
    public String imgc;
    public String as_filetype;
    public String as_sitesearch;
    public String as_rights;
    public String imgtype;

    public ImageFilter() {
        query = null;
        start = 0;
        rsz = 8;
        clear();
    }
    public void clear() {
        imgsz= null;
        imgc = null;
        as_filetype = null;
        as_rights = null;
        as_sitesearch = null;
        imgtype = null;
    }

}
