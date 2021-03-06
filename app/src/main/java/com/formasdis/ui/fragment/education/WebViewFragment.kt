package com.formasdis.ui.fragment.education

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.formasdis.R


class WebViewFragment(private val urlPdf: String, private val isPdf: Boolean) : Fragment() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pdf_reader, container, false)
        val webView: WebView = view.findViewById(R.id.webviewPdf)
        //webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        if (!isPdf) {
            webView.settings.setSupportZoom(true)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(urlPdf)
        } else {
            webView.settings.setSupportZoom(true)
            webView.settings.javaScriptEnabled = true
            //webView.getSettings().setJavaScriptEnabled(true)
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$urlPdf")


            //val settings: WebSettings = webView.getSettings()
            //settings.javaScriptEnabled = true
            //settings.databaseEnabled = true
            /*val databasePath: String =
                this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath()
            settings.databasePath = databasePath
            webview.setWebChromeClient(object : WebChromeClient() {
                override fun onExceededDatabaseQuota(
                    url: String,
                    databaseIdentifier: String,
                    currentQuota: Long,
                    estimatedSize: Long,
                    totalUsedQuota: Long,
                    quotaUpdater: QuotaUpdater
                ) {
                    quotaUpdater.updateQuota((5 * 1024 * 1024).toLong())
                }
            })
            */
        }

        return view
    }
}