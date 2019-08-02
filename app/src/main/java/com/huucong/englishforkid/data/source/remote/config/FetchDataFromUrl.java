package com.huucong.englishforkid.data.source.remote.config;

import android.os.AsyncTask;
import android.util.Log;
import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchDataFromUrl extends AsyncTask<String,Void, List<EnglishVideo>> {
    private static final String TAG = FetchDataFromUrl.class.getSimpleName();
    private static final int WIDTH_ELEMENT = 140;
    private static final String KEY_SONG = "Song: ";
    private static final String KEY_SHORT_STORY = "Short stories: ";
    private onFetchDataListener mOnFetchDataListener;

    public FetchDataFromUrl(onFetchDataListener onFetchDataListener) {
        this.mOnFetchDataListener = onFetchDataListener;
    }

    @Override
    protected List<EnglishVideo> doInBackground(String... strings) {
        List<EnglishVideo> englishVideos = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0])
                .timeout(30000)
                .get();
            Elements elements = document.select("div.views-field.views-field-field-image");
            if (elements != null && elements.size() > 0) {
                for (Element element : elements) {
                    String elementWidth = element.getElementsByTag("img").attr("width");
                    if (!elementWidth.equals(String.valueOf(WIDTH_ELEMENT))) continue;
                    String title = element
                        .getElementsByTag("img").first().attr("title");
                    String category = null;

                    if (title.contains(KEY_SONG)) {
                        title = title.substring(KEY_SONG.length());
                        category = Constants.CATEGORY_SONG;
                    } else if(title.contains(KEY_SHORT_STORY)) {
                        title = title.substring(KEY_SHORT_STORY.length());
                        category = Constants.CATEGORY_SHORT_STORY;
                    }

                    String thumbnail = element
                        .getElementsByTag("img").first().attr("src");
                    String link = Constants.URL_SITE +
                        element.getElementsByTag("a").first().attr("href");
                    Log.d(TAG, title + "\n" + thumbnail + "\n" + link + "\n");

                    if (title != null) {
                        EnglishVideo englishVideo = new EnglishVideo.Builder(title)
                            .thumbnail(thumbnail)
                            .category(category)
                            .link(link)
                            .build();
                        englishVideos.add(englishVideo);
                    }
                }
                return englishVideos;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<EnglishVideo> englishVideos) {
        super.onPostExecute(englishVideos);
        mOnFetchDataListener.onFetchDataSuccess(englishVideos);
    }

}
