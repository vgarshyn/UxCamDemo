package com.example.uxcamsample.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.uxcamsample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SideMenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: DemoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            viewModel.doLogout()
        }
    }
}