package com.example.marvelapplication.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.marvelapplication.main.MainActivity
import com.example.marvelapplication.main.comics.ComicsFragment
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initView()
        listenCharactersData()
    }

    private fun initView() {

        binding.run {
            charactersAdapter = CharactersAdapter {character -> characterClick(character) }
            recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            recyclerViewCharacters.adapter=charactersAdapter
        }
    }

    private fun listenCharactersData() {
        viewModel.getCharacters().observe(requireActivity(),{
            charactersAdapter.submitList(it)
        })
    }

    private fun characterClick(character: MarvelCharactersResults) {
        val comicsFragment = ComicsFragment(character)
        (activity as MainActivity).loadFragment(comicsFragment,"ComicsFragment")
    }

}