package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val buttonStart: Button = findViewById(R.id.buttonStart)


        buttonStart.setOnClickListener {
            val name = editTextName.text.toString()

            // Verificación: No debe empezar con un número y debe tener menos de 10 caracteres
            if (name.isNotEmpty() && !name[0].isDigit() && name.length <= 10) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("playerName", name) // Pasamos el nombre a la MainActivity
                startActivity(intent)
                finish() // Finalizamos la LoginActivity
            } else {
                Toast.makeText(this, "Nombre inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
