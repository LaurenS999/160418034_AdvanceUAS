package com.example.a160418034_projectuts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.model.Kategori
import com.example.a160418034_projectuts.viewmodel.RecipeListModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list_by_kategori.*

class RecipeListByKategoriFragment : Fragment() {
    private lateinit var ViewModel: RecipeListModel
    private lateinit var recipeListAdapter:RecipeListByKategoriAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list_by_kategori, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val KategoriName = RecipeListByKategoriFragmentArgs.fromBundle(requireArguments()).NamaKategori

        recipeListAdapter = RecipeListByKategoriAdapter(arrayListOf(), KategoriName)

        ViewModel = ViewModelProvider(this).get(RecipeListModel::class.java)
        ViewModel.FecthByKategori(KategoriName)

        recViewRecipeByKategori.layoutManager = LinearLayoutManager(context)
        recViewRecipeByKategori.adapter = recipeListAdapter

        observeViewModel()

    }

    fun observeViewModel(){
        ViewModel.RecipeLD.observe(viewLifecycleOwner, Observer {
            recipeListAdapter.updateRecipeList(it)
        })

        ViewModel.RecipeLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                txtErrorRecipeByKategori.visibility = View.VISIBLE
            } else {
                txtErrorRecipeByKategori.visibility = View.GONE
            }
        })

        ViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressBar2.visibility = View.VISIBLE
                recViewRecipeByKategori.visibility = View.GONE
            } else {
                progressBar2.visibility = View.GONE
                recViewRecipeByKategori.visibility = View.VISIBLE
            }
        })
    }
}