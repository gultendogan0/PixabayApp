package com.gultendogan.pixabayapp.ui.dialog.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import com.gultendogan.pixabayapp.common.base.ui.BaseBottomSheetDialog
import com.gultendogan.pixabayapp.databinding.DialogDetailsBinding
import java.lang.Exception

@AndroidEntryPoint
class DetailsDialog : BaseBottomSheetDialog<DialogDetailsBinding>() {
    override val _bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DialogDetailsBinding
        get() = DialogDetailsBinding::inflate

    init {
        setFullScreenDialog()
    }

    private val details by navArgs<DetailsDialogArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        details.hit.apply {
            binding.avatar.load(this.userImageURL) {
                transformations(CircleCropTransformation())
            }
            binding.cover.load(this.webformatURL)
            //binding.name.text = this.user
            //binding.tags.text = this.tags
            //binding.views.text = "Views: ${this.views}"
            binding.like.text = this.likes.toString()
            binding.comments.text = this.comments.toString() + " (comments)"
            binding.userName.text = this.user
            binding.views.text = this.views.toString() + " (views)"
            binding.fullSize.setOnClickListener {
                loadFullSize(this.largeImageURL)
            }
        }

    }

    private fun loadFullSize(url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "You should install a web browser", Toast.LENGTH_SHORT)
                .show()
        }
    }
}