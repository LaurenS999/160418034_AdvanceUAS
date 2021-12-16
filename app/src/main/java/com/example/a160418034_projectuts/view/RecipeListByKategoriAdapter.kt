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

class RecipeListByKategoriAdapter(val recipeList: ArrayList<Recipe>, val Kategori:String): RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>(), RecipeDetailClick
{
    class RecipeViewHolder(var view: RecipeListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList: List<Recipe>)
    {
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListAdapter.RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecipeListItemBinding>(inflater, R.layout.recipe_list_item, parent, false)
        return RecipeListAdapter.RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeListAdapter.RecipeViewHolder, position: Int) {
        holder.view.detailListener = this
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
        val id = v.tag.toString().toInt()
        val action = RecipeListByKategoriFragmentDirections.actionRecipeDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}