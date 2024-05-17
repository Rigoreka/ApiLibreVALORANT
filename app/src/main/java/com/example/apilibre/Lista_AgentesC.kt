package com.example.apilibre

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.apilibre.databinding.ActivityListaAgentesCBinding
import com.example.apilibre.databinding.ListaagentesBinding

class Lista_AgentesC : AppCompatActivity() {
    private lateinit var binding: ActivityListaAgentesCBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityListaAgentesCBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //obtener valor de los datos por medio del intent
        val Nombre=intent.getStringExtra("name")
        val Descrip=intent.getStringExtra("description")
        val Img=intent.getStringExtra("iconUrl")

        //Mostrar los datos en la actividad
        binding.txtNombre.text=Nombre
        binding.txtDescrip.text=Descrip

        Glide.with(this)
            .load(Img)
            .into(binding.imgAgentes)
    }
}