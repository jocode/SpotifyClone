# Spotify Clone 

This is a clone of the Spotify app made with Android and Kotlin. The application use the Spotify API to get the data from the [Spotify API](https://developer.spotify.com/documentation/web-api/) and display it in a list, search for songs and artist and show the library of the user.
The endpoints used is located in the [Web Api Reference](https://developer.spotify.com/documentation/web-api/reference/#).

> :hammer: **Download the app from [Spotify Clone APK](https://github.com/jocode/SpotifyClone/blob/develop/apk/app-debug.apk?raw=true)**

## :star: Features

- [x] Fetch new releases from Spotify
- [x] Search for songs and artists
- [x] Show the library of the user
- [x] Authenticate with Spotify
- [x] Get token to make the requests
- [x] App arquitecture using MVVM and Clean Architecture

| :runner: For run the app just clone the repository and execute the app on Android Studio.

# Stack tech used

This application is developed using Kotlin and uses the following components:
- MVVM + Clean Architecture
- Retrofit
- Room
- Coroutines
- Navigation Component
- ViewModel
- ViewBinding
- LiveData
- Dagger Hilt


## Screenshots

|                    Home Screen                    |                        Search                         |                       Library                        |                  
| :-----------------------------------------------: | :---------------------------------------------------: | :----------------------------------------------------: 
|   ![Home](screenshots/home.jpg?raw=true)   |   ![Favorites](screenshots/search.jpg?raw=true)   |   ![Post Detail](screenshots/library.jpg?raw=true) |
| ![LogIn](screenshots/login.jpg?raw=true)


### User guide
- Use phones with Android API 21+
- Sign in with a Spotify account

## :dart: Architecture

The application is built using Clean Architeture pattern based on [Architecture Components](https://developer.android.com/jetpack/guide#recommended-app-arch) on Android. The application is divided into three layers:

![Clean Arquitecture](https://devexperto.com/wp-content/uploads/2018/10/clean-architecture-own-layers.png)

- Domain: This layer contains the business logic of the application, here we define the data models and the use cases.
- Data: This layer contains the data layer of the application. It contains the database, network and the repository implementation.
- Presentation: This layer contains the presentation layer of the application.


The organization of the application is based on the following diagram:

    app/
    ├── core/
    ├── data/
    │	├── local/
    │	├── models/
    │	├── remote/
    │   └── RepositoryImplementationFiles
    ├── domain/
    │   └── models/
    │   └── repositories/
    │   └── usecases/
    ├── presentation/
    │   └── ui/
    │        └── fragments/
    │        └── adapters/
    │   └── viewmodels/
    └── di/


Each layer have your own models and the mappers are using extensions functions to transform the data between the layers.

## :wrench: Dependencies

- Hilt for dependency injection
- Kotlin Coroutines
- Retrofit
- Logging Interceptor
- Gson
- Room
- LiveData
- Navigation Component
- Concat Adapter

All these dependencies are used following google guidelines for JetPack components, [Architecture Components](https://developer.android.com/jetpack/guide#recommended-app-arch) on Android. This allow us have a better integration between the dependencies.

