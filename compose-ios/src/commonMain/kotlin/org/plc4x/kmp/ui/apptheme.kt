package org.plc4x.kmp.ui

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object AppTheme {
    val colors: Colors = Colors()

    class Colors(
        val backgroundDark: Color = Color(0xFF2B2B2B),
        val backgroundMedium: Color = Color(0xFF3C3F41),
        val backgroundLight: Color = Color.White,
        val switchOn: Color = Color(0xFFC4EF74),
        val switchOff: Color = Color(0xFFEF3118),


        val material: androidx.compose.material.Colors = lightColors(
            background = backgroundLight,
            surface = backgroundMedium,
            primary = Color.White
        ),
    )
}