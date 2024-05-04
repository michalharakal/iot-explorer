package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.plc4x.kmp.ui.presentation.feature.home.HomeViewModel

@Screen
@Composable
fun HomeView(viewModel: HomeViewModel) {
    val roomsState by viewModel.state.collectAsState()

    LazyColumn {
        items(roomsState.rooms.size) { roomIndex ->
            Room(roomsState.rooms[roomIndex]) {id ->
                viewModel.handleButtonClick(id)
            }
        }
    }
}