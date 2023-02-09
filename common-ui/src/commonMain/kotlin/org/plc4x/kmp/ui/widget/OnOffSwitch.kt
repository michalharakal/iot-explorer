package org.plc4x.kmp.ui.widget

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Atom
@Composable
fun OnOffSwitch(lightIsOff: Boolean) {
    Button(
        { /*TODO*/ })
    {
        Text(if (lightIsOff) "Aus" else "Ein")
    }
}



