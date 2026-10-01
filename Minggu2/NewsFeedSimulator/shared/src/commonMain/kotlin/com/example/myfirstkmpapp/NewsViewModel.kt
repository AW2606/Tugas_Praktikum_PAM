package com.example.myfirstkmpapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * [NewsViewModel.kt]
 * Kelas ViewModel sederhana yang mengatur:
 * 1. Flow Builder (cold stream) yang mensimulasikan data berita baru setiap 2 detik.
 * 2. Operator .filter untuk menyaring berita berdasarkan kategori.
 * 3. Operator .map untuk mentransformasi data berita menjadi format tampilan string.
 * 4. StateFlow untuk menyimpan dan memancarkan jumlah berita yang sudah dibaca.
 * 5. Coroutine suspend function untuk mengambil detail berita secara asynchronous.
 */
class NewsViewModel {

    // Scope coroutine untuk background tasks jika diperlukan
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    // 1. Flow Builder: Cold stream yang mensimulasikan data berita baru setiap 2 detik.
    // Menggunakan kotlin.time atau counter sederhana tanpa fungsi JVM-only agar aman di commonMain.
    private var newsCounter = 1
    val rawNewsFlow: Flow<NewsItem> = flow {
        val categories = listOf("Tech", "Sport", "Business", "Science")
        while (true) {
            delay(2000L) // Jeda 2 detik sesuai spesifikasi tugas praktikum
            val currentId = newsCounter++
            val category = categories[(currentId - 1) % categories.size]
            val news = NewsItem(
                id = currentId,
                title = "Berita Terbaru ke-$currentId tentang $category",
                category = category,
                timestamp = "T+${currentId * 2}s"
            )
            emit(news) // Memancarkan data berita baru ke dalam flow
        }
    }

    // 4. StateFlow: Menyimpan jumlah berita yang sudah dibaca secara reaktif.
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    // Fungsi untuk menambah jumlah berita yang dibaca
    fun incrementReadCount() {
        _readCount.value += 1
    }

    // Fungsi helper untuk mereset counter baca jika diperlukan
    fun resetReadCount() {
        _readCount.value = 0
    }

    /**
     * 2 & 3. Operator .filter dan .map
     * Menerima kategori pilihan pengguna, lalu menyaring (filter) dan mentransformasi (map)
     * rawNewsFlow menjadi Flow<String> yang siap ditampilkan di UI.
     */
    fun getFilteredAndMappedNewsFlow(selectedCategory: String): Flow<String> {
        return rawNewsFlow
            .filter { news ->
                // Operator .filter: Hanya lewatkan berita jika kategori cocok atau "All" dipilih
                selectedCategory == "All" || news.category == selectedCategory
            }
            .map { news ->
                // Operator .map: Transformasi data NewsItem menjadi string format tampilan
                "[${news.category.uppercase()}] ${news.title} (${news.timestamp})"
            }
    }

    /**
     * 5. Coroutines & Suspend Function:
     * Mengambil detail berita secara asynchronous menggunakan suspend function dan withContext.
     * Mensimulasikan network/database delay selama 1 detik.
     */
    suspend fun fetchNewsDetail(newsId: Int): NewsDetail {
        return withContext(Dispatchers.Default) {
            delay(1000L) // Simulasi proses async network/database
            NewsDetail(
                id = newsId,
                title = "Detail Lengkap Berita #$newsId",
                category = "Kategori Utama",
                content = "Ini adalah isi konten mendalam dari berita nomor $newsId yang diambil secara asynchronous menggunakan suspend function.",
                author = "Redaksi KMP Simulator"
            )
        }
    }
}
