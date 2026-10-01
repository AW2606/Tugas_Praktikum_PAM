package com.example.myfirstkmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * [App.kt]
 * Antarmuka Compose Multiplatform utama untuk "News Feed Simulator".
 * Mengintegrasikan:
 * 1. Flow & Operators (.filter, .map) yang dikumpulkan secara reaktif.
 * 2. StateFlow untuk menampilkan jumlah berita yang sudah dibaca.
 * 3. Coroutines (suspend function) untuk mengambil detail berita secara asynchronous saat item diklik.
 */
@Composable
fun App() {
    // Inisialisasi ViewModel / State Holder sederhana
    val viewModel = remember { NewsViewModel() }

    // State untuk kategori filter terpilih ("All", "Tech", "Sport", dll)
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Tech", "Sport", "Business", "Science")

    // State untuk daftar berita hasil transform (.map & .filter)
    val newsList = remember { mutableStateOf(listOf<String>()) }

    // StateFlow collection: Mengambil jumlah berita yang sudah dibaca
    val readCount by viewModel.readCount.collectAsState()

    // State untuk menyimpan detail berita yang sedang dipilih (untuk demo suspend function)
    var selectedNewsDetail by remember { mutableStateOf<NewsDetail?>(value = null) }
    var isLoadingDetail by remember { mutableStateOf(false) }

    // Coroutine scope untuk UI event handling (async detail fetch)
    val coroutineScope = rememberCoroutineScope()

    // Mengumpulkan Flow berita setiap kali selectedCategory berubah
    LaunchedEffect(selectedCategory) {
        newsList.value = emptyList() // Reset list saat kategori berubah
        viewModel.getFilteredAndMappedNewsFlow(selectedCategory).collectLatest { formattedNews ->
            // Menambahkan berita baru ke bagian atas daftar
            newsList.value = listOf(formattedNews) + newsList.value.take(15)
        }
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Informasi Mahasiswa & Judul Tugas
            Text(
                text = "News Feed Simulator (KMP)",
                fontSize = 22.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Adhitya Warman | NIM: 124140007",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 4. StateFlow Display: Jumlah berita yang sudah dibaca
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Berita Dibaca (StateFlow): $readCount",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(onClick = { viewModel.resetReadCount() }) {
                        Text("Reset")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 2. Filter Kategori (Operator .filter)
            Text(text = "Filter Kategori:", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                categories.forEach { category ->
                    Button(
                        onClick = { selectedCategory = category },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategory == category)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        ),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = category,
                            color = if (selectedCategory == category)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 1 & 3. Feed Berita Live Stream (Flow builder, .map, .filter)
            Text(
                text = "Live Stream Berita (Update tiap 2 detik):",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                    .padding(8.dp)
            ) {
                if (newsList.value.isEmpty()) {
                    Text(
                        text = "Menunggu simulasi berita masuk...",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(newsList.value) { itemText ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Increment StateFlow read count
                                        viewModel.incrementReadCount()
                                        // 5. Coroutines async fetch detail (suspend function)
                                        coroutineScope.launch {
                                            isLoadingDetail = true
                                            // Simulasi ambil ID dari item string (simplifikasi demo)
                                            val parsedId = itemText.hashCode() % 100 + 1
                                            selectedNewsDetail = viewModel.fetchNewsDetail(kotlin.math.abs(parsedId))
                                            isLoadingDetail = false
                                        }
                                    },
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Text(
                                    text = itemText,
                                    modifier = Modifier.padding(12.dp),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }

            // 5. Menampilkan Detail Berita hasil Async Suspend Function
            if (selectedNewsDetail != null || isLoadingDetail) {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        if (isLoadingDetail) {
                            Text(text = "Memuat detail berita secara asynchronous...", fontSize = 13.sp)
                        } else if (selectedNewsDetail != null) {
                            Text(text = "【 Detail Berita (Suspend Function) 】", style = MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = selectedNewsDetail!!.title, style = MaterialTheme.typography.bodyMedium)
                            Text(text = selectedNewsDetail!!.content, fontSize = 12.sp)
                            Text(text = "Penulis: ${selectedNewsDetail!!.author}", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
