package com.shrikantbadwaik.tmdb.data.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

data class Image(
    @JsonProperty("aspect_ratio")
    val aspectRatio: String?,
    @JsonProperty("file_path")
    val filePath: String?,
    @JsonProperty("height")
    val height: Int,
    @JsonProperty("vote_average")
    val voteAverage: Float,
    @JsonProperty("vote_count")
    val voteCount: Float,
    @JsonProperty("width")
    val width: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(aspectRatio)
        parcel.writeString(filePath)
        parcel.writeInt(height)
        parcel.writeFloat(voteAverage)
        parcel.writeFloat(voteCount)
        parcel.writeInt(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}