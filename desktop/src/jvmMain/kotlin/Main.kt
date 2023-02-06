import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.appModule
import kotlinx.coroutines.Dispatchers
import org.plc4x.kmp.driver.IoTDriver
import org.plc4x.kmp.ui.AppView
import org.plc4x.kmp.ui.di.initKoin

private val koin = initKoin(Dispatchers.IO) {
    modules(appModule())
    initDriver()
}.koin

fun initDriver() {

}

fun main() = application {
    val windowTitle = remember { mutableStateOf("IoT-Explorer") }

    koin.get<IoTDriver>().connect()

    Window(
        onCloseRequest = ::exitApplication,
        title = windowTitle.value
    ) {
        AppView()
    }
}
