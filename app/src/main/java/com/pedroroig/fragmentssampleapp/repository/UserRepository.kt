package com.pedroroig.fragmentssampleapp.repository

import com.pedroroig.fragmentssampleapp.domain.model.User

class UserRepository {

    fun getUsers() =
        listOf(
            User("Pepe", "car mechanic", "He fixed cars for a living until one fell on him"),
            User("Carlos", "programmer", "He loves animals and death metal"),
            User("Shaun", "piano player", "Youngest of 13 siblings, he likes doing nothing"),
            User ("Pierre", "hitman", null)
        )
}
