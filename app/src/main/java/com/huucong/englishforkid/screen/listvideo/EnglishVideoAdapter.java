package com.huucong.englishforkid.screen.listvideo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.huucong.englishforkid.R;
import com.huucong.englishforkid.data.model.EnglishVideo;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnglishVideoAdapter extends RecyclerView.Adapter<
    EnglishVideoAdapter.EnglishVideoViewHolder> {
    private List<EnglishVideo> mEnglishVideos = new ArrayList<>();

    @NonNull
    @Override
    public EnglishVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_english_video, parent, false);
        return new EnglishVideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishVideoViewHolder holder, int position) {
        holder.setData(holder.itemView, mEnglishVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return mEnglishVideos == null ? 0 : mEnglishVideos.size();
    }

    public void updateData(List<EnglishVideo> params) {
        if (params == null) return;
        mEnglishVideos.clear();
        mEnglishVideos.addAll(params);
        notifyDataSetChanged();
    }

    static class EnglishVideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewThumbnail;
        private TextView mTextViewTitle;

        public EnglishVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewThumbnail = itemView.findViewById(R.id.image_thumbnail);
            mTextViewTitle = itemView.findViewById(R.id.text_title);
        }

        public void setData(View itemView, EnglishVideo englishVideo) {
            mTextViewTitle.setText(englishVideo.getTitle());
            Glide.with(itemView)
                .load(englishVideo.getThumbnail())
                .fitCenter()
                .into(mImageViewThumbnail);
        }
    }
}

