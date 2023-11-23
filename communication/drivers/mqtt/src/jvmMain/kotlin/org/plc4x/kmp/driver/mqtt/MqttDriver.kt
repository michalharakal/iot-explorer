package org.plc4x.kmp.driver.mqtt


import org.eclipse.paho.client.mqttv3.*
import org.plc4x.kmp.connector.IotConnector


actual open class MqttDriver<in T> actual constructor(connector: IotConnector<T>) : BaseMqtt() {
    private lateinit var client: MqttClient
    val iotConnector: IotConnector<in T> = connector


    // mqtt-client subscribe --host=mqtt.kopcek.lan --topic=/home/#
    override fun connect() {
        client = MqttClient("tcp://192.168.56.114", "iot-client")
        try {

            val connOpts = MqttConnectOptions()
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval( 1000);
            connOpts.setCleanSession(true)
            connOpts.setAutomaticReconnect(true)


            try {
                val cn: IMqttToken = client.connectWithResult(connOpts)
            } catch (me: MqttException) {
                println("reason " + me.reasonCode)
                println("msg " + me.message)
                println("loc " + me.localizedMessage)
                println("cause " + me.cause)
                println("excep $me")
                return
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
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