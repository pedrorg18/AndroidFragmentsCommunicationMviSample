package com.pedroroig.fragmentssampleapp.features.users

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedroroig.fragmentssampleapp.*
import com.pedroroig.fragmentssampleapp.features.users.detail.UserDetailsFragment
import com.pedroroig.fragmentssampleapp.features.users.list.UserListFragment
import com.pedroroig.fragmentssampleapp.utils.launchFragmentReplacing
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchListFragment()

        observeViewModel()
        vm.loadUserList()
    }

    private fun observeViewModel() {
        vm.mainActivityViewStateLiveData.observe(this, Observer {
            render(it)
        })
    }

    private fun render(viewState: MainActivityViewState) {
        when(viewState) {
            MainActivityViewState.Loading -> renderLoading()
            is MainActivityViewState.Content -> renderContent(viewState.content)
            is MainActivityViewState.Error -> renderError(viewState.message)
        }
    }

    private fun renderLoading() {
        progressBar.visibility = VISIBLE
    }

    private fun renderContent(content: MainActivityViewContent) {
        progressBar.visibility = GONE
        when(content) {
            MainActivityViewContent.ShowUserList -> launchListFragment()
            MainActivityViewContent.ShowUserDetails -> launchDetailsFragment()
        }
    }

    private fun renderError(message: String) {
        progressBar.visibility = GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun launchListFragment() {
        launchFragmentReplacing(
            UserListFragment(),
            R.id.container_fragment_main,
            supportFragmentManager, false)
    }

    private fun launchDetailsFragment() {
        launchFragmentReplacing(
            UserDetailsFragment(),
            R.id.container_fragment_main,
            supportFragmentManager,
            addToBackStack = true,
            doSlideInOutAnimation = true
        )
    }
}
