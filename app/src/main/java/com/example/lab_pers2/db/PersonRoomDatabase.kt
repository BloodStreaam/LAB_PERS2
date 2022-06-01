package com.example.lab_pers2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lab_pers2.dao.PersonDao
import com.example.lab_pers2.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Person::class], version = 3, exportSchema = false)
abstract class  PersonRoomDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var personDao = database.personDao()

                    // Delete all content here.
                    //personDao.deleteAll()

                    // Add sample words.
                    var person = Person("Henrique", "25", "jorgesilvaa108@gmail.com" )
                    personDao.insert(person)
                    person = Person("Jorge", "23", "jorgesilvaa108@gmail.com")
                    personDao.insert(person)

                    // TODO: Add your own words!
                    person = Person("Silva", "23", "jorgesilvaa108@gmail.com")
                    personDao.insert(person)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var personDao = database.personDao()

                    // Delete all content here.
                    //personDao.deleteAll()

                    // Add sample words.
                    var person = Person("Henrique", "25", "jorgesilvaa108@gmail.com" )
                    personDao.insert(person)
                    person = Person("Jorge", "23", "jorgesilvaa108@gmail.com")
                    personDao.insert(person)

                    // TODO: Add your own words!
                    person = Person("Silva", "23", "jorgesilvaa108@gmail.com")
                    personDao.insert(person)
                }
            }
        }
    }




    companion object {
        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context: Context): PersonRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "Person_Database"
                ).fallbackToDestructiveMigration()
                  .build()

                INSTANCE = instance
                instance
            }
        }
    }
}