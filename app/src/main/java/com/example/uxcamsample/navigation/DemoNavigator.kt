package com.example.uxcamsample.navigation

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

class DemoNavigator(
    activity: FragmentActivity,
    containerId: Int,
    drawerHost: DrawerHost
) : AppNavigator(activity, containerId) {

    private val drawerNavigator: DrawerNavigator by lazy {
        DrawerNavigator(drawerHost, fragmentManager)
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is InitDrawer,
            is ToggleLeftMenu -> drawerNavigator.applyCommand(command)
            else -> super.applyCommand(command)
        }
    }
}