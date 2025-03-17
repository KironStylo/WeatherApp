package com.kironstylo.weatherApp.searchCityFeature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation

@Composable
fun CityLocationItem(
    modifier: Modifier = Modifier,
    geolocation: Geolocation,
    onClick: (Geolocation) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFCDDDDD))
            .height(75.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                onClick(geolocation)
            }
    ){
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.8f),
            verticalArrangement = Arrangement.spacedBy(
                8.dp, Alignment.CenterVertically
            )
        ){
            Text(
                text = geolocation.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = geolocation.country,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = geolocation.alias,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f),
            contentAlignment = Alignment.Center
        ){
            RadioButton(
                selected = false,
                onClick = {},
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF8E4CE3),
                    unselectedColor = Color(0xFF8E4CE3)
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CityLocationItemPreview(){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        innerPadding ->
        CityLocationItem(
            Modifier.padding(innerPadding),
            Geolocation(
                id = 1,
                name = "City",
                country = "Country",
                alias = "Alias",
                latitude = 2.0,
                longitude = 2.0
            )
        ){

        }
    }
}