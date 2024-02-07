package com.example.trabajojesusfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var crearCuenta: TextView
    private lateinit var continua : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crearCuenta = findViewById(R.id.crearCuenta)
        continua = findViewById(R.id.continuarInicio)

        crearCuenta.isClickable = true

        crearCuenta.setOnClickListener {
            intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }

        continua.setOnClickListener {
            intent = Intent(this, SeriesPeliculas::class.java)
            startActivity(intent)
        }


    }
}