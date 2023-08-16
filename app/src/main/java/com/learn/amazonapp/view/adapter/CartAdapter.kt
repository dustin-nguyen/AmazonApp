package com.learn.amazonapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.ViewHolderCartItemBinding
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.cart.CartPresenter

class CartAdapter(val listOfItem: List<Product>, val cartPresenter: CartPresenter):
    RecyclerView.Adapter<CartAdapter.ProductViewHolder>() {
    private lateinit var binding: ViewHolderCartItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewHolderCartItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ProductViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listOfItem[position])
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }


    private lateinit var productSelected: (Product, Int) -> Unit
    fun setOnProductSelectedListener(listener: (Product, Int) -> Unit) {
        productSelected = listener
    }

    inner class ProductViewHolder(val binding: ViewHolderCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    productSelected(listOfItem[position], position)
                }
            }
        }

        fun bind(product: Product) {
            binding.apply {
                tvName.text = product.product_name
                tvBody.text = product.description
                tvPrice.text = "$ ${product.price}"
                //Picasso.get().load(URL_IMAGE+category.category_image_url).into(binding.imgCategory)

            }
            setupButton(product)

        }

        private fun setupButton(product: Product) {
            binding.btnIncrease.setOnClickListener {
                val currentValue = binding.tvQuantity.text.toString().toInt()+1
                binding.tvQuantity.setText(currentValue.toString())
                updateTotalPrice(product)

                cartPresenter.updateTotalPrice(product.price.toInt())

            }
            binding.btnDecrease.setOnClickListener {
                val currentValue = binding.tvQuantity.text.toString().toInt()
                if(currentValue>0){
                    binding.tvQuantity.setText((currentValue - 1).toString())
                    updateTotalPrice(product)
                    cartPresenter.updateTotalPrice(-product.price.toInt())
                }



            }
        }
        private fun updateTotalPrice(product: Product){
            val currentQuantity= binding.tvQuantity.text.toString().toInt()
            val totalPrice=currentQuantity * product.price.toInt()
            binding.tvTotalPrice.text= totalPrice.toString()
        }

    }

    companion object {
        const val URL_IMAGE = "http://10.0.2.2/myshop/images/"
    }
}