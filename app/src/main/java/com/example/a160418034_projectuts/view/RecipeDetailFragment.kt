package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.databinding.FragmentRecipeDetailBinding
import com.example.a160418034_projectuts.model.Recipe
import com.example.a160418034_projectuts.util.loadImage
import com.example.a160418034_projectuts.viewmodel.RecipeDetailModel
import com.example.a160418034_projectuts.viewmodel.RecipeListModel
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.view.*

class RecipeDetailFragment : Fragment(), ButtonEditClick, ButtonDeleteClick {
    private lateinit var ViewModel: RecipeDetailModel
    private lateinit var dataBinding:FragmentRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentRecipeDetailBinding>(inflater, R.layout.fragment_recipe_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.editListner = this
        dataBinding.deleteListener = this
        val uuid = RecipeDetailFragmentArgs.fromBundle(requireArguments()).Index

        ViewModel = ViewModelProvider(this).get(RecipeDetailModel::class.java)
        ViewModel.fetch(uuid)

//        imageView2.loadImage(ViewModel.RecipeDetialLD.value?.photoUrl,
//                view.progressBarRecipeDetail)

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.RecipeDetialLD.observe(viewLifecycleOwner, Observer {
            dataBinding.detail= it
        })

    }

    override fun onButtonDeleteClick(v: View, obj: Recipe) {
        ViewModel.DeleteRecipe(obj)
    }

    override fun onButtonEditClick(v: View, obj: Recipe) {
        val uuid = RecipeDetailFragmentArgs.fromBundle(requireArguments()).Index
        ViewModel.EditRepice(uuid, txtNamaRecipe.text.toString(), txtKategori.text.toString()
            ,txtDescription.text.toString(), txtRecipe.text.toString(), txtStep.text.toString())
    }
}