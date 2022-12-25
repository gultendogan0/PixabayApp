package com.gultendogan.pixabayapp.ui.pages.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import com.gultendogan.pixabayapp.databinding.FragmentFavBinding
import com.gultendogan.pixabayapp.ui.adapters.PixaBayImagesAdapter
import com.gultendogan.pixabayapp.ui.pages.discover.DiscoverFragmentDirections
import com.gultendogan.pixabayapp.utils.ext.initGrid
import com.gultendogan.pixabayapp.utils.ext.navigateWith
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel: FavViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.favoriteList.observe(viewLifecycleOwner){
            println("breakpoint")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}