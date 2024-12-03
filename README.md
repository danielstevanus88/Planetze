# Planetze

<!-- Not sure whether to add badges -->

## Description
This project is a carbon-tracking Android app that helps users monitor daily carbon emissions from activities like commuting, energy use, and shopping. 
It provides real-time carbon footprint calculations, visual dashboards, and comparisons to regional averages, encouraging users to take simple steps to reduce their carbon footprint.

## Table of Contents
- [Description](#description)
- [Project Overview](#project-overview)
  - [Key Features](#key-features)
  - [App Preview](#app-preview)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Building from Source](#building-from-source)
  - [Post-Installation](#post-installation)
- [Folder Structure](#folder-structure)
  - [Key Directories and Files](#key-directories-and-files)
- [Acknowledgments](#acknowledgments)
- [Technologies Used](#technologies-used)

## Project Overview

Planetze is a sustainability platform designed to empower individuals and employees to track, reduce, and offset their carbon footprint. 
With the rising importance of climate action, Planetze provides users with personalized insights into their environmental impact and offers real-time data on their daily carbon emissions. 
Users can adopt eco-friendly habits, and contribute to certified carbon offset projects, making climate action accessible and achievable for everyone.

### Key Features:

**Eco Tracker**: Tracks users' carbon emissions based on their daily activities.  
**Eco Gauge**: A visual representation of progress toward carbon reduction goals, motivating users wit clear, tangible results.  
**Eco Balance**: Provides the users with the ability to offset their carbon emissions.

### App Preview:
![Splash Screen](docs/splash_screen.png)
![Welcome Page](docs/login_option.png)
![Login Page](docs/login.png)
![Register Page](docs/register.png)
![Eco Tracker Screen]()
![Eco Gauge Screen]()
![Eco Balance Screen]()
<!-- TODO -->

## Installation

### Prerequisites:
- Android device running Android 5.0 (Lollipop) or higher.
- Android Studio if you want to build the app from source.

### Building from Source:
1. Clone the repository:
   ```bash
   git clone https://github.com/danielstevanus88/Planetze
2. Open the project in Android Studio.
3. Sync the Gradle files and ensure all dependencies are resolved.
4. Connect your Android device or set up an emulator.
5. Build and run the app by clicking the **Run** button.

### Post-Installation:
- Log in with your credentials or create a new account.
- Explore the app and its features!

## Folder Structure
```
Planetze/
├── README.md                                 # Project documentation
├── app/                                      # Main Android app module
│   ├── build.gradle.kts                      # App-level Gradle build script
│   ├── google-services.json                  # Firebase configuration file
│   ├── proguard-rules.pro                    # ProGuard rules for release builds
│   └── src/                                  # Source code directory
│       ├── androidTest/                      # Instrumentation tests
│       ├── main/                             # Main application code
│       │   ├── AndroidManifest.xml           # App’s manifest file
│       │   ├── assets/                       # Asset files (csv file)
│       │   ├── ic_launcher-playstore.png     # Play Store app icon
│       │   ├── java/                         # Java source code
│       │   ├── planetze_logo-playstore.png   # Additional app graphics
│       │   └── res/                          # Resources (layouts, drawables, strings, etc.)
│       └── test/                             # Unit test code (test for login)
├── build.gradle.kts                          # Project-level Gradle build script
├── docs/                                     # Documentation assets (pull request template, app screenshot)
├── gradle/                                   # Gradle wrapper and dependency configurations
│   ├── libs.versions.toml                    # Dependency version catalog
│   └── wrapper/                              # Gradle wrapper files
├── gradle.properties                         # Gradle build properties
├── gradlew                                   # Gradle wrapper script (Unix)
├── gradlew.bat                               # Gradle wrapper script (Windows)
├── local.properties                          # Local environment configuration
└── settings.gradle.kts                       # Settings file for Gradle
```

### Key Directories and Files:
- **`app/`**: Contains the Android application code and configurations.
  - **`src/main/`**: Main source code for the app, including resources and the manifest.
  - **`src/androidTest/`**: Instrumented tests for UI testing.
  - **`src/test/`**: Unit tests folder containing the test for login.
- **`docs/`**: Documentation assets like images and templates.
- **`gradle/`**: Gradle wrapper files and dependency version management.
- **`build.gradle.kts`**: Kotlin-based Gradle build configuration for the project.
- **`README.md`**: Main project documentation.

<!-- Not sure whether to add license -->

## Acknowledgments
- This is a grouped project for CSCB07H3.
- Contributors: [@Daniel Stevanus](https://github.com/danielstevanus88), [@Muxun Zhang](https://github.com/muxunzzz), [@Tommy Wang](https://github.com/Grimshock1015), [@Helena Zhao](https://github.com/HelenaZhao05), [@Rizky Rajendra Ananta Dewa](https://github.com/RRDewa).

## Technologies Used
- **Java**: For Android app development.
- **Firebase**: For authentication and data storage.
