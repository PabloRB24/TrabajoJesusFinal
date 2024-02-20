package com.example.trabajojesusfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
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

    // Variables para las imágenes de la sección Series
    private lateinit var imageSelectorS: ImageView
    private lateinit var imageSelectorS2: ImageView
    private lateinit var imageSelectorS3: ImageView
    private lateinit var imageSelectorS4: ImageView
    private lateinit var imageSelectorS5: ImageView
    private lateinit var imageSelectorS6: ImageView
    private lateinit var imageSelectorS7: ImageView
    private lateinit var imageSelectorS8: ImageView

    // Variables para las imágenes de la sección Películas
    private lateinit var imageSelectorP: ImageView
    private lateinit var imageSelectorP2: ImageView
    private lateinit var imageSelectorP3: ImageView
    private lateinit var imageSelectorP4: ImageView
    private lateinit var imageSelectorP5: ImageView
    private lateinit var imageSelectorP6: ImageView
    private lateinit var imageSelectorP7: ImageView
    private lateinit var imageSelectorP8: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_peliculas)

        abandonar = findViewById(R.id.textView4)
        series = findViewById(R.id.series)
        peliculas = findViewById(R.id.peliculas)
        perfil = findViewById(R.id.imagePerfil)
        carrPeliculas = findViewById(R.id.carruselPeliculas1)
        carrSeries = findViewById(R.id.carruselSeries1)

        // Sección Series
        imageSelectorS = findViewById(R.id.imageSelectorS)
        imageSelectorS2 = findViewById(R.id.imageSelectorS2)
        imageSelectorS3 = findViewById(R.id.imageSelectorS3)
        imageSelectorS4 = findViewById(R.id.imageSelectorS4)
        imageSelectorS5 = findViewById(R.id.imageSelectorS5)
        imageSelectorS6 = findViewById(R.id.imageSelectorS6)
        imageSelectorS7 = findViewById(R.id.imageSelectorS7)
        imageSelectorS8 = findViewById(R.id.imageSelectorS8)

        // Sección Películas
        imageSelectorP = findViewById(R.id.imageSelectorP)
        imageSelectorP2 = findViewById(R.id.imageSelectorP2)
        imageSelectorP3 = findViewById(R.id.imageSelectorP3)
        imageSelectorP4 = findViewById(R.id.imageSelectorP4)
        imageSelectorP5 = findViewById(R.id.imageSelectorP5)
        imageSelectorP6 = findViewById(R.id.imageSelectorP6)
        imageSelectorP7 = findViewById(R.id.imageSelectorP7)
        imageSelectorP8 = findViewById(R.id.imageSelectorP8)

        carrSeries.visibility = View.VISIBLE
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

        imageSelectorS.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "brujaescarlatayvisionserie")
            intent.putExtra("nombre", "Bruja escarlata y vision")
            intent.putExtra("sinopsis", "Combinando el estilo clásico de las sitcoms con el MCU (Universo Cinematográfico de Marvel), cuenta la historia Wanda Maximoff y Vision, dos seres con superpoderes que viven una vida idílica en las afueras de una ciudad hasta que un día comienzan a sospechar que no todo es lo que parece.")
            startActivity(intent)
        }

        imageSelectorS2.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "highschoolserie")
            intent.putExtra("nombre", "High School Musical: El musical: La serie")
            intent.putExtra("sinopsis", "Un grupo de estudiantes de secundaria audicionará para participar en el espectáculo organizado por el club de teatro.")
            startActivity(intent)
        }

        imageSelectorS3.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "elmisterserie")
            intent.putExtra("nombre", "El Míster")
            intent.putExtra("sinopsis", "Expulsado de la NCAA, el entrenador Marvyn Korn encuentra redención en Westbrook al convertirse en mentor compasivo para chicas, guiándolas hacia la autovaloración y el éxito.")
            startActivity(intent)
        }

        imageSelectorS4.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "lokiserie")
            intent.putExtra("nombre", "Loki")
            intent.putExtra("sinopsis", "\n" +
                    "Loki, tras robar el Cubo Cósmico, enfrenta la eliminación o ayuda contra una amenaza mayor con la Autoridad de Variación Temporal, alterando la historia humana al viajar en el tiempo.")
            startActivity(intent)
        }

        imageSelectorS5.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "ojodehalconserie")
            intent.putExtra("nombre", "Ojo de Halcón")
            intent.putExtra("sinopsis", "Clint Barton (Ojo de Halcón) busca una Navidad tranquila, pero se asocia con Kate Bishop para desvelar una conspiración tras el Lapso en Nueva York.")
            startActivity(intent)
        }

        imageSelectorS6.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "quepasariasiserie")
            intent.putExtra("nombre", "¿Qué pasaría si...?")
            intent.putExtra("sinopsis", "Inspirándose en los cómics del mismo nombre, cada episodio explora un momento crucial del Universo Cinematográfico Marvel.")
            startActivity(intent)
        }

        imageSelectorS7.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "starwarstheclonewarsserie")
            intent.putExtra("nombre", "Star Wars the clone wars")
            intent.putExtra("sinopsis", "\n" +
                    "En la guerra galáctica, los Jedi luchan contra los Separatistas y sus conspiradores, enfrentándose a un conflicto desafiante por la libertad en la Galaxia.")
            startActivity(intent)
        }

        imageSelectorS8.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "thebeatlesgetbackserie")
            intent.putExtra("nombre", "The Beatles get back")
            intent.putExtra("sinopsis", "Serie documental de tres partes sobre los Beatles con más de 60 horas de metraje, destacando su genio creativo y última actuación en Savile Row.")
            startActivity(intent)
        }


        imageSelectorP.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "lamillaverdepeli")
            intent.putExtra("nombre", "La milla verde")
            intent.putExtra("sinopsis", "En el sur de Estados Unidos durante la Depresión, el vigilante Paul Edgecomb descubre el don sobrenatural de John Coffey, mostrando que los milagros pueden surgir en lugares inesperados.")
            startActivity(intent)
        }

        imageSelectorP2.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "listaschinderpeli")
            intent.putExtra("nombre", "Lista schinder")
            intent.putExtra("sinopsis", "Oskar Schindler utiliza su astucia para salvar judíos, adquiere una fábrica en Cracovia, emplea a cientos con influencia nazi, mientras su gerente judío dirige la operación en la sombra.")
            startActivity(intent)
        }

        imageSelectorP3.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "parasitospeli")
            intent.putExtra("nombre", "Parasito")
            intent.putExtra("sinopsis", "Parásito: organismo que se alimenta de las sustancias de un ser vivo de distinta especie, viviendo en su interior causando daño o enfermedad.")
            startActivity(intent)
        }

        imageSelectorP4.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "pulpfictionpeli")
            intent.putExtra("nombre", "Pulp fiction")
            intent.putExtra("sinopsis", "Jules y Vincent, asesinos a sueldo, reciben la misión de recuperar un maletín mientras Vincent debe cuidar de la esposa de su jefe, Mia, advirtiendo sobre los riesgos involucrados.")
            startActivity(intent)
        }

        imageSelectorP5.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "radicalpeli")
            intent.putExtra("nombre", "Radical")
            intent.putExtra("sinopsis", "En una ciudad fronteriza mexicana, un profesor frustrado prueba un método radical para romper la apatía de sus alumnos, basado en hechos reales.")
            startActivity(intent)
        }

        imageSelectorP6.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "thedarkknightpeli")
            intent.putExtra("nombre", "The dark knight")
            intent.putExtra("sinopsis", "Batman regresa para combatir el crimen junto a Gordon y Dent, pero la entrada del Joker desencadena el caos y amenaza Gotham.")
            startActivity(intent)
        }

        imageSelectorP7.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "theshawshankredemptionpeli")
            intent.putExtra("nombre", "The shawshank redemption")
            intent.putExtra("sinopsis", "Andrew Dufresne, condenado por el asesinato de su esposa, enfrenta la vida en Shawshank, ganándose la confianza del director y el respeto de los presos, incluido Red.")
            startActivity(intent)
        }

        imageSelectorP8.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            intent.putExtra("nombreImagen", "yournamepeli")
            intent.putExtra("nombre", "Your name")
            intent.putExtra("sinopsis", "Taki y Mitsuha intercambian cuerpos durante el sueño, comunicándose por notas, construyendo un vínculo que se transforma en un romance.")
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