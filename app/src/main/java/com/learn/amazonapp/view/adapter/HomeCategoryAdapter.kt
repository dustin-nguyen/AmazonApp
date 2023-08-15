package com.learn.amazonapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.ViewHolderHomeCategoryBinding
import com.learn.amazonapp.model.remote.entity.Category
import com.squareup.picasso.Picasso

class HomeCategoryAdapter (val listOfCategory: List<Category>) :
    RecyclerView.Adapter<HomeCategoryAdapter.CategoryHomeViewHolder>() {
    private lateinit var binding: ViewHolderHomeCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewHolderHomeCategoryBinding.inflate(layoutInflater, parent, false)
        val viewHolder = CategoryHomeViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CategoryHomeViewHolder, position: Int) {
        holder.bind(listOfCategory[position])
    }

    override fun getItemCount(): Int {
        return listOfCategory.size
    }



    private lateinit var categorySelected :(Category, Int) ->Unit
    fun setOnCategorySelectedListener(listener: (Category, Int) -> Unit) {
        categorySelected = listener
    }
    inner class CategoryHomeViewHolder (val binding: ViewHolderHomeCategoryBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    categorySelected(listOfCategory[position], position)
                }
            }
        }
        fun bind(category: Category){
            binding.apply {
                tvTitle.text=category.category_name
               Picasso.get().load(URL_IMAGE+category.category_image_url).into(binding.imgCategory)

            }
        }

    }
    companion object {
        const val URL_IMAGE = "http://10.0.2.2/myshop/images/"
    }
}