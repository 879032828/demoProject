package com.example.administrator.view_test.Receiver;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class FileUtil {

    private static volatile FileUtil mInstance;
    private Context mContext;

    public FileUtil(Context context) {
        mContext = context;
    }

    public static FileUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new FileUtil(context);
                }
            }
        }
        return mInstance;
    }

    public void save(String content) {
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            fos = mContext.openFileOutput("version", MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String load() {
        BufferedReader br = null;
        FileInputStream fis = null;
        String content = null;
        try {
            fis = mContext.openFileInput("version");
            br = new BufferedReader(new InputStreamReader(fis));
            content = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
