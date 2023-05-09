package com.example.mytranslate;
import java.util.Map;


import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Link {
    @FormUrlEncoded
    @POST("api/v1/dicservice.json/lookup")
     Call<Object> dicservice(@FieldMap Map<String,String> map);
    //Call<Model> dicservice(@Query("key") String key, @Query("q") String q, @Query("lang") String lang);
}
// @POST("/api/v1.5/tr.json/translate")
