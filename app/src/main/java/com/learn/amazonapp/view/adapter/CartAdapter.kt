package com.learn.amazonapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.ViewHolderCartItemBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Product
import com.learn.amazonapp.presenter.cart.CartPresenter
import com.learn.amazonapp.view.CartCommunicator

class CartAdapter(val listOfItem: List<ProductInCart>, val cartPresenter: CartPresenter, val cartCommunicator: CartCommunicator):
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


    private lateinit var productSelected: (ProductInCart, Int) -> Unit
    fun setOnProductSelectedListener(listener: (ProductInCart, Int) -> Unit) {
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

        fun bind(product: ProductInCart) {
            binding.apply {
                tvName.text = product.product.product_name
                tvBody.text = product.product.description
                tvPrice.text = "$ ${product.product.price}"
            }
            setupButton(product)

        }

        private fun setupButton(product: ProductInCart) {
            binding.btnIncrease.setOnClickListener {
                val currentValue = binding.tvQuantity.text.toString().toInt()
                product.quantity=(currentValue + 1)
                binding.tvQuantity.setText(product.quantity.toString())
                updateTotalPrice(product)
                cartPresenter.updateTotalPrice(product.product.price.toInt())
                cartCommunicator.updateList(product)
            }
            binding.btnDecrease.setOnClickListener {
                val currentValue = binding.tvQuantity.text.toString().toInt()
                if(currentValue>0){
                    product.quantity=(currentValue - 1)
                    binding.tvQuantity.setText(product.quantity.toString())
                    updateTotalPrice(product)
                    cartPresenter.updateTotalPrice(-product.product.price.toInt())
                    cartCommunicator.updateList(product)
                }
            }
        }
        private fun updateTotalPrice(product: ProductInCart){
            val currentQuantity= binding.tvQuantity.text.toString().toInt()
            val totalPrice=currentQuantity * product.product.price.toInt()
            binding.tvTotalPrice.text= totalPrice.toString()
            product.amount=totalPrice
        }

    }
}