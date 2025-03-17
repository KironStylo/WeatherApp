package com.kironstylo.weatherApp.searchCityFeature.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTitleText(text:String, textStyle: TextStyle = TextStyle(), modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = textStyle,
        modifier = modifier
    )
}