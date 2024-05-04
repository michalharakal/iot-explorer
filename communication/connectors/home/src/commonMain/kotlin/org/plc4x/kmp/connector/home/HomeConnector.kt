package org.plc4x.kmp.connector.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.plc4x.kmp.connector.DataEvent
import org.plc4x.kmp.connector.IotConnector
import org.plc4x.kmp.connector.NoneEvent
import org.plc4x.kmp.connector.home.domain.*


class HomeConnector : IotConnector<HomeModel> {

    private val _home = MutableStateFlow(HomeModel(createHome()))

    private fun createHome(): List<Room> =
        listOf(
            Room(
                "kitchen",
                listOf(
                    LightStatus("light 1", SwitchStatus.ON),
                    LightStatus("light 2", SwitchStatus.OFF)
                ),
                Temperature("kitchen", 20.2f)
            ),
            Room(
                "living room",
                listOf(
                    LightStatus("light 1", SwitchStatus.ON),
                    LightStatus("light 2", SwitchStatus.OFF),
                    LightStatus("light TV", SwitchStatus.ON)
                ),
                Temperature("living room", 22f)
            ),
            Room(
                "bedroom",
                listOf(
                    LightStatus("light", SwitchStatus.ON),
                ),
                Temperature("bedroom", 18f)
            )
        )


    override fun handleEvent(dataEvent: DataEvent) {
        _home.value = evenToHomeModel(dataEvent, _home.value)
    }

    override fun modelState(): Flow<HomeModel> = _home
}