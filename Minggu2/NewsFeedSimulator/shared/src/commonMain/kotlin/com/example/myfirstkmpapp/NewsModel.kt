package com.example.myfirstkmpapp

/**
 * [NewsModel.kt]
 * Mendefinisikan struktur data (Model) untuk berita dan detail berita.
 * Digunakan dalam aliran Flow dan state management aplikasi News Feed Simulator.
 */

data class NewsItem(
    val id: Int,
    val title: String,
    val category: String, // Contoh kategori: "Tech", "Sport", "Business"
    val timestamp: String
)

data class NewsDetail(
    val id: Int,
    val title: String,
    val category: String,
    val content: String,
    val author: String
)
