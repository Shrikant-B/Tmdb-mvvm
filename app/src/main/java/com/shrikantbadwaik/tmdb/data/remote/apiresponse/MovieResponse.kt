package com.shrikantbadwaik.tmdb.data.remote.apiresponse

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import com.shrikantbadwaik.tmdb.data.model.Movie

data class MovieResponse(
    @JsonProperty("page")
    val page: Int,
    @JsonProperty("results")
    val movieList: MutableList<Movie>?,
    @JsonProperty("total_results")
    val totalResults: Int,
    @JsonProperty("total_pages")
    val totalPages: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Movie),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(movieList)
        parcel.writeInt(totalResults)
        parcel.writeInt(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResponse> {
        override fun createFromParcel(parcel: Parcel): MovieResponse {
            return MovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}