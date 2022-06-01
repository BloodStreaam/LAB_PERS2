package com.example.lab_pers2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.lab_pers2.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    @Query("SELECT * FROM person_table order by name")
    fun getOrderedPeople(): Flow<List<Person>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(person: Person) //Suspend permite que caso a função esteja a demorar muito a aplicação não bloqueia o que faz com que a função trabalhe num tempo diferente do resto da aplicação

    @Query("Delete from person_table")
    suspend fun deleteAll()
}