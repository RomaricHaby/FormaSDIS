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


    private val requiredPermissionList = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private var download_file_url = "https://enasis.univ-lyon1.fr/clarolinepdfplayerbundle/pdf/449313"
    var per = 0f
    private val PERMISSION_CODE = 4040

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPdfBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.openPdf.setOnClickListener {
            if (checkAndRequestPermission())
                launchPdf()
        }
    }

    private fun launchPdf() {
        /*startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                this, download_file_url,
                "Title", "dir",true
            )
        )*/

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

    private fun checkAndRequestPermission(): Boolean {
        val permissionsNeeded = ArrayList<String>()

        for (permission in requiredPermissionList) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                permissionsNeeded.add(permission)
            }
        }

        if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsNeeded.toTypedArray(),
                PERMISSION_CODE
            )
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
                val readPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val writePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (readPermission && writePermission)
                    launchPdf()
                else {
                    Toast.makeText(this, " Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
