# Planetze

## Description
This project is a carbon-tracking Android app that helps users monitor daily carbon emissions from activities like commuting, energy use, and shopping. 
It provides real-time carbon footprint calculations, visual dashboards, and comparisons to regional averages, encouraging users to take simple steps to reduce their carbon footprint.

## Table of Contents
- [Description](#description)
- [Project Overview](#project-overview)
  - [Key Features](#key-features)
- [Assumptions](#assumptions)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Building from Source](#building-from-source)
  - [Post-Installation](#post-installation)
- [Dependencies](#dependencies)
- [Folder Structure](#folder-structure)
  - [Key Directories and Files](#key-directories-and-files)
- [Future Roadmap](#future-roadmap)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## Project Overview

Planetze is a sustainability platform designed to empower individuals and employees to track, reduce, and offset their carbon footprint. 
With the rising importance of climate action, Planetze provides users with personalized insights into their environmental impact and offers real-time data on their daily carbon emissions. 
Users can adopt eco-friendly habits, and contribute to certified carbon offset projects, making climate action accessible and achievable for everyone.

### Key Features:

**Eco Tracker**: Tracks users' carbon emissions based on their daily activities.  
**Eco Gauge**: A visual representation of progress toward carbon reduction goals, motivating users wit clear, tangible results.  
**Eco Balance**: Provides the users with the ability to offset their carbon emissions.

## Assumptions

The following assumptions are made for using and building Planetze:

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
- To quickly explore the app’s features, you can use the following demo account:
  - **Email**: `alatgt88@gmail.com`
  - **Password**: `abcdef`
- Explore the app and its features!

## Dependencies

This project relies on the following dependencies:

### Core Android Libraries
- **AndroidX AppCompat**: Provides backward-compatible versions of Android components.
- **Material Components**: Implements Material Design guidelines.
- **ConstraintLayout**: A powerful layout manager for creating flexible and efficient UI.

### Jetpack Libraries
- **Lifecycle LiveData and ViewModel**: Manages UI-related data lifecycle-aware.
- **Navigation Components**: Simplifies navigation within the app.

### Firebase
- **Firebase Analytics**: Tracks user engagement and events.
- **Firebase Authentication**: Handles user sign-in and authentication.
- **Firebase Realtime Database**: Provides real-time data synchronization.
- **Firebase Crashlytics**: Tracks app crashes and provides detailed insights.

### Third-Party Libraries
- **Glide**: Efficient image loading and caching.
- **MPAndroidChart**: For creating interactive and customizable charts.
- **Material Calendar View**: Provides a customizable Material Design calendar widget.
- **Mockito**: Used for unit testing.

### Testing Dependencies
- **JUnit**: Standard testing framework for Java.
- **Espresso**: UI testing for Android apps.

### Additional Tools
- **Legacy Support v4**: For maintaining compatibility with older Android versions.
- **RecyclerView**: A flexible view for rendering lists.
- **Google Play Services Wallet**: For handling Google Wallet services.

Dependencies are managed through Gradle. To review the full list of dependencies and their versions, see the [`build.gradle.kts`](app/build.gradle) file.

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

## Future Roadmap

Here are some planned features and improvements for upcoming releases of Planetze:

- **Eco Hub**: A resource center with educational content, and sustainability tips to help users stay informed and engaged.
- **Eco Agent**: It's an easy-to-navigate chat interface where the user can interact with the AI conversationally.

If you’d like to suggest a new feature or contribute to an existing one, feel free to open an [issue](https://github.com/danielstevanus88/Planetze/issues) or submit a pull request!

## Technologies Used
- **Java**: For Android app development.
- **Firebase**: For authentication and data storage.

## Contributing
We welcome contributions!  
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`. Please follow the [branch naming standard](https://www.conventionalcommits.org/en/v1.0.0/).
3. Commit your changes: `git commit -m 'Add a new feature'`. Please follow the [convention](https://www.conventionalcommits.org/en/v1.0.0/).
4. Push to the branch: `git push origin feature-name`.
5. Submit a pull request.

For major changes, please open an issue first to discuss what you'd like to change.

## Acknowledgments
- This is a grouped project for CSCB07H3.
- **GitHub Repository**: [Planetze on GitHub](https://github.com/danielstevanus88/Planetze)
- **Jira Board**: [Planetze on Jira](https://planetze.atlassian.net/jira/software/projects/PLAN/boards/1/backlog)
- Contributors: [@Daniel Stevanus](https://github.com/danielstevanus88), [@Muxun Zhang](https://github.com/muxunzzz), [@Tommy Wang](https://github.com/Grimshock1015), [@Helena Zhao](https://github.com/HelenaZhao05), [@Rizky Rajendra Ananta Dewa](https://github.com/RRDewa).
