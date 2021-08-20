package com.toasttab.pulseman.test

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "testcircleci",
        icon = painterResource("pulse.png"),
        state = rememberWindowState(width = 900.dp, height = 1200.dp)
    ) {
        MaterialTheme(colors = AppTheme.colors.material) {
            DesktopTheme {
                Surface {
                    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                        Text("Hello World")
                    }
                }
            }
        }

        MenuBar {
            Menu("File") {
                Item(text = "Save", onClick = { println("save") }, shortcut = KeyShortcut(Key.S))
                Item(text = "Save as", onClick = { println("save as") })
                Item(
                    text = "Load project",
                    onClick = { println("load project") },
                    shortcut = KeyShortcut(Key.L)
                )
                Item(text = "Copy current tab", onClick = { println("copy current tab") })
            }
        }
    }
}

class SampleTestClass() {
    fun testFunction() = true
}
