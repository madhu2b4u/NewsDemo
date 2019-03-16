package com.demo.newyorktimes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.demo.R;
import com.demo.newyorktimes.adapter.NewsAdapter;
import com.demo.newyorktimes.datamodels.Article;
import com.demo.newyorktimes.datamodels.Result;
import com.demo.utils.ApiClient;
import com.demo.utils.BaseActivity;
import com.demo.utils.WebApi;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.etSearch)
    EditText etSearch;
    ArrayList<Result> newsList;
    NewsAdapter newsAdapter;
    WebApi webApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NY Times Most Popular");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        enableActionBar(false);
        webApi = ApiClient.getRetrofit().create(WebApi.class);
        newsList=new ArrayList<Result>();


        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.VISIBLE);


        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, newsList);
        recyclerView.setAdapter(newsAdapter);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (newsList != null) {
                    filter(editable.toString());
                }

            }


        });

        /**
         GET News
         **/
        getData();


    }

    public  void getData(){
        Call<Article> call = webApi.getArticle();
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                renderData(response.body());
            }
            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                call.cancel();
            }
        });
    }

    void filter(String text) {
        ArrayList<Result> temp = new ArrayList();
        for (Result d : newsList) {
            if (d.getTitle().toLowerCase().contains(text.toLowerCase()) ) {
                temp.add(d);
            }
        }
        newsAdapter.updateList(temp);
    }

    private void renderData(Article response) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (response.getResults() != null && !response.getResults().isEmpty()) {
            newsList.clear();
            newsList.addAll(response.getResults());
            newsAdapter.notifyDataSetChanged();
        }
    }


}
