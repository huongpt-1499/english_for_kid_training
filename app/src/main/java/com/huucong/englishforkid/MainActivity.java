package com.huucong.englishforkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.data.source.remote.config.FetchDataFromUrl;
import com.huucong.englishforkid.data.source.remote.config.onFetchDataListener;
import com.huucong.englishforkid.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements onFetchDataListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchDataFromUrl(this).execute(Constants.URL_SHORT_STORY);
    }

    @Override
    public void onFetchDataSuccess(List<EnglishVideo> englishVideos) {
    }
}
