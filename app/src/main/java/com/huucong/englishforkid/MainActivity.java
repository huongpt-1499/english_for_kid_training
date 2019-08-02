package com.huucong.englishforkid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.data.source.remote.config.onFetchDataListener;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onFetchDataListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFetchDataSuccess(List<EnglishVideo> englishVideos) {
    }

    @Override
    public void onFetchDataFail(String error) {
    }
}
