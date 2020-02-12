package com.pedroroig.fragmentssampleapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Show the fragment if already created. If not, create it
 *
 * @param fragmentTag identifier for the fragment, when created it will be searched by this tag
 * @param fragment the fragment to load. It will be used only if it's not been created yet
 * @param fragmentContainerId id of the ViewGroup that will contain the fragment
 */
fun showOrCreateFragment(
    fragmentTag: String,
    fragment: Fragment,
    @Suppress("SameParameterValue") fragmentContainerId: Int,
    supportFragmentManager: FragmentManager
) {
    with(supportFragmentManager) {
        if (findFragmentByTag(fragmentTag) != null) {
            //if the fragment exists, show it.
            beginTransaction().show(findFragmentByTag(fragmentTag)!!).commit()
        } else {
            //if the fragment does not exist, add it to fragment manager.
            beginTransaction()
                .add(
                    fragmentContainerId,
                    fragment,
                    fragmentTag
                )
                .commit()
        }
    }
}

fun createFragment(
        fragmentTag: String,
        fragment: Fragment,
        @Suppress("SameParameterValue") fragmentContainerId: Int,
        supportFragmentManager: FragmentManager
) {
    with(supportFragmentManager) {
            //if the fragment does not exist, add it to fragment manager.
            beginTransaction()
                    .add(
                            fragmentContainerId,
                            fragment,
                            fragmentTag
                    )
                    .commit()
    }
}

/**
 * inserts the given fragment into the given container ID. It replaces previous fragment if any
 */
fun launchFragmentReplacing(frag: Fragment, fragContainerId: Int, fragmentManager: FragmentManager,
                            addToBackStack: Boolean = true) {
    val transaction = fragmentManager.beginTransaction()

    transaction.replace(fragContainerId, frag)
    if(addToBackStack)
        transaction.addToBackStack(null)

    transaction.commit()
}

fun isFragmentVisible(fragmentTag: String, supportFragmentManager: FragmentManager) =
        supportFragmentManager.findFragmentByTag(fragmentTag)?.isVisible == true

/**
 * Hides a fragment if it exists.
 */
fun hideFragmentIfExists(fragmentTag: String, supportFragmentManager: FragmentManager) {
    with(supportFragmentManager) {
        if (findFragmentByTag(fragmentTag) != null) {
            //if the other fragment is visible, hide it.
            beginTransaction()
                    .hide(findFragmentByTag(fragmentTag)!!)
                    .commit()
        }
    }
}


fun launchFragmentIfExists(fragmentTag: String, supportFragmentManager: FragmentManager) {
    with(supportFragmentManager) {
        if (findFragmentByTag(fragmentTag) != null) {
            beginTransaction().detach(findFragmentByTag(fragmentTag)!!)
                    .attach(findFragmentByTag(fragmentTag)!!)
                    .commitNow()
        }
    }
}

fun clearAllFragments(supportFragmentManager: FragmentManager) {
    supportFragmentManager.fragments.forEach { fragment ->
        fragment?.let {
            supportFragmentManager.beginTransaction().remove(fragment).commitNow()
        }
    }
}
