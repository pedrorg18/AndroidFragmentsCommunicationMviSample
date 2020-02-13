package com.pedroroig.fragmentssampleapp.features.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedroroig.fragmentssampleapp.*
import com.pedroroig.fragmentssampleapp.features.users.list.UserListFragment
import com.pedroroig.fragmentssampleapp.utils.launchFragmentReplacing

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
        Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show()
    }

    private fun renderContent(content: MainActivityViewContent) {
        when(content) {
            MainActivityViewContent.ShowUserList -> launchListFragment()
        }
    }

    private fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun launchListFragment() {
        launchFragmentReplacing(
            UserListFragment(),
            R.id.container_fragment_main,
            supportFragmentManager, false)
    }
}
