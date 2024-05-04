package org.plc4x.kmp.ui.widget

import androidx.compose.foundation.layout.*
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

@Organism
@Composable
fun Room(room: Room, onclickHandler: (String) -> Unit) {
    Column(Modifier.padding(10.dp)) {
        RoomName(room.name)
        RoomLights(room.lights) { id ->
            onclickHandler(id)
        }
    }
}

@Organism
@Composable
fun RoomLights(lights: List<LightStatus>, onclickHandler: (String) -> Unit) {
    Row {
        LightsColumn(lights.filterIndexed { index, _ -> index < 2 }) { id ->
            onclickHandler(id)
        }
        Spacer(modifier = Modifier.width(10.dp))
        LightsColumn(lights.filterIndexed { index, _ -> index >= 2 }) { id ->
            onclickHandler(id)
        }
    }
}

@Composable
fun LightsColumn(lights: List<LightStatus>, onclickHandler: (String) -> Unit) {
    Column(Modifier.padding(10.dp)) {
        lights.forEach { light ->
            Row(Modifier.width(300.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    LightBox(light) { id ->
                        onclickHandler(id)
                    }
                }
            }
        }
    }
}

@Molecule
@Composable
fun LightBox(light: LightStatus, onclickHandler: (String) -> Unit) {
    Row {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            Text(light.name)
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            LedLight(light.switchStatus == SwitchStatus.ON)
        }
        OnOffSwitch(light.name, light.switchStatus == SwitchStatus.ON) { id ->
            onclickHandler(id)
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
