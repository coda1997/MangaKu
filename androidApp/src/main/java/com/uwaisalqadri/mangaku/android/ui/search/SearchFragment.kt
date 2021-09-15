package com.uwaisalqadri.mangaku.android.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.utils.getTitle
import org.koin.androidx.compose.getViewModel

class SearchFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchScreen()
            }
        }
    }

    @Composable
    fun SearchScreen(
        viewModel: SearchViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            TopBar(name = "Search")

            if (uiState.loading) {
                Text(text = "Loading...")
            } else {
                uiState.listManga.forEach {
                    Text(text = (it.getTitle()))
                }
            }
        }
    }
}











