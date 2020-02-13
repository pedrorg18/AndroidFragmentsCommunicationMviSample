package com.pedroroig.fragmentssampleapp.features.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroroig.fragmentssampleapp.domain.model.User
import com.pedroroig.fragmentssampleapp.features.users.detail.UserDetailsDomainToViewStateMapper
import com.pedroroig.fragmentssampleapp.features.users.detail.UserDetailsFragmentViewState
import com.pedroroig.fragmentssampleapp.features.users.list.UserListFragmentViewState
import com.pedroroig.fragmentssampleapp.features.users.list.UsersListDomainToViewStateMapper
import com.pedroroig.fragmentssampleapp.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private var userList: List<User> = emptyList()
    private val compositeDisposable = CompositeDisposable()

    val mainActivityViewStateLiveData = MutableLiveData<MainActivityViewState>()
    val userListFragmentViewStateLiveData = MutableLiveData<UserListFragmentViewState>()
    val userDetailsFragmentViewStateLiveData = MutableLiveData<UserDetailsFragmentViewState>()

    fun loadUserList() {
        mainActivityViewStateLiveData.value = MainActivityViewState.Loading

        compositeDisposable.add(
            userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userList = it

                    mainActivityViewStateLiveData.value =
                        MainActivityViewState.Content(
                            MainActivityViewContent.ShowUserList
                        )

                    userListFragmentViewStateLiveData.value = UsersListDomainToViewStateMapper()
                        .map(it)

                },
                    {
                        it.printStackTrace()
                        mainActivityViewStateLiveData.value =
                            MainActivityViewState.Error(it.message ?: "unknown error")

                    })
        )

    }

    fun loadUserDetail(userPosition: Int) {
        val currentUser = userList[userPosition]
        mainActivityViewStateLiveData.value =
            MainActivityViewState.Content(
                MainActivityViewContent.ShowUserDetails
            )

        userDetailsFragmentViewStateLiveData.value = UserDetailsDomainToViewStateMapper()
            .map(currentUser)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}