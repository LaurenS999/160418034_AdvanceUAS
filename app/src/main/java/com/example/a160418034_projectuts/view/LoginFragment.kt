package com.example.a160418034_projectuts.view

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.databinding.FragmentLoginBinding
import com.example.a160418034_projectuts.databinding.FragmentProfileBinding
import com.example.a160418034_projectuts.model.User
import com.example.a160418034_projectuts.viewmodel.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import kotlin.math.log

class LoginFragment : Fragment(), ButtonLogin {
    private lateinit var ViewModel: UserModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.loginListener = this

        ViewModel = ViewModelProvider(this).get(UserModel::class.java)
        ViewModel.Fetch()

    }

    override fun onButtonLoginClick(v: View) {
        ViewModel.FetchUser(txtUsername.text.toString(), txtPassword.text.toString())
        if(ViewModel.UserDetail.value?.Username == txtUsername.text.toString())
        {
            Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification("Berhasil Login",
                                "A new notification created",
                                R.drawable.ic_baseline_person_24)
                    }

            val action = LoginFragmentDirections.actionLoginFragmentToRecipeListFragment(txtUsername.text.toString())
            Navigation.findNavController(v).navigate(action)
        }
        else
        {
            //Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
        }
    }
}