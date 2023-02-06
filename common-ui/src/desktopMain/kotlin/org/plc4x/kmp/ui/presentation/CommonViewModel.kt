package org.plc4x.kmp.ui.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

actual open class CommonViewModel actual constructor() {
    actual val clientScope: CoroutineScope = GlobalScope

    protected actual open fun onCleared() {}
}