package com.example.presentation.products.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.entity.models.AnimalModel
import com.example.presentation.databinding.FragmentMainMenuBinding
import com.example.presentation.products.adapters.AnimalRvAdapter
import com.example.presentation.products.viewmodels.MainMenuViewModel
import com.example.repository.utils.ErrorType
import com.example.repository.utils.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private val mainMenuViewModel by viewModels<MainMenuViewModel>()

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var animalRvAdapter: AnimalRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupViews()

        observeErrors()

        getProducts()
    }

    private fun setupAdapter() {

        animalRvAdapter = AnimalRvAdapter()
    }

    private fun observeErrors() {

        mainMenuViewModel.otherErrorLV.observe(viewLifecycleOwner) {
            when (it) {
                ErrorType.NETWORK -> {
                    Toast.makeText(
                        requireContext(),
                        "Unfortunately an error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ErrorType.TIMEOUT -> {
                    Toast.makeText(
                        requireContext(),
                        "متاسفانه ارتباط با سرور برقرار نشد.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ErrorType.UNKNOWN -> {
                    Toast.makeText(requireContext(), "خطای ناشناخته", Toast.LENGTH_SHORT).show()
                }
                ErrorType.CACHE -> {
                    Toast.makeText(requireContext(), "خطای دیتابیس", Toast.LENGTH_SHORT).show()
                }
            }
        }

        mainMenuViewModel.networkErrorLV.observe(viewLifecycleOwner) {
            /**
             * send error info to sentry or server..
             */
            Toast.makeText(
                requireContext(),
                "متاسفانه مشکلی در ارتباط با سرور به وجود آمد.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getProducts() {

        mainMenuViewModel.observeAnimals().observe(
            viewLifecycleOwner
        ) {
            when (it) {
                State.emptyList -> {
                    stateChanged(false, false)
                    Toast.makeText(requireContext(), "لیست خالی است.", Toast.LENGTH_SHORT).show()
                }
                State.idle -> {
                    //do nothing for this
                }
                State.loading -> {
                    stateChanged(true)
                }
                is State.failed -> {
                    stateChanged(false)
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is State.success<*> -> {
                    val data = it.data as List<AnimalModel>
                    if (data.size > 0) {
                        stateChanged(false, true)
                        animalRvAdapter.updateList(data)
                    } else {
                        stateChanged(false, false)
                    }
                }
            }
        }
    }

    private fun setupViews() {

        binding.rvMainMenuFragmentProductRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = animalRvAdapter
        }
    }

    private fun stateChanged(isLoading: Boolean, hasData: Boolean = false) =
        if (isLoading) {
            binding.apply {
                rvMainMenuFragmentProductRv.visibility = View.GONE
                constMainMenuFragmentEmptyList.visibility = View.GONE
                pgMainMenuFragmentProgressbar.visibility = View.VISIBLE
            }
        } else {
            binding.pgMainMenuFragmentProgressbar.visibility = View.GONE
            if (hasData) {
                binding.apply {
                    constMainMenuFragmentEmptyList.visibility = View.GONE
                    rvMainMenuFragmentProductRv.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    constMainMenuFragmentEmptyList.visibility = View.VISIBLE
                    rvMainMenuFragmentProductRv.visibility = View.GONE
                }
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}