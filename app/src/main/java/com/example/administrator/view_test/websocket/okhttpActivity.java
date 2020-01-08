package com.example.administrator.view_test.websocket;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.view_test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class okhttpActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder builder;
    private Response execute;

    public Button request;
    public Button bt_okio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initClient();

        HashMap<String, String> hashMap = new HashMap<>();

        request = (Button) findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(v);
            }
        });


        bt_okio = (Button) findViewById(R.id.bt_okio);
        bt_okio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initClient() {
        /**
         *1、初始化client客户端
         * */
        builder = new OkHttpClient.Builder();
        Cache cache = new Cache(new File(Environment.getExternalStorageDirectory() + "RewriteResponseCacheControl.tmp"), 1024 * 1024);
        try {
            cache.evictAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        okHttpClient = builder.cache(cache).build();
    }

    public void get(View view) {
        /**
         *2、初始化请求
         * */
        Request request = new Request.Builder()
                .url("https://suggest.taobao.com/sug?code=utf-8&q=卫衣&callback=cb")
                .build();

        Call realCall = okHttpClient.newCall(request);
        realCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okhttpActivity", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("okhttpActivity", response.message());
            }
        });

        ImageView iv = new ImageView(this);
        Glide.with(this).load("https://ss1.bdstatic" +
                ".com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2669567003," +
                "3609261574&fm=27&gp=0.jpg22222222asads")
                .into(iv);
    }

    /************************************ okio 使用 ***********************************/
    public void readFile(String filename) throws IOException {
        Source source = null;
        BufferedSource bufferedSource = null;
        File file = new File(filename);
        source = Okio.source(file);
        bufferedSource = Okio.buffer(source);
        String read = bufferedSource.readString(Charset.forName("utf-8"));
    }
}
