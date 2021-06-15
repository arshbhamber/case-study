package com.target.targetcasestudy.ui.detail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.data.model.DealDetailItemType
import com.target.targetcasestudy.data.model.DealItem
import com.target.targetcasestudy.data.model.DealDetailItemWrapper
import com.target.targetcasestudy.databinding.DealItemDetailBinding
import com.target.targetcasestudy.databinding.TextItemBinding

class DealDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var list: List<DealDetailItemWrapper<*>>

    override fun getItemViewType(position: Int): Int {
        return position

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (list[viewType].type) {

            DealDetailItemType.DEAL_TOP_VIEW -> DealDetailViewHolder(
                DealItemDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> TextViewHolder(
                TextItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).textView
            )

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (list[position].type) {
            DealDetailItemType.DEAL_TOP_VIEW -> (holder as DealDetailViewHolder).setupUI(list[position].data as DealItem)
            DealDetailItemType.DEAL_TEXT_VIEW  -> (holder as TextViewHolder).updateData(list[position].data as String)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class DealDetailViewHolder(
        private val binding: DealItemDetailBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setupUI(deal: DealItem) {

            Glide.with(binding.root.context).load(deal.image_url).into(binding.imageView)
            binding.tvTitle.text = deal.title

            deal.sale_price?.let { salePrice ->
                binding.llSecondaryPrice.visibility = View.VISIBLE
                binding.tvPrimaryPrice.text = salePrice.display_string
                deal.regular_price?.let { regularPrice ->
                    binding.tvSecondaryPrice.text = regularPrice.display_string
                    binding.tvSecondaryPrice.paintFlags =
                        binding.tvSecondaryPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }

            } ?: run{

                deal.regular_price?.let { regularPrice ->
                    binding.tvPrimaryPrice.text = regularPrice.display_string
                }

            }


        }


    }


    inner class TextViewHolder(
        private val textView: TextView
    ) : RecyclerView.ViewHolder(textView) {

        fun updateData(text: String) {
            textView.text = text
        }

    }

}