# My Profile App

Aplikasi profil pribadi sederhana berbasis **Kotlin Multiplatform** dan **Compose Multiplatform** yang menampilkan halaman profil tunggal untuk target platform Android dan Desktop.

---

## 🧩 Komponen Composable

Aplikasi ini menggunakan 3 komponen `@Composable` utama yang bersifat reusable di file `ProfileComponents.kt`:

1. **`ProfileHeader(name: String, painter: Painter, ...)`**
   * Menampilkan foto profil berbentuk lingkaran (`CircleShape`) dan nama lengkap pengguna dengan tulisan tebal (`FontWeight.Bold`).
2. **`InfoItem(icon: ImageVector, label: String, value: String, ...)`**
   * Menampilkan satu baris informasi kontak/lokasi berupa ikon di sisi kiri, serta label berwarna abu-abu dan nilai teks di sisi kanan. Digunakan untuk menampilkan Email, Phone, dan Location.
3. **`ProfileCard(name: String, bio: String, email: String, phone: String, location: String, painter: Painter, ...)`**
   * Membungkus seluruh tampilan profil di dalam sebuah `Card` bersudut tumpul (`RoundedCornerShape(12.dp)`) dan bayangan (`elevation(4.dp)`). Di dalamnya berisi `ProfileHeader`, bio/deskripsi singkat, 3 `InfoItem`, dan tombol `Button("Follow")`.

---

## 📷 Screenshot

### Android
![Image Alt](https://github.com/AW2606/MyProfileApp/blob/9cd22ef2bcdac01fb5a3c495310f3f0d81d955f4/Dokumentasi.png)



---

## 🚀 Cara Menjalankan Aplikasi

### Android App
Jalankan perintah Gradle berikut melalui terminal di root project:
```bash
./gradlew :androidApp:assembleDebug
```
Atau gunakan tombol **Run** dengan konfigurasi `androidApp` di Android Studio.

### Desktop (JVM) App
Jalankan perintah Gradle berikut melalui terminal di root project:
```bash
./gradlew :shared:run
```
Atau gunakan run configuration untuk Desktop / JVM dari toolbar Android Studio.
