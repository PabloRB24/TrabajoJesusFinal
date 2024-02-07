package com.example.trabajojesusfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Perfil : AppCompatActivity() {

    private lateinit var atras : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        atras = findViewById(R.id.atras)

        atras.setOnClickListener {
            intent = Intent(this, SeriesPeliculas::class.java)
            startActivity(intent)
        }
    }
}