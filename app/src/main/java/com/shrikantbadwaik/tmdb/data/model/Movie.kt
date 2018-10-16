package com.shrikantbadwaik.tmdb.data.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import com.shrikantbadwaik.tmdb.domain.Constants

data class Movie(
    @JsonProperty("poster_path")
    val posterPath: String?,
    @JsonProperty("adult")
    val adult: Boolean,
    @JsonProperty("overview")
    val overview: String?,
    @JsonProperty("release_date")
    val releaseDate: String?,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("backdrop_path")
    val backdropPath: String?,
    @JsonProperty("vote_average")
    val voteAverage: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeFloat(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    fun posterPath(): String = Constants.TMDB_IMAGE_PATH + posterPath
}