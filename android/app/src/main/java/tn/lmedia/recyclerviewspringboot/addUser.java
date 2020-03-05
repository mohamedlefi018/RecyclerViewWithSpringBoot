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
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;


public class addUser extends AppCompatActivity {
    private Button btnAdd;
    private Button btnCancel;
    private EditText edName;
    private EditText edEmail;
    private EditText edPhone;
    private EditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        edName=(EditText)findViewById(R.id.edName);
        edEmail=(EditText)findViewById(R.id.edEmail);
        edPhone=(EditText)findViewById(R.id.edPhone);
        edPassword=(EditText)findViewById(R.id.edPassword);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
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

    public void addData() {
        JSONObject user=new JSONObject();
        String url=MainActivity.ip+"/users/";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        try{
            user.put("name",edName.getText().toString());
            user.put("phone",edPhone.getText().toString());
            user.put("email",edEmail.getText().toString());
            user.put("password",edPassword.getText().toString());
            RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());


        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Erreur au niveau de creation d'objet json",Toast.LENGTH_LONG).show();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"user added",Toast.LENGTH_LONG).show();
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
