create database Mediklik;
use Mediklik;

create table User(
UserID integer not null,
UserName varchar(32) not null,
Password varchar(64) not null,
IsAdmin boolean not null,
Balance integer not null,
primary key (UserID)
);

create table Category(
CategoryID integer not null,
CategoryName varchar(32) not null,
primary key (CategoryID)
);

create table Item(
ItemID integer not null,
ItemName varchar(32) not null,
ItemPrice integer not null,
CategoryID integer,
ItemRating double,
ItemImage varchar(64),
ItemDescription Text,
primary key (ItemID),
foreign key (CategoryID) references Category(CategoryID)
);

create table Cart(
UserID integer not null,
ItemID integer not null,
CartQuantity integer not null,
foreign key (UserID) references User(UserID),
foreign key (ItemID) references Item(ItemID)
);

create table Store(
StoreID integer not null,
StoreName varchar(32) not null,
StoreRating double,
primary key (StoreID)
);

create table Inventory(
StoreID integer not null,
ItemID integer not null,
InventoryQuantity integer not null,
primary key (StoreID),
foreign key (StoreID) references Store(StoreID),
foreign key (ItemID) references Item(ItemID)
);

create table Transaction(
TransactionID integer not null,
UserID integer not null,
primary key (TransactionID),
foreign key (UserID) references User(UserID)
);

create table TransactionItem(
TransactionID integer not null,
ItemID integer not null,
TransactionQuantity integer not null,
foreign key(TransactionID) references Transaction(TransactionID),
foreign key(ItemID) references Item(ItemID)
);

#create table CurrentID(
#UserID integer,
#CategoryID integer,
#ItemID integer,
#StoreID integer,
#TransactionID integer
#);

#IDs
#insert into CurrentID values(1,-1,0,-1,-1);

#User account
insert into User values (0, "admin", "adminpassword", 1, 0);
insert into User values (1, "dummy", "password", 1, 1000000);

#Categories
insert into Category values (0, "Uncategorized");
insert into Category values (1, "Syrup");
insert into Category values (2, "Tablet");
insert into Category values (3, "Capsule");
insert into Category values (4, "Oles");
insert into Category values (5, "Others");

#Items
insert into Item values (0, "Paratusin", 39800, 1, 4.50, "paratusin.png", "Paratusin merupakan obat untuk meringankan gejala flu seperti demam, sakit kepala, hidung tersumbat dan bersin-bersin yang disertai batuk.");
insert into Item values (1, "Sanmol", 19600, 1, 4.50, "sanmol.png", "Sanmol Sirup 60 ml adalah obat yang digunakan untuk meredakan demam dan rasa nyeri atau sakit kepala pada anak-anak maupun dewasa.

Sanmol Sirup 60 ml mengandung zat aktif Paracetamol (Acetaminophen) yaitu zat aktif yang memiliki aktivitas sebagai penurun demam/antipiretik dan pereda nyeri/analgesik yang bekerja dengan cara menghambat pembentukan prostaglandin yaitu zat yang memicu nyeri dan demam di hipotalamus untuk meningkatkan pelepasan panas supaya suhu tubuh normal");
insert into Item values (2, "OBH Combi", 21400, 1, 4.50, "OBH combi.png", "OBH Combi Dewasa merupakan obat untuk mengatasi gejala flu seperti pilek, hidung tersumbat, demam, sakit kepala, bersin disertai oleh batuk.");
insert into Item values (3, "Vicks", 19400, 1, 4.50, "vicks.png", "Vicks Formula 44 merupakan obat yang digunakan untuk mengatasi batuk tidak berdahak dan meringankan pilek.");
insert into Item values (4, "Woods", 20100, 1, 4.50, "woods.png", "Woods Antitussive merupakan obat yang digunakan untuk meringankan rasa batuk tidak berdahak yang disebabkan oleh alergi pada saluran pernapasan bagian atas.");

insert into Item values (5, "Incidal", 5200, 2, 4.50, "incidal.png", "INCIDAL OD 10MG digunakan pada kondisi alergi seperti rinitis perenial, rinitis alergi dan urtikaria idiopatik kronik. Obat ini memiliki kandungan bahan aktif Cetirizine HCl.");
insert into Item values (6, "Panadol", 12500, 2, 4.50, "panadol.png", "Deskripsi: PANADOL BIRU TABLET 1 STRIP memiliki kandungan bahan aktif Paracetamol. Paracetamol bekerja dengan cara mengurangi kadar prostaglandin di dalam tubuh, sehingga tanda peradangan seperti demam dan nyeri akan berkurang. Obat ini digunakan untuk meringankan rasa sakit seperti sakit kepala, sakit gigi serta menurunkan demam.

Paracetamol dapat dikonsumsi sesudah makan. Konsultasikan terlebih dahulu kepada dokter apabila akan digunakan pada pasien dengan kondisi:
• Riwayat alergi terhadap kandungan obat ini.
• Pasien dengan penyakit gangguan hati berat.
• Pasien yang mengkonsumsi alkohol dapat meningkatkan potensi kerusakan hati.
• Pasien dengan penyakit gangguan ginjal.

Interaksi dapat terjadi apabila digunakan bersamaan dengan obat-obatan seperti: 
• Metoclopramide dapat meningkatkan efek analgetic paracetamol.
• Paracetamol meningkatkan efek obat warfarin.
• Carbamazepine meningkatkan potensi kerusakan hati.");
insert into Item values (7, "Troches", 13800, 2, 4.50, "troches.png", "SP TROCHES MEIJI STRAW LOZ TAB 6S merupakan antiseptik mulut yang dapat digunakan untuk faringitis, tonsilitis, stomatitis serta pencegahan infeksi pada rongga mulut termasuk setelah ekstraksi gigi.  Mengandung Deqiline Cl yang dapat digunakan dengan cara dihisap.");
insert into Item values (8, "Mylanta", 9082, 2, 4.50, "mylanta.png", "Mylanta merupakan obat yang digunakan untuk mengatasi penyakit-penyakit yang disebabkan oleh kelebihan produksi asam lambung, seperti sakit maag dan tukak lambung. 

Dalam 1 tab Mylanta mengandung : 
1. Magnesium Hidroksida berperan sebagai antasida untuk mengatasi tukak lambung dengan meningkatkan pH asam lambung melalui reaksi netralisasi. Senyawa ini juga memberikan perlindungan tambahan dari asam lambung dengan melapisi mukosa lambung menggunakan endapan koloid silika. 
2. Aluminium Hidroksida (Al (OH)3) adalah zat yang bekerja sebagai antasida dengan bereaksi dengan asam berlebih di dalam lambung sehingga mengurangu gejala ulkus, mulas atau dyspepsia (maag). 
3. Simetikon berfungsi dalam mengurangi kembung, ketidaknyamanan atau rasa sakit yang disebabkan oleh gas yang berlebihan dalam perut atau usus. Obat ini bekerja dengan menurunkan tegangan permukaan gelembung gas sehingga lebih mudah untuk dikeluarkan.");
insert into Item values (9, "Bodrex Migraine", 2600, 2, 4.50, "bodrex.png", "Membantu meringankan rasa sakit kepala pada migrain.");

insert into Item values (10, "Diapet", 5800, 3, 4.50, "diapet.png", "DIAPET CAP 10S merupakan obat herbal yang digunakan untuk mengatasi mencret atau diare dengan cara memadatkan feses yang cair dan mengurangi rasa mulas. Diapet Cap termasuk golongan obat herbal yang dapat dibeli secara bebas tanpa resep dokter dengan kandungan: 1.Psidium guajava folium (daun jambu biji) 2.curcumae domesticae rhizoma (rimpang kunyit) 3.terminalia chebulae fructus (buah mojokeling) 4.punicae granati pericarpium (kulit buah delima) Diapet Cap dapat dikonsumsi sesuai petunjuk penggunaan yang tertera pada kemasan obat, berkonsultasi dengan dokter terlebih dahulu atau dewasa dan anak -anak 2 x 2 kapsul/hari. Perbanyak konsumsi air putih untuk mencegah dehidrasi.");
insert into Item values (11, "Obeslim", 115300, 3, 4.50, "obeslim.png", "OBESLIM CAP 30S memiliki kandungan bahan aktif yaitu Orlistat. Orlistat merupakan obat yang digunakan untuk membantu menurunkan berat badan. Obat ini bekerja dengan cara menghambat enzim yang mencerna lemak, sehingga lemak tidak diserap ke dalam tubuh.

Obeslim dapat dikonsumsi bersama makanan. Minum segera sebelum atau selama atau hingga 1 jam setelah makan utama. Konsultasikan terlebih dahulu kepada dokter apabila akan digunakan pada pasien dengan riwayat atau kondisi sakit seperti:
• Riwayat alergi terhadap kandungan obat ini.
• Pasien dengan diabetes.
• Penyakit tiroid.
• Epilepsi.
• Gangguan hati dan ginjal.");
insert into Item values (12, "Xenical", 165000, 3, 4.50, "xenical.png", "XENICAL 120 MG merupakan obat yang mengandung orlistat. Orlistat digunakan bersama makanan rendah kalori, sebagai terapi tambahan pada pasien obesitas dengan Index Massa Tubuh (Body Mass Index/BMI) 30 kg/m2 dan diatasnya atau pasien yang berkelebihan berat badan dengan BMI 27 kg/m2 dan diatasnya yang memiliki faktor risiko terkait. Orlistat bekerja dengan menghambat enzim lipase lambung dan pankreas. Hidrolisis trigliserida terhambat dan menyebabkan penurunan penyerapan lemak dari makanan. Dalam penggunaan obat ini HARUS SESUAI DENGAN PETUNJUK DOKTER.");
insert into Item values (13, "Sangobion", 18500, 3, 4.50, "sangobion.png", "SANGOBION adalah vitamin dan zat besi penambah darah dengan kandungan Ferrous gluconate, manganese sulfate, copper Sulfate, vitamin C, folic acid, vitamin B12. Kandungan pada produk ini membantu proses pembentukan hemoglobin ditubuh sehingga dapat membantu mengatasi anemia saat menstruasi, hamil, menyusui, masa pertumbuhan, dan setelah mengalami pendarahan. Anemia karena kekurangan zat besi dan mineral lain yang membantu pembentukan darah.");
insert into Item values (14, "Lian Hua", 34656, 3, 4.50, "lian hua.png", " LIANHUA QINGWEN CAP 12S STRIP 2S merupakan obat herbal China yang memiliki manfaat seperti: 
• Membantu meringankan gejala influenza 
• Membantu menurunkan demam 
• Membantu meredakan batuk dan tenggorokan kering. 
Produk ini dapat dikonsumsi setelah makan. Konsultasikan terlebih dahulu kepada dokter apabila akan digunakan pada pasien dengan riwayat alergi terhadap kandungan obat ini.");

insert into Item values (15, "Desolex", 34500, 4, 4.50, "desolex.png", "Mengatasi Dermatitis atopik & dermatitis kontak, berbagai macam eksim, terutama pada anak-anak, gatal-gatal, eritema, terbakar sinar matahari & dermatitis tipe lain.");
insert into Item values (16, "Counterpain", 88100, 4, 4.50, "counterpain.png", "Counterpain cool mengandung methyl salisilat, mentol, eugenol, yang diproduksi oleh PT Taisho Pharmaceutical dengan nomor registrasi BPOM QD111709501. Counterpain cool merupakan krim yang digunakan untuk meringankan nyeri otot dan sendi, kesleo, encok, dan pegal linu. Kandungan mentol pada Counterpain cool memberikan sensasi dingin saat digunakan. Setelah penggunaan, Counterpain cool tidak meninggalkan bekas pada kulit.");
insert into Item values (17, "Medi-Klin TR", 58600, 4, 4.50, "medi-klin.png", "Mediklin TR merupakan preparat akne berbentuk gel yang diaplikasikan pada kulit dengan akne vulgaris yang disertai inflamasi dan komedo.");
insert into Item values (18, "Geliga", 5700, 4, 4.50, "geliga.png", "Balsem geliga dapat membantu meringankan nyeri dan keseleo, termasuk nyeri punggung, nyeri otot, dan memar. Dengan rasa hangat yang pas dan tidak lengket.");
insert into Item values (19, "Caladine", 125000, 4, 4.50, "caladine.png", "CALADINE LOTION merupakan lotion anti gatal yang mengandung Calamine, Zinc Oxyde dan Diphenhydramine HCL. Caladine lotion digunakan untuk mengobati gatal karena biang keringet, udara panas, gigitan serangga. Selain itu dapat digunakan sebagai antialergi, antideptik dan penyejuk kulit.");

insert into Item values (20, "Herocyn", 14400, 5, 4.50, "herocyn.png", "HEROCYN BEDAK KULIT 85 G merupakan bedak yang digunakan untuk membantu meredakan biang keringat dan gatal-gatal pada kulit.");
insert into Item values (21, "Salonpas", 8700, 5, 4.50, "salonpas.png", "Salonpas koyo hijau merupakan plester yang mengandung methyl salicilate, menthol, camphor. Kandungan bahan aktif dalam Salonpas koyo hijau dapat digunakan untuk membantu meredakan rasa nyeri yang disebabkan oleh nyeri otot, nyeri sendi, terkilir, punggung pegal. Salonpas koyo hijau jangan digunakan bersama dengan kompres panas, dan pada saat yang sama menggunakan obat luar analgesik lainnya. Hentikan pemakaian apabila kondisi memburuk, dan gejela tidak membaik setelah 7 hari pemakaian, timbul kemerahan, gatal atau iritasi kulit.");
insert into Item values (22, "Kapas", 480000, 5, 4.50, "kapas.png", "ADINDA KAPAS PEMBALUT merupakan Kapas kesehatan digunakan untuk membalut luka.");
insert into Item values (23, "Hansaplast", 7600, 5, 4.50, "hansaplast.png", "HANSAPLAST KAIN ELASTIS merupakan alat kesehatan yang mengandung silvercare, digunakan untuk menutup luka dan memberi bantalan bagi luka.
Indikasi Umum");
insert into Item values (24, "Betadine", 6900, 5, 4.50, "betadine.png", "Betadine sol  merupakan larutan untuk membersihkan dan mengobati luka, juga sebagai desinfektan pada luka terbuka.");

insert into Cart values (1, 0, 3);
insert into Cart values (1, 1, 4);
insert into Cart values (1, 2, 5);