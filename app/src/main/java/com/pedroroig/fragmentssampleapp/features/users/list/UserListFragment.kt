package com.pedroroig.fragmentssampleapp.features.users.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedroroig.fragmentssampleapp.R
import com.pedroroig.fragmentssampleapp.features.users.MainViewModel

/**
 * A fragment representing a list of users.
 */
class UserListFragment : Fragment() {

    private lateinit var userListRecyclerViewAdapter: UserListRecyclerViewAdapter

    private val vm: MainViewModel by lazy {
        ViewModelProvider(activity!!)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        initRecyclerView(view as RecyclerView)

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        vm.userListFragmentViewStateLiveData.observe(activity!!, Observer<UserListFragmentViewState> {
                viewState ->
            render(viewState)
        })
    }

    private fun render(viewState: UserListFragmentViewState) {
        userListRecyclerViewAdapter.updateValues(viewState.userList)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        userListRecyclerViewAdapter =
            UserListRecyclerViewAdapter(
                emptyList(),
                elementClickListener
            )
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = userListRecyclerViewAdapter
        }
    }

    private val elementClickListener: (Int) -> Unit = {
        Toast.makeText(context, "click user: $it", Toast.LENGTH_SHORT).show()
        vm.loadUserDetail(it)
    }
}
