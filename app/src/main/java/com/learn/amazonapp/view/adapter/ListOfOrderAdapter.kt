package com.learn.amazonapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.ViewHolderCartCheckoutBinding
import com.learn.amazonapp.databinding.ViewHolderOrderBinding
import com.learn.amazonapp.model.ProductInCart
import com.learn.amazonapp.model.remote.entity.Order
import com.learn.amazonapp.view.adapter.checkout.OrderConfirmAdapter

class ListOfOrderAdapter(val listOfItem: List<Order> ):
    RecyclerView.Adapter<ListOfOrderAdapter.OrderViewHolder>() {
    private lateinit var binding: ViewHolderOrderBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewHolderOrderBinding.inflate(layoutInflater, parent, false)
        val viewHolder = OrderViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(listOfItem[position])
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }


    private lateinit var productSelected: (Order, Int) -> Unit
    fun setOnProductSelectedListener(listener: (Order, Int) -> Unit) {
        productSelected = listener
    }

    inner class OrderViewHolder(val binding: ViewHolderOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    productSelected(listOfItem[position], position)
                }
            }
        }

        fun bind(order: Order) {
            binding.apply {
                tvOrderId.text = "Order : ${order.order_id}"
                tvAmount.text =  "Amount: $ ${order.bill_amount}"
                tvAddress.text = "To : ${order.address}"
                tvDate.text = order.order_date
                tvStatus.text = "Status: ${order.order_status}"
            }
        }
    }
}