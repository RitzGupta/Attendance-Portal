package com.example.android.college;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    private String TAG = "SignInActivity";
    private EditText admissionNo, password;
    private Button logIn;
    private String admissionStr, passwordStr;


    //DB
    private static final String URL_DATA = "https://950ce165.ngrok.io/signIn";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RequestOptions option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        admissionNo = (EditText) findViewById(R.id.etAdmissionNo);
        password = (EditText) findViewById(R.id.etPassword);
        logIn = (Button) findViewById(R.id.btnLogIn);

        admissionStr = admissionNo.getText().toString().trim();
        passwordStr = password.getText().toString().trim();


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("hoooooooooo",admissionStr);
                jsonRequest(admissionStr,passwordStr);
            }
        });


    }

    private void jsonRequest(final String admissionStr, final String passwordStr) {

        request = new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject obj = null;
                for (int i = 0; i < response.length(); i++)
                    try {
                        obj = response.getJSONObject(0);
                        Log.e(TAG, "OnResponse Method");
                        Toast.makeText(SignIn.this, obj.getString("AdmissionNo")+"  "+admissionStr, Toast.LENGTH_SHORT).show();
                        if ("Pushpendra".equals(obj.getString("AdmissionNo").trim()) && "kps@9350".equals(obj.getString("Password").trim())) {
                            Intent intent = new Intent(SignIn.this, Home_Page.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                           /* Toast.makeText(SignIn.this, "LogInSuccessfully", Toast.LENGTH_SHORT).show();*/
                        }
                        /*String admissionStr = obj.getString("AdmissionNo").trim();
                        String passwordStr = obj.getString("Password").trim();
                        Intent intent = new Intent(SignIn.this, Home_Page.class);
                        startActivity(intent);
                        Toast.makeText(SignIn.this, "LogInSuccessfully", Toast.LENGTH_SHORT).show();
*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SignIn.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignIn.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new Hashtable<>();
                params.put("admissionStr",admissionStr);
                params.put("passwordStr",passwordStr);
                return super.getParams();
            }
        };
        requestQueue = Volley.newRequestQueue(SignIn.this);
        requestQueue.add(request);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignIn.this,MainActivity.class);
        startActivity(intent);

    }
}
