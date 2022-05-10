package com.michaelmagdy.youtubeapi.ui.playlists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelmagdy.youtubeapi.model.PlaylistYtModel
import com.michaelmagdy.youtubeapi.network.ApiConfig
import com.michaelmagdy.youtubeapi.util.Constants.CHANNEL_ID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : ViewModel() {

    private val _playlist = MutableLiveData<PlaylistYtModel?>()
    val playlist = _playlist
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllPlaylistLoaded = MutableLiveData<Boolean>()
    val isAllPlaylistLoaded = _isAllPlaylistLoaded
    var nextPageToken: String? = null

    init {
        getPlaylist()
    }

    fun getPlaylist() {
        _isLoading.value = true
        val client = ApiConfig
            .getService()
            .getPlaylist(
                "snippet,contentDetails",
                CHANNEL_ID,
                "10",
                nextPageToken)
        client.enqueue(object : Callback<PlaylistYtModel> {
            override fun onResponse(
                call: Call<PlaylistYtModel>,
                response: Response<PlaylistYtModel>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        if (data.nextPageToken != null){
                            nextPageToken = data.nextPageToken
                        } else {
                            nextPageToken = null
                            _isAllPlaylistLoaded.value = true
                        }
                        if (data.items.isNotEmpty()){
                            _playlist.value = data
                        }
                    }
                }

            }

            override fun onFailure(call: Call<PlaylistYtModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Failure: ", t)
            }
        })
    }

    companion object {
        private val TAG = PlaylistsViewModel::class.java.simpleName
    }
}