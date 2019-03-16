package com.demo.newyorktimes.ui;

import android.util.Log;

import com.demo.newyorktimes.datamodels.Article;
import com.demo.utils.ApiClient;
import com.demo.utils.WebApi;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

public class GetNewsData {
    WebApi webApi;


    @Test
    public void getData()  {

        webApi = ApiClient.getRetrofit().create(WebApi.class);
        Call<Article> call = webApi.getArticle();
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                assertNotNull(response);
                assertEquals(response.body().getStatus(), "OK");
            }
            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                call.cancel();
            }
        });


    }
}