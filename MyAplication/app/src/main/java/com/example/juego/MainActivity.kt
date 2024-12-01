package com.example.juego
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private var timeLeftInMillis: Long = 75000 // 1 minuto y 15 segundos
    private var score = 0
    private var firstImage: ImageButton? = null
    private var secondImage: ImageButton? = null
    private val images = listOf(
        R.drawable.cethosiabiblis1_background, R.drawable.graphiumagamemnon_background,
        R.drawable.hebomoia_background, R.drawable.hypolimnas_background
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val scoreText: TextView = findViewById(R.id.score)
        val timerText: TextView = findViewById(R.id.timer)
        val targetImage: ImageView = findViewById(R.id.frente)
        val randomTarget = images.random() // Selecciona una imagen al azar
        // Recibimos el nombre del jugador desde LoginActivity
       // val playerName = intent.getStringExtra("playerName")
        val userName: TextView = findViewById(R.id.userName)

        Log.d("MainActivity", "onCreate: Activity iniciada")

        // Inicia el temporizador
        startTimer(timerText)

      /*  if (playerName == null) {
            Toast.makeText(this, "Error: Nombre de usuario no recibido", Toast.LENGTH_SHORT).show()
            finish()
        }

        if (playerName != null) {
            userName.text = "Usuario: $playerName"
        } else {
            Log.e("MainActivity", "Nombre del jugador no recibido.")
            finish() // Finaliza la actividad si no se recibe el nombre
        }*/

        Log.d("MainActivity", "Imagen objetivo aleatoria seleccionada: $randomTarget")
        targetImage.setImageResource(randomTarget)


        // Logica de clic en los botones
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in 0 until gridLayout.childCount) {
            val button = findViewById<ImageButton>(R.id.frente)

            button.setOnClickListener {
                onImageClick(button)
            }
        }

    }

    private fun updateScoreText() {
        val scoreText: TextView = findViewById(R.id.score)
        scoreText.text = "Puntos: $score"
        Log.d("MainActivity", "Puntos actualizados: $score") // Para depuración
    }

    private fun startTimer(timerText: TextView) {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                timerText.text = "Tiempo restante: $secondsLeft"
            }

            override fun onFinish() {
                // Maneja el fin del juego
            }
        }.start()
    }

    private fun checkForMatch() {
        if (firstImage?.tag == secondImage?.tag) {
            score += 100 // Correcto
            Log.d("MainActivity", "Coincidencia encontrada!")
        } else {
            score -= 10  // Incorrecto
            Log.d("MainActivity", "No hay coincidencia.")
        }
        updateScoreText()
    }

    private fun getActualImageResource(button: ImageButton): Int {
        // Aquí implementa la lógica para obtener el recurso de imagen real basado en el botón
        return R.drawable.frente_background// Reemplaza con la lógica adecuada
    }

    private fun onImageClick(button: ImageButton) {
        // Aquí va la lógica para verificar si las imágenes coinciden
        // Implementa la lógica para mostrar las imágenes al hacer clic
        // Cambia la imagen del botón al clic
        button.setImageResource(getActualImageResource(button)) // Usa tu lógica para obtener la imagen real

        // Verifica si ya hay una imagen seleccionada
        if (firstImage == null) {
            firstImage = button
            firstImage?.isEnabled = false // Deshabilita el botón para evitar clics múltiples
        } else if (secondImage == null && button != firstImage) {
            secondImage = button
            secondImage?.isEnabled = false // Deshabilita el segundo botón también

            // Aquí llamas a checkForMatch()
            checkForMatch()

            // Reinicia las selecciones para la siguiente jugada
            firstImage = null
            secondImage = null
        }
    }
}