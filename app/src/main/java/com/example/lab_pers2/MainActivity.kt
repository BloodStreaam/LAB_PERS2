package com.example.lab_pers2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_pers2.ViewModel.PersonViewModel
import com.example.lab_pers2.ViewModel.PersonViewModelFactory
import com.example.lab_pers2.adapter.PersonAdapter
import com.example.lab_pers2.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Observer


class MainActivity : AppCompatActivity() {
    private val newPersonActivityRequestCode = 1
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
            val adapter = PersonAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        personViewModel.allPeople.observe(this) { persons ->
            // Update the cached copy of the words in the adapter.
            persons.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewPersonActivity::class.java)
            startActivityForResult(intent, newPersonActivityRequestCode)
           /* val person = Person(name = "Iara")
            personViewModel.insert(person)*/
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        lateinit var name: String
        lateinit var idade: String
        lateinit var email: String
        if (requestCode == newPersonActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewPersonActivity.EXTRA_NAME)?.let { reply ->
                print(reply)
                name = reply
            }
            intentData?.getStringExtra(NewPersonActivity.EXTRA_EMAIL)?.let { reply ->
                email= reply
                //val person = Person(reply)
                //personViewModel.insert(person)
            }
            intentData?.getStringExtra(NewPersonActivity.EXTRA_IDADE)?.let { reply ->
                idade = reply
                //val person = Person(reply)
                //personViewModel.insert(person)
            }
            val person = Person(name, idade, email)
            personViewModel.insert(person)

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }




}