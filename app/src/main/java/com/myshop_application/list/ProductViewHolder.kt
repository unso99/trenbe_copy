package com.myshop_application.list

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.myshop_application.databinding.ItemProdBinding
import com.myshop_application.model.Product
import com.myshop_application.repository.ImageProvider

class ProductViewHolder(private val binding: ItemProdBinding) :
    RecyclerView.ViewHolder(binding.root), ImageProvider.Callback {

    fun bind(item: Product) {
        binding.item = item
        ImageProvider(this).getImage(item.imageUrl)
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }
}
