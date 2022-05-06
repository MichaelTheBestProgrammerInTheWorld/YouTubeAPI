package com.michaelmagdy.youtubeapi.network

import com.michaelmagdy.youtubeapi.model.ChannelModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("channels")
    fun getChannel(
        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<ChannelModel>
}