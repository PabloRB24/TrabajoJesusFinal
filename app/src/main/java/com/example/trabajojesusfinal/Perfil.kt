package com.example.trabajojesusfinal


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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


class Perfil : AppCompatActivity() {
    private lateinit var atras : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var usuariosRef : DatabaseReference
    private lateinit var nombre : TextView
    private lateinit var correo : TextView
    private lateinit var contra : TextView
    private lateinit var foto : ImageView
    private lateinit var mostrarContra : Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        atras = findViewById(R.id.atras)
        nombre = findViewById(R.id.editTextText)
        correo = findViewById(R.id.editTextTextEmailAddress)
        contra = findViewById(R.id.editTextTextPassword)
        foto = findViewById(R.id.imagen)
        mostrarContra = findViewById(R.id.mostrarContra)

        var contraseña : String = ""

        atras.setOnClickListener {
            intent = Intent(this, SeriesPeliculas::class.java)
            startActivity(intent)
        }
        mostrarContra.setOnClickListener {
            contra.text = contraseña
        }


        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference("usuarios")

        obtenerNombre { nombreUsuario ->
            nombre.text = "$nombreUsuario"
        }
        obtenerContra {contraUsuario ->
            contraseña = "$contraUsuario"
            var c : Int = 0
            var con: String = ""
            contraseña.forEach {
                con += "X"
            }
                contra.text = con
        }
        obtenerCorreo {correoUsuario ->
            correo.text = correoUsuario
        }
        obtenerFoto { fotoUsuario->
            var ruta = resources.getIdentifier(fotoUsuario,"drawable",packageName)
            foto.setImageResource(ruta)
        }

    }

    // callback es una función lambda que se ejecuta una vez que se realiza la lectura de datos
    // unit devolverá el resultado de lo leído
    private fun obtenerNombre(callback: (String) -> Unit) {
        val usuarioActual: FirebaseUser? = auth.currentUser

        if (usuarioActual != null) {
            val uidUsuario = usuarioActual.uid
            // Leemos a través del dataSnapshot el nombre del usuario actual
            usuariosRef.child(uidUsuario).child("nombre").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Guardamos el nombre en nombreUsuario tanto si existe o no
                    val nombreUsuario = dataSnapshot.value as String?

                    if (nombreUsuario != null && nombreUsuario.isNotEmpty()) {
                        // Devolvemos el nombre de usuario
                        callback(nombreUsuario)
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

    private fun obtenerCorreo(callback: (String) -> Unit) {
        val usuarioActual: FirebaseUser? = auth.currentUser

        if (usuarioActual != null) {
            val uidUsuario = usuarioActual.uid
            // Leemos a través del dataSnapshot el correo del usuario actual
            usuariosRef.child(uidUsuario).child("email").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Guardamos el nombre en correoUsuario tanto si existe o no
                    val correoUsuario = dataSnapshot.value as String?

                    if (!correoUsuario.isNullOrEmpty()) {
                        // Devolvemos el correo de usuario
                        callback(correoUsuario)
                    } else {
                        Log.d("Firebase", "El correo no está configurado en la base de datos")
                        // Si fuera nulo o estuviera en blanco devolvemos el siguiente mensaje
                        callback("Desconocido")
                    }
                }

                // Por si hubiera un error, estamos obligados hacer esta función al hacer addListenerForSingleValueEvent
                override fun onCancelled(databaseError: DatabaseError) {

                    Log.e("Firebase", "Error al leer el correo desde la base de datos: ${databaseError.message}")
                    callback("Error al leer el correo")
                }
            })
        } else {
            Log.d("Firebase", "El usuario no está autenticado")
            callback("Usuario no autenticado")
        }
    }

    private fun obtenerContra(callback: (String) -> Unit) {
        val usuarioActual: FirebaseUser? = auth.currentUser

        if (usuarioActual != null) {
            val uidUsuario = usuarioActual.uid
            // Leemos a través del dataSnapshot el contraseña del usuario actual
            usuariosRef.child(uidUsuario).child("contra").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Guardamos el nombre en contraUsuario tanto si existe o no
                    val contraUsuario = dataSnapshot.value as String?

                    if (contraUsuario != null && contraUsuario.isNotEmpty()) {
                        // Devolvemos el nombre de usuario
                        callback(contraUsuario)
                    } else {
                        Log.d("Firebase", "La contraseña no está configurada en la base de datos")
                        // Si fuera nulo o estuviera en blanco devolvemos el siguiente mensaje
                        callback("Desconocido")
                    }
                }

                // Por si hubiera un error, estamos obligados hacer esta función al hacer addListenerForSingleValueEvent
                override fun onCancelled(databaseError: DatabaseError) {

                    Log.e("Firebase", "Error al leer el contraseña desde la base de datos: ${databaseError.message}")
                    callback("Error al leer el nombre")
                }
            })
        } else {
            Log.d("Firebase", "El usuario no está autenticado")
            callback("Usuario no autenticado")
        }
    }

    private fun obtenerFoto(callback: (String) -> Unit) {
        val usuarioActual: FirebaseUser? = auth.currentUser

        if (usuarioActual != null) {
            val uidUsuario = usuarioActual.uid
            // Leemos a través del dataSnapshot el foto del usuario actual
            usuariosRef.child(uidUsuario).child("foto").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Guardamos la foto en fotoUsuario tanto si existe o no
                    val fotoUsuario = dataSnapshot.value as String?

                    if (fotoUsuario != null && fotoUsuario.isNotEmpty()) {
                        // Devolvemos la foto de usuario
                        callback(fotoUsuario)
                    } else {
                        Log.d("Firebase", "El foto no está configurado en la base de datos")
                        // Si fuera nulo o estuviera en blanco devolvemos el siguiente mensaje
                        callback("Desconocido")
                    }
                }

                // Por si hubiera un error, estamos obligados hacer esta función al hacer addListenerForSingleValueEvent
                override fun onCancelled(databaseError: DatabaseError) {

                    Log.e("Firebase", "Error al leer el nombre desde la base de datos: ${databaseError.message}")
                    callback("Error al leer la foto")
                }
            })
        } else {
            Log.d("Firebase", "El usuario no está autenticado")
            callback("Usuario no autenticado")
        }
    }

}