package com.example.trabajojesusfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SeriesPeliculas : AppCompatActivity() {

    private lateinit var perfil : ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var usuariosRef : DatabaseReference
    private lateinit var series : ImageView
    private lateinit var peliculas : ImageView
    private lateinit var carrPeliculas : HorizontalScrollView
    private lateinit var carrSeries : HorizontalScrollView
    private lateinit var abandonar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_peliculas)

        abandonar = findViewById(R.id.textView4)
        series = findViewById(R.id.series)
        peliculas = findViewById(R.id.peliculas)
        perfil = findViewById(R.id.imagePerfil)
        carrPeliculas = findViewById(R.id.carruselPeliculas1)
        carrSeries = findViewById(R.id.carruselSeries1)


        carrSeries.visibility = View.INVISIBLE
        carrPeliculas.visibility = View.INVISIBLE



        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference("usuarios")

        obtenerFoto { fotoUsuario->
            val ruta = resources.getIdentifier(fotoUsuario,"drawable",packageName)
            perfil.setImageResource(ruta)
        }

        perfil.setOnClickListener {
            intent = Intent(this,Perfil::class.java)
            startActivity(intent)
        }

        series.setOnClickListener {
            carrSeries.visibility = View.VISIBLE
            carrPeliculas.visibility = View.INVISIBLE
        }

        peliculas.setOnClickListener {
            carrSeries.visibility = View.INVISIBLE
            carrPeliculas.visibility = View.VISIBLE
        }

        abandonar.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    private fun obtenerFoto(callback: (String) -> Unit) {
        val usuarioActual: FirebaseUser? = auth.currentUser

        if (usuarioActual != null) {
            val uidUsuario = usuarioActual.uid
            // Leemos a través del dataSnapshot el nombre del usuario actual
            usuariosRef.child(uidUsuario).child("foto").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Guardamos el nombre en nombreUsuario tanto si existe o no
                    val fotoUsuario = dataSnapshot.value as String?

                    if (fotoUsuario != null && fotoUsuario.isNotEmpty()) {
                        // Devolvemos el nombre de usuario
                        callback(fotoUsuario)
                    } else {
                        Log.d("Firebase", "El nombre no está configurado en la base de datos")
                        // Si fuera nulo o estuviera en blanco devolvemos el siguiente mensaje
                        callback("Desconocido")
                    }
                }

                // Por si hubiera un error, estamos obligados hacer esta función al hacer addListenerForSingleValueEvent
                override fun onCancelled(databaseError: DatabaseError) {

                    Log.e("Firebase", "Error al leer el nombre desde la base de datos: ${databaseError.message}")
                    callback("Error al leer el nombre")
                }
            })
        } else {
            Log.d("Firebase", "El usuario no está autenticado")
            callback("Usuario no autenticado")
        }
    }
}