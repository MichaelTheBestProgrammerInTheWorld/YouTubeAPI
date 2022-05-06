package com.michaelmagdy.youtubeapi.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.michaelmagdy.youtubeapi.databinding.FragmentPlaylistsBinding

class PlaylistsFragment : Fragment() {

    private lateinit var playlistsViewModel: PlaylistsViewModel
    private var _binding: FragmentPlaylistsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playlistsViewModel =
            ViewModelProvider(this).get(PlaylistsViewModel::class.java)

        _binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        playlistsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}