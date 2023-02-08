package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import org.plc4x.kmp.ui.presentation.model.Room

@Composable
fun HomeView(rooms: List<Room>) {

    LazyColumn {
        items(rooms.size) { roomIndex ->
            Room(rooms[roomIndex])
        }
    }
}