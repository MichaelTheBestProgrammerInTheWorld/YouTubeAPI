package com.michaelmagdy.youtubeapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class PlaylistItemsModel(
    @SerializedName("nextPageToken")
    val nextPageToken: String?,

    @SerializedName("items")
    val items: List<PlaylistYtModel.PlaylistItem>
)
