package org.plc4x.kmp.ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable


@Composable
fun AppView() {
    DisableSelection {
        Surface {
            DeviceExplorerView()
        }
    }
}