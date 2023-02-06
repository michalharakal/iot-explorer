package org.plc4x.kmp.ui.presentation.model

data class Room(
    val name: String,
    val lights: List<LightStatus>,
    val temperature: Temperature
)

data class House(val rooms: List<Room>)
