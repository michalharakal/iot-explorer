package org.plc4x.kmp.driver.mqtt


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.plc4x.kmp.connector.IotConnector

actual open class MqttDriver<in T> actual constructor(connector: IotConnector<T>) : BaseMqtt() {
    private lateinit var client: MqttClient
    val iotConnector: IotConnector<in T> = connector


    // mqtt-client subscribe --host=mqtt.kopcek.lan --topic=/home/#
    override fun connect() {
        client = MqttClient("tcp://mqtt.kopcek.lan", "test")
        client.connect()
        client.subscribe("/home/#")
        client.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                try {
                    iotConnector.handleEvent(convertToEvent(topic ?: "", message.toString()))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun connectionLost(cause: Throwable?) {
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
            }
        })
    }

    override fun disconnect() {
        client.disconnect()
    }
}