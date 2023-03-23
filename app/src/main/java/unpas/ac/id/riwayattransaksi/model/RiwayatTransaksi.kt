package unpas.ac.id.riwayattransaksi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RiwayatTransaksi(
    @PrimaryKey val id: String,
    val norekening: String,
    val jenistransaksi: String,
    val tnggaltransaksi: String,
    val nama: String
)
