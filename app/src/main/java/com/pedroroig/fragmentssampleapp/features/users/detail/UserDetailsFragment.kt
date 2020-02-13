package com.pedroroig.fragmentssampleapp.features.users.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedroroig.fragmentssampleapp.R
import com.pedroroig.fragmentssampleapp.features.users.MainViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : Fragment() {

    private val vm: MainViewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_user_details, container, false)

        observeViewModel()

        return rootView
    }

    private fun observeViewModel() {
        vm.userDetailsFragmentViewStateLiveData.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun render(viewState: UserDetailsFragmentViewState) {
        with(viewState) {
            text_user_title.text = title
            text_user_subtitle.text = subTitle
            text_user_under_picture.text = longText
            image_user.visibility = VISIBLE
        }

    }

}