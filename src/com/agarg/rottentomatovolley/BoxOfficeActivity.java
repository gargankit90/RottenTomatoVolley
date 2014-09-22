package com.agarg.rottentomatovolley;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class BoxOfficeActivity extends Activity {
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_box_office);
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);
        fetchBoxOfficeMovies();
	}
	private void fetchBoxOfficeMovies() {
		// TODO Auto-generated method stub
		// pass second argument as "null" for GET requests
		new RottenTomatoesClient().getBoxOfficeMovies(
		       new Response.Listener<JSONObject>() {
		           @Override
		           public void onResponse(JSONObject response) {
		        	   JSONArray items = null;
		        	   try {
		                   VolleyLog.v("Response:%n %s", response.toString(4));
		                    // Get the movies json array
		                    items = response.getJSONArray("movies");
		                    // Parse json array into array of model objects
		                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
		                    // Load model objects into the adapter
		                    for (BoxOfficeMovie movie : movies) {
		                       adapterMovies.add(movie);
		                    }
		               } catch (JSONException e) {
		                   e.printStackTrace();
		               }
		           }
		       }, new Response.ErrorListener() {
		           @Override
		           public void onErrorResponse(VolleyError error) {
		               VolleyLog.e("Error: ", error.getMessage());
		           }
		       });
	}
}