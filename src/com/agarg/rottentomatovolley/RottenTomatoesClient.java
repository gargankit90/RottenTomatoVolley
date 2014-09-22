package com.agarg.rottentomatovolley;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

public class RottenTomatoesClient {
    private final String API_KEY = "ENTER YOUR KEY";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
//    private AsyncHttpClient client;

    public RottenTomatoesClient() {
//        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl + "?apikey="+API_KEY;
    }
    
    // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
    public void getBoxOfficeMovies(Response.Listener<JSONObject> handler, Response.ErrorListener errorHandler) {
        String url = getApiUrl("lists/movies/box_office.json");
        JsonObjectRequest req = new JsonObjectRequest(url, null,handler, errorHandler);
        //        RequestParams params = new RequestParams("apikey", API_KEY);
//        client.get(url, params, handler);
     // add the request object to the queue to be executed
     ApplicationController.getInstance().addToRequestQueue(req);
    }
}
