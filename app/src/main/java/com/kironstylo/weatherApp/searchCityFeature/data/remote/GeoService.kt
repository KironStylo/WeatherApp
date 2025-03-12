package com.kironstylo.weatherApp.searchCityFeature.data.remote

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GeoService @Inject constructor(
    private val api: GeoApiClient
) {
     fun getLocation(cityName: String): Flow<Resource<List<Geolocation>>> = flow {
         emit(Resource.Loading())
         try{
             val response = api.getLatitudeAndLongitude(cityName)
             if(response.isSuccessful){
                 val data = response.body()?.results?.map{
                     it.toGeolocation()
                 } ?: emptyList()
                 emit(Resource.Success(data))
             }
         }
         catch(e: HttpException){
             emit(Resource.Error("Oops something went wrong"))
         }
         catch(e: IOException){
             emit(Resource.Error("Couldn't reach server. Check your connection"))
         }
     }.flowOn(Dispatchers.IO)
}