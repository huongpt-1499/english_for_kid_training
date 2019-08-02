package com.huucong.englishforkid.data.source.remote.config;

import com.huucong.englishforkid.data.model.EnglishVideo;
import java.util.List;

public interface onFetchDataListener {
    void onFetchDataSuccess(List<EnglishVideo> englishVideos);
    void onFetchDataFail(String error);
}
