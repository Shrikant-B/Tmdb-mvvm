package com.shrikantbadwaik.tmdb.data.remote.apiresponse

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import com.shrikantbadwaik.tmdb.data.model.Image

data class ImageResponse(
    @JsonProperty("id")
    val movieId: String?,
    @JsonProperty("backdrops")
    val backdropList: MutableList<Image>?,
    @JsonProperty("posters")
    val posterList: MutableList<Image>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(Image),
        parcel.createTypedArrayList(Image)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(movieId)
        parcel.writeTypedList(backdropList)
        parcel.writeTypedList(posterList)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ImageResponse(movieId=$movieId, backdropList=$backdropList, posterList=$posterList)"
    }

    companion object CREATOR : Parcelable.Creator<ImageResponse> {
        override fun createFromParcel(parcel: Parcel): ImageResponse {
            return ImageResponse(parcel)
        }

        override fun newArray(size: Int): Array<ImageResponse?> {
            return arrayOfNulls(size)
        }
    }


}