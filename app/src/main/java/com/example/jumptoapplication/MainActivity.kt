package com.example.jumptoapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    private lateinit var buttonBrowser: Button
    private lateinit var editTextLink: EditText
    private lateinit var buttonMail: Button
    private lateinit var editTextMail: EditText
    private lateinit var buttonPhone: Button
    private lateinit var editTextPhone: EditText
    private lateinit var buttonMap: Button
    private lateinit var editTextLat: EditText
    private lateinit var editTextLong: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBrowser = findViewById(R.id.buttonBrowser)
        editTextLink = findViewById(R.id.editText)

        buttonMail = findViewById(R.id.buttonMail)
        editTextMail = findViewById(R.id.editTextMail)

        buttonPhone = findViewById(R.id.buttonPhone)
        editTextPhone = findViewById(R.id.editTextPhone)

        buttonMap = findViewById(R.id.buttonMap)
        editTextLat = findViewById(R.id.editTextLat)
        editTextLong = findViewById(R.id.editTextLong)


        buttonBrowser.setOnClickListener {
            val link = editTextLink.text.toString()
            if (URLUtil.isValidUrl(link)) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = link.toUri()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Enter valid URL", Toast.LENGTH_SHORT).show()
            }
        }

        buttonMail.setOnClickListener {

            val email = editTextMail.text.toString()
            if (EMAIL_ADDRESS.matcher(email).matches()) {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show()
            }
        }

        buttonPhone.setOnClickListener {
            val phoneNumber = editTextPhone.text.toString()
            if (android.util.Patterns.PHONE.matcher(phoneNumber).matches()) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show()
            }
        }

        buttonMap.setOnClickListener {
            val latitude = editTextLat.text.toString().toDoubleOrNull()
            val longitude = editTextLat.text.toString().toDoubleOrNull()

            if (latitude != null && longitude != null) {
                val uri = Uri.parse("geo:$latitude,$longitude")
                Intent(Intent.ACTION_VIEW, uri)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Enter valid coordinates", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

