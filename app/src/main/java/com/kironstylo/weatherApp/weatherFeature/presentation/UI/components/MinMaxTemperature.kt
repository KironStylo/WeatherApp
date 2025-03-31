package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

@Composable
fun MinMaxTemperatureInfo(
    maxTemperature: Double,
    minTemperature: Double
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        TempInfo(
            modifier = Modifier.weight(0.5f),
            text = "Max",
            temp = maxTemperature
        )
        TempInfo(
            modifier = Modifier.weight(0.5f),
            text = "Min",
            temp = minTemperature
        )
    }
}

@Composable
fun TempInfo(
    modifier: Modifier = Modifier,
    text: String,
    temp: Double
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append("$text: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)) {
                append("$temp ºC")
            }
        }
    )
}

@Preview
@Composable
fun MinMaxTemperaturePreview() {
    MinMaxTemperatureInfo(
        maxTemperature = 4.0,
        minTemperature = 12.0
    )
}