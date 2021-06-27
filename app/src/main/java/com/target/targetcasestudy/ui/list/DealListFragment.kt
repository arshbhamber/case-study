package com.target.targetcasestudy.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.data.network.Resource.Status
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.ui.DealItemAdapter
import com.target.targetcasestudy.ui.Listener
import com.target.targetcasestudy.ui.detail.DealDetailFragment


class DealListFragment : Fragment() {

    private var viewModel: DealListViewModel? = null
    private lateinit var binding: FragmentDealListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.let { ViewModelProvider(it).get(DealListViewModel::class.java) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDealListBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        addObservers()

//        viewModel?.fetchListOfDeals()

    }

    private fun setupUI(){

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            ).apply {
                context?.resources?.getDrawable(R.drawable.divider, null)?.let { setDrawable(it) }
            }
        )

        binding.recyclerView.adapter = DealItemAdapter().apply {
            listener = object : Listener {
                override fun itemCLicked(item: Deal) {
                    showDealItemFragment(item)
//                    viewModel?.selectedDeal?.postValue(item)
                }

            }
        }

    }

    private fun showDealItemFragment(item: Deal) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )

            .replace(R.id.container, DealDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("KEY",item.id)
                }
            })
            .addToBackStack("item")
            .commit()

    }


    private fun addObservers() {

        viewModel?.dealListLiveData?.observe(viewLifecycleOwner, {

            when(it.status){

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    (binding.recyclerView.adapter as DealItemAdapter).apply {
                        dealsList = viewModel?.dealListLiveData?.value?.data
//                        dealsList = StaticData.deals
                        notifyDataSetChanged()
                    }
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()

                }

            }

        })
    }


}
