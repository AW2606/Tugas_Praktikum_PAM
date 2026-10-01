package com.example.myprofileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Objek penampung ikon Material dasar agar tidak memerlukan dependency tambahan.
 */
object Icons {
    object Default {
        // Ikon Surat / Email
        val Email: ImageVector = ImageVector.Builder(
            name = "Default.Email",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).path(fill = SolidColor(Color.Black)) {
            moveTo(20f, 4f)
            lineTo(4f, 4f)
            curveTo(2.9f, 4f, 2.01f, 4.9f, 2.01f, 6f)
            lineTo(2f, 18f)
            curveTo(2f, 19.1f, 2.9f, 20f, 4f, 20f)
            lineTo(20f, 20f)
            curveTo(21.1f, 20f, 22f, 19.1f, 22f, 18f)
            lineTo(22f, 6f)
            curveTo(22f, 4.9f, 21.1f, 4f, 20f, 4f)
            close()
            moveTo(20f, 8f)
            lineTo(12f, 13f)
            lineTo(4f, 8f)
            lineTo(4f, 6f)
            lineTo(12f, 11f)
            lineTo(20f, 6f)
            lineTo(20f, 8f)
            close()
        }.build()

        // Ikon Telepon / Phone
        val Phone: ImageVector = ImageVector.Builder(
            name = "Default.Phone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).path(fill = SolidColor(Color.Black)) {
            moveTo(6.62f, 10.79f)
            curveTo(8.06f, 13.62f, 10.38f, 15.94f, 13.21f, 17.38f)
            lineTo(15.41f, 15.18f)
            curveTo(15.69f, 14.9f, 16.08f, 14.82f, 16.43f, 14.93f)
            curveTo(17.55f, 15.3f, 18.75f, 15.5f, 20f, 15.5f)
            curveTo(20.55f, 15.5f, 21f, 15.95f, 21f, 16.5f)
            lineTo(21f, 20f)
            curveTo(21f, 20.55f, 20.55f, 21f, 20f, 21f)
            curveTo(10.61f, 21f, 3f, 13.39f, 3f, 4f)
            curveTo(3f, 3.45f, 3.45f, 3f, 4f, 3f)
            lineTo(7.5f, 3f)
            curveTo(8.05f, 3f, 8.5f, 3.45f, 8.5f, 4f)
            curveTo(8.5f, 5.25f, 8.7f, 6.45f, 9.07f, 7.57f)
            curveTo(9.18f, 7.92f, 9.1f, 8.31f, 8.82f, 8.59f)
            lineTo(6.62f, 10.79f)
            close()
        }.build()

        // Ikon Lokasi / LocationOn
        val LocationOn: ImageVector = ImageVector.Builder(
            name = "Default.LocationOn",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 2f)
            curveTo(8.13f, 2f, 5f, 5.13f, 5f, 9f)
            curveTo(5f, 14.25f, 12f, 22f, 12f, 22f)
            curveTo(12f, 22f, 19f, 14.25f, 19f, 9f)
            curveTo(19f, 5.13f, 15.87f, 2f, 12f, 2f)
            close()
            moveTo(12f, 11.5f)
            curveTo(10.62f, 11.5f, 9.5f, 10.38f, 9.5f, 9f)
            curveTo(9.5f, 7.62f, 10.62f, 6.5f, 12f, 6.5f)
            curveTo(13.38f, 6.5f, 14.5f, 7.62f, 14.5f, 9f)
            curveTo(14.5f, 10.38f, 13.38f, 11.5f, 12f, 11.5f)
            close()
        }.build()
    }
}

/**
 * Composable untuk menampilkan bagian header profil yang berisi foto berbentuk lingkaran dan nama.
 *
 * @param name Nama lengkap yang akan ditampilkan di bawah foto.
 * @param painter Resource gambar/foto yang akan ditampilkan.
 * @param modifier Modifier opsional untuk penyesuaian layout.
 */
@Composable
fun ProfileHeader(
    name: String,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    // Layout vertikal untuk menyusun foto dan nama secara berurutan ke bawah
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Foto profil berbentuk lingkaran dengan ContentScale.Crop agar gambar terisi penuh
        Image(
            painter = painter,
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Nama pengguna dengan tulisan tebal (Bold)
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Composable untuk menampilkan satu baris informasi kontak/lokasi berisi ikon di kiri
 * serta label dan nilainya di kanan.
 *
 * @param icon Ikon Material Vector yang ditampilkan di sebelah kiri.
 * @param label Teks label informasi (misal: "Email", "Phone", "Location").
 * @param value Teks nilai informasi (misal: isi email, nomor HP, atau nama kota).
 * @param modifier Modifier opsional untuk penyesuaian layout.
 */
@Composable
fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    // Layout horisontal menyusun ikon di kiri dan teks di kanan
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Ikon indikator di sebelah kiri
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.size(12.dp))

        // Layout vertikal untuk label di atas dan nilai di bawahnya
        Column {
            // Label informasi berwarna abu-abu
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray,
            )
            // Nilai informasi teks biasa
            Text(
                text = value,
                fontSize = 14.sp,
            )
        }
    }
}

/**
 * Composable utama untuk membungkus seluruh komponen profil ke dalam sebuah Card.
 *
 * @param name Nama lengkap pengguna.
 * @param bio Deskripsi singkat pengguna.
 * @param email Alamat email pengguna.
 * @param phone Nomor telepon pengguna.
 * @param location Lokasi pengguna.
 * @param painter Resource gambar profil.
 * @param onFollowClick Callback yang dipanggil saat tombol Follow diklik.
 * @param modifier Modifier opsional untuk penyesuaian layout.
 */
@Composable
fun ProfileCard(
    name: String,
    bio: String,
    email: String,
    phone: String,
    location: String,
    painter: Painter,
    onFollowClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    // Card pembungkus profil dengan elevation 4.dp dan sudut membulat 12.dp
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        // Column menyusun seluruh elemen di dalam Card secara berurutan
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // (1) Header Profil
            ProfileHeader(
                name = name,
                painter = painter,
            )

            // (2) Teks Bio / deskripsi singkat
            Text(
                text = bio,
                fontSize = 14.sp,
                color = Color.Gray,
            )

            // (3) InfoItem Email
            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = email,
            )

            // (4) InfoItem Phone
            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = phone,
            )

            // (5) InfoItem Location
            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = location,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // (6) Tombol Follow
            Button(
                onClick = onFollowClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Follow")
            }
        }
    }
}
