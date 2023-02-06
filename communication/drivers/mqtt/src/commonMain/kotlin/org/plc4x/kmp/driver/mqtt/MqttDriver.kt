package org.plc4x.kmp.driver.mqtt


import org.plc4x.kmp.connector.*
import org.plc4x.kmp.driver.IoTDriver


abstract class BaseMqtt : IoTDriver {

    private fun topicToResourceLocator(topic: String): ResourceLocator {
        return PathResourceLocator(topic)
    }

    fun convertToEvent(topic: String, message: String): DataEvent {
        return ValueEvent(topicToResourceLocator(topic), NumericalValue(message.toFloat()))
    }
}


expect open class MqttDriver<in T>(connector: IotConnector<T>) : BaseMqtt

