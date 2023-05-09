package com.example.mytranslate;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
//https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=API-ключ&lang=en-ru&text=time
public interface Link2 {
     @GET("api/v1/dicservice.json/lookup")
   //  Call<Model> dicservice(@Query("key") String key, @Query("lang") String lang, @Query("text") String text);
     Call<ResponseBody> dicservice(@Query("key") String key, @Query("lang") String lang, @Query("text") String text);
    }