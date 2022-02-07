# NewsApp
The app show a list of top headlines retrieved from News API. When clicking on one of the articles, it show details about that article.

### Getting started
To build a project, enter the project directory and use the `./gradlew assemble` command or use "Import Project" in Android Studio.
- Use `./gradlew connectedAndroidTest` to run the tests on a connected device.

### Requirements
- buildToolsVersion `32.0.0`
- compileSdk `32` - Android version 12
- minSdk `23` - Android version 6

### Architecture
My project architecture is based on the "Android Architecture Components" from Google, to provide a cleaner code, with clear separation between the view, the data and the business logic. <br>
Data is observed using Kotlin Flows and the DataBinding Library binds UI components in layouts to the DataSources.

### Built With
- `Kotlin` - official programming language for Android development 
- `Coroutines` - for background operations.
- `Room` - Persistence of Data.
- `Hilt (dagger)` - Dependency Injection Framework.
- `Navigation component`(from jetpack) - to manage fragment operations.
- `Material` -  for the user interface
- `Glide` -  media management and image loading

## Project structure

| Path | Description |
|:----:|:-----------:|
| `buildsrc/src/main/java/` | Configs and dependencies of the project|
| `app/src/androidTest/java/` | All tests of the project|
| `app/src/main/java/data/source` | All entities, dataSources of the project|
| `app/src/main/java/data/repository` | All repo implemented to get access to the data|
| `app/src/main/java/di` | modules - Dependency Injection|
| `app/src/main/java/domain` | models and repositories entry|
| `app/src/main/java/ui` | User Interface of the project


### Screenshots
Overview screen
![alt text](https://github.com/jdevapp/NewsApp/blob/main/resources/device-2021-09-24-155523.png?raw=true)
![alt text](https://github.com/jdevapp/NewsApp/blob/main/resources/Screenshot_20220207_201040.png?raw=true)
Details screen
![alt text](https://github.com/jdevapp/NewsApp/blob/main/resources/Screenshot_20220207_201014.png?raw=true)