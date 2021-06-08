package com.example.uxcamsample.navigation

import com.example.uxcamsample.ui.LoginFragment
import com.example.uxcamsample.ui.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun login() = FragmentScreen {
        LoginFragment()
    }

    fun home() = FragmentScreen {
        MainFragment()
    }

}