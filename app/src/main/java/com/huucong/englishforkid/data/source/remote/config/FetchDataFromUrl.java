package com.huucong.englishforkid.data.source.remote.config;

import android.os.AsyncTask;
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
    private OnFetchDataListener mOnFetchDataListener;

    public FetchDataFromUrl(OnFetchDataListener onFetchDataListener) {
        this.mOnFetchDataListener = onFetchDataListener;
    }

    @Override
    protected List<EnglishVideo> doInBackground(String... strings) {
        List<EnglishVideo> englishVideos = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0])
                .timeout(Constants.TIME_OUT)
                .get();
            Elements elements = document.select(Constants.CSS_QUERY);
            if (elements != null && elements.size() > 0) {
                for (Element element : elements) {
                    String elementWidth = element.getElementsByTag(Constants.TAG_IMG)
                        .attr(Constants.ATTR_WIDTH);
                    if (!String.valueOf(WIDTH_ELEMENT).equals(elementWidth)) continue;
                    String title = element
                        .getElementsByTag(Constants.TAG_IMG)
                        .first()
                        .attr(Constants.ATTR_TITLE);

                    String category = null;

                    if (title.contains(KEY_SONG)) {
                        title = title.substring(KEY_SONG.length());
                        category = Constants.CATEGORY_SONG;
                    } else if(title.contains(KEY_SHORT_STORY)) {
                        title = title.substring(KEY_SHORT_STORY.length());
                        category = Constants.CATEGORY_SHORT_STORY;
                    }

                    String thumbnail = element
                        .getElementsByTag(Constants.TAG_IMG)
                        .first()
                        .attr(Constants.ATTR_SRC);

                    String link = Constants.URL_SITE +
                        element.getElementsByTag(Constants.TAG_A).first().attr(Constants.ATTR_HREF);

                    if (!title.isEmpty()) {
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
            mOnFetchDataListener.onFetchDataFail(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<EnglishVideo> englishVideos) {
        super.onPostExecute(englishVideos);
        mOnFetchDataListener.onFetchDataSuccess(englishVideos);
    }

}
