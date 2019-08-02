package com.huucong.englishforkid.data.model;

public class EnglishVideo {

    private String mTitle;
    private String mThumbnail;
    private String mCategory;
    private String mLink;
    private String mData;

    public EnglishVideo() {
    }

    public EnglishVideo(Builder builder){
        this.mTitle = builder.mTitle;
        this.mThumbnail = builder.mThumbnail;
        this.mCategory = builder.mCategory;
        this.mLink = builder.mLink;
        this.mData = builder.mData;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public String getCategory(){
        return mCategory;
    }

    public void setCategory(String category){
        this.mCategory = category;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        this.mData = data;
    }

    public static class Builder{
        private String mTitle;
        private String mThumbnail;
        private String mCategory;
        private String mLink;
        private String mData;

        public Builder(String mTitle) {
            this.mTitle = mTitle;
        }

        public Builder thumbnail(String thumbnail){
            this.mThumbnail = thumbnail;
            return this;
        }

        public Builder category(String category){
            this.mCategory = category;
            return this;
        }

        public Builder link(String link){
            this.mLink = link;
            return this;
        }

        public Builder data(String data){
            this.mData = data;
            return this;
        }

        public EnglishVideo build(){
            EnglishVideo englishVideo = new EnglishVideo(this);
            return englishVideo;
        }
    }
}
