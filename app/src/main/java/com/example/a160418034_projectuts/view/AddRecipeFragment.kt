package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.databinding.FragmentAddRecipeBinding
import com.example.a160418034_projectuts.databinding.FragmentRecipeDetailBinding
import com.example.a160418034_projectuts.model.Recipe
import com.example.a160418034_projectuts.viewmodel.RecipeDetailModel
import kotlinx.android.synthetic.main.fragment_add_recipe.*

class AddRecipeFragment : Fragment(),ButtonAddClick {
    private lateinit var viewModel:RecipeDetailModel
    private lateinit var databinding: FragmentAddRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentAddRecipeBinding>(inflater, R.layout.fragment_add_recipe, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailModel::class.java)
        databinding.addListener = this

    }

    override fun onButtonAddClick(v: View) {
        var recipe = Recipe(txtRecipeName.text.toString(), txtKategoriAdd.text.toString(), txtRecipeName.text.toString(), txtKategori.text.toString(), txtStepAdd.text.toString(), txtPhotoUrl.text.toString(), 0)
        val list = listOf(recipe)
        viewModel.addRecipe(list)
        Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }
}