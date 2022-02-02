package com.example.presentation.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.models.AnimalModel
import com.example.presentation.R
import com.example.presentation.databinding.RvAnimalsrvItemBinding

class AnimalRvAdapter : RecyclerView.Adapter<AnimalRvAdapter.AnimalsViewHolder>() {

    private val productList = mutableListOf<AnimalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val binding = RvAnimalsrvItemBinding.inflate(LayoutInflater.from(parent.context))
        return AnimalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class AnimalsViewHolder(val binding: RvAnimalsrvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(animalModel: AnimalModel) {
            binding.apply {
                txtRvAnimalItemTitle.text = animalModel.name
                txtRvAnimalRvAnimalType.text = animalModel.animalType
                txtRvProductItemWeight.text = "${animalModel.weightMax} KG"
                txtRvAnimalItemHeightMax.text = "${animalModel.lengthMax} CM"

                Glide.with(binding.root.context)
                    .load(animalModel.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.icon_rvproductitem_star)
                    .into(binding.imgMainMenuRvItemImage)

            }
        }

    }

    fun updateList(list: List<AnimalModel>) {
        productList.clear()
        productList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
}