package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.model.Kategori
import com.example.a160418034_projectuts.viewmodel.KategoriModel
import com.example.a160418034_projectuts.viewmodel.RecipeListModel
import kotlinx.android.synthetic.main.fragment_kategori_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class KategoriListFragment : Fragment() {
    private lateinit var ViewModel: KategoriModel
    private val KategoriListAdapter = KategoriListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModel = ViewModelProvider(this).get(KategoriModel::class.java)

        ViewModel.refresh()
        //Digunakan saat Reset Database
//        ViewModel.ClearKategori()
//        addDefaultKategori()

        recViewKategori.layoutManager = LinearLayoutManager(context)
        recViewKategori.adapter = KategoriListAdapter

        observeViewModel()
    }

    fun addDefaultKategori() {
        var todo = Kategori("Ayam")
        var todo2 = Kategori("Nasi")
        val list = listOf(todo, todo2)
        ViewModel.addTodo(list)

    }

    fun observeViewModel(){
        ViewModel.KategoriLD.observe(viewLifecycleOwner, Observer {
            KategoriListAdapter.updateRecipeList(it)
            if(it.isEmpty())
            {
                txtEmptyKategori.visibility = View.VISIBLE
            }
            else
            {
                txtEmptyKategori.visibility = View.GONE
            }
        })

        ViewModel.KategoriLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                txtErrorKategori.visibility = View.VISIBLE
            } else {
                txtErrorKategori.visibility = View.GONE
            }
        })

        ViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressBarKategori.visibility = View.VISIBLE
                recViewKategori.visibility = View.GONE
            } else {
                progressBarKategori.visibility = View.GONE
                recViewKategori.visibility = View.VISIBLE
            }
        })
    }
}