package tn.lmedia.recyclerviewspringboot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditUser extends AppCompatActivity {
    private Button btnEdit;
    private Button btnCancel;
    private EditText edId;
    private EditText edName;
    private EditText edEmail;
    private EditText edPhone;
    private EditText edPassword;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        edId=(EditText)findViewById(R.id.edId);
        edName=(EditText)findViewById(R.id.edName);
        edEmail=(EditText)findViewById(R.id.edEmail);
        edPhone=(EditText)findViewById(R.id.edPhone);
        edPassword=(EditText)findViewById(R.id.edPassword);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        getUser(id);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Operation canceled",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void getUser(String id) {
        String url = MainActivity.ip+"/users/getUserById/"+id;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject o=new JSONObject(response);
                            String name=o.getString("name");
                            String email=o.getString("email");
                            String password=o.getString("password");
                            int phone=o.getInt("phone");
                            edId.setText(String.valueOf(o.getInt("id")));
                            edName.setText(name);
                            edEmail.setText(email);
                            edPhone.setText(String.valueOf(phone));
                            edPassword.setText(password);
                        }catch (JSONException error) {
                            Toast.makeText(getApplicationContext(),"Liste vide",Toast.LENGTH_LONG).show();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Erreur...", Toast.LENGTH_LONG).show();
                    }
                }
        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void editUser() {
        JSONObject user=new JSONObject();
        String url=MainActivity.ip+"/users/";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        try{
            user.put("id",edId.getText().toString());
            user.put("name",edName.getText().toString());
            user.put("phone",edPhone.getText().toString());
            user.put("email",edEmail.getText().toString());
            user.put("password",edPassword.getText().toString());
            RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());


        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Erreur au niveau de creation d'objet json",Toast.LENGTH_LONG).show();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"user edited",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error getting Response : ",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

}
