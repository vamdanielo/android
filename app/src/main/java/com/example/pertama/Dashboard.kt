package com.example.pertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import org.w3c.dom.Text

class Dashboard : AppCompatActivity() {
    private lateinit var userDao : UserDao
    public var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val db = AbsenDatabase.getDatabase(this)
        userDao = db.userDao()

        id = intent.getIntExtra("ID",0)


        val tvNamaDepan = findViewById<TextView>(R.id.TvNamaDepan)
        val tvNamaBelakang = findViewById<TextView>(R.id.tvNamaBelakang)
        val tvUsername = findViewById<TextView>(R.id.username)
        val tvEmail = findViewById<TextView>(R.id.email)

        lifecycleScope.launch(Dispatchers.IO) {
        val user = userDao.getUserById(id)
            withContext(Dispatchers.Main) {

                tvNamaDepan.text = (user?.namaDepan)
                tvNamaBelakang.text = (user?.namaBelakang)
                tvUsername.text = (user?.username)
                tvEmail.text = (user?.email)
            }
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}