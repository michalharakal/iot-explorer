package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.plc4x.kmp.ui.common.AppTheme


@Composable
fun LedLight(isOn: Boolean) {
    Canvas(modifier = Modifier.size(30.dp)) {
        drawCircle(
            color = if (isOn) AppTheme.colors.switchOn else AppTheme.colors.switchOff
        )
    }
}