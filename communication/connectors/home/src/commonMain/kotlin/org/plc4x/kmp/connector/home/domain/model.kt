package org.plc4x.kmp.connector.home.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import org.plc4x.kmp.connector.DataEvent
import org.plc4x.kmp.connector.NumericalValue
import org.plc4x.kmp.connector.SwitchEvent
import org.plc4x.kmp.connector.ValueEvent

// https://github.com/cashapp/molecule/blob/trunk/sample/src/main/java/com/example/molecule/presenter.kt

// internal value 1/100 of a degree
data class Temperature(val name: String, val temperature: Float)

enum class SwitchStatus {
    ON,
    OFF,
    UNKNOWN
}


data class LightStatus(val name: String, val switchStatus: SwitchStatus)


data class Room(
    val name: String,
    val lights: List<LightStatus>,
    val temperature: Temperature
)

data class HomeModel(val rooms: List<Room>)

fun createHomeImage(): HomeModel = HomeModel(emptyList())

fun evenToHomeModel(event: DataEvent, originalHome: HomeModel): HomeModel {
    var home = originalHome
    when (event) {
        is ValueEvent -> {
            val destinationRoom = event.name.room
            home = originalHome.copy(
                rooms = home.rooms.map { room ->
                    if (destinationRoom == room.name) {
                        room.copy(
                            lights = room.lights,
                            temperature = if (event.name.id == room.temperature.name) {
                                Temperature(
                                    event.name.id,
                                    when (event.value) {
                                        is NumericalValue -> (event.value as NumericalValue).value
                                        else -> room.temperature.temperature
                                    }
                                )
                            } else {
                                room.temperature
                            }
                        )
                    } else {
                        room
                    }
                }
            )
        }

        is SwitchEvent -> {
            val destinationRoom = event.name.room
            home = home.copy(
                rooms = home.rooms.map { room ->
                    if (destinationRoom == room.name) {
                        room.copy(
                            lights = room.lights.map { light ->
                                if (light.name == event.name.id) {
                                    light.copy(
                                        switchStatus = when (event.value) {
                                            true -> SwitchStatus.ON
                                            false -> SwitchStatus.OFF
                                        }
                                    )
                                } else {
                                    light
                                }
                            },
                            temperature = room.temperature
                        )
                    } else {
                        room
                    }
                })
        }

        else -> {
            // Nothing to
        }
    }
    return home

}

@Composable
fun HomePresenter(events: Flow<DataEvent>): HomeModel {
    var home by remember { mutableStateOf(createHomeImage()) }

    LaunchedEffect(Unit) {
        events.collect { event ->
            home = evenToHomeModel(event, home)
        }
    }
    return home
}

