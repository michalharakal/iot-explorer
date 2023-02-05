package org.plc4x.kmp.ui.presentation.model


enum class SwitchStatus {
    ON,
    OFF
}


data class LightStatus(val name: String, val switchStatus: SwitchStatus)
