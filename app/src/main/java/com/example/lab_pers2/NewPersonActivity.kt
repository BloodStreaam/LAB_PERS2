package com.example.lab_pers2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewPersonActivity : AppCompatActivity() {

    private lateinit var editPersonView: EditText
    private lateinit var idadePersonView: EditText
    private lateinit var emailPersonView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_person)
        editPersonView = findViewById(R.id.edit_word)
        idadePersonView = findViewById(R.id.edit_idade)
        emailPersonView = findViewById(R.id.edit_email)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPersonView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editPersonView.text.toString()
                val idade = idadePersonView.text.toString()
                val email = emailPersonView.text.toString()
                replyIntent.putExtra(EXTRA_NAME, name)
                replyIntent.putExtra(EXTRA_IDADE, idade)
                replyIntent.putExtra(EXTRA_EMAIL, email)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_NAME = "com.example.android.wordlistsql.NAME"
        const val EXTRA_IDADE = "com.example.android.wordlistsql.IDADE"
        const val EXTRA_EMAIL = "com.example.android.wordlistsql.EMAIL"
    }
}
