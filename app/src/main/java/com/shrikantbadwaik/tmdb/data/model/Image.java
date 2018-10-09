package com.shrikantbadwaik.tmdb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shrikantbadwaik.tmdb.domain.helper.Constants;

public class Image implements Parcelable {
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @JsonProperty("aspect_ratio")
    private String aspectRatio;
    @JsonProperty("file_path")
    private String filePath;
    @JsonProperty("height")
    private int height;
    @JsonProperty("vote_average")
    private float voteAverage;
    @JsonProperty("vote_count")
    private float voteCount;
    @JsonProperty("width")
    private int width;

    public Image() {
    }

    protected Image(Parcel in) {
        aspectRatio = in.readString();
        filePath = in.readString();
        height = in.readInt();
        voteAverage = in.readFloat();
        voteCount = in.readFloat();
        width = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aspectRatio);
        dest.writeString(filePath);
        dest.writeInt(height);
        dest.writeFloat(voteAverage);
        dest.writeFloat(voteCount);
        dest.writeInt(width);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return Constants.TMDB_IMAGE_PATH + filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}