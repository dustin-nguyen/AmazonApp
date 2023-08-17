package com.learn.amazonapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.Constant.PRODUCT_KEY
import com.learn.amazonapp.Constant.TO_CART_ACTION
import com.learn.amazonapp.databinding.ViewHolderHomeCategoryBinding
import com.learn.amazonapp.databinding.ViewHolderItemBinding
import com.learn.amazonapp.model.remote.entity.Category
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.view.HomeCommunicator
import com.learn.amazonapp.view.LoginCommunicator
import com.squareup.picasso.Picasso

class ShowItemAdapter(val listOfItem: List<Product>,val parentHomeCommunicator: HomeCommunicator):
    RecyclerView.Adapter<ShowItemAdapter.ProductViewHolder>() {
    private lateinit var binding: ViewHolderItemBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewHolderItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ProductViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listOfItem[position])
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }



    private lateinit var productSelected :(Product, Int) ->Unit
    fun setOnProductSelectedListener(listener: (Product, Int) -> Unit) {
        productSelected = listener
    }

    inner class ProductViewHolder (val binding: ViewHolderItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    productSelected(listOfItem[position], position)
                }
            }
        }
        fun bind(product: Product){
            binding.apply {
                tvName.text=product.product_name
                tvBody.text=product.description
                ratingBar.rating=product.average_rating.toFloat()
                tvPrice.text="$ ${product.price}"
                //Picasso.get().load(URL_IMAGE+category.category_image_url).into(binding.imgCategory)
                btnAddToCart.setOnClickListener {
                    parentHomeCommunicator.sendProductToCart(product)
                }
            }

        }

    }
    companion object {
        const val URL_IMAGE = "http://10.0.2.2/myshop/images/"
    }
}