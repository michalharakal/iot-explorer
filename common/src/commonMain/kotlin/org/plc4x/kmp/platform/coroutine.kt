package org.plc4x.kmp.platform

import kotlinx.coroutines.CoroutineScope

interface CoroutineContextProvider {
    fun mainScope(): CoroutineScope
    fun ioScope(): CoroutineScope
}