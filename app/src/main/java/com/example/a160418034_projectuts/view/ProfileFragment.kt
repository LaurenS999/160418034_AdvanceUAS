package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.databinding.FragmentProfileBinding
import com.example.a160418034_projectuts.databinding.FragmentRecipeDetailBinding
import com.example.a160418034_projectuts.model.User
import com.example.a160418034_projectuts.util.loadImage
import com.example.a160418034_projectuts.viewmodel.RecipeDetailModel
import com.example.a160418034_projectuts.viewmodel.UserModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class ProfileFragment : Fragment() {
    private lateinit var ViewModel: UserModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener{
            val action = ProfileFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        ViewModel = ViewModelProvider(this).get(UserModel::class.java)
        //Digunakan Saat Reset Database
//        addDeafultUser()
        ViewModel.FetchUser("Lauren", "123")

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.UserDetail.observe(viewLifecycleOwner, Observer {
            dataBinding.user= it
        })

    }

    fun addDeafultUser()
    {
        var user1 = User("Lauren", "123", "jl. basuki rahmat", "123456789", "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
        var user2 = User("Lauren2", "456", "jl. dewi sartika", "987654321", "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
        val list = listOf(user1,user2)
        ViewModel.adduser(list)
    }

}