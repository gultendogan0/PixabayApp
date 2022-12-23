package com.gultendogan.pixabayapp.ui.pages.discover

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.gultendogan.pixabayapp.R
import com.gultendogan.pixabayapp.common.base.ui.BaseFragment
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import com.gultendogan.pixabayapp.common.utils.ext.initGrid
import com.gultendogan.pixabayapp.common.utils.ext.navigateWith
import com.gultendogan.pixabayapp.databinding.FragmentDiscoverBinding
import com.gultendogan.pixabayapp.ui.adapters.PixaBayImagesAdapter

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverBinding
        get() = FragmentDiscoverBinding::inflate

    private val vM by viewModels<DiscoverViewModel>()


    private val mainAdapter by lazy {
        PixaBayImagesAdapter {
            DiscoverFragmentDirections.dialogDetails(it).navigateWith(this@DiscoverFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vM.getDiscover()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.rv.initGrid(
            mainAdapter,
            GridLayoutManager(requireContext(), 2)
        )

        vM.discoveryObservable.observe(viewLifecycleOwner) {
            binding.swipe.isRefreshing = it is ResponseWrapper.Loading
            when (it) {
//                is ResponseWrapper.Failure -> TODO()
//                is ResponseWrapper.LocalFailure -> TODO()
                is ResponseWrapper.Success -> mainAdapter.setItems(it.value.hits)
            }
        }

        binding.swipe.setOnRefreshListener {
            vM.getDiscover()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_action_search -> DiscoverFragmentDirections.actionDiscoverFragmentToSearchFragment()
                .navigateWith(this)

            R.id.menu_action_fav -> DiscoverFragmentDirections.actionDiscoverFragmentToFavFragment()
                .navigateWith(this)


        }
        return super.onOptionsItemSelected(item)
    }
}