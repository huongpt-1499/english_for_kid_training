package com.huucong.englishforkid.screen.listvideo;

import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.data.source.remote.config.FetchDataFromUrl;
import com.huucong.englishforkid.data.source.remote.config.OnFetchDataListener;
import com.huucong.englishforkid.utils.Constants;

import java.util.List;

public class ListVideoPresenter implements ListVideoContract.Presenter, OnFetchDataListener {
    private ListVideoContract.View mView;

    @Override
    public void setView(ListVideoContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    public void onStart(String category) {
        loadVideo(category);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadVideo(String category) {
        if (Constants.CATEGORY_SONG.equals(category)) {
            new FetchDataFromUrl(this).execute(Constants.URL_SONG);
        } else if (Constants.CATEGORY_SHORT_STORY.equals(category)) {
            new FetchDataFromUrl(this).execute(Constants.URL_SHORT_STORY);
        }
    }

    @Override
    public void onFetchDataSuccess(List<EnglishVideo> englishVideos) {
        mView.showEnglishVideos(englishVideos);
    }

    @Override
    public void onFetchDataFail(String error) {
        mView.showErrorLoadingVideos(error);
    }
}
