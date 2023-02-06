package org.iot.driver.mqtt.jvm

import mu.KotlinLogging
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.plc4x.kmp.driver.IotDriver

class MqttConnector(val iotDriver: IotDriver) {

    private val logger = KotlinLogging.logger {}

    fun connect() {
        val client = MqttClient("tcp://mqtt.kopcek.lan", "test")
        client.connect()
        client.subscribe("/home/#")
        client.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                iotDriver.handleMessage(message?.payload.toString() ?: "")
                logger.info { "Message arrived: $message" }
            }

            override fun connectionLost(cause: Throwable?) {
                logger.info { "Connection lost" }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                logger.info { "Delivery complete" }
            }
        })
    }
}
