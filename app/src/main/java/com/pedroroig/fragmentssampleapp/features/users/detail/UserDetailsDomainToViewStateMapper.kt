package com.pedroroig.fragmentssampleapp.features.users.detail

import com.pedroroig.fragmentssampleapp.domain.model.User

class UserDetailsDomainToViewStateMapper {

    fun map(domainUser: User) =
        with(domainUser) {
            UserDetailsFragmentViewState(
                name,
                job,
                bio
            )
        }

}