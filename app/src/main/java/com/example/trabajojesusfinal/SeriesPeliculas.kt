package com.example.trabajojesusfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SeriesPeliculas : AppCompatActivity() {

    private lateinit var perfil : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_peliculas)

        perfil = findViewById(R.id.imagePerfil)


        perfil.setOnClickListener {
            intent = Intent(this,Perfil::class.java)
            startActivity(intent)
        }




    }
}