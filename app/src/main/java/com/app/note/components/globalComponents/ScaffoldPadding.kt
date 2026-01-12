package com.app.note.components.globalComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


@Composable
fun scaffoldPaddingForBottomBar(paddingValues: PaddingValues): Modifier{

        return Modifier.padding(
            0.dp,0.dp,0.dp,
            bottom = paddingValues.calculateBottomPadding())

}

@Composable
fun generalScreenScaffoldPadding(paddingValues: PaddingValues,layoutDirection: LayoutDirection) : Modifier{

    return Modifier.padding(
        paddingValues.calculateStartPadding(layoutDirection),paddingValues.calculateTopPadding()
        ,paddingValues.calculateEndPadding(layoutDirection),
        0.dp)

}