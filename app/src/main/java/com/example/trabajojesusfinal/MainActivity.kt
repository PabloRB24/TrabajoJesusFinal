package com.example.trabajojesusfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.ContentValues
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var crearCuenta: TextView
    private lateinit var continua : Button
    private lateinit var auth: FirebaseAuth

    private lateinit var email : EditText
    private lateinit var pass : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = Firebase.auth

        // Evento personalizado para Google Analytics
        val analytics : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integración de mensaje completa")
        analytics.logEvent("InitScreen", bundle)

        acceder()



        crearCuenta.isClickable = true

        crearCuenta.setOnClickListener {
            intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }


    }


    private fun acceder(){

        crearCuenta = findViewById(R.id.crearCuenta)
        continua = findViewById(R.id.continuarInicio)

        email= findViewById(R.id.editTextTextEmailAddress)
        pass= findViewById(R.id.editTextTextPassword)

        continua.setOnClickListener{
            if (email.text.isNotEmpty() && pass.text.isNotEmpty()){

                auth.signInWithEmailAndPassword(email.text.toString(),
                    pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        val logged = Intent(this, SeriesPeliculas::class.java)
                        logged.putExtra("email",email.text.toString())
                        startActivity(logged)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        crearCuenta.setOnClickListener {
            val registrarse = Intent (this, CrearCuenta::class.java)
            startActivity(registrarse)
        }
    }

    private fun showAlert(){
        Log.d(ContentValues.TAG, "Error creando nuevo usuario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en el login de usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}