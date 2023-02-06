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
                        "Küche",
                        listOf(
                            LightStatus("Licht 1", SwitchStatus.ON),
                            LightStatus("Licht 2", SwitchStatus.OFF)
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Küche", "20.2")
                    ),
                    Room(
                        "Wohnzimmer",
                        listOf(
                            LightStatus("Licht 1", SwitchStatus.ON),
                            LightStatus("Licht 2", SwitchStatus.OFF),
                            LightStatus("Licht TV", SwitchStatus.ON)
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Wohnzimmer", "22")
                    ),
                    Room(
                        "Schlafzimmer",
                        listOf(
                            LightStatus("Licht", SwitchStatus.ON),
                        ),
                        org.plc4x.kmp.ui.presentation.model.Temperature("Schlafzimmer", "18")
                    )
                )
            )
        }
    }
}