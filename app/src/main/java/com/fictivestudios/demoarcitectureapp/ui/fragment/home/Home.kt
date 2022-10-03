package com.fictivestudios.demoarcitectureapp.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercemvvmpractice2.utilities.extensions.showToast
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.base.BaseFragment
import com.fictivestudios.demoarcitectureapp.data.model.response.user.User
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.databinding.HomeactivityBinbing
import com.fictivestudios.demoarcitectureapp.utils.Titlebar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Home : BaseFragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: HomeactivityBinbing


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_home, container, false)





        setupObserver()


        return binding.root

    }

    override fun setTitlebar(titlebar: Titlebar) {

    }


    private fun setupUI(users: ArrayList<User>) {
        val homeDailyExerciesAdapter = MainAdapter(users)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            binding.recyclerView.adapter = homeDailyExerciesAdapter
            binding.recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    private fun setupObserver() {
        mainViewModel.fetchUsers()

        mainViewModel.response.observe(getActivityContext!!, Observer { response ->

            when (response) {

                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    setupUI(response.data!!.users as ArrayList<User>)
                    binding.recyclerView.visibility = View.VISIBLE

                    Log.d("Success", response.toString())

                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(response.message.toString(),getActivityContext)
                    Log.d("Error", response.message.toString())
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

            }

        })


    }




}