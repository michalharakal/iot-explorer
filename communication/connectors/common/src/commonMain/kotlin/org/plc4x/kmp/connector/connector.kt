package org.plc4x.kmp.connector

import kotlinx.coroutines.flow.Flow

interface IotConnector<T> {
    fun handleEvent(dataEvent: DataEvent)

    fun modelState(): Flow<T>
}