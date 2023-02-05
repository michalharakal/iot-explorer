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
                "Küche",
                listOf(
                    LightStatus("Licht 1", SwitchStatus.ON),
                    LightStatus("Licht 2", SwitchStatus.OFF)
                ),
                Temperature("Küche", 20.2f)
            ),
            Room(
                "Wohnzimmer",
                listOf(
                    LightStatus("Licht 1", SwitchStatus.ON),
                    LightStatus("Licht 2", SwitchStatus.OFF),
                    LightStatus("Licht TV", SwitchStatus.ON)
                ),
                Temperature("Wohnzimmer", 22f)
            ),
            Room(
                "Schlafzimmer",
                listOf(
                    LightStatus("Licht", SwitchStatus.ON),
                ),
                Temperature("Schlafzimmer", 18f)
            )
        )


    override fun handleEvent(dataEvent: DataEvent) {
        _home.value = evenToHomeModel(dataEvent, _home.value)
    }

    override fun modelState(): Flow<HomeModel> = _home
}