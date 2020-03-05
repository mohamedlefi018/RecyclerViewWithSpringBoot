package tn.lmedia.recyclerviewspringboot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.LinkedList;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        public final ImageButton btnShow;
        public final ImageButton btnEdit;
        public final ImageButton btnRemove;


        public WordViewHolder(final View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
            btnShow=(ImageButton)itemView.findViewById(R.id.btnShow);
            btnEdit=(ImageButton)itemView.findViewById(R.id.btnEdit);
            btnRemove=(ImageButton)itemView.findViewById(R.id.btnRemove);
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),showUser.class);
                    String txt=wordItemView.getText().toString();
                    String id=txt.substring(0,txt.indexOf(":"));
                    intent.putExtra("id",id);
                    itemView.getContext().startActivity(intent);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),EditUser.class);
                    String txt=wordItemView.getText().toString();
                    String id=txt.substring(0,txt.indexOf(":"));
                    intent.putExtra("id",id);
                    itemView.getContext().startActivity(intent);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt=wordItemView.getText().toString();
                    String id=txt.substring(0,txt.indexOf(":"));
                    removeUser(id,itemView.getContext());

                }
            });

        }

        @Override
        public void onClick(View v) {

            if(btnShow.getVisibility()==View.VISIBLE)
                {
                    btnShow.setVisibility(View.INVISIBLE);
                    btnRemove.setVisibility(View.INVISIBLE);
                    btnEdit.setVisibility(View.INVISIBLE);
                }

            else
                {
                    btnShow.setVisibility(View.VISIBLE);
                    btnRemove.setVisibility(View.VISIBLE);
                    btnEdit.setVisibility(View.VISIBLE);
                }


        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);

    }


    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
        holder.btnShow.setVisibility(View.INVISIBLE);
        holder.btnEdit.setVisibility(View.INVISIBLE);
        holder.btnRemove.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    public void removeUser(String id, final Context context){
        JSONObject user=new JSONObject();
        String url=MainActivity.ip+"/users/deleteUserById/"+id;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        try{
            user.put("id",id);
            RequestQueue requstQueue = Volley.newRequestQueue(context);


        }catch(Exception e){
            Toast.makeText(context,"Erreur au niveau de creation d'objet json",Toast.LENGTH_LONG).show();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"User deleted",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(context,MainActivity.class);
                        context.startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error getting Response : ",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

}