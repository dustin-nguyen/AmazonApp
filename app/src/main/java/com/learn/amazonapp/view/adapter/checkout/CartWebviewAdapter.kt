package com.learn.amazonapp.view.adapter.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.ViewHolderCartCheckoutBinding
import com.learn.amazonapp.databinding.ViewHolderCartItemBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.presenter.cart.CartPresenter
import com.learn.amazonapp.view.CartCommunicator
import com.learn.amazonapp.view.adapter.CartAdapter

class CartWebviewAdapter(val listOfItem: List<ProductInCart>, ):
    RecyclerView.Adapter<CartWebviewAdapter.ProductViewHolder>() {
    private lateinit var binding: ViewHolderCartCheckoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewHolderCartCheckoutBinding.inflate(layoutInflater, parent, false)
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

    inner class ProductViewHolder(val binding: ViewHolderCartCheckoutBinding) :
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
                tvAmount.text=  "Amount      $ ${product.amount}"
                tvPrice.text =  "Unit Price  $ ${product.product.price}"
                tvQuantity.text="Quantity      ${product.quantity}"
                //Picasso.get().load(URL_IMAGE+category.category_image_url).into(binding.imgCategory)

            }


        }

    }
}