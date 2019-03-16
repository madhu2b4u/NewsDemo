package com.demo.utils;




import com.demo.newyorktimes.datamodels.Article;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApi {

    @GET(AppConstants.CONNECT)
    Call<Article> getArticle();

}
