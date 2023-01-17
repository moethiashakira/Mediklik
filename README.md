# Mediklik
![Tampilan Awal](doc/img/mediklik_1.jpg)
## Tentang Mediklik
Mediklik adalah aplikasi e-commerce open-source untuk segala kebutuhan obat Anda. 

### Fitur
* Penyaringan berdasarkan kategori
* User inteface yang sederhana
* Cross-platform

## Penggunaan
Download semua file dalam repository ini menggunakan `git`:
```
git clone https://github.com/raphael-lesmana/Mediklik.git
```

Lalu, install [JavaFX](https://openjfx.io/) dan [MariaDB](https://mariadb.org/) beserta [connectornya](https://mariadb.com/downloads/connectors/) (pastikan Anda memilih versi "Java").

Jalankan file `run.sql`:
```
mysql -u root -p < run.sql
```

Tambahkan library JavaFX dan MariaDB ke build path projek Java ini melalui IDE Java Anda, lalu Mediklik bisa dijalankan.
