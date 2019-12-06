package com.example.administrator.view_test.websocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.view_test.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class websocketActivity extends AppCompatActivity {

    private static final String TAG = "websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketTest.test(websocketActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        initWebSocket();
    }

    private void initWebSocket() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("www.baidu.com").build();
        ClientWebSocketListener listener = new ClientWebSocketListener();

        okHttpClient.newWebSocket(request, listener);
        okHttpClient.dispatcher().executorService().shutdown();
    }

    private WebSocket mWebSocket;

    private final class ClientWebSocketListener extends WebSocketListener {
        public ClientWebSocketListener() {
            super();
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            mWebSocket = webSocket;
            mWebSocket.send("您好，我是客户端");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            Log.d(TAG, text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            if (null != mWebSocket) {
                mWebSocket.close(1000, "再见");
                mWebSocket = null;
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }
    }
}
