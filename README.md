# WeatherApp
Are you thinking of what the weather is like in some city? Well wonder no more, this app can tell you how the weather is over there.

## How does it work?
There's a search bar you can use to type the name of the city you want to learn about its weather.

When you have finally typed the name of the city, you can click the button with the message:
>_"Ver clima" or "Check weather"_

The app will immediately send an 
api request to the Open-Meteo website
to retrieve three essential pieces of
information.

The first two things needed to determine
what the temperature is over there are
latitude and longitude. For that the app requests
data to be sent over to the phone through an API call
to this web page: 
>[Geocoding API](https://open-meteo.com/en/docs/geocoding-api)

Once data is returned from this call and it's successful, then the app proceeds to
make another API call to this web page:
>[Forecast API](https://open-meteo.com/en/docs) 

in order to get what the temperature is in 
the city you searched just from its latitude and longitude.



