package com.formasdis.ui.fragment.education

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.formasdis.R

class PdfReaderFragment(private val urlPdf: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_young, container, false)

        val webView: WebView = view.findViewById(R.id.webview)

        webView.webViewClient = WebViewClient()

        webView.settings.setSupportZoom(true)

        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$urlPdf")

        return view
    }
}