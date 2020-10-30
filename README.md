# OpenForecast
[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=21)
[![License: MIT](https://img.shields.io/badge/License-MIT-red.svg)](https://opensource.org/licenses/MIT)

 OpenForecast is a weather simple app with friendly UI:) This app allows you to view the weather in different cities, as well as see detailed weather in a specific city.
 
 <a name="tasks"></a>
## Assigned tasks
- Load weather from [OpenWeather API](https://openweathermap.org/api)
- Add at least 2 cities by default (Saint-Petersburg and Moscow)
- Ability to add new city
- Ability to delete the selected city
- Сities should be displayed with temperatures in each city
- Ability to view detailed information about the weather in the selected city, as well as a 7-day weather forecast
- Ability to view saved weather in offline mode
- Automatically update the weather when an internet connection appears
- Ability to search news by keyword, language, sorted by popularity or date of publication
- Visualize news headlines organized in two categories: Local news and World news
- 2 languages supported (EN, RU)

<a name="demo"></a>
## Demo

![](test_resized.gif)

<a name="tools"></a>
## Languages, libraries and tools used

 * [Kotlin](https://kotlinlang.org/)
 * [AndroidX libraries](https://developer.android.com/jetpack/androidx)
 * [Android LifeCycle](https://developer.android.com/topic/libraries/architecture)
 * [Material Components for Android](https://github.com/material-components/material-components-android) 
 * [Retrofit2](https://github.com/square/retrofit)
 * [Anko](https://github.com/Kotlin/anko)
 
<a name="requirements"></a>
## Requirements
- min SDK 21

<a name="installation"></a>
## Installation

- Clone the app and import to Android Studio.
``git clone https://github.com/N-1-K-k-1/OpenForecast.git``  
You'll need to provide API key to fetch the weather data from the OpenWeather API. Currently the weather is fetched from [OpenWeather API](https://openweathermap.org/api)
- Generate an API key from [OpenWeather API](https://home.openweathermap.org/api_keys)
- Go to file /api/WeatherService.kt in our project root folder
- Change the API in the annotation that shown below:
```
    @Headers("X-Api-Key: Your API key")
```
- Build the app 
 
<a name="license"></a>
## License

MIT License
```
Copyright (c) [2020] [Viacheslav Proshkin]
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
