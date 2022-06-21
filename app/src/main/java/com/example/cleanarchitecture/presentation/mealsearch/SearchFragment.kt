package com.example.cleanarchitecture.presentation.mealsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding:FragmentSearchBinding?=null
    private val binding:FragmentSearchBinding
    get() = _binding!!
    private val searchViewModel:SearchViewModel by viewModels()
    private val mealSearchAdapter=MealSearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtsearchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.recycler.apply {
            adapter=mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            searchViewModel.mealsearchlist.collect {
                if (it.isLoading){
                    binding.progressbar.visibility=View.VISIBLE
                }
                if (it.error.isNotBlank()){
                    binding.progressbar.visibility=View.GONE
                }
                it.list?.let {
                    binding.progressbar.visibility=View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }
            }
        }
    }
}