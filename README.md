Expense Tracker App

An Android application to help users manage daily expenses with smart insights and a clean, modern UI.
📱 



✨ Features
➕ Add, update, and delete expenses
📂 Categorize transactions (Food, Transport, Shopping, etc.)
📊 Visual insights into spending habits
🏆 Identify top spending category
📅 Track weekend vs weekday expenses
💰 View total balance overview
🗄️ Offline storage using Room Database
🖥️ Clean two-screen navigation system
🛠️ Tech Stack

Kotlin — Core programming language
Jetpack Compose — Modern UI toolkit
Room Database — Local data persistence
MVVM Architecture — Scalable and maintainable structure
Material Design 3 — UI styling and components
🏗️ Architecture

MVVM (Model - View - ViewModel)
├── UI Layer (Compose Screens)
├── ViewModel (Business Logic)
├── Repository (Data Handling)
└── Room Database (Local Storage)

🚀 Getting Started

Prerequisites
Android Studio (Hedgehog or later)
Minimum SDK: API 26 (Android 8.0)
Kotlin 1.9+
Installation
Bash
git clone https://github.com/AromaAhmad/Expense-Tracker.git
Open project in Android Studio
Let Gradle sync
Run on emulator or physical device
📂 Project Structure

app/
├── data/
│   ├── local/          # Room DB, DAO, Entities
│   └── repository/     # Repository layer
├── ui/
│   ├── screens/        # UI Screens
│   ├── components/     # Reusable components
│   └── theme/          # Styling
└── viewmodel/          # ViewModels

🔮 Future Improvements
📤 Export data (PDF/CSV)
📊 Advanced analytics & charts
☁️ Cloud sync (Firebase)
🔐 User authentication
🌙 Dark mode
🎯 Purpose

This project was built to strengthen Android development skills, focusing on clean architecture, local data handling, and modern UI design using Jetpack Compose.

👩‍💻 Author
Aroma Ahmad
BS Computer Science — University of Sargodha
📧 aromaahmad91@gmail.com
🔗 https://github.com/AromaAhmad⁠�