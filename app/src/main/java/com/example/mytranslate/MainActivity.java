package com.example.mytranslate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText twords;
    private TextView trans;
    private Button trBtn;
    private final String Url = "https://dictionary.yandex.net";
    //private final String KEY = "trnsl.1.1.20180";
    //private final String KEY = "trnsl.1.1.20180505T062052Z.94bd11862211dbbb.e401116ce3f22cb3bcb79be0982ab20c17dbd592";
    private final String KEY = "dict.1.1.20210416T180752Z.83b08b9784c639d1.4e8477cdecce71828fdf1bdac02a0337c2d74b1c";
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Url)
            .build();
    private Link2 intf = retrofit.create(Link2.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //устанавливаем политики на потоки,
        //они регулируют список операций разрешенных и запрещенных к выполнению в потоках
        //разрешаем для основного потока работать с сетью и дисками
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        twords = (EditText) findViewById(R.id.text);
        trans = (TextView) findViewById(R.id.translated);
        trBtn = (Button) findViewById(R.id.Btn);
        trBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Mytext",twords.getText().toString());
                Call<ResponseBody> call = intf.dicservice(KEY,"ru-en",twords.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       try {
                            JSONObject mainObj = new JSONObject(response.body().string());
                            Log.i("Mytext" , response.body().string());
                            JSONArray def = mainObj.getJSONArray("def");
                            JSONObject inDef = def.getJSONObject(0);
                            JSONArray tr = inDef.getJSONArray("tr");
                            JSONObject inTr = tr.getJSONObject(0);
                            JSONArray syn = inTr.getJSONArray("syn");
                            JSONObject firstW = syn.getJSONObject(0);
                            String s = (String) firstW.get("text");

                            Log.i("Mytext", s);
                            trans.setText(s);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
