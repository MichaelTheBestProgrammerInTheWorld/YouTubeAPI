package com.michaelmagdy.youtubeapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.michaelmagdy.youtubeapi.BuildConfig
import com.michaelmagdy.youtubeapi.databinding.ActivityPlayerBinding
import android.widget.Toast

import java.lang.String
import android.content.Intent





class PlayerActivity : YouTubeBaseActivity() {

    private val RECOVERY_DIALOG_REQUEST = 1

    private var _binding: ActivityPlayerBinding? = null
    private val binding get() = _binding!!
    private var youtubePlayer: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoId = intent.getStringExtra("video_id")
        val videoTitle = intent.getStringExtra("video_title")
        val videoDescription = intent.getStringExtra("video_description")

        binding.tvVideoTitle.text = videoTitle
        binding.tvVideoDescription.text = videoDescription

        binding.youtubePlayer.initialize(BuildConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubePlayer = p1
                youtubePlayer?.loadVideo(videoId)
                youtubePlayer?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                errorReason: YouTubeInitializationResult?
            ) {
                Snackbar.make(binding.root, "Failed to initialize youtube player ", Snackbar.LENGTH_LONG).show()
//                p1?.getErrorDialog(this@PlayerActivity, 0)
                if (errorReason!!.isUserRecoverableError()) {
                    errorReason.getErrorDialog(this@PlayerActivity, RECOVERY_DIALOG_REQUEST).show()
                } else {
                    val errorMessage =
                        String.format("error player", errorReason.toString())
                    Toast.makeText(this@PlayerActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

}