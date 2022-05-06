package com.michaelmagdy.youtubeapi.ui.videos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelmagdy.youtubeapi.model.ChannelModel
import com.michaelmagdy.youtubeapi.model.VideoYtModel
import com.michaelmagdy.youtubeapi.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideosViewModel : ViewModel() {

    private val _video = MutableLiveData<VideoYtModel?>()
    val video = _video
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllVideoLoaded = MutableLiveData<Boolean>()
    val isAllVideoLoaded = _isAllVideoLoaded
    private val _message = MutableLiveData<String>()
    val message = _message
    private var nextPageToken: String? = null

    init {
        getVideoList()
    }

    fun getVideoList(){
        _isLoading.value = true
        val client = ApiConfig.getService().getVideo("snippet", "UCkXmLjEr95LVtGuIm3l2dPg", "date", nextPageToken)
        client.enqueue(object : Callback<VideoYtModel>{
            override fun onResponse(call: Call<VideoYtModel>, response: Response<VideoYtModel>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        if (data.nextPageToken != null){
                            nextPageToken = data.nextPageToken
                        } else {
                            _isAllVideoLoaded.value = true
                        }
                        if (data.items.isNotEmpty()){
                            _video.value = data
                        }
                    } else {
                        _message.value = "No video"
                    }
                } else {
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<VideoYtModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Failed: ", t)
                _message.value = t.message
            }
        })
    }

    companion object {
        private val TAG = VideosViewModel::class.java.simpleName
    }
}