package com.kironstylo.weatherApp.searchCityFeature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.searchCityFeature.presentation.components.CityLocationItem
import com.kironstylo.weatherApp.searchCityFeature.presentation.components.CustomTitleText
import com.kironstylo.weatherApp.searchCityFeature.presentation.components.SearchView
import com.kironstylo.weatherApp.searchCityFeature.presentation.components.TitleText

@Composable
fun CityScreen(
    modifier: Modifier,
    locationUIState: LocationUIState,
    onEvent: (LocationEvent) -> Unit,
    onClick: (Geolocation) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 0.dp,
            alignment = Alignment.Top
        )
    ) {
        SearchView(
            query,
            modifier = Modifier.weight(0.15f),
            ) {
            query = it
            onEvent(LocationEvent.SearchEvent(query))
        }
        Result(
            modifier = Modifier.weight(0.8f),
            query = query,
            locationUIState = locationUIState,
            onClick = onClick
        )
        ConfirmButton(
            modifier = Modifier.weight(0.15f)
        )
    }
}

@Composable
fun Result(
    query: String = "",
    modifier: Modifier = Modifier,
    locationUIState: LocationUIState,
    onClick: (Geolocation) -> Unit
) {
    val noQuery = query.isBlank()
    val noResults = locationUIState.geolocationItems.isEmpty()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = if (noQuery || noResults) Alignment.TopCenter else Alignment.TopCenter
    ) {
        if (query.isBlank()) {
            CustomTitleText(
                "Los resultados aparecerán cuando escribas el nombre de una ciudad...",
                TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )
        } else {
            if(locationUIState.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    color = Color(0xFFB58FE7),
                    trackColor = Color(0xFFA599B5)

                )
            }
            else{
                if(noResults){
                    CustomTitleText(
                        "No se encontraron resultados con el nombre: $query",
                        TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFBD2859)
                        )
                    )
                }
                else{
                    Column{
                        CustomTitleText(
                            "Resultados",
                            TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                            )
                        )
                        Result(
                            locationUIState,
                            onClick = onClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true)
@Composable
fun SearchUIPreview() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFB5B9B9)
    ) { innerPadding ->
        CityScreen(
            Modifier.padding(innerPadding),
            locationUIState = LocationUIState(
                listOf(
                    Geolocation(
                        id = 1,
                        name = "City",
                        country = "Country",
                        alias = "Alias",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                ),
                false
            ),
            {}
        ) {
        }
    }
}


@Composable
fun Result(locationUIState: LocationUIState, onClick: (Geolocation) -> Unit) {
    val rvState = rememberLazyListState()
    LazyColumn(
        state = rvState,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentPadding = PaddingValues(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(locationUIState.geolocationItems, key = {it.id}){
            CityLocationItem(
                geolocation = it,
                onClick = onClick
            )
        }
    }
}

