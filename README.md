# WeatherApp ☀️
Are you thinking of what the weather is like in some city? Well wonder no more, this app can tell you how the weather is over there.

# How to use the weather app: Guide 📔

## How can I use this app?📱
It's as simple as typing the name of a place, pushing a button, searching the place with that name
associated with it, tapping it, and getting information about that place such as the time, and temperature.

### 1. Typing the name of place⌨️
For starters, what you need to do is type on the search bar the actual name of the place you want to get
information about its weather and time, then press the button to proceed with the next

### 2. Choosing the place of your preference🗽
Since a lot of places might use a name that's also used by another city; state, region, country, or area to refer to it,
the app will give you a list of ten places that have a similar name yet are located in two separate countries, states, regions, etc.

To aid you in your search, you can see the name of the country as well as the name of the region to easily identify the place you meant by the name you searched.

Once you have found it, it's just a matter of tapping it and waiting for the weather data to arrive to your phone in seconds.

### 3. Getting the results of your search📊
After you choose the city of preference, you will see weather data appearing on your screen.
It might take a few seconds to load all the data and prepare the right visuals for the weather information received.

This screen will present data including time of place, current temperature, list of temperatures by hour, visual icons to express
the state of weather, and other useful stats.

### 4. Now what?🤔
You can check the weather in another city if you want by going back to the previous search menu. However, your current search results 
will disappear once you return to the search menu screen.

## Version Log 📜
At the time of development, I used XML to design the App's UI. I didn't add new features until now I decided to work
from where I left off. This project now implements Jetpack Compose for all UI components in the App. Due to that, I had 
to migrate to new current version catalog system to add dependencies using Gradle. I had a difficult time trying to get 
Dagger-Hilt to work as soon as I updated Android Studio since there would be an error caused by Kapt, but I found this useful
post on [Stack Overflow](https://stackoverflow.com/questions/71525731/java-lang-illegalaccesserror-class-org-jetbrains-kotlin-kapt3-base-kaptcontext)
which could help anyone who comes across this issue.

### Version 1.0
This version had the following features:
* Search up to 10 cities
* Discover the time and temperature of the city at the time of the search
* Display an icon according to the time whether it's night or day

### Version 2.0
This version has the following features:
* Two screens: A search-city screen and weather-info screen.
* Discover weather information such as:
  * Weather name
  * weather icon
  * Current temperature
  * Current time
  * Min and max temperatures
  * Rain probability 
  * Humidity
  * Wind speed.
  * A list of today's temperature for every hour with a weather icon.

### App software-details 🤖

The app sends three API requests to two websites: Open-Meteo and Time Api

First, the app displays sends an API request to return all the possible matches of city names from your search.
>[Geocoding API](https://open-meteo.com/en/docs/geocoding-api)
> This website returns all possible matches of city names along their geolocation information like longitude and latitude.

Then, the app proceeds to make another API request to determine the time of the place based on coordinates provided from the previous API results.
>[Timezone API](https://timeapi.io/)
> This website returns the local time of the city based on its coordinates provided by the previous API request.

Finally, the app makes a last API request to obtain the weather of the city using again its coordinates
>[Forecast API](https://open-meteo.com/en/docs)
> This website provides weather data provided a location. This API call includes a variety of parameters to ensure weather information is completely shown on the app.

Each website contains examples of how each API call should be made and you could always choose the amount of parameters in your search to your like.


### Some possible improvements for the app 🧰🪛
I'll improve the following things in the near future:
> * Add a UI component to show the weather name, max and min temperature, and day of the week at the end of the screen.
> * I am planning to add a Settings screen to allow users select app language, temperature and wind speed units.
> * Regarding weather icons, I might use a different method to display them since I don't think it's a good idea to store all icons in the drawable folder.
> * Regarding code optimizations, I might use a different approach to MVVM architectures since all view models are injected in my views for previewing and UI tests.

This list is not sorted by improvement of most importance, I'm simply pointing out what improvements could be made to the app.

# Completed improvements
So far, I have managed to implement these things on the app:
1) Create packages for specific app features and divide each package into three layers: presentation, domain, and data.
2) Make Search screen more intuitive by adding a checkbox at the end of a city so users know from what city they're getting weather data.
3) Search screen uses UIStates and UIEvents which are handled by the viewmodel. This means the screen can be previewed or tested.



