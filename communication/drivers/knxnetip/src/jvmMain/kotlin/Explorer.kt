/*
import org.apache.plc4x.java.PlcDriverManager
import org.apache.plc4x.java.api.PlcDriver
import org.apache.plc4x.java.api.exceptions.PlcConnectionException
import org.apache.plc4x.java.api.messages.PlcBrowseRequest
import org.apache.plc4x.java.api.messages.PlcDiscoveryRequest
import org.apache.plc4x.java.api.types.PlcResponseCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class Explorer {
    private val logger: Logger = LoggerFactory.getLogger(Explorer::class.java)

    fun explore() {
        PlcDriverManager()
            .getConnection("modbus:tcp://127.0.0.1")
            .use { conn ->
                if (!conn.metadata.canRead()) {
                    println("Cannot read!!")
                    return
                }
                val readRequest = conn.readRequestBuilder()
                    .addItem("value-1", "coil:1")
                    .addItem("value-2", "holding-register:10")
                    .build()
                val response = readRequest.execute().get(1, TimeUnit.MINUTES)
                response.fieldNames.forEach { fieldName ->
                    val responseCode = response.getResponseCode(fieldName)
                    if (responseCode !== PlcResponseCode.OK) {
                        println("Error[$fieldName]: ${responseCode.name}")
                        return
                    }
                    val numValues = response.getNumberOfValues(fieldName)
                    // If it's just one element, output just one single line.
                    if (numValues == 1) {
                        println("Value[$fieldName]: ${response.getObject(fieldName)}")
                    } else {
                        println("Value[$fieldName]:")
                        for (i in 0 until numValues) {
                            println(" - " + response.getObject(fieldName, i))
                        }
                    }
                }
            }
        exitProcess(0)
    }

    fun exploret() {
        /*
        // Iterate over all installed drivers and execute their browse functionality (If they support it)
        val driverManager = PlcDriverManager()
        for (protocolCode in driverManager.listDrivers()) {
            val driver: PlcDriver = driverManager.getDriver(protocolCode)
            if (driver.metadata.canDiscover()) {
                logger.info("Performing discovery for {} protocol", driver.getProtocolName())
                val discoveryRequest: PlcDiscoveryRequest = driver.discoveryRequestBuilder().build()
                val future = discoveryRequest.executeWithHandler { discoveryItem ->
                    println(" - Found device with connection-url ${discoveryItem.connectionUrl}")
                    try {
                        driverManager.getConnection(discoveryItem.connectionUrl).use { connection ->
                            if (connection.metadata.canBrowse()) {
                                val browseRequest: PlcBrowseRequest = connection.browseRequestBuilder().build()
                                val bfuture = browseRequest.execute().whenComplete { browseResponse, throwable ->
                                    if (throwable != null) {
                                        throwable.printStackTrace()
                                    } else {
                                        for (value in browseResponse.values) {
                                            println(
                                                java.lang.String.format(
                                                    "%60s : %60s",
                                                    value.getAddress(),
                                                    value.getDataType()
                                                )
                                            )
                                        }
                                    }
                                }
                                while (!bfuture.isDone) {
                                    println("Calculating...");
                                    Thread.sleep(300);
                                }
                            }
                        }
                    } catch (e: PlcConnectionException) {
                        throw RuntimeException(e)
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }
                while (!future.isDone) {
                    println("Calculating...");
                    Thread.sleep(300);
                }
            }
        }


         */
    }
}

 */