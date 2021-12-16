package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.viewmodel.RecipeListModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_kategori_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class FavoriteFragment : Fragment() {
    private lateinit var ViewModel: RecipeListModel
    private val recipeListAdapter = FavoriteAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModel = ViewModelProvider(this).get(RecipeListModel::class.java)
        ViewModel.favorite()

        recViewFav.layoutManager = LinearLayoutManager(context)
        recViewFav.adapter = recipeListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.RecipeLD.observe(viewLifecycleOwner, Observer {
            recipeListAdapter.updateRecipeList(it)
        })

        ViewModel.RecipeLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                txtErrorFav.visibility = View.VISIBLE
            } else {
                txtErrorFav.visibility = View.GONE
            }
        })

        ViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressBarFav.visibility = View.VISIBLE
                recViewFav.visibility = View.GONE
            } else {
                progressBarFav.visibility = View.GONE
                recViewFav.visibility = View.VISIBLE
            }
        })
    }
}