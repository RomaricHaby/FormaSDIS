package com.formasdis.pdf

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.formasdis.databinding.ActivityMainPdfBinding

class MainActivityPdf : AppCompatActivity() {
    private lateinit var binding: ActivityMainPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPdfBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.openPdf.setOnClickListener {
                launchPdf()
        }
    }
    private fun launchPdf() {
        startActivity(
            PdfViewerActivity.launchPdfFromPath(
                this,
                "test.pdf",
                "ta mere",
                "assets",
                enableDownload = false,
                fromAssets = true,
            )
        )
    }
}
