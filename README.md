# WeatherApp
Are you thinking of what the weather is like in some city? Well wonder no more, this app can tell you how the weather is over there.

## How can I use this app?
It's as simple as typing the name of a place, pushing a button, searching the place with that name
associated with it, tapping it, and getting information about that place such as the time, and temperature.

## Step-by-step demonstration
In this guide, you will learn how to effectively use this app in its beta-version. To make sure you do not
get lost with all the text details, I added meaningful images that will assist you with this short online guide:

# How to use the weather app: Guide

### 1. Typing the name of place
For starters, what you need to do is type on the search bar the actual name of the place you want to get 
information about its weather and time, then press the button to proceed with the next 

### 2. Choosing the place of your preference
Since a lot of places might use a name that's also used by another city; state, region, country, or area to refer to it,
the app will give you a list of ten places that have a similar name yet are located in two separate countries, states, regions, etc.

To aid you in your search, you can see the name of the country as well as the name of the region to easily identify the place you meant by the name you searched.

Once you have found it, it's just a matter of tapping it and waiting for the weather data to arrive to your phone in seconds.

### 3. Getting the results of your search
You will see that a piece of background will cover the screen, 
and post each piece of information at a time, this means the process was successful.

Inside the box is the weather data including the time of the place you searched, its name, and finally
its temperature along with visual icons to express the current state of the temperature and the hour of the day.

### 4. Now what?
You can search another place if you want, but beware the results will disappear once you press the button.

This is to leave space for the list of cities to be shown on the screen, and the process starts over again 
just like from step 2.


### App software-details

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

in order to get the value in celsius of the temperature in that place you searched 
using the latitude and longitude.



