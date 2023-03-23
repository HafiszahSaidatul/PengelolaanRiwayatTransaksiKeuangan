package unpas.ac.id.riwayattransaksi.persistances
import androidx.lifecycle.LiveData
import androidx.room.*
import unpas.ac.id.riwayattransaksi.model.RiwayatTransaksi

@Dao
interface RiwayatTransaksiDao {
    @Query("SELECT * FROM riwayattransaksi")
    fun loadAll(): LiveData<List<RiwayatTransaksi>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: RiwayatTransaksi)
}