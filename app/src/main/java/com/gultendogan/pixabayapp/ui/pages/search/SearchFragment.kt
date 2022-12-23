package com.gultendogan.pixabayapp.ui.pages.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.gultendogan.pixabayapp.common.base.ui.BaseFragment
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import com.gultendogan.pixabayapp.common.utils.ext.getQueryChangeFlow
import com.gultendogan.pixabayapp.common.utils.ext.init
import com.gultendogan.pixabayapp.common.utils.ext.initGrid
import com.gultendogan.pixabayapp.common.utils.ext.navigateWith
import com.gultendogan.pixabayapp.databinding.FragmentSearchBinding
import com.gultendogan.pixabayapp.model.network.response.PixaBayResponse
import com.gultendogan.pixabayapp.ui.MainActivityViewModel
import com.gultendogan.pixabayapp.ui.adapters.PixaBayImagesAdapter
import com.gultendogan.pixabayapp.ui.pages.discover.DiscoverFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val mainAdapter by lazy {
        PixaBayImagesAdapter {
            DiscoverFragmentDirections.dialogDetails(it).navigateWith(this@SearchFragment)
        }
    }

    private val vM by viewModels<SearchViewModel>()
    private val mainVM by activityViewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vM.search()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchResults.initGrid(mainAdapter, GridLayoutManager(requireContext(), 2))

        binding.retryBtn.setOnClickListener {
            vM.search()
        }
        lifecycleScope.launch {
            binding.searchView.getQueryChangeFlow()
                .debounce(800)
                .distinctUntilChanged()
                .asLiveData()
                .observe(viewLifecycleOwner) { query ->
                    Log.d("SEARCH", "onViewCreated: query = ${query.first}")
                    //todo handle query empty
                    vM.setQuery(query.first?.toString())
                    vM.search()
                }
        }
        vM.searchObservable.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it is ResponseWrapper.Loading
            when (it) {
                is ResponseWrapper.Failure -> onFailure()
                is ResponseWrapper.LocalFailure -> onLocalFailure()
                is ResponseWrapper.Success -> onData(it.value)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainVM.setToolbarLifted(true)
    }

    override fun onPause() {
        super.onPause()
        mainVM.setToolbarLifted(false)

    }
    private fun onLocalFailure() {

    }

    private fun onFailure() {

    }

    private fun onData(response: PixaBayResponse) {
        val hits = response.hits
        binding.noResults.isVisible = hits.isEmpty()
        binding.searchResults.isVisible = hits.isNotEmpty()
        if (hits.isNotEmpty()) {
            mainAdapter.setItems(hits)
        }
    }


}