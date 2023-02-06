package org.plc4x.kmp.ui.presentation.feature.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.plc4x.kmp.connector.home.HomeConnector
import org.plc4x.kmp.connector.home.domain.HomeModel
import org.plc4x.kmp.ui.presentation.CommonViewModel
import org.plc4x.kmp.ui.presentation.model.*


class HomeViewModel : CommonViewModel(), KoinComponent {

    private val homeConnector = get<HomeConnector>()

    private val _state = MutableStateFlow(House(emptyList()))
    val state: StateFlow<House>
        get() = _state


    /*
    Molecule
    val state: StateFlow<House> = clientScope.launchMolecule(RecompositionClock.Immediate) {
        mapToViewData(HomePresenter(homeConnector.modelState()))
    }
     */

    init {
        clientScope.launch {
            homeConnector.modelState().collect { homeModel ->
               _state.value = mapToViewData(homeModel)
            }
        }
    }

    private fun mapToViewData(homePresenter: HomeModel): House = House(
        homePresenter.rooms.map { room ->
            Room(
                name = room.name,
                lights = room.lights.map { light ->
                    LightStatus(
                        light.name,
                        when (light.switchStatus) {
                            org.plc4x.kmp.connector.home.domain.SwitchStatus.ON -> SwitchStatus.ON
                            org.plc4x.kmp.connector.home.domain.SwitchStatus.OFF -> SwitchStatus.OFF
                            else -> SwitchStatus.OFF
                        }
                    )
                },
                temperature = room.temperature.let { Temperature(it.name, temperatureFormatter(it.temperature)) }
            )
        }
    )

    private fun temperatureFormatter(temperature: Float): String = "${temperature}°C"
}