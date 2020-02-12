package com.pedroroig.fragmentssampleapp.features.users

sealed class MainActivityViewState {
    object Loading: MainActivityViewState()
    data class Content(val content: MainActivityViewContent): MainActivityViewState()
    data class Error(val message: String): MainActivityViewState()
}

sealed class MainActivityViewContent {
    object ShowUserList : MainActivityViewContent()
}