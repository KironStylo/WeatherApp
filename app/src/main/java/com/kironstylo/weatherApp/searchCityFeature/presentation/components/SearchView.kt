package com.kironstylo.weatherApp.searchCityFeature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.searchCityFeature.presentation.LocationEvent

@Composable
fun SearchView(
    query: String,
    modifier: Modifier = Modifier,
    onEvent:(String) -> Unit,
){
    Column (
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 0.dp
        )
    ) {
        TitleText()
        FindCity(query, onEvent)
    }
}

@Composable
fun TitleText(){
    CustomTitleText(
        "Ciudad a buscar",
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun FindCity(query:String,onEvent:(String)->Unit){
    OutlinedTextField(
        query,
        {
            onEvent(it)
        },
        label = {
            Text("Ingrese nombre de ciudad")
        },
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF2EFEF),
            unfocusedTextColor = Color(0xFF2E2F2F),
            focusedTextColor = Color(0xFF051014),
            focusedLabelColor = Color(0xFF9a6add),
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun SearchViewPreview(){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFACBDBA)
    ) { innerPadding ->
        SearchView (
            "",
            Modifier.padding(innerPadding)
        )
        {  }
    }
}