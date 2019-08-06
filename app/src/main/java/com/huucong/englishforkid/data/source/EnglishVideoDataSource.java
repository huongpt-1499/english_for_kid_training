package com.huucong.englishforkid.data.source;

import com.huucong.englishforkid.data.model.EnglishVideo;
import java.util.List;
import androidx.annotation.NonNull;

public interface EnglishVideoDataSource {

    List<EnglishVideo> getEnglishVideos(String category);

    interface LocalDataSource extends EnglishVideoDataSource {
        boolean insertEnglishVideo(@NonNull EnglishVideo englishVideo);
        boolean deleteEnglishVideo(@NonNull String id);
    }

}
