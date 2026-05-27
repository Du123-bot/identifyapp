package com.example.identifyapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {

                val imageBitmap =
                    result.data?.extras?.get("data") as Bitmap

                imageView.setImageBitmap(imageBitmap)

                Toast.makeText(this, "Đã chụp ảnh", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Camera bị hủy", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.imageView)

        val btnCamera = findViewById<Button>(R.id.btnCamera)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnCamera.setOnClickListener {

            Toast.makeText(this, "Đã bấm nút camera", Toast.LENGTH_SHORT).show()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                cameraLauncher.launch(intent)
            }
            else{
                Toast.makeText(this, "Thiết bị không có app camera", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}