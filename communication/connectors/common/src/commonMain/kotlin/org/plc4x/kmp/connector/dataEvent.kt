package org.plc4x.kmp.connector

sealed interface EventValue
data class NumericalValue(val value: Float) : EventValue


interface Identity<T> {
    val id: T
}


interface ResourceLocator : Identity<String> {
    override val id: String
    val location: String
    val room: String
    val type: String
    val path: String
}

class PathResourceLocator(private val source: String) : ResourceLocator {
    // /[location]/[type]/[room]/
    private val segments = source.split("/")
    override val id: String
        get() = segments[4]
    override val location: String
        get() = segments[1]
    override val room: String
        get() = segments[3]
    override val type: String
        get() = segments[2]
    override val path: String
        get() = source

}


sealed interface DataEvent
object NoneEvent : DataEvent
data class ValueEvent(val name: ResourceLocator, val value: EventValue) : DataEvent
data class SwitchEvent(val name: ResourceLocator, val value: Boolean) : DataEvent
