package com.example.myprofileapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import myprofileapp.shared.generated.resources.Foto
import org.jetbrains.compose.resources.painterResource

import myprofileapp.shared.generated.resources.Res
import myprofileapp.shared.generated.resources.compose_multiplatform

/**
 * Titik masuk utama UI aplikasi "My Profile App".
 * Memanggil [ProfileCard] dengan data profil mahasiswa di tengah layar.
 */
@Composable
@Preview
fun App() {
    MaterialTheme {
        // Box pembungkus utama untuk menempatkan kartu profil tepat di tengah layar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Memanggil ProfileCard dengan data profil lengkap
            ProfileCard(
                name = "Adhitya Warman",
                bio = "Mahasiswa Teknik Informatika yang sedang berusaha belajar dengan semangat terkait Pengembangan Aplikasi Mobile, semoga kuat lah ya🥲 .",
                email = "adhitya.124140007@student.itera.ac.id",
                phone = "+6281234567890",
                location = "Way Huwi, Lampung Selatan, Lampung, Indonesia",
                painter = painterResource(Res.drawable.Foto),
                onFollowClick = {
                    // Callback saat tombol Follow diklik
                }
            )
        }
    }
}
