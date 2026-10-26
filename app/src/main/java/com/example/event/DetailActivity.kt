package com.example.event
import com.example.event.R

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.tvName).text = "Họ tên: ${intent.getStringExtra("name")}"
        findViewById<TextView>(R.id.tvMssv).text = "MSSV: ${intent.getStringExtra("mssv")}"
        findViewById<TextView>(R.id.tvEmail).text = "Email: ${intent.getStringExtra("email")}"
        findViewById<TextView>(R.id.tvPhone).text = "SĐT: ${intent.getStringExtra("phone")}"
        findViewById<TextView>(R.id.tvGender).text = "Giới tính: ${intent.getStringExtra("gender")}"
        findViewById<TextView>(R.id.tvSocials).text = "Mạng xã hội: ${intent.getStringExtra("socials")}"
    }
}