package di

import org.koin.dsl.bind
import org.koin.dsl.module
import org.plc4x.kmp.connector.IotConnector
import org.plc4x.kmp.connector.home.HomeConnector
import org.plc4x.kmp.driver.IoTDriver
import org.plc4x.kmp.driver.mqtt.MqttDriver

fun appModule() = module {
    // JVM specific
    single {
        val connector = get<IotConnector<HomeConnector>>()
        val driver = MqttDriver(connector)
        driver.connect()
        driver
    } bind IoTDriver::class

    single {
        HomeConnector()
    } bind IotConnector::class
}
