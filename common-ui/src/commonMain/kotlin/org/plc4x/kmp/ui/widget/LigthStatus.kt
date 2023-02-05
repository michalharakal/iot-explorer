package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.plc4x.kmp.ui.presentation.model.LightStatus
import org.plc4x.kmp.ui.presentation.model.SwitchStatus

@Composable
fun LightStatus(lightStatus: LightStatus) {
    Row {
        Text(lightStatus.name)
        when (lightStatus.switchStatus) {
            SwitchStatus.ON -> LedLight(true)
            SwitchStatus.OFF -> LedLight(false)
        }
    }
}