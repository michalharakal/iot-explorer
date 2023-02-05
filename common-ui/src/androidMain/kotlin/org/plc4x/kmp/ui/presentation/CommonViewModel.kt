package org.plc4x.kmp.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class CommonViewModel actual constructor() : ViewModel() {
    actual val clientScope: CoroutineScope = viewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}
