package com.target.targetcasestudy.ui.detail

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.target.targetcasestudy.MainViewModel
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.network.Resource
import com.target.targetcasestudy.databinding.FragmentDealItemBinding


class DealDetailFragment : Fragment() {

    private lateinit var binding: FragmentDealItemBinding
    private var viewModel: DealDetailViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(DealDetailViewModel::class.java)
        }

        arguments?.getInt("KEY")?.let { id ->

            viewModel?.getDeal(id)

        }

        addObservers()

    }

    private fun addObservers() {

        viewModel?.dealLiveData?.observe(viewLifecycleOwner, {

            when (it.status) {

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                    it.data?.let { it1 -> setupUI(it1) }

                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }


        })
    }

    private fun setupUI(deal: Deal) {

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
            binding.tvDescription.text = deal.description

        } ?: run{

            deal.regular_price?.let { regularPrice ->
                binding.tvPrimaryPrice.text = regularPrice.display_string
            }

        }


    }


}
