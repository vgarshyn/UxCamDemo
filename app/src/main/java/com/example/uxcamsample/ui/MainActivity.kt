package com.example.uxcamsample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.uxcamsample.R
import com.example.uxcamsample.navigation.DemoNavigator
import com.example.uxcamsample.navigation.DrawerHost
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class MainActivity : AppCompatActivity(), DrawerHost {

    private val activityModule = module {

        factory<Navigator> {
            DemoNavigator(this@MainActivity, R.id.mainContainer, this@MainActivity)
        }

        viewModel {
            DemoViewModel(
                router = get()
            )
        }
    }

    private val navigationHolder: NavigatorHolder by inject()
    private val navigator: Navigator by inject()

    override val drawer: DrawerLayout
        get() = findViewById(R.id.drawerLayout)

    private val viewModel: DemoViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(activityModule)
        setContentView(R.layout.activity_main)
        val content = findViewById<View>(R.id.mainContainer)
        val leftMenu = findViewById<View>(R.id.leftMenuContainer)
        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                content.translationX = leftMenu.width * slideOffset
            }

            override fun onDrawerOpened(drawerView: View) = Unit

            override fun onDrawerClosed(drawerView: View) = Unit

            override fun onDrawerStateChanged(newState: Int) = Unit
        })
        viewModel.start()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(activityModule)
    }
}