package com.pedroroig.fragmentssampleapp.features.users.list

import com.pedroroig.fragmentssampleapp.domain.model.User

class UsersListDomainToViewStateMapper {

    fun map(domainUserList: List<User>) =
        UserListFragmentViewState(
            domainUserList.map {
                it.name
            }
        )
}