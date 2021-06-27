package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.databinding.DealListItemBinding

class DealItemAdapter : RecyclerView.Adapter<DealItemAdapter.DealItemViewHolder>() {

    var dealsList: List<Deal>? = null
    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dealsList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        viewHolder.setup(dealsList!![position])
    }

    inner class DealItemViewHolder(
        private val binding: DealListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setup(item: Deal) {

            binding.dealListItemImageView.apply {
                setOnClickListener{
                    listener?.itemCLicked(item)
                }
            }

            binding.dealListItemTitle.text = item.title
            binding.dealListItemPrice.text = item.regular_price?.display_string
            binding.tvAisle.text = item.aisle.uppercase()
            Glide.with(binding.root.context).load(item.image_url).into(binding.dealListItemImageView)

        }
    }
}



interface Listener{
    fun itemCLicked(item: Deal)
}