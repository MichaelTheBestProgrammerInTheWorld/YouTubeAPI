package com.michaelmagdy.youtubeapi.network

import com.michaelmagdy.youtubeapi.model.ChannelModel
import com.michaelmagdy.youtubeapi.model.VideoYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("channels")
    fun getChannel(
        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<ChannelModel>

    @GET("search")
    fun getVideo(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order: String
    ) : Call<VideoYtModel>
}