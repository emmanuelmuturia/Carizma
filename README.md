# Carizma

![playstore](https://github.com/emmanuelmuturia/Carizma/assets/55001497/9caeef5c-fc03-4dc0-99ca-fc7e074b67b8)


## Overview
- Carizma is an Android app that plays car sounds in 8D... 
- Think of it as Spotify but for cars...
- It is still in its early stages of development but feel free to play around with it and let me know what you come up with, will ya? 

## Table of Contents

1. [Background](#Background)
2. [Architecture](#Architecture)
3. [Testing](#Testing)
4. [Screenshots](#Screenshots)
5. [Credits](#Credits)
6. [Trivia](#Trivia)
7. [Future](#Future)

## Background
- Carizma was conceptualised and first built to explore the capabilities of Media3 ExoPlayer in a Jetpack Compose Setting...
- The idea of incorporating cars and car-related attributes was based on personal preferences and to give the project some personality...
- This project is a public build and is thus a continuous work of art as outlined in the last section of this documentation...

## Architecture
- Carizma currently uses packages with sub-packages, each which represent one of the three layers of Android apps, that is the Presentation, Domain, and Data Layers...
- The packages represent the app features, for readability purposes...

## Testing
- Testing is yet to be done as the core features are still under construction...

## Screenshots

## Credits
- Carizma is powered and made possible thanks to the following Tools, Technologies, and Libraries:

### 1. Jetpack Compose
- It takes advantage of [Jetpack Compose](https://developer.android.com/jetpack/compose) to provide a Kotlin-first approach to building the User Interface (UI)...

### 2. Media3 ExoPlayer
- As the core feature, Audio Playback is fully controlled by [Media3 ExoPlayer](https://developer.android.com/media/media3/exoplayer) provided by Google...

### 3. Gemini API
- To implement Artificial Intelligence (AI) as seen with the "Fun Fact" feature for each car, Carizma utilised the [Gemini API](https://ai.google.dev/) by Google...

### 4. Glide
- [Glide](https://bumptech.github.io/glide/int/compose.html) has been used to allow for Image Loading over the Internet...

### 5. Firebase
- For its backend, Carizma fully relies on [Firebase](https://firebase.google.com/) using the [Android SDK](https://firebase.google.com/docs/android/setup)...

### 6. Timber
- For logging purposes, [Timber](https://github.com/JakeWharton/timber) has been used to aid in the Kotlin-first approach of building...

### 7. MockK, Robolectric, and JUnit4
- [MockK](https://mockk.io/) will be used to mock dependencies for testing...
- [Robolectric](https://robolectric.org/) will be used as a Test Runner to simulate an Android environment in order to test Android-specific dependencies...
- [JUnit4](https://junit.org/junit4/) will be used to run regular Unit Tests that do not involve any Android-specific dependencies...

## Trivia

## Future
- Being a public build, Carizma is still under development...