package org.plc4x.kmp.ui.widget

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Atom
@Composable
fun OnOffSwitch(id:String, lightIsOff: Boolean, onclickHandler:(String)->Unit) {
    Button(
        { onclickHandler(id) })
    {
        Text(if (lightIsOff) "off" else "on")
    }
}



