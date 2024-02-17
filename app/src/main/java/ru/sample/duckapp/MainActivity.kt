package ru.sample.duckapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import android.widget.ProgressBar

import ru.sample.duckapp.infra.Api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.buttonGet)

        val imageView: ImageView = findViewById(R.id.duckMe) // assuming you have an ImageView with id "duckMe"

        val inputField: TextInputEditText = findViewById(R.id.textInputEditText)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        btn.setOnClickListener {
            val duckCode = inputField.text.toString()

            if(duckCode == "") {
                Api.helpers.getRandomDuck(imageView, progressBar)
            } else {
                Api.helpers.getDuckByCode(imageView, progressBar, duckCode)
            }
        }
    }
}