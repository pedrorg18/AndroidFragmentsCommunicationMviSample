package com.pedroroig.fragmentssampleapp.features.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroroig.fragmentssampleapp.repository.UserRepository

class MainViewModel : ViewModel() {

    private val userRepository = UserRepository()


    val mainActivityViewStateLiveData = MutableLiveData<MainActivityViewState>()
    val userListFragmentViewStateLiveData = MutableLiveData<UserListFragmentViewState>()

    fun loadUserList() {
        mainActivityViewStateLiveData.value =
            MainActivityViewState.Content(
                MainActivityViewContent.ShowUserList
            )

        userListFragmentViewStateLiveData.value = UsersListDomainToViewStateMapper().map(
            userRepository.getUsers())
    }

    fun loadUserDetail(user: String) {
        Log.i("USERS::", "$user clicked")
    }
}