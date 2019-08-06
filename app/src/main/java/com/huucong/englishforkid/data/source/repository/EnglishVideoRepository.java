package com.huucong.englishforkid.data.source.repository;

import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.data.source.EnglishVideoDataSource;
import com.huucong.englishforkid.data.source.local.EnglishVideoLocalDataSource;
import java.util.List;
import androidx.annotation.NonNull;

public class EnglishVideoRepository implements EnglishVideoDataSource.LocalDataSource {
    private static EnglishVideoRepository instance;
    private EnglishVideoLocalDataSource mEnglishLocalDataSource;

    public EnglishVideoRepository(
        @NonNull EnglishVideoLocalDataSource englishVideoLocalDataSource) {
        this.mEnglishLocalDataSource = englishVideoLocalDataSource;
    }

    public static synchronized EnglishVideoRepository getInstance(
        @NonNull EnglishVideoLocalDataSource englishVideoLocalDataSource) {
        if (instance == null) {
            instance = new EnglishVideoRepository(englishVideoLocalDataSource);
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
    @Override
    public List<EnglishVideo> getEnglishVideos(String category) {
        return mEnglishLocalDataSource.getEnglishVideos(category);
    }

    @Override
    public boolean insertEnglishVideo(@NonNull EnglishVideo englishVideo) {
        return mEnglishLocalDataSource.insertEnglishVideo(englishVideo);
    }

    @Override
    public boolean deleteEnglishVideo(@NonNull String id) {
        return mEnglishLocalDataSource.deleteEnglishVideo(id);
    }
}
