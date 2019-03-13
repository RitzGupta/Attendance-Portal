package com.example.android.college;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Attendance_Detail extends AppCompatActivity {

    private static final String URL_DATA = "https://bb95133f.ngrok.io/attendance";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItemAttendance> listItemAttendances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__detail);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItemAttendances  = new ArrayList<>();

        loadRecyclerViewData();
    }
    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                //Fatch the data from JSON file
                try {

                    JSONArray array = new JSONArray(response);

                    /*JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("heros");*/

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        ListItemAttendance item = new ListItemAttendance(
                                o.getString("Subject"),
                                o.getString("Attendance")

                        );
                        listItemAttendances.add(item);
                    }

                    //add RecyclerView.Adapter adapter to MyAdapter class and pass the two parameter to constructor
                    //"MyAdapter" List<ListItem> listItems and contex
                    adapter = new MyAdapterAttendance(listItemAttendances, getApplicationContext());
                    //connect to the recycler to the adapter
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
