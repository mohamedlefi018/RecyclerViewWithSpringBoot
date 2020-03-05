package tn.lmedia.recyclerviewspringboot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class showUser extends AppCompatActivity {
    private Button btnReturn;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
         Intent i=getIntent();
         String id=i.getStringExtra("id");
         btnReturn=(Button)findViewById(R.id.btnReturn);
         tvName=(TextView)findViewById(R.id.tvName);
         tvEmail=(TextView)findViewById(R.id.tvEmail);
         tvPhone=(TextView)findViewById(R.id.tvPhone);
         tvPassword=(TextView)findViewById(R.id.tvPassword);
         getUser(id);
         btnReturn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(i);
             }
         });
    }

    private void getUser(String id) {
            //Spring Boot IP Address
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
                                    tvName.setText(name);
                                    tvEmail.setText(email);
                                    tvPhone.setText(String.valueOf(phone));
                                    tvPassword.setText(password);
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

}
