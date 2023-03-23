package unpas.ac.id.riwayattransaksi.persistances

import androidx.room.Database
import androidx.room.RoomDatabase
import unpas.ac.id.riwayattransaksi.model.RiwayatTransaksi

@Database(entities = [RiwayatTransaksi::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun riwyatTransaksiDao(): RiwayatTransaksiDao
}