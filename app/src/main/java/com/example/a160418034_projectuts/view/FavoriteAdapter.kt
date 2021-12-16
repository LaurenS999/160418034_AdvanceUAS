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
import com.example.a160418034_projectuts.util.loadImage
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class FavoriteAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(),RecipeDetailClick {
    class FavoriteViewHolder(var view: RecipeListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList: List<Recipe>)
    {
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecipeListItemBinding>(inflater, R.layout.recipe_list_item, parent, false)
        return FavoriteAdapter.FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        if(recipeList.isNotEmpty()) {
            holder.view.recipe = recipeList[position]
            holder.view.detailListener = this
        }
        else
        {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onRecipeDetialClick(v: View) {
        var id = v.tag.toString().toInt()
        val action = FavoriteFragmentDirections.actionRecipeDetilFragment(id)
        Navigation.findNavController(v).navigate(action)
    }

}