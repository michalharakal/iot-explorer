package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.plc4x.kmp.ui.presentation.model.LightStatus
import org.plc4x.kmp.ui.presentation.model.Room
import org.plc4x.kmp.ui.presentation.model.SwitchStatus

@Composable
fun Room(room: Room) {
    Column(Modifier.padding(10.dp)) {
        RoomName(room.name)
        RoomLights(room.lights)
        //RoomTemperature(room.temperature)
    }

}

@Composable
fun RoomLights(lights: List<LightStatus>) {
    Row {
        LightsColumn(lights.filterIndexed { index, _ -> index < 2 })
        Spacer(modifier = Modifier.width(10.dp))
        LightsColumn(lights.filterIndexed { index, _ -> index >= 2 })
    }
}

@Composable
fun LightsColumn(lights: List<LightStatus>) {
    Column(Modifier.padding(10.dp)) {
        lights.forEach { light ->
            Row(Modifier.width(300.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    Text(light.name)
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    Row {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                            LedLight(light.switchStatus == SwitchStatus.ON)
                        }
                        Button({ /*TODO*/ }) {
                            Text(if (light.switchStatus == SwitchStatus.ON) "Aus" else "Ein")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RoomName(name: String) {
    Text(
        name, style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    )
}
