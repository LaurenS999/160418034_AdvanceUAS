package com.example.a160418034_projectuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.databinding.RecipeListItemBinding
import com.example.a160418034_projectuts.model.Recipe
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipeListAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>(), RecipeDetailClick {
    class RecipeViewHolder(var view: RecipeListItemBinding) : RecyclerView.ViewHolder(view.root)
    fun updateRecipeList(newRecipeList: List<Recipe>)
    {
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<RecipeListItemBinding>(inflater, R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.view.recipe = recipeList[position]
        holder.view.detailListener = this
    }
    override fun getItemCount(): Int {
        return recipeList.size
    }
    fun updateTodoList(newRecipeList: List<Recipe>) {
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }
    override fun onRecipeDetialClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = RecipeListFragmentDirections.actionRecipeDetil(id)
        Navigation.findNavController(v).navigate(action)
    }
}