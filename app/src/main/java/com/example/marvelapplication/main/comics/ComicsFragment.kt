package com.example.marvelapplication.main.comics


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.characters.MarvelCharactersResults
import com.example.marvelapplication.databinding.FragmentComicsBinding
import com.example.marvelapplication.main.MainActivity
import com.example.marvelapplication.utils.Constants
import com.example.marvelapplication.utils.GlideUtils
import com.example.marvelapplication.utils.Utils
import com.example.marvelapplication.utils.Utils.toString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment(private val character: MarvelCharactersResults) :
    Fragment(R.layout.fragment_comics) {

    private val viewModel: ComicsViewModel by viewModels()
    private lateinit var binding: FragmentComicsBinding
    private lateinit var comicsAdapter: ComicsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComicsBinding.bind(view)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        initView()
        initClick()
        getComics()
        listenComicsData()
    }


    private fun initClick() {
        binding.run {
            btnBack.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }
        }
    }

    private fun initView() {

        binding.run {

            comicsAdapter = ComicsAdapter(listOf())
            recyclerViewComics.layoutManager =
                GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
            (recyclerViewComics.layoutManager as GridLayoutManager).orientation =
                GridLayoutManager.HORIZONTAL


//            recyclerViewSeries.layoutManager =
//                GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
//            (recyclerViewSeries.layoutManager as GridLayoutManager).orientation =
//                GridLayoutManager.HORIZONTAL

            recyclerViewComics.adapter = comicsAdapter
//            recyclerViewSeries.adapter=comicsAdapter


            character.run {
                thumbnail.run {
                    GlideUtils.urlToImageView(
                        requireContext(), "$path.$extension",
                        imgComics
                    )
                }
                textName.text = name
                if (!description.isEmpty()) {
                    textDescription.text = description
                } else descriptionLabel.visibility = View.INVISIBLE

                if (urls.isNotEmpty()) {
                    relatedLinksLabel.visibility = View.VISIBLE
                    for (item in urls) {
                        when (item.type) {
                            "detail" -> {
                                detailsLabel.visibility = View.VISIBLE
                                detailsLabel.setOnClickListener {
                                    val openURL = Intent(Intent.ACTION_VIEW)
                                    openURL.data = Uri.parse(item.url)
                                    startActivity(openURL)

                                }
                            }
                            "wiki" -> {
                                wikiLabel.visibility = View.VISIBLE
                                wikiLabel.setOnClickListener {
                                    val openURL = Intent(Intent.ACTION_VIEW)
                                    openURL.data = Uri.parse(item.url)
                                    startActivity(openURL)

                                }
                            }
                            "comiclink" -> {
                                comicLinkLabel.visibility = View.VISIBLE
                                comicLinkLabel.setOnClickListener {
                                    val openURL = Intent(Intent.ACTION_VIEW)
                                    openURL.data = Uri.parse(item.url)
                                    startActivity(openURL)

                                }
                            }

                        }

                    }
                }


            }
        }
    }

    private fun getComics() {
        if (Utils.isNetworkAvailable(requireContext())) {
            val currentDate = Utils.getCurrentDateTime()
            val dateString = currentDate.toString("yyyy-MM-dd")
            viewModel.getComics(character.id, Constants.COMICS_START_DATE + "," + dateString)
        } else {
            Utils.showToast(requireContext(), getString(R.string.no_internet))
        }
    }

    private fun listenComicsData() {
        viewModel.postComics.observe(requireActivity(), {
            it?.let { comics ->
                comicsAdapter.updateComics(comics.data.results)
            }
        })
    }

}