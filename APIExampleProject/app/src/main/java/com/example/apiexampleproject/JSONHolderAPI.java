package com.example.apiexampleproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONHolderAPI {
    @GET("posts")
    Call<List<Post>> getData(@Query("userId")String[] uesrId ,
                             @Query("_sort")String sort,
                             @Query("_order")String order);

    @POST("posts")
    Call<Post> createPost(
            @Body Post dmp);

  //  Call<List<Post>> getData(int i);
}
