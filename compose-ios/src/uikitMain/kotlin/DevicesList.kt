@file:Suppress("FunctionName")

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*


@Composable
fun ListScreen() {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Iot Explorer") })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
           Text("Temperature 23")
        }
    }
}
