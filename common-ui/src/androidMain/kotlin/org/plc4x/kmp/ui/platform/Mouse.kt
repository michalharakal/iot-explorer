package org.plc4x.kmp.ui.platform

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.pointerMoveFilter(
    onEnter: () -> Boolean,
    onExit: () -> Boolean,
    onMove: (Offset) -> Boolean
): Modifier = this.pointerMoveFilter(onEnter = onEnter, onExit = onExit, onMove = onMove)


@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.cursorForHorizontalResize(): Modifier =
    this
