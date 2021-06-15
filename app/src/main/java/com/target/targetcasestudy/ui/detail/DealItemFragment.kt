package com.target.targetcasestudy.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.MainViewModel
import com.target.targetcasestudy.data.network.Resource
import com.target.targetcasestudy.databinding.FragmentDealItemBinding


class DealItemFragment : Fragment() {

    private lateinit var binding: FragmentDealItemBinding
    private var viewModel: MainViewModel? = null

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
            ViewModelProvider(this).get(MainViewModel::class.java)
        }
//        use this to fetch deal detail from api
//        viewModel?.selectedDeal?.value?.let { viewModel?.fetchDealItem(it) }

        setupUI()

        addObservers()

    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel?.selectedDeal?.value?.let { item ->

            binding.recyclerView.adapter = DealDetailAdapter().apply {
                viewModel?.getWrapperList(item)?.let {
                    this.list = it
                }
            }

        }


    }

    private fun addObservers(){

        viewModel?.dealLiveData?.observe(viewLifecycleOwner,{

            when(it.status){

                Resource.Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = DealDetailAdapter().apply {
                        it.data?.let { item ->
                            viewModel?.getWrapperList(item)?.let { list ->
                                this.list = list
                            }
                        }
                    }

                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()
                }
            }


        })
    }


}
