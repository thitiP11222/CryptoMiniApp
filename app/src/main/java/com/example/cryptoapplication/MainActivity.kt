package com.example.cryptoapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapplication.APIClient
import com.example.cryptoapplication.ListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change Status Bar Color to Match Toolbar
        window.statusBarColor = resources.getColor(R.color.toolbarBlue, theme)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val response = APIClient.instance.getCoins()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val coins = response.body()?.data?.coins ?: emptyList()
                    recyclerView.adapter = ListView(coins)
                } else {
                    Toast.makeText(this@MainActivity, "Error loading data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}