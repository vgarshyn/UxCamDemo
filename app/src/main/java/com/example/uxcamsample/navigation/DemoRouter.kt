package com.example.uxcamsample.navigation

import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router

class DemoRouter : Router() {

    fun toggleLeftMenu() {
        executeCommands(ToggleLeftMenu())
    }

    fun initDrawer() {
        executeCommands(InitDrawer())
    }

    fun showLogin() {
        executeCommands(Replace(Screens.login()))
    }

    fun showHome() {
        executeCommands(Replace(Screens.home()))
    }
}