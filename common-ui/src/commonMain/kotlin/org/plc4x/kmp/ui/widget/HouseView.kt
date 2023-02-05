package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.plc4x.kmp.ui.presentation.feature.home.HomeViewModel
import org.plc4x.kmp.ui.presentation.feature.main.NodeViewModel

@Composable
fun HomeView(viewModel: HomeViewModel) {
    val viewState by viewModel.state.collectAsState()

    LazyColumn {
        items(viewState.rooms.size) { roomIndex ->
            Room(viewState.rooms[roomIndex])
        }
    }
}