package com.example.android.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile extends AppCompatActivity {

    private static final String URL_DATA = "https://950ce165.ngrok.io"+"/student";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RequestOptions option;
    ImageView profileImage;
    TextView profileName, admissionNo, rollNo, erollNo, email, mobile, program, branch, semester, dob, bloodGroup, father, mother, pincode, address, permanantAddress;

    //Variable from my_header.xml
    private LinearLayout myHeader;
    private ImageView userProfileImage;
    private TextView userNameHead, userMailIdHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        //Ids of attributes
        profileImage = (ImageView) findViewById(R.id.profile_image);
        profileName = (TextView) findViewById(R.id.tvProfileName);
        admissionNo = (TextView) findViewById(R.id.tvAdmissionNumber);
        rollNo = (TextView) findViewById(R.id.tvRollNo);
        erollNo = (TextView) findViewById(R.id.tvErollNo);
        email = (TextView) findViewById(R.id.tvEmial);
        mobile = (TextView) findViewById(R.id.tvMobile);
        program = (TextView) findViewById(R.id.tvProgram);
        branch = (TextView) findViewById(R.id.tvBranch);
        semester = (TextView) findViewById(R.id.tvSemester);
        dob = (TextView) findViewById(R.id.tvDOB);
        bloodGroup = (TextView) findViewById(R.id.tvBloodGroup);
        father = (TextView) findViewById(R.id.tvFatherName);
        mother = (TextView) findViewById(R.id.tvMotherName);
        pincode = (TextView) findViewById(R.id.tvPinCode);
        address = (TextView) findViewById(R.id.tvAddress);
        permanantAddress = (TextView) findViewById(R.id.tvPermanentAddress);
        //Request option for Glide
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);


        jsonRequest();
    }

    private void jsonRequest() {
        request = new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject obj = null;
                try {
                    obj = response.getJSONObject(0);
                    profileName.setText(obj.getString("Name"));
                    admissionNo.setText(obj.getString("Admission_No"));
                    rollNo.setText(obj.getString("Roll_No"));
                    erollNo.setText(obj.getString("EN._No"));
                    email.setText(obj.getString("Email"));
                    mobile.setText(obj.getString("Mobile"));
                    program.setText(obj.getString("Program"));
                    branch.setText(obj.getString("Branch"));
                    semester.setText(obj.getString("Semester"));
                    dob.setText(obj.getString("D.O.B"));
                    bloodGroup.setText(obj.getString("Blood_Group"));
                    father.setText(obj.getString("Father"));
                    mother.setText(obj.getString("Mother"));
                    pincode.setText(obj.getString("Pincode"));
                    address.setText(obj.getString("Address"));
                    permanantAddress.setText(obj.getString("Permanent_Address"));

                    //Load image from internet and set into Imageview using Glide
                    Glide.with(UserProfile.this)
                            .load(obj.getString("Profile_Pic"))
                            .apply(option)
                            .into(profileImage);





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
        requestQueue = Volley.newRequestQueue(UserProfile.this);
        requestQueue.add(request);

    }
}
