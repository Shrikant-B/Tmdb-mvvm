package com.shrikantbadwaik.tmdb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie implements Parcelable {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("vote_average")
    private float voteAverage;

    public Movie() {
    }

    protected Movie(Parcel in) {
        posterPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        voteAverage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeFloat(voteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPosterPath() {
        return TMDB_IMAGE_PATH + posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }
}