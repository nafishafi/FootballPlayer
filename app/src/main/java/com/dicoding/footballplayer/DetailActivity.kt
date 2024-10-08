package com.dicoding.footballplayer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>("key_hero", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>("key_hero")
        }

        if (dataHero != null) {
            val tvDetailName = findViewById<TextView>(R.id.detailName)
            val tvDetailDescription = findViewById<TextView>(R.id.detailDescription)
            val ivDetailPhoto = findViewById<ImageView>(R.id.detailPhoto)

            tvDetailName.text = dataHero.name
            tvDetailDescription.text = dataHero.description
            ivDetailPhoto.setImageResource(dataHero.photo)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                // Membuat Share Intent
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    // Mengirim data deskripsi dari detailDescription
                    putExtra(Intent.EXTRA_TEXT, findViewById<TextView>(R.id.detailDescription).text.toString())
                    type = "text/plain"
                }
                // Memulai aktivitas Share
                startActivity(Intent.createChooser(shareIntent, "Bagikan menggunakan"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}