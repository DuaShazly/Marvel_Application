package com.example.marvelapplication.main.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.marvelapplication.main.MainActivity
import com.example.marvelapplication.main.comics.ComicsFragment
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var charactersAdapter: CharactersAdapter
    private var marvelNames = mutableListOf<MarvelCharactersResults>()



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
//            svCharacters.setOnQueryTextListener(this@HomeFragment)
        }


    }

    private fun listenCharactersData() {
        viewModel.getCharacters().observe(requireActivity(),{
            marvelNames = it
            charactersAdapter.submitList(it)
        })
    }

    private fun characterClick(character: MarvelCharactersResults) {
        val comicsFragment = ComicsFragment(character)
        (activity as MainActivity).loadFragment(comicsFragment,"ComicsFragment")
    }

    private fun searchByName(query:String) {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu.findItem(R.id.character_search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint = "Search Character"
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
//                    allCharacterViewModel.searchQuery.value = newText
                }
                return true
            }
        })

    }



}