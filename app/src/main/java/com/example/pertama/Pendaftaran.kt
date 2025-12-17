package com.example.pertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Pendaftaran : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNamaDepan: EditText
    private lateinit var etNamaBelakang: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnKirim: Button
    private lateinit var btnBatal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendaftaran)



        val cancel = findViewById<Button>(R.id.btnCancel)
        cancel.setOnClickListener{
            val intentCancel = Intent(this, MainActivity::class.java)
            startActivity(intentCancel)
            finish()
        }

        etUsername = findViewById(R.id.editTextText)
        etEmail = findViewById(R.id.editEmail)
        etNamaDepan = findViewById(R.id.etNamaDepan)
        etNamaBelakang = findViewById(R.id.etNamaBelakang)
        etPassword = findViewById(R.id.editPassword)
        etConfirmPassword = findViewById(R.id.editTextTextPassword)
        btnKirim = findViewById(R.id.btnKirim)
        btnBatal = findViewById(R.id.btnCancel)

        btnKirim.setOnClickListener {
            submitForm()
        }

    }

    private fun submitForm() {
        // Ambil value
        val username = etUsername.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val namaDepan = etNamaDepan.text.toString().trim()
        val namaBelakang = etNamaBelakang.text.toString().trim()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()





        // Validasi sederhana
        when {
            username.isEmpty() -> {
                etUsername.requestFocus()
                showShort("Isi username terlebih dahulu")
            }
            email.isEmpty() -> {
                etEmail.requestFocus()
                showShort("Isi email terlebih dahulu")
            }
            namaDepan.isEmpty() -> {

                etNamaDepan.requestFocus()
                showShort("Isi nama depan terlebih dahulu")
            }
            namaBelakang.isEmpty() -> {

                etNamaBelakang.requestFocus()
                showShort("Isi nama belakang terlebih dahulu")
            }
            password.isEmpty() -> {

                etPassword.requestFocus()
                showShort("Isi password terlebih dahulu")
            }
            confirmPassword.isEmpty() -> {

                etConfirmPassword.requestFocus()
                showShort("Konfirmasi password terlebih dahulu")
            }
            password != confirmPassword -> {

                etPassword.error = "Password berbeda"
                etConfirmPassword.error = "Password berbeda"
                etConfirmPassword.requestFocus()
                showShort("Password tidak sama")
            }
            else -> {
                val UserToSave = UserEntity(
                    namaDepan = namaDepan,
                    namaBelakang = namaBelakang,
                    username = username,
                    email = email,
                    password = password
                )
                val db = AbsenDatabase.getDatabase(this)

                lifecycleScope.launch (Dispatchers.IO){
                    db.userDao().insertUser(UserToSave)
                }

                val namaUser = "$namaDepan $namaBelakang"
                Toast.makeText(this, "User $namaUser berhasil dibuat.", Toast.LENGTH_LONG).show()

                etUsername.setText("")
                etEmail.setText("")
                etNamaDepan.setText("")
                etNamaBelakang.setText("")
                etPassword.setText("")
                etConfirmPassword.setText("")


            }
        }
    }

    private fun clearFields() {
        etUsername.text.clear()
        etEmail.text.clear()
        etNamaDepan.text.clear()
        etNamaBelakang.text.clear()
        etPassword.text.clear()
        etConfirmPassword.text.clear()
    }

    private fun showShort(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pendaftaran)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}