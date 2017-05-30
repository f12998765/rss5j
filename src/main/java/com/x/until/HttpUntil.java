package com.x.until;

import com.x.mode.Entry;
import com.x.mode.EntryList;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by zero on 2017/5/28.
 */
public class HttpUntil {
    public static final int TIMEOUT = 1000 * 3;
    private final static OkHttpClient client = new OkHttpClient();

    public void get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Fail\t"+request.url());
//                e.printStackTrace();
                EntryList.num-=1;

                EntryList.UrlMap.put(url,true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                System.out.println("Success\t"+request.url());
                List<Entry> list=ReadUntil.read(request.url().toString(),response.body().string());

                EntryList.map.put(url,list);

                EntryList.UrlMap.put(url,true);

                EntryList.num-=1;

//                com.x.mode.EntryList.test();
            }
        });

    }

}
