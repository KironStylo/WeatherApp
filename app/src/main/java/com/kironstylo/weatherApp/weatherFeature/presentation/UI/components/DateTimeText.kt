package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import java.time.LocalDateTime

@Composable
fun DateTimeText(date: LocalDateTime){
    val color = Color(0xFF000000)
    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = color
    )
    Row(
        modifier = Modifier.height(18.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
    ){
        Text(DateFormatter.formatDate(date, "dd/MM/yyyy"), style = textStyle, textAlign = TextAlign.End, modifier = Modifier.weight(0.5f))
        VerticalDivider(
            thickness = 1.dp,
            color = color,
            modifier = Modifier
                .padding(vertical = 1.dp)
        )
        Text(DateFormatter.formatDate(date, "hh:mm a"), style = textStyle, modifier = Modifier.weight(0.5f))
    }
}

@Preview
@Composable
fun DateTimeTextPreview(){
    DateTimeText(
        date = LocalDateTime.now()
    )
}