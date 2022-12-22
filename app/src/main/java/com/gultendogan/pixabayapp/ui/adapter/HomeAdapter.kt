package com.gultendogan.pixabayapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gultendogan.pixabayapp.data.PixabayBean.Hit
import com.gultendogan.pixabayapp.databinding.HomeRecyclerItemBinding

class HomeAdapter :RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: HomeRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    object HomeDiffCallback: DiffUtil.ItemCallback<Hit>(){
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.user == newItem.user
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

    }

    private val diffList = AsyncListDiffer(this,HomeDiffCallback)
    var product: List<Hit>
        get()=diffList.currentList
        set(value)=diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(product){
                binding.tvTitle.text = product[position].user
                binding.tvPrice.text = product[position].userId.toString()

                Glide.with(binding.ivImage)
                    .load(product[position].previewURL)
                    .circleCrop()
                    .into(binding.ivImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }

}