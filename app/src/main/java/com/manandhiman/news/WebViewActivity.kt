package com.manandhiman.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url: String = intent.getStringExtra("url").toString()

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        webView.settings.setSupportZoom(true)

    }
}