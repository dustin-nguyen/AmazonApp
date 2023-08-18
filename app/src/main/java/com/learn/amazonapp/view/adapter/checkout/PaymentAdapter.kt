package com.learn.amazonapp.view.adapter.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.amazonapp.databinding.RadioItemBinding
import com.learn.amazonapp.model.remote.entity.Address

class PaymentAdapter(val listOfItem: List<String>):
    RecyclerView.Adapter<PaymentAdapter.RadioViewHolder>() {
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
    fun getSelectedPayMethod(): String? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            listOfItem[selectedPosition]
        } else {
            null
        }
    }

    inner class RadioViewHolder(val binding: RadioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(payMethod: String) {
            binding.radioButton.text= payMethod
            binding.radioButton.isChecked = selectedPosition == position

            binding.radioButton.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }

        }

    }
}