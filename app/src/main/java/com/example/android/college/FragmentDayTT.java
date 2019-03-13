package com.example.android.college;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.college.DaysContentTT;
import com.example.android.college.R;
import com.example.android.college.RecyclerViewAdapterTT;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentDayTT extends Fragment {

    View v;
    private static final String TAG = "TimeTableActivity";
    private final String JSON_URL = "https://950ce165.ngrok.io/timeTable";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RecyclerView myreyclerview;
    private List<DaysContentTT> lstContent;

    public FragmentDayTT() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.day_fragment_tt, container, false);
        myreyclerview  = (RecyclerView)v.findViewById(R.id.day_recyclerview);
        RecyclerViewAdapterTT recyclerViewAdapter= new RecyclerViewAdapterTT(getContext(), lstContent);
        myreyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myreyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lstContent = new ArrayList<>();
        jsonrequest();
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        DaysContentTT daysContentTT= new DaysContentTT();

                        daysContentTT.setTeacherName(jsonObject.getString("TeacherName"));
                        daysContentTT.setSubjectName(jsonObject.getString("SubjectName"));
                        daysContentTT.setSubjectCode(jsonObject.getString("SubjectCode"));
                        lstContent.add(daysContentTT);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }



}
