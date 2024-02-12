package com.example.trabajojesusfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import androidx.appcompat.app.AlertDialog

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class CrearCuenta : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var usuariosRef : DatabaseReference

    private lateinit var imagen : ImageView
    private lateinit var correo : EditText
    private lateinit var nombre : EditText
    private lateinit var contra : EditText
    private lateinit var continua : Button
    private lateinit var derecha : ImageView
    private lateinit var izquierda : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        var cont : Int = 1
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference("usuarios")

        imagen = findViewById(R.id.imageSelector)
        correo = findViewById(R.id.mail)
        nombre = findViewById(R.id.usuario)
        contra = findViewById(R.id.contra)
        continua = findViewById(R.id.continuarInicio)
        derecha = findViewById(R.id.imageView3)
        izquierda = findViewById(R.id.imageView2)

        derecha.setOnClickListener{
            if (cont >= 4){
                cont = 1
            }else{
                cont++
            }

            when(cont){

                1->{
                    imagen.setImageResource(R.drawable.among_us_amarillo)
                }
                2->{
                    imagen.setImageResource(R.drawable.among_us_rojo)
                }
                3->{
                    imagen.setImageResource(R.drawable.among_us_azul)
                }
                4->{
                    imagen.setImageResource(R.drawable.among_us_verde)
                }
            }
        }

        izquierda.setOnClickListener{
            if (cont <= 1){
                cont = 4
            }else{
                cont--
            }

            when(cont){

                1->{
                    imagen.setImageResource(R.drawable.among_us_amarillo)
                }
                2->{
                    imagen.setImageResource(R.drawable.among_us_rojo)
                }
                3->{
                    imagen.setImageResource(R.drawable.among_us_azul)
                }
                4->{
                    imagen.setImageResource(R.drawable.among_us_verde)
                }
            }
        }




        continua.setOnClickListener {
            if (correo.text.isNotEmpty() && contra.text.isNotEmpty() && nombre.text.isNotEmpty()) {
                Log.d("Prueba", "Creado nuevo usuario")
                auth.createUserWithEmailAndPassword(
                    correo.text.toString(),
                    contra.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val imageViewName = resources.getResourceEntryName(imagen.id)
                        datosUsuario(correo.text.toString(),nombre.text.toString(), imageViewName, contra.text.toString())
                        val registrado = Intent(this, MainActivity::class.java)
                        startActivity(registrado)
                    } else {
                        showAlert("Error creando el usuario")
                    }
                }
            }
        }



    }

    private fun showAlert(mensaje : String){
        //Log.d(TAG, "Error creando nuevo usuario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun datosUsuario (correo: String, nombre: String, foto: String, contra: String){
        val usuarioActual : FirebaseUser? = auth.currentUser
        if (usuarioActual !=null) {
            // insertamos los datos del usuario actual en nuestra Base de Datos
            val user = Usuario (correo, nombre, foto, contra)
            usuariosRef.child(usuarioActual.uid).setValue(user)
        } else {

        }

    }
}