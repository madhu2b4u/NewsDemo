package com.demo.newyorktimes.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.R;
import com.demo.newyorktimes.datamodels.Json;
import com.demo.newyorktimes.datamodels.Result;
import com.demo.utils.AppConstants;
import com.demo.utils.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.textViewAbstract)
    TextView textViewAbstract;
    @BindView(R.id.textViewDate)
    TextView textViewDate;
    @BindView(R.id.textViewViews)
    TextView textViewViews;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewSource)
    TextView textViewSource;
    @BindView(R.id.textViewSection)
    TextView textViewSection;
    @BindView(R.id.textViewKeyWords)
    TextView textViewKeyWords;
    @BindView(R.id.textViewAuthor)
    TextView textViewAuthor;

    @BindView(R.id.imageView)
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NY Times Most Popular");
        setSupportActionBar(toolbar);
        enableActionBar(true);

        //getData from the api of particular title

        String data = getIntent().getStringExtra("data");
        Result result = Json.gson().fromJson(data, Result.class);
        Log.e(DetailsActivity.class.getName(), result.getTitle());

        textViewAbstract.setText(getResources().getString(R.string.abstracts)+" "+result.getAbstract());
        textViewKeyWords.setText(result.getAdxKeywords());
        textViewViews.setText(result.getViews()+" "+getResources().getString(R.string.views));
        textViewTitle.setText(result.getTitle());
        textViewSection.setText(getResources().getString(R.string.section)+" "+result.getSection());
        textViewSource.setText(getResources().getString(R.string.source)+" "+result.getSource());
        textViewAuthor.setText(getResources().getString(R.string.published)+" "+result.getByline());
        textViewDate.setText(AppConstants.formatdate(result.getPublishedDate()));

        try {
            Glide.with(getApplicationContext())
                    .load(result.getMedia()
                            .get(0)
                            .getMediaMetadata()
                            .get(2)
                            .getUrl())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }*/


}
