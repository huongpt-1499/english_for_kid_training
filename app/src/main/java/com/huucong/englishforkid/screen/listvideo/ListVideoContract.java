package com.huucong.englishforkid.screen.listvideo;

import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.screen.BasePresenter;

import java.util.List;

public interface ListVideoContract {
    interface View {
        void showEnglishVideos(List<EnglishVideo> englishVideos);
        void showErrorLoadingVideos(String error);
    }

    interface Presenter extends BasePresenter<View> {
        void loadVideo(String category);
        void searchVideo(String category, String keyword);
    }
}
