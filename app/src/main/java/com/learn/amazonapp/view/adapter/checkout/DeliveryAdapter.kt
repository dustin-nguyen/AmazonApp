package com.learn.amazonapp.view.adapter.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.RadioItemBinding
import com.learn.amazonapp.model.remote.entity.Address


class DeliveryAdapter (val listOfItem: List<Address>)
    : RecyclerView.Adapter<DeliveryAdapter.RadioViewHolder>() {

    private lateinit var binding: RadioItemBinding
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = RadioItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = RadioViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(listOfItem[position])
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }
    // Method to get the selected address
    fun getSelectedAddress(): Address? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            listOfItem[selectedPosition]
        } else {
            null
        }
    }

    inner class RadioViewHolder(val binding: RadioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(address: Address) {
           binding.radioButton.text= "${address.title}\n${address.address}"
            binding.radioButton.isChecked = selectedPosition == position

            binding.radioButton.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }

        }
    }
}