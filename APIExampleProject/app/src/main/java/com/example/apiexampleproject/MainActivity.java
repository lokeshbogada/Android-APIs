package com.example.apiexampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;

    TextView textView;
    JSONHolderAPI jsonHolderAPI;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String url = "https://jsonplaceholder.typicode.com/posts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this);

        textView = findViewById(R.id.textView_result);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        jsonHolderAPI = retrofit.create(JSONHolderAPI.class);

       requestQueue = Volley.newRequestQueue(this);


       // getData();
      //  createPost();
      //  volleyPractice();


    }

    private void volleyPractice() {

       stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               textView.setText(response.toString());

           }

       }, new com.android.volley.Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });

       requestQueue.add(stringRequest);


    }

    private void createPost() {
        Post postParameters = new Post(120479,1,"Paradise","paradise apiexample Project ffgfgwfasgaafefeef");
        Call<Post> call = jsonHolderAPI.createPost(postParameters);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.code()==1){
                    textView.setText(response.code());
                }
                Post post = response.body();

                    String json="";
                    json +="UserID : "+post.getUserId()+"\n";
                    json +="ID : "+post.getId()+"\n";
                    json +="Title : "+post.getTitle()+"\n";
                    json +="text : "+post.getText()+"\n\n";

                    textView.append(json);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getData() {
        Call<List<Post>> call = jsonHolderAPI.getData(new String[]{"2","5"},"userId","desc");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.code()==1){
                    textView.setText(response.code()+"\n");

                }
                List<Post>list = response.body();
                for(Post post:list){
                    String json="";
                    json +="UserID : "+post.getUserId()+"\n";
                    json +="ID : "+post.getId()+"\n";
                    json +="Title : "+post.getTitle()+"\n";
                    json +="text : "+post.getText()+"\n\n";

                    textView.append(json);
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                textView.setText(t.getMessage());

            }
        });

    }
}