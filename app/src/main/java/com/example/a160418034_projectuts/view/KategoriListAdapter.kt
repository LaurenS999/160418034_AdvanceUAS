package com.example.a160418034_projectuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.model.Kategori
import com.example.a160418034_projectuts.model.Recipe
import com.example.a160418034_projectuts.util.loadImage
import kotlinx.android.synthetic.main.kategori_list_item.view.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class KategoriListAdapter(val kategoriList: ArrayList<Kategori>): RecyclerView.Adapter<KategoriListAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateRecipeList(newKategoriList: List<Kategori>)
    {
        kategoriList.clear()
        kategoriList.addAll(newKategoriList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kategori_list_item, parent, false)
        return KategoriViewHolder(view)

    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        holder.view.btnKategori.setText(kategoriList[position].name)
        holder.view.btnKategori.setOnClickListener {
            val action = KategoriListFragmentDirections.actionKategoriListFragment2ToRecipeListByKategoriFragment(kategoriList[position].name.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return kategoriList.size
    }

    fun updateTodoList(newKategoriList: List<Kategori>) {
        kategoriList.clear()
        kategoriList.addAll(newKategoriList)
        notifyDataSetChanged()
    }


}