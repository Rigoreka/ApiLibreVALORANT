package com.example.apilibre

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apilibre.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rclAgente.layoutManager = LinearLayoutManager(this)

        fetchAgents()
    }

    private fun fetchAgents() {
        CoroutineScope(Dispatchers.IO).launch {
            Fuel.get("https://valorant-api.com/v1/agents?language=es-ES")
                .responseString { _, _, result ->
                    when (result) {
                        is Result.Failure -> {
                            runOnUiThread {
                                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_LONG).show()
                            }
                        }
                        is Result.Success -> {
                            val data = result.get()
                            val agentsResponse = Gson().fromJson(data, agentsResponse::class.java)
                            val conteO=agentsResponse.data.size

                            runOnUiThread {
                                binding.txt1.setText("Hay $conteO agentes en la lista")
                                binding.rclAgente.adapter = AgenteAdapter(agentsResponse.data) { agent ->
                                    val intent = Intent(
                                        this@MainActivity,
                                        Lista_AgentesC::class.java
                                    ).apply {
                                        putExtra("name", agent.Name)
                                        putExtra("description", agent.Descrip)
                                        putExtra("iconUrl", agent.Icon)
                                    }
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
        }
    }
}
