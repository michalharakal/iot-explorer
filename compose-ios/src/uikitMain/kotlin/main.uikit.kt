import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import kotlinx.cinterop.*
import org.plc4x.kmp.ui.presentation.model.LightStatus
import org.plc4x.kmp.ui.presentation.model.Temperature
import org.plc4x.kmp.ui.widget.HomeView
import org.plc4x.kmp.ui.presentation.model.Room
import org.plc4x.kmp.ui.presentation.model.SwitchStatus
import platform.UIKit.*
import platform.Foundation.*


fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @ObjCObjectBase.OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?
    ): Boolean {

        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application("IoT Explorer") {
            Column {
                // To skip upper part of screen.
                Box(modifier = Modifier.height(48.dp))
                HomeView(
                    listOf(
                        Room(
                            name = "Küche",
                            lights = listOf(
                                LightStatus("Licht 1", SwitchStatus.ON),
                                LightStatus("Licht 2", SwitchStatus.OFF)
                            ),
                            temperature = Temperature("_", "21.0")
                        ),
                        Room(
                            name = "Wohnzimmer",
                            lights = listOf(
                                LightStatus("Licht 1", SwitchStatus.ON),
                                LightStatus("Licht 2", SwitchStatus.OFF)
                            ),
                            temperature = Temperature("_", "21.0")
                        ),
                        Room(
                            name = "Schlafzimmer",
                            lights = listOf(
                                LightStatus("Licht 1", SwitchStatus.ON),
                            ),
                            temperature = Temperature("_", "21.0")
                        )
                    )
                )
            }
        }
        window!!.makeKeyAndVisible()
        return true
    }
}

