package com.app.note.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import android.view.Window
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun NoteTheme(
    themes : String?,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {




    val colorScheme = when(themes){

        "System"->{
         if(isSystemInDarkTheme())
         {DarkColorScheme}
         else
         {LightColorScheme}
        }
        "Light"->{
            LightColorScheme
        }
        "Dark" ->{
            DarkColorScheme
        }
        else ->{
            DarkColorScheme
        }
    }


    val Settings = when(themes){

        "System"->{
            if(isSystemInDarkTheme()){
                statusBarIconColor(false)
            }else{
                statusBarIconColor(true)
            }
        }
        "Light"->{
            statusBarIconColor(true)
        }
        "Dark" ->{
            statusBarIconColor(false)
        }
        else ->{
            statusBarIconColor(false)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


@Composable
fun statusBarIconColor(isLight : Boolean){

    val view = LocalView.current

    if(!view.isInEditMode){
        val window =(view.context as Activity).window
        val insetController = WindowCompat.getInsetsController(window,view)
        insetController.isAppearanceLightStatusBars= isLight
        insetController.isAppearanceLightNavigationBars=isLight
    }
}