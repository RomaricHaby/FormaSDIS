package com.formasdis.pdf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
