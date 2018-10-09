package com.shrikantbadwaik.tmdb.data.remote.apiresponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shrikantbadwaik.tmdb.data.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageResponse implements Parcelable {
    public static final Creator<ImageResponse> CREATOR = new Creator<ImageResponse>() {
        @Override
        public ImageResponse createFromParcel(Parcel in) {
            return new ImageResponse(in);
        }

        @Override
        public ImageResponse[] newArray(int size) {
            return new ImageResponse[size];
        }
    };

    @JsonProperty("id")
    private String movieId;
    @JsonProperty("backdrops")
    private List<Image> backdropList;
    @JsonProperty("posters")
    private List<Image> posterList;

    public ImageResponse() {
        backdropList = new ArrayList<>();
        posterList = new ArrayList<>();
    }

    protected ImageResponse(Parcel in) {
        movieId = in.readString();
        backdropList = in.createTypedArrayList(Image.CREATOR);
        posterList = in.createTypedArrayList(Image.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);
        dest.writeTypedList(backdropList);
        dest.writeTypedList(posterList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public List<Image> getBackdropList() {
        return backdropList;
    }

    public void setBackdropList(List<Image> backdropList) {
        this.backdropList = backdropList;
    }

    public List<Image> getPosterList() {
        return posterList;
    }

    public void setPosterList(List<Image> posterList) {
        this.posterList = posterList;
    }
}
