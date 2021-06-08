package com.example.uxcamsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uxcamsample.navigation.DemoRouter
import com.uxcam.UXCam
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DemoViewModel(private val router: DemoRouter) : ViewModel() {

    fun start() {
        viewModelScope.launch {
            initAnalytics()
            //some init work
            delay(500)
            router.showLogin()
        }
    }

    private fun initAnalytics() {
        UXCam.setUserIdentity("anonymous")
        UXCam.setAutomaticScreenNameTagging(false)
        UXCam.startWithKey("KEY_UXCAM_KEY")
    }

    fun doLogin() {
        viewModelScope.launch {
            //some login
            delay(500)
            UXCam.setUserIdentity("actual_user")
            router.initDrawer()
            router.showHome()
        }
    }

    fun toggleMenu() {
        router.toggleLeftMenu()
        UXCam.logEvent("menu_toggle")
    }

    fun doLogout() {
        router.toggleLeftMenu()
        router.showLogin()
    }

    fun sendAnalytics() {
        UXCam.logEvent("test")
    }
}