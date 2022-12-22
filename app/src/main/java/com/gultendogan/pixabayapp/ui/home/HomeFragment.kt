package com.gultendogan.pixabayapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.gultendogan.pixabayapp.databinding.FragmentHomeBinding
import com.gultendogan.pixabayapp.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        viewModel.getData()
        observe()
    }

    private fun observe(){
        lifecycleScope.launchWhenCreated {
            viewModel.characterList.observe(viewLifecycleOwner){
                homeAdapter.product = it
            }
        }
    }

    private fun initRecycler(){
        binding.homeRecycler.apply {
            homeAdapter = HomeAdapter()
            this.layoutManager = GridLayoutManager(context,2)
            adapter = homeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}