package com.example.uxcamsample.navigation

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.uxcamsample.R
import com.example.uxcamsample.ui.SideMenuFragment
import com.github.terrakok.cicerone.Command

class DrawerNavigator(
    private val drawerHost: DrawerHost,
    private val fm: FragmentManager
) {
    private val drawer: DrawerLayout
        get() = drawerHost.drawer

    private var menuFragment: SideMenuFragment? = null

    fun applyCommand(command: Command) {
        when (command) {
            is ToggleLeftMenu -> toggleLeftMenu()
            is InitDrawer -> initNavigationDrawer()
        }
    }

    private fun toggleLeftMenu() {
        val gravity = GravityCompat.START
        if (drawer.isDrawerOpen(gravity)) {
            drawer.closeDrawer(gravity)
        } else {
            drawer.openDrawer(gravity)
        }
    }

    private fun initNavigationDrawer() {
        menuFragment?.let {
            fm.beginTransaction().remove(menuFragment!!).commitAllowingStateLoss()
            menuFragment = null
        }

        menuFragment = SideMenuFragment().apply {
            fm.beginTransaction()
                .replace(R.id.leftMenuContainer, this, this::class.java.simpleName)
                .commit()
        }
    }

}