# 🐶 Dog Training & Pet Care App

A native Android application designed to help dog owners manage their pet's training progress, health events, walks, and daily care in one place.

The application allows users to create multiple pet profiles, follow structured training courses, schedule recurring health reminders, track walks, and receive personalised daily recommendations.

---

## Features

### 🐕 Pet Profiles

* Create and manage multiple pet profiles.
* Switch between pets while maintaining separate data for each.
* Store pet information locally.

### 🎓 Training Hub

* Browse structured dog training courses.
* Complete lessons and unlock new ones as you progress.
* Track overall course progress.
* Continue previously started courses.

### ❤️ Health Hub

* Create health events such as vaccinations, medication, grooming, and vet appointments.
* Schedule one-time or recurring reminders.
* Support for:

  * Daily
  * Weekly
  * Monthly
  * Yearly repeats
* View upcoming events and weekly schedule.

### 🚶 Walk Tracker

* Log daily walks.
* Record walk duration and notes.
* View previous walks for each pet.

### 📊 Dashboard

* View personalised information for the selected pet.
* Upcoming health events.
* Current training progress.
* Daily training recommendations.

---

## Architecture

The application follows the **MVVM (Model–View–ViewModel)** architecture to separate UI, business logic, and data management.

### Layers

* UI (Jetpack Compose)
* ViewModels
* Repositories
* Local Data Sources
* Room Database

The app uses a state-driven UI where each screen observes immutable UI state exposed by its ViewModel.

---

## Technologies

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Room (SQLite)
* Coroutines
* SharedPreferences
* Navigation Compose
* Material Design 3

---

## Project Structure

```text
app
├── data
│   ├── local
│   ├── model
│   ├── repository
│   └── source
├── ui
│   ├── components
│   ├── navigation
│   └── screens
└── utils
```

---

## Screenshots

*Screenshots will be added here.*

---

## APK

*APK download link will be added here.*

---

## Installation

1. Clone the repository.

```bash
git clone <repository-url>
```

2. Open the project in Android Studio.

3. Sync Gradle.

4. Run the application on an emulator or Android device.

---

## Future Improvements

* Cloud backup and synchronisation.
* Push notifications for health reminders.
* Training statistics and achievements.
* Calendar view for health events.
* Wear OS integration.

---

## License

This project was developed for educational purposes.
