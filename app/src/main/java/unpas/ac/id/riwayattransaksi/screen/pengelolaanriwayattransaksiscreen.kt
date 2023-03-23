package unpas.ac.id.riwayattransaksi.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import unpas.ac.id.riwayattransaksi.model.RiwayatTransaksi
import unpas.ac.id.riwayattransaksi.persistances.AppDatabase

@Composable
fun pengelolaanriwayattransaksiscreen () {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "riwayat-transaksi"
    ).build()
    val riwayatTransaksiDao = db.riwyatTransaksiDao()

    val list : LiveData<List<RiwayatTransaksi>> = riwayatTransaksiDao.loadAll()
    val items: List<RiwayatTransaksi> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        Formriwayattransaksi(riwayatTransaksiDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "No Rekening", fontSize = 14.sp)
                        Text(
                            text = item.norekening, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jenis Transaksi", fontSize = 14.sp)
                        Text(
                            text = item.jenistransaksi, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal transaksi", fontSize = 14.sp)
                        Text(
                            text = item.tnggaltransaksi, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}