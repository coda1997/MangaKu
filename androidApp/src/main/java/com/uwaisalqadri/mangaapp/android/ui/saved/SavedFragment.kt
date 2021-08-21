package com.uwaisalqadri.mangaapp.android.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaapp.android.ui.components.TopBar

class SavedFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SavedScreen()
            }
        }
    }

    @Composable
    fun SavedScreen() {
        Column {
            TopBar(name = "Saved")
        }
    }
}