package org.plc4x.kmp.ui.presentation.feature.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.plc4x.kmp.ui.presentation.CommonViewModel
import org.plc4x.kmp.ui.presentation.model.House
import org.plc4x.kmp.ui.presentation.model.LightStatus
import org.plc4x.kmp.ui.presentation.model.Room
import org.plc4x.kmp.ui.presentation.model.SwitchStatus


class NodeViewModel : CommonViewModel(), KoinComponent {

    private val _houseState = MutableStateFlow(House(emptyList()))
    val state: StateFlow<House>
        get() = _houseState

    init {
        clientScope.launch {
            _houseState.value = House(
                listOf(
                    Room(
                        "Kitchen",
                        listOf(
                            LightStatus("Light 1", SwitchStatus.ON),
                            LightStatus("Light 2", SwitchStatus.OFF)
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Küche", "20.2")
                    ),
                    Room(
                        "Living room",
                        listOf(
                            LightStatus("Light 1", SwitchStatus.ON),
                            LightStatus("Light 2", SwitchStatus.OFF),
                            LightStatus("Light TV", SwitchStatus.ON)
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Wohnzimmer", "22")
                    ),
                    Room(
                        "Bedroom",
                        listOf(
                            LightStatus("Light", SwitchStatus.ON),
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Bedroom", "18")
                    )
                )
            )
        }
    }
}