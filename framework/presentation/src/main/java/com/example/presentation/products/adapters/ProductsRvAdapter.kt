package com.example.presentation.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.models.ProductModel
import com.example.presentation.R
import com.example.presentation.databinding.RvProductsrvItemBinding

class ProductsRvAdapter : RecyclerView.Adapter<ProductsRvAdapter.ProductsViewHolder>() {

    private val productList = mutableListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = RvProductsrvItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductsViewHolder(val binding: RvProductsrvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productModel: ProductModel) {
            binding.apply {
                txtRvProductItemTitle.text = productModel.title
                txtRvProductRvBoothTitle.text = productModel.boothTitle
                txtRvProductItemRate.text = "(${productModel.rateCount}) ${productModel.rating}"
                txtRvProductItemWeight.text = "${productModel.weight} گرم"
                txtRvProductItemPrice.text

                Glide.with(binding.root.context)
                    .load("https://img5.downloadha.com/hosein/files/2022/01/Top-Gear-UK-February-2022.jpg") // TODO: 1/13/2022 Fake Image URL
                    .centerCrop()
                    .placeholder(R.drawable.icon_mainmenufragment_basalam)
                    .into(binding.imgMainMenuRvItemImage)

            }
        }

    }

    fun updateList(list: List<ProductModel>) {
        productList.clear()
        productList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
}