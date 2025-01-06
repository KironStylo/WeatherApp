package com.kironstylo.weatherApp.ui.view.searchui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.ui.viewModel.GeoViewModel

@Composable
fun CityScreen(geoViewModel: GeoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .background(Color(0xFFACBDBA)),
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.Top
        )
    ) {
        Search(geoViewModel)
        Result(geoViewModel)
    }
}

@Composable
fun Search(geoViewModel: GeoViewModel) {
    val cityName: String by geoViewModel.cityName.observeAsState(initial = "")
    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0xFFCDDDDD))
        ,
        verticalArrangement = Arrangement.Center
    ) {
        CityTitle(Modifier.align(Alignment.Start))
        City(cityName){
            geoViewModel.onCityNameChanged(it)
        }
        CityButton{
            geoViewModel.searchCity(cityName)
        }
    }
}

@Composable
fun CityTitle(modifier: Modifier) {
    Text(
        text = "Ciudad a buscar",
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF051014)
        ),
        modifier = modifier.padding(vertical = 2.dp).fillMaxWidth()
    )
}

@Composable
fun City(cityName: String, onTextChanged:(String) -> Unit) {
    OutlinedTextField(
        value = cityName,
        maxLines = 1,
        onValueChange = {onTextChanged(it)},
        label = {
            Text(text = "Ingresa nombre de ciudad")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF2EFEF),
            unfocusedTextColor = Color(0xFF2E2F2F),
            focusedTextColor = Color(0xFF051014),
            focusedLabelColor = Color(0xFF9a6add),
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)
    )

}

@Composable
fun CityButton(onButtonPressed:() -> Unit) {
    Button(
        onClick =  onButtonPressed ,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF9a6add),
            contentColor = Color.White
        )
    ) {
        Text("Buscar ciudad")
    }
}

@Composable
fun Result(geoViewModel: GeoViewModel){
    val cityList :List<Result> by geoViewModel.cityList.observeAsState(initial = listOf())
    val rvState = rememberLazyListState()
    LazyColumn ( state = rvState, modifier = Modifier.height(225.dp).fillMaxWidth(), contentPadding = PaddingValues(horizontal= 9.dp),verticalArrangement = Arrangement.spacedBy(2.dp)){
        items(cityList){
            city -> CityResultCard(city)

        }
    }
}

@Composable
fun CityResultCard(city: Result){
    Column(
        modifier = Modifier
            .background(Color.White)
            .height(75.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.CenterVertically
        )
    ){
        Text(text = city.name, modifier = Modifier.wrapContentSize())
        Row(modifier = Modifier.fillMaxWidth()){
            Text(text = city.country ?: "Unknown country", modifier = Modifier.weight(1f))
            Text(text = city.alias ?: "Unknown alias")

        }
    }
}

