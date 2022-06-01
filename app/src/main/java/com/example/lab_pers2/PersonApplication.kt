package com.example.lab_pers2

import android.app.Application
import com.example.lab_pers2.db.PersonRoomDatabase
import com.example.lab_pers2.repository.PersonRepository

class PersonApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { PersonRoomDatabase.getDatabase(this) }
    val repository by lazy { PersonRepository(database.personDao()) }

}