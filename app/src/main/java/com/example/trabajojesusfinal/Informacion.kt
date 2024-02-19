package com.example.trabajojesusfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Informacion : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    private lateinit var peliculasRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)
        var lista : List<Peliculas>

        database = FirebaseDatabase.getInstance()
        peliculasRef = database.getReference("peliculas-results")

         obtenerListaNombres {
             lista = it

        }

    }


//Terminar


    private fun obtenerListaNombres(callback: (List<Peliculas>) -> Unit) {


        peliculasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listaNombres = mutableListOf<Peliculas>()

                for (nombreSnapshot in dataSnapshot.children) {

                    val nombre = nombreSnapshot.child("name").value as String?
                    val sinopsis = nombreSnapshot.child("overview").value as String?

                    if (!nombre.isNullOrEmpty() && !sinopsis.isNullOrEmpty()) {
                        var pelicula : Peliculas = Peliculas(nombre,sinopsis)
                        listaNombres.add(pelicula)
                    }
                }

                callback(listaNombres)
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

}
data class Peliculas(val nombre : String?, val sinopsis : String?)