package org.plc4x.kmp.ui.presentation.feature.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.plc4x.kmp.domain.repository.DeviceRepository
import org.plc4x.kmp.ui.presentation.CommonViewModel
import org.plc4x.kmp.ui.presentation.feature.main.model.DeviceTreeNode
import org.plc4x.kmp.ui.presentation.feature.main.model.mapFacilitiesToTree

class DevicesTreeViewModel : CommonViewModel(), KoinComponent {
    private val deviceRepository: DeviceRepository by inject()

    private val _devicesState = MutableStateFlow(DeviceTreeNode())
    val state: StateFlow<DeviceTreeNode>
        get() = _devicesState

    init {
        clientScope.launch {
            combine(
                deviceRepository.facilities,
                deviceRepository.categories,
                deviceRepository.protocols
            ) { facilities, _, protocols ->
                mapFacilitiesToTree(facilities, protocols)
            }.catch { throwable ->
                // TODO: emit a UI error here. For now we'll just rethrow
                throw throwable
            }.collect {
                _devicesState.value = it
            }
        }
    }
}