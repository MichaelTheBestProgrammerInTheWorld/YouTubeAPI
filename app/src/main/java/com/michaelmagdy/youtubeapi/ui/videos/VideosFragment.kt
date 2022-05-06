package com.michaelmagdy.youtubeapi.ui.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.michaelmagdy.youtubeapi.R

class VideosFragment : Fragment() {

    private lateinit var videosViewModel: VideosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videosViewModel =
            ViewModelProvider(this).get(VideosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_videos, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        videosViewModel.channel.observe(viewLifecycleOwner, Observer {
            if (it != null && it.items.isNotEmpty()){
                it.items.forEach {  channel ->
                    textView.text = channel.snippet.title
                }
            }
        })
        return root
    }
}