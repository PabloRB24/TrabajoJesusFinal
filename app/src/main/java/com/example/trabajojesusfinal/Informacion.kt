package com.example.trabajojesusfinal
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Informacion : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var peliculasRef: DatabaseReference
    private lateinit var seriesRef: DatabaseReference
    private lateinit var botonAtras : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)


        botonAtras = findViewById(R.id.atras)
        database = FirebaseDatabase.getInstance()
        peliculasRef = database.getReference("peliculas-results")
        seriesRef = database.getReference("peliculas-results")

        val nombreImagen = intent.getStringExtra("nombreImagen")
        val nombrePS = intent.getStringExtra("nombre")
        val sinopsis = intent.getStringExtra("sinopsis")

        val nombreTextView: TextView = findViewById(R.id.textView8)
        val sinopsisTextView: TextView = findViewById(R.id.editTextCV)
        val imagenPeli : ImageView = findViewById(R.id.imagenPeli)
        val trailer : ImageView = findViewById(R.id.video)

        botonAtras.setOnClickListener {
            var intent = Intent(this, SeriesPeliculas::class.java)
            startActivity(intent)
        }

        trailer.setOnClickListener {
            var trailerUrl = ""
            when(nombrePS){
                "Bruja escarlata y vision"->{
                   trailerUrl = "https://www.youtube.com/watch?v=sj9J2ecsSpo"

                }
                "High School Musical: El musical: La serie"->{
                   trailerUrl = "https://www.youtube.com/watch?v=u_Nvc0LBIfo"

                }
                "El Míster"->{
                    trailerUrl = "https://www.youtube.com/watch?v=O8dnmcMgrRU"

                }
                "Loki"->{
                    trailerUrl = "https://www.youtube.com/watch?v=uz1CREWyISo"

                }
                "Ojo de Halcón"->{
                    trailerUrl = "https://www.youtube.com/watch?v=JTEMQlEI2g0"

                }
                "¿Qué pasaría si...?"->{
                    trailerUrl = "https://www.youtube.com/watch?v=Q4og7q_BWUU"

                }
                "Star Wars the clone wars"->{
                    trailerUrl = "https://www.youtube.com/watch?v=ZLW2jkd6E7g"

                }
                "The Beatles get back"->{
                    trailerUrl = "https://www.youtube.com/watch?v=ykw5YDTnOMs"

                }
                "La milla verde"->{
                    trailerUrl = "https://www.youtube.com/watch?v=hBtSF4-cnzk"

                }
                "Lista schinder"->{
                    trailerUrl = "https://www.youtube.com/watch?v=7q-ETFeMxwI"

                }
                "Parasito"->{
                    trailerUrl = "https://www.youtube.com/watch?v=90dWVETAdtI"

                }
                "Pulp fiction"->{
                    trailerUrl = "https://www.youtube.com/watch?v=auCgsj0MV-M"

                }
                "Radical"->{
                    trailerUrl = "https://www.youtube.com/watch?v=4CpKulS9h88"

                }
                "The dark knight"->{
                    trailerUrl = "https://www.youtube.com/watch?v=EXeTwQWrcwY"

                }
                "The shawshank redemption"->{
                    trailerUrl = "https://www.youtube.com/watch?v=NmzuHjWmXOc"

                }
                "Your name"->{
                    trailerUrl = "https://www.youtube.com/watch?v=qz0TDMd_cB0"

                }


            }
            if (trailerUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                startActivity(intent)

            }
        }

        var ruta = resources.getIdentifier(nombreImagen,"drawable",packageName)
        imagenPeli.setImageResource(ruta)

        nombreTextView.text = nombrePS
        sinopsisTextView.text = sinopsis

    }

 }


