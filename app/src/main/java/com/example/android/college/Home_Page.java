package com.example.android.college;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home_Page extends AppCompatActivity {


    /*//for json
    private static final String URL_DATA = "https://ccfb10cb.ngrok.io";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RequestOptions option;

*/
    private static final String URL_DATA = "https://950ce165.ngrok.io/event";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    private DrawerLayout myDrawer;
    private ActionBarDrawerToggle myToggle;
    private NavigationView mNavigationView;
    private static final String TAG = "Home_pageActivity";


    //my_header.xml
    private LinearLayout myHeader;
    private ImageView userProfileImageHead;
    private TextView userNameHead, userMailIdHead;
    private CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);


        myDrawer = (DrawerLayout) findViewById(R.id.myDrawer);
        myToggle = new ActionBarDrawerToggle(this, myDrawer, R.string.open, R.string.Close);


        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();


        //Inflate data from another xml file which is not activity xml file(get id in different layout)
        final View inflatedView = getLayoutInflater().inflate(R.layout.my_header, null);
        myHeader = (LinearLayout) inflatedView.findViewById(R.id.profile);
        userProfileImageHead = (ImageView) inflatedView.findViewById(R.id.userProfileImage);
        userNameHead = (TextView) findViewById(R.id.userNameHead);
        userMailIdHead = (TextView) findViewById(R.id.userMailIdHead);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.navigationView);

        //OnClick Navigation Header
        View headerview = mNavigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "hiiiiii");
                startActivity(new Intent(Home_Page.this, UserProfile.class));
            }
        });

        //Onclick Navigation item
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                 /*   case R.id.academiccalender: {
                        startActivity(new Intent(Home_Page.this, AcademicCalender.class));
                        break;
                    }
                 */
                    case R.id.attendance:
                        startActivity(new Intent(Home_Page.this, Attendance.class));
                        break;
                    case R.id.feereciept:
                        Toast.makeText(Home_Page.this, "fee", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.marks:
                        startActivity(new Intent(Home_Page.this, Marks.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(Home_Page.this, UserProfile.class));
                        break;
                    case R.id.timetable:
                        startActivity(new Intent(Home_Page.this, TimeTable.class));
                        break;

                }
                return true;
            }
        });

/*        //Request option for Glide
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);



        jsonRequest();*/




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();



    }//close onCreate


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (myToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }



       /*// Json for header.xml
     private void jsonRequest() {

        request = new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject obj = null;
                try {
                    obj = response.getJSONObject(0);
                    userNameHead.setText(obj.getString("Name"));
                    userMailIdHead.setText(obj.getString("Email"));


                    //Load image from internet and set into Imageview using Glide
                    Glide.with(MainActivity.this)
                            .load(obj.getString("Profile_Pic"))
                            .apply(option)
                            .into(userProfileImageHead);





                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);


    }
*/




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
                        ListItem item = new ListItem(
                                o.getString("Desc"),
                                o.getString("Venue"),
                                o.getString("Image")
                        );
                        listItems.add(item);
                    }

                    //add RecyclerView.Adapter adapter to MyAdapter class and pass the two parameter to constructor
                    //"MyAdapter" List<ListItem> listItems and contex
                    adapter = new MyAdapter(listItems, getApplicationContext());
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

    private static long sayBackPress;

    @Override
    public void onBackPressed() {
        if(myDrawer.isDrawerOpen(GravityCompat.START)) {
            myDrawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
        }
        if (sayBackPress + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(Home_Page.this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }
}
